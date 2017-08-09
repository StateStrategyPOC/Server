package server;

import common.ActionOnTheWire;
import common.PlayerToken;
import common.RRClientNotification;
import server_store.ServerStore;
import server_store.StoreAction;
import server_store_actions.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * Manages a connection with the client in a request response fashion.
 */
public class ReqRespHandler extends Thread implements Observer {

    private final Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ServerStore SERVER_STORE;

    public ReqRespHandler(Socket socket) {
        this.SERVER_STORE = ServerStore.getInstance();
        this.socket = socket;
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.SERVER_STORE.observeState(this);
    }
    @Override
    public void run() {
        try {
            this.resolveClientRequest(this.getRequest(this.objectInputStream));
        } catch ( IOException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                this.objectOutputStream.close();
                this.objectInputStream.close();
                this.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    private ActionOnTheWire getRequest(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        ActionOnTheWire request = (ActionOnTheWire) inputStream.readObject();
        return request;
    }

    private void sendRequest(RRClientNotification response, ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(response);
        outputStream.flush();
    }
    private void closeConnection(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream) throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }
    @Override
    public void update(Observable o, Object arg) {
        StoreAction propagatedAction = (StoreAction) arg;
        switch(propagatedAction.getActionIdentifier()){
            case "@SERVER_SET_RESPONSE":
                this.setResponse(propagatedAction);
                break;
            case "@SERVER_TRANSFORM_CHANNEL":
                this.transformChannel(propagatedAction);
                break;
        }
    }

    private void resolveClientRequest(ActionOnTheWire request){
        if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.getGames())){
            this.SERVER_STORE.propagateAction(new GamesGetGamesAction());
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.joinGame())){
            Integer gameId = (Integer) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(gameId,playerName));
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.joinNewGame())){
            String gameMapName = (String) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            Game game = new Game(gameMapName);
            this.SERVER_STORE.propagateAction(new GamesAddGameAction(game));
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(game.getGamePublicData().getId(),playerName));
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.makeAction())){
            StoreAction action = (StoreAction) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameMakeActionAction(playerToken,action));
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.onDemandGameStart())){
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            this.SERVER_STORE.propagateAction(new GameOnDemandStartAction(playerToken));
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.publishChatMsg())){
            String message = (String) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GamePostMsgAction(message,playerToken));
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.subscribe())){
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            this.SERVER_STORE.propagateAction(new GameSubscribeAction(playerToken));
        }
    }

    private void transformChannel(StoreAction propagatedAction) {
        ServerTransformChannelAction castedAction = (ServerTransformChannelAction) propagatedAction;
        this.SERVER_STORE.propagateAction(new GameSetPSHandlersAction(castedAction.getGame(),castedAction.getPlayerToken(),this.socket,this.objectOutputStream));
    }

    private void setResponse(StoreAction propagatedAction){
        ServerSetResponseAction castedAction = (ServerSetResponseAction) propagatedAction;
        try {
            this.sendRequest(castedAction.getResponse(),this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream,this.objectInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

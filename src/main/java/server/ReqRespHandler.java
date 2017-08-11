package server;

import common.ActionOnTheWire;
import common.GamePublicData;
import common.PlayerToken;
import common.RRClientNotification;
import server_store.ServerStore;
import common.StoreAction;
import server_store_actions.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * Manages a connection with the client in a request response fashion.
 */
public class ReqRespHandler extends Thread {

    private final Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ServerStore SERVER_STORE;
    private final UUID handlerId;

    public ReqRespHandler(Socket socket) {
        this.handlerId = UUID.randomUUID();
        this.SERVER_STORE = ServerStore.getInstance();
        this.socket = socket;
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ActionOnTheWire action = (ActionOnTheWire) inputStream.readObject();
        return action;
    }

    private void sendRequest(RRClientNotification response, ObjectOutputStream outputStream)  {
        try {
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void closeConnection(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void resolveClientRequest(ActionOnTheWire request){
        if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.getGames())){
            ArrayList<GamePublicData> gamesList = new ArrayList<>();
            for (Game game : this.SERVER_STORE.getState().getGames()) {
                gamesList.add(game.getGamePublicData());
            }
            this.sendRequest(new RRClientNotification(true,null,null,gamesList,null,null),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.joinGame())){
            Integer gameId = (Integer) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(gameId,playerName));
            Game game = Helpers.findGameById(gameId,this.SERVER_STORE.getState().getGames());
            if (game == null){
                this.sendRequest(new RRClientNotification(false),this.objectOutputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);

        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.joinNewGame())){
            String gameMapName = (String) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            Game game = new Game(gameMapName);
            this.SERVER_STORE.propagateAction(new GamesAddGameAction(game));
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(game.getGamePublicData().getId(),playerName));
            this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);

        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.makeAction())){
            StoreAction action = (StoreAction) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameMakeActionAction(playerToken,action));
            Game game = Helpers.findGameById(playerToken.getGameId(),this.SERVER_STORE.getState().getGames());
            if (game == null){
                this.sendRequest(new RRClientNotification(false),this.objectOutputStream);
                this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.onDemandGameStart())){
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            this.SERVER_STORE.propagateAction(new GameOnDemandStartAction(playerToken));
            Game game = Helpers.findGameById(playerToken.getGameId(),this.SERVER_STORE.getState().getGames());
            if (game == null){
                this.sendRequest(new RRClientNotification(false),this.objectOutputStream);
                this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
                return;
            }
            //this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.publishChatMsg())){
            String message = (String) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GamePostMsgAction(message,playerToken));
            Game game = Helpers.findGameById(playerToken.getGameId(),this.SERVER_STORE.getState().getGames());
            if (game == null){
                this.sendRequest(new RRClientNotification(false),this.objectOutputStream);
                this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket,this.objectOutputStream,this.objectInputStream);
        }
        else if (request.getIdentifierMapper().equals(ServerMethodsNameProvider.subscribe())){
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            Game game = Helpers.findGameById(playerToken.getGameId(),this.SERVER_STORE.getState().getGames());
            if (game == null){
                this.sendRequest(new RRClientNotification(false),this.objectOutputStream);
                return;
            }
            PubSubHandler handler = new PubSubHandler(socket,objectOutputStream,playerToken);
            game.getPubSubHandlers().add(handler);
            ConnectionListener.getInstance().registerPubSubHandler(handler);
            if (game.getPlayers().size() == 8) {
                SERVER_STORE.propagateAction(new GameStartGameAction(game));
            } else if (game.getPlayers().size() == 2) {
                SERVER_STORE.propagateAction(new GameStartableGameAction(game, true));
            }
        }
    }
}

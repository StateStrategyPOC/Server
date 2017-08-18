package server;

import common.ActionOnTheWire;
import common.GamePublicData;
import common.PlayerToken;
import common.RRNotification;
import server_store.ServerStore;
import common.StoreAction;
import server_store_actions.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Manages a connection with the client in a request response fashion.
 */
public class ReqRespHandler extends Thread {

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
    }

    @Override
    public void run() {
        try {
            this.resolveClientRequest(this.getRequest(this.objectInputStream));
        } catch (IOException | ClassNotFoundException e) {
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

    /**
     * Listens for an object on the connection associated with this class
     *
     * @param inputStream The inbound stream of messages to listen on
     * @return A received message of the type {@link common.ActionOnTheWire}
     * @throws IOException Network problem
     * @throws ClassNotFoundException If the received message is not of the type {@link common.ActionOnTheWire}
     */
    private ActionOnTheWire getRequest(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return (ActionOnTheWire) inputStream.readObject();
    }

    /**
     * Sends a response over the connection associated with this class
     * @param response The response of the type {@link common.RRNotification} to be sent
     * @param outputStream The outbound message stream to be used to send the response
     * @throws IOException Network problem
     */
    private void sendRequest(RRNotification response, ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(response);
        outputStream.flush();

    }

    /**
     * Closes the connection associated with this class
     * @param socket The socket that represents the connection
     * @param outputStream The outbound message stream associated with the connection
     * @param inputStream The inbound message stream associated with the connection
     * @throws IOException Network problem
     */
    private void closeConnection(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream) throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();


    }

    /**
     * Makes the behaviour encoded in a received {@link common.ActionOnTheWire} be executed by the rest of the application
     * via Action propagation
     * @param request Incoming message whose encoded behaviour must be executed by the rest of the application
     * @throws IOException Network problem
     */
    private void resolveClientRequest(ActionOnTheWire request) throws IOException {
        if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.getGames())) {
            ArrayList<GamePublicData> gamesList = new ArrayList<>();
            for (Game game : this.SERVER_STORE.getState().getGames()) {
                gamesList.add(game.getGamePublicData());
            }
            this.sendRequest(new RRNotification(true, null, null, null, gamesList, null, null), this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.joinGame())) {
            Integer gameId = (Integer) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(gameId, playerName));
            Game game = Helpers.findGameById(gameId, this.SERVER_STORE.getState().getGames());
            if (game == null) {
                this.sendRequest(new RRNotification(false, "the action cannot be performed", null, null, null, null, null), this.objectOutputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(), this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);

        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.joinNewGame())) {
            String gameMapName = (String) request.getParameters().get(0);
            String playerName = (String) request.getParameters().get(1);
            Game game = new Game(gameMapName);
            this.SERVER_STORE.propagateAction(new GamesAddGameAction(game));
            this.SERVER_STORE.propagateAction(new GameJoinGameAction(game.getGamePublicData().getId(), playerName));
            this.sendRequest(game.getLastRRclientNotification(), this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);

        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.makeAction())) {
            StoreAction action = (StoreAction) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GameMakeActionAction(playerToken, action));
            Game game = Helpers.findGameById(playerToken.getGameId(), this.SERVER_STORE.getState().getGames());
            if (game == null) {
                this.sendRequest(new RRNotification(false, "the action cannot be performed", null, null, null, null, null), this.objectOutputStream);
                this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(), this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.onDemandGameStart())) {
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            this.SERVER_STORE.propagateAction(new GameOnDemandStartAction(playerToken));
            Game game = Helpers.findGameById(playerToken.getGameId(), this.SERVER_STORE.getState().getGames());
            if (game == null) {
                this.sendRequest(new RRNotification(false, "the action cannot be performed", null, null, null, null, null), this.objectOutputStream);
                this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
                return;
            }
            //this.sendRequest(game.getLastRRclientNotification(),this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.publishChatMsg())) {
            String message = (String) request.getParameters().get(0);
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(1);
            this.SERVER_STORE.propagateAction(new GamePostMsgAction(message, playerToken));
            Game game = Helpers.findGameById(playerToken.getGameId(), this.SERVER_STORE.getState().getGames());
            if (game == null) {
                this.sendRequest(new RRNotification(false, "the action cannot be performed", null, null, null, null, null), this.objectOutputStream);
                this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
                return;
            }
            this.sendRequest(game.getLastRRclientNotification(), this.objectOutputStream);
            this.closeConnection(this.socket, this.objectOutputStream, this.objectInputStream);
        } else if (request.getActionIdentifier().equals(EncodedBehaviourIdentifiers.subscribe())) {
            PlayerToken playerToken = (PlayerToken) request.getParameters().get(0);
            Game game = Helpers.findGameById(playerToken.getGameId(), this.SERVER_STORE.getState().getGames());
            if (game == null) {
                this.sendRequest(new RRNotification(false, "the action cannot be performed", null, null, null, null, null), this.objectOutputStream);
                return;
            }
            PubSubHandler handler = new PubSubHandler(socket, objectOutputStream, playerToken);
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

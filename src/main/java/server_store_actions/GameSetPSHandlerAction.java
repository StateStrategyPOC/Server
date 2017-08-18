package server_store_actions;

import common.PlayerToken;
import server.Game;
import common.StoreAction;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * An Action for signalling that a {@link server.PubSubHandler} should be initialized to take care of async communications
 * with a Player
 */
public class GameSetPSHandlerAction extends StoreAction {

    //The game of the Player the handler will refer to
    private final Game game;
    //Socket and streams to be used to persist an existing connection and use it for async communication
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    //The token of the player the handler will refer to
    private final PlayerToken playerToken;

    public GameSetPSHandlerAction(Game game, PlayerToken playerToken, Socket socket, ObjectOutputStream outputStream) {
        super("@SERVER_GAME_SET_PS","@SERVER_GROUP");
        this.game = game;
        this.playerToken = playerToken;
        this.socket = socket;
        this.outputStream = outputStream;
    }

    public Game getGame() {
        return game;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String toString() {
        return "GameSetPSHandlerAction{" +
                "game=" + game +
                ", socket=" + socket +
                ", outputStream=" + outputStream +
                ", playerToken=" + playerToken +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

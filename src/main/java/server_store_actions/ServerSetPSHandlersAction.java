package server_store_actions;

import common.PlayerToken;
import server.Game;
import server_store.StoreAction;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSetPSHandlersAction extends StoreAction {

    private final Game game;
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final PlayerToken playerToken;

    public ServerSetPSHandlersAction(Game game, PlayerToken playerToken, Socket socket, ObjectOutputStream outputStream) {
        super("@SERVER_SET_PS","@SERVER_GROUP");
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
}

package server_store_actions;

import common.PlayerToken;
import server.Game;
import server_store.StoreAction;

public class ServerTransformChannelAction extends StoreAction {

    private final Game game;
    private final PlayerToken playerToken;

    public ServerTransformChannelAction(Game game, PlayerToken playerToken) {
        super("@SERVER_TRANSFORM_CHANNEL", "@SERVER_GROUP");
        this.game = game;
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public Game getGame() {
        return game;
    }
}

package server_store_actions;

import common.PlayerToken;
import server.Game;
import common.StoreAction;

import java.util.UUID;

public class ServerTransformChannelAction extends StoreAction {

    private final Game game;
    private final PlayerToken playerToken;
    private final UUID handlerId;

    public ServerTransformChannelAction(Game game, PlayerToken playerToken, UUID handlerId) {
        super("@SERVER_TRANSFORM_CHANNEL", "@SERVER_GROUP");
        this.game = game;
        this.playerToken = playerToken;
        this.handlerId = handlerId;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public Game getGame() {
        return game;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

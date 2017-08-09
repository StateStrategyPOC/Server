package server_store_actions;


import common.PlayerToken;
import server_store.StoreAction;

import java.util.UUID;

public class GameOnDemandStartAction extends StoreAction {

    private final PlayerToken playerToken;
    private final UUID handlerId;

    public GameOnDemandStartAction(PlayerToken playerToken, UUID handlerId) {
        super("@SERVER_GAME_ON_DEMAND_START", "@SERVER_GROUP");
        this.playerToken = playerToken;
        this.handlerId = handlerId;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

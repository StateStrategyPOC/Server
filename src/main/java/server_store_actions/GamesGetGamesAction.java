package server_store_actions;

import common.StoreAction;

import java.util.UUID;

public class GamesGetGamesAction extends StoreAction {

    private final UUID handlerId;

    public GamesGetGamesAction(UUID handlerId) {
        super("@SERVER_GAMES_GET_GAMES","@SERVER_GROUP");
        this.handlerId = handlerId;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

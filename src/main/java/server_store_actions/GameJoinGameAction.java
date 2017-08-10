package server_store_actions;

import common.StoreAction;

import java.util.UUID;

/**
 * Created by giorgiopea on 13/03/17.
 */
public class GameJoinGameAction extends StoreAction {

    private final int gameId;
    private final String playerName;
    private final UUID handlerId;

    public GameJoinGameAction(int gameId, String playerName, UUID handlerId) {
        super("@SERVER_GAME_JOIN_GAME","@SERVER_GROUP");
        this.gameId = gameId;
        this.playerName = playerName;
        this.handlerId = handlerId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

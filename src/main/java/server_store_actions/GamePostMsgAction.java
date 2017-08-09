package server_store_actions;

import common.PlayerToken;
import server_store.StoreAction;

import java.util.UUID;

/**
 * Created by giorgiopea on 04/05/17.
 *
 */
public class GamePostMsgAction extends StoreAction {

    private final PlayerToken playerToken;
    private final String message;
    private final UUID handlerId;

    public GamePostMsgAction(String message, PlayerToken playerToken, UUID handlerId) {
        super("@SERVER_GAME_POST_MSG","@SERVER_GROUP");
        this.playerToken = playerToken;
        this.message = message;
        this.handlerId = handlerId;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public String getMessage() {
        return message;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

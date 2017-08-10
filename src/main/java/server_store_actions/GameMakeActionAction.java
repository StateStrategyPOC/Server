package server_store_actions;

import common.PlayerToken;
import common.StoreAction;

import java.util.UUID;

/**
 * Created by giorgiopea on 12/03/17.
 */
public class GameMakeActionAction extends StoreAction {

    private final StoreAction action;
    private final PlayerToken playerToken;
    private final UUID handlerId;

    public GameMakeActionAction(PlayerToken playerToken, StoreAction action, UUID handlerId) {
        super("@SERVER_GAME_MAKE_ACTION","@SERVER_GROUP");
        this.playerToken = playerToken;
        this.action = action;
        this.handlerId = handlerId;
    }

    public StoreAction getAction() {
        return action;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

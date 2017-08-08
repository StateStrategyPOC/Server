package server_store_actions;

import common.PlayerToken;
import server.Game;
import server_store.StoreAction;

/**
 * Created by giorgiopea on 12/03/17.
 */
public class GameMakeActionAction extends StoreAction {

    private final StoreAction action;
    private final PlayerToken playerToken;

    public GameMakeActionAction(PlayerToken playerToken, StoreAction action) {
        super("@SERVER_GAME_MAKE_ACTION","@SERVER_GROUP");
        this.playerToken = playerToken;
        this.action = action;
    }

    public StoreAction getAction() {
        return action;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}

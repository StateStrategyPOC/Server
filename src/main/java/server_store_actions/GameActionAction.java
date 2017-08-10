package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * Created by giorgiopea on 21/03/17.
 *
 */
public class GameActionAction extends StoreAction {

    private final Game game;
    private final StoreAction action;

    public GameActionAction(StoreAction action, Game game) {
        super("@SERVER_GAME_GAME_ACTION","@SERVER_GROUP");
        this.action = action;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public StoreAction getAction() {
        return action;
    }
}

package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * Created by giorgiopea on 20/03/17.
 */
public class GameTurnTimeoutExpiredAction extends StoreAction {

    private final Game game;

    public GameTurnTimeoutExpiredAction(Game game) {
        super("@SERVER_GAME_TURNTIMEOUT_EXPIRED","@SERVER_GROUP");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}

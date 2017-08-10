package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * Created by giorgiopea on 14/03/17.
 */
public class GamesAddGameAction extends StoreAction {

    private final Game payload;

    public GamesAddGameAction(Game game) {
        super("@SERVER_GAMES_ADD_GAME","@SERVER_GROUP");
        this.payload = game;
    }

    public Game getPayload() {
        return payload;
    }
}

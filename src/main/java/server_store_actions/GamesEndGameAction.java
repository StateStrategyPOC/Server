package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * Created by giorgiopea on 20/03/17.
 */
public class GamesEndGameAction extends StoreAction {

    private final Game game;

    public GamesEndGameAction(Game game) {
        super("@SERVER_GAMES_END_GAME","@SERVER_GROUP");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "GamesEndGameAction{" +
                "game=" + game +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

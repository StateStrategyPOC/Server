package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * An Action for signalling that a new game must be managed
 */
public class GamesAddGameAction extends StoreAction {

    private final Game game;

    public GamesAddGameAction(Game game) {
        super("@SERVER_GAMES_ADD_GAME","@SERVER_GROUP");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "GamesAddGameAction{" +
                "game=" + game +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * An Action for signalling that a game has to end
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
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                "game=" + game +
                '}';
    }
}

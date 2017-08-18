package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * An Action for signalling that a game has to start
 */
public class GameStartGameAction extends StoreAction {

    private final Game game;

    public GameStartGameAction(Game game) {
        super("@SERVER_GAME_START_GAME","@SERVER_GROUP");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "GameStartGameAction{" +
                "game=" + game +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

package server_store_actions;

import server.Game;
import common.StoreAction;

/**
 * An Action for signaling that a player has run off time to perform an in game action
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

    @Override
    public String toString() {
        return "GameTurnTimeoutExpiredAction{" +
                "game=" + game +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

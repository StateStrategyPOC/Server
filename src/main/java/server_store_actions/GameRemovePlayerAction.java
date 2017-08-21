package server_store_actions;

import common.Player;
import common.StoreAction;

/**
 * An Action for signalling that a Player has to be considered out of the game he currently belongs to
 */
public class GameRemovePlayerAction extends StoreAction {
    private final Player player;

    public GameRemovePlayerAction(Player player) {
        super("@SERVER_GAME_REMOVE_PLAYER", "@SERVER_GROUP");
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}

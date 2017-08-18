package server_store_actions;

import common.PlayerToken;
import common.StoreAction;

import java.util.UUID;

/**
 *  An Action for signaling that a Player wants to subscribe to notifications relative to a game
 */
public class GameSubscribeAction extends StoreAction {

    private final PlayerToken playerToken;

    public GameSubscribeAction(PlayerToken playerToken, UUID handlerId) {
        super("@SERVER_GAME_SUBSCRIBE","@SERVER_GROUP");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    @Override
    public String toString() {
        return "GameSubscribeAction{" +
                "playerToken=" + playerToken +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

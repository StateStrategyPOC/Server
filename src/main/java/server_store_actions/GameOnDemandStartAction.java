package server_store_actions;


import common.PlayerToken;
import common.StoreAction;

/**
 * An Action for signalling that the creator of a Game Room has decided to start the game
 */
public class GameOnDemandStartAction extends StoreAction {

    private final PlayerToken playerToken;

    public GameOnDemandStartAction(PlayerToken playerToken) {
        super("@SERVER_GAME_ON_DEMAND_START", "@SERVER_GROUP");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    @Override
    public String toString() {
        return "GameOnDemandStartAction{" +
                "playerToken=" + playerToken +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

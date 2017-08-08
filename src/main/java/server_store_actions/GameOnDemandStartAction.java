package server_store_actions;


import common.PlayerToken;
import server_store.StoreAction;

public class GameOnDemandStartAction extends StoreAction {

    private final PlayerToken playerToken;

    public GameOnDemandStartAction(PlayerToken playerToken) {
        super("@SERVER_GAME_ON_DEMAND_START", "@SERVER_GROUP");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}

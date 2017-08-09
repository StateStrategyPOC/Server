package server_store_actions;

import common.PlayerToken;
import server_store.StoreAction;

public class GameSubscribeAction extends StoreAction {

    private final PlayerToken playerToken;

    public GameSubscribeAction(PlayerToken playerToken) {
        super("@SERVER_GAME_SUBSCRIBE","@SERVER_GROUP");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}

package server_state_policies;

import common.RRClientNotification;
import server.Game;
import server.Helpers;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.GamePostMsgAction;

public class GamePostChatMsgStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GamePostMsgAction castedAction = (GamePostMsgAction) action;
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),state.getGames());
        if (game != null){
            game.setLastChatMsg(castedAction.getMessage());
            game.setLastRRclientNotification(new RRClientNotification(true));
        }
        return state;
    }
}

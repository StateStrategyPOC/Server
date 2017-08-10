package server_state_policies;

import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GamesEndGameAction;

public class GamesEndGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GamesEndGameAction castedAction = (GamesEndGameAction) action;
        for (PubSubHandler handler : castedAction.getGame().getPubSubHandlers()){
            handler.setRunningFlag(false);
        }
        state.getGames().remove(castedAction.getGame());
        return state;
    }
}

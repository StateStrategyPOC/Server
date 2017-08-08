package server_state_policies;

import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.GamesAddGameAction;

public class GamesAddGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GamesAddGameAction castedAction = (GamesAddGameAction) action;
        state.getGames().add(castedAction.getPayload());
        return state;
    }
}

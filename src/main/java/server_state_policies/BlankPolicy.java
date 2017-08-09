package server_state_policies;

import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;

public class BlankPolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        return state;
    }
}

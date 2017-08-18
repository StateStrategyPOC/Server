package server_store;

import common.StoreAction;

public class BlankPolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        return state;
    }
}

package server_store;

import common.StoreAction;

public interface SidePolicy {
    void apply(ServerState state, StoreAction action);

}

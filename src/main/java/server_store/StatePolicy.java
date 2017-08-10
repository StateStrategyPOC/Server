package server_store;

import common.StoreAction;

public interface StatePolicy {
    ServerState apply(ServerState state, StoreAction action);
}

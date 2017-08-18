package server_store;

import common.StoreAction;

/**
 * Represents a lambda MVC State Policy
 */
public interface StatePolicy {
    ServerState apply(ServerState state, StoreAction action);
}

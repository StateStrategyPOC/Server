package server_store;

import common.StoreAction;


/**
 * Represents a lambda MVC State Policy
 */
public interface SidePolicy {
    void apply(ServerState state, StoreAction action);

}

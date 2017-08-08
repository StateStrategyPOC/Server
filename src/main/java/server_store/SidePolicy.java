package server_store;

public interface SidePolicy {
    ServerState apply(ServerState state, StoreAction action);

}

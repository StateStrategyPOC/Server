package server_store;

public interface SidePolicy {
    void apply(ServerState state, StoreAction action);

}

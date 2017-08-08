package server_store;

public interface StatePolicy {
    ServerState apply(ServerState state, StoreAction action);
}

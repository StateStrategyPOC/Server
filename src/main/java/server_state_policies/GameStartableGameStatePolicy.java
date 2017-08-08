package server_state_policies;

import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.GameStartableGameAction;

public class GameStartableGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GameStartableGameAction castedAction = (GameStartableGameAction) action;
        castedAction.getGame().setStartableGame(castedAction.isStartable());
        return state;
    }
}

package server_state_policies;

import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GamesAddGameAction;

public class GamesAddGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(99));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(98));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(92));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(91));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(89));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(83));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(82));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(70));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(67));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(58));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(50));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(47));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(45));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(43));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(38));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(31));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(30));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(27));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(6));
        GamesAddGameAction castedAction = (GamesAddGameAction) action;
        state.getGames().add(castedAction.getGame());
        return state;
    }
}

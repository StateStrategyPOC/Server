package server_state_policies;

import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GamesEndGameAction;

public class GamesEndGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(90));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(87));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(76));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(54));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(46));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(42));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(29));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(25));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(20));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(10));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(8));
        GamesEndGameAction castedAction = (GamesEndGameAction) action;
        for (PubSubHandler handler : castedAction.getGame().getPubSubHandlers()){
            handler.setRunningFlag(false);
        }
        castedAction.getGame().getCurrentTimer().cancel();
        state.getGames().remove(castedAction.getGame());
        return state;
    }
}

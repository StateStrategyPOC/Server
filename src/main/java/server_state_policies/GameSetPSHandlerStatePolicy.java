package server_state_policies;

import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GameSetPSHandlerAction;

public class GameSetPSHandlerStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(85));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(81));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(79));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(75));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(68));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(63));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(61));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(53));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(49));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(28));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(19));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(18));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(7));
        GameSetPSHandlerAction castedAction = (GameSetPSHandlerAction) action;
        castedAction.getGame().getPubSubHandlers().add(new PubSubHandler(castedAction.getSocket(),castedAction.getOutputStream(),castedAction.getPlayerToken()));
        return state;
    }
}

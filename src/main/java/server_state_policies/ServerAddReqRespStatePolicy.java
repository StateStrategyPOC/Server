package server_state_policies;

import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.ServerAddReqRespHandlerAction;

public class ServerAddReqRespStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        ServerAddReqRespHandlerAction castedAction = (ServerAddReqRespHandlerAction) action;
        state.getReqRespHandlers().add(castedAction.getHandler());
        return state;
    }
}

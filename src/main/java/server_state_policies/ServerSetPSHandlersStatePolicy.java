package server_state_policies;

import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.ServerSetPSHandlersAction;

public class ServerSetPSHandlersStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        ServerSetPSHandlersAction castedAction = (ServerSetPSHandlersAction) action;
        castedAction.getGame().getPubSubHandlers().add(new PubSubHandler(castedAction.getSocket(),castedAction.getOutputStream(),castedAction.getPlayerToken()));
        return state;
    }
}

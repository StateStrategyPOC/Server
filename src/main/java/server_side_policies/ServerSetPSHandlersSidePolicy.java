package server_side_policies;

import server.ConnectionListener;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.ServerSetPSHandlersAction;

public class ServerSetPSHandlersSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        ServerSetPSHandlersAction castedAction = (ServerSetPSHandlersAction) action;
        PubSubHandler handler = castedAction.getGame().getPubSubHandlers().get(castedAction.getGame().getPubSubHandlers().size()-1);
        ConnectionListener.getInstance().registerPubSubHandler(handler);
    }
}

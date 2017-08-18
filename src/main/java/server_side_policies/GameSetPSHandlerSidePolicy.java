package server_side_policies;

import server.ConnectionListener;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameSetPSHandlerAction;

public class GameSetPSHandlerSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameSetPSHandlerAction castedAction = (GameSetPSHandlerAction) action;
        PubSubHandler handler = castedAction.getGame().getPubSubHandlers().get(castedAction.getGame().getPubSubHandlers().size()-1);
        ConnectionListener.getInstance().registerPubSubHandler(handler);
    }
}

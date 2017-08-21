package server_side_policies;

import common.PSNotification;
import server.PubSubHandler;
import server.TurnTimeout;
import server_store.ServerState;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameStartGameAction;

public class GameStartGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameStartGameAction castedAction = (GameStartGameAction) action;
        for (PubSubHandler pubSubHandler : castedAction.getGame().getPubSubHandlers()){
            PSNotification notification = new PSNotification(null, null, null,
                    false, false, null, null, true, false, false, false, castedAction.getGame().getMapName());

            if (pubSubHandler.getPlayerToken().equals(castedAction.getGame().getCurrentPlayer().getPlayerToken())){
                notification = new PSNotification(null, null, null,
                        false, false, null, null, true, true, false, false, castedAction.getGame().getMapName());
                pubSubHandler.queueNotification(notification);
            }
            else {
                pubSubHandler.queueNotification(notification);
            }
        }
        castedAction.getGame().getCurrentTimer().schedule(new TurnTimeout(castedAction.getGame()),state.getTurnTimeout());
    }
}

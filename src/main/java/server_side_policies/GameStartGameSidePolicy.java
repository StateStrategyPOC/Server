package server_side_policies;

import common.PSClientNotification;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GameStartGameAction;

public class GameStartGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameStartGameAction castedAction = (GameStartGameAction) action;
        for (PubSubHandler pubSubHandler : castedAction.getGame().getPubSubHandlers()){
            PSClientNotification notification = new PSClientNotification();
            notification.setGameNeedToStart(true);
            pubSubHandler.queueNotification(notification);
            if (pubSubHandler.getPlayerToken().equals(castedAction.getGame().getCurrentPlayer().getPlayerToken())){
                notification = new PSClientNotification();
                notification.setTurnNeedToStart(true);
                pubSubHandler.queueNotification(notification);
            }
        }
    }
}

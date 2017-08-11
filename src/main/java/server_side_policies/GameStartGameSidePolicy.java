package server_side_policies;

import common.PSClientNotification;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameStartGameAction;

public class GameStartGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameStartGameAction castedAction = (GameStartGameAction) action;
        for (PubSubHandler pubSubHandler : castedAction.getGame().getPubSubHandlers()){
            PSClientNotification notification = new PSClientNotification();
            notification.setGameNeedToStart(true);
            notification.setGameMapName(castedAction.getGame().getMapName());
            if (pubSubHandler.getPlayerToken().equals(castedAction.getGame().getCurrentPlayer().getPlayerToken())){
                notification.setTurnNeedToStart(true);
            }
            pubSubHandler.queueNotification(notification);

        }
    }
}

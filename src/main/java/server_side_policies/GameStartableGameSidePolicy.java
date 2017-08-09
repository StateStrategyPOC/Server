package server_side_policies;

import common.PSClientNotification;
import server.Helpers;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GameStartGameAction;
import server_store_actions.GameStartableGameAction;

public class GameStartableGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameStartableGameAction castedAction = (GameStartableGameAction) action;
        if (castedAction.isStartable()){
            PubSubHandler handler = Helpers.findPubSubHandlerByPlayerToken(castedAction.getGame().getCurrentPlayer().getPlayerToken(),castedAction.getGame());
            if (handler == null){
                return;
            }
            PSClientNotification notification = new PSClientNotification();
            notification.setGameCanBeStarted(true);
            handler.queueNotification(notification);
        }
    }
}

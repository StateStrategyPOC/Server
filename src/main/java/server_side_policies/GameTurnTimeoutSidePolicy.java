package server_side_policies;

import common.PSClientNotification;
import server.Game;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameTurnTimeoutExpiredAction;

public class GameTurnTimeoutSidePolicy implements SidePolicy {


    @Override
    public void apply(ServerState state, StoreAction action) {
        GameTurnTimeoutExpiredAction castedAction = (GameTurnTimeoutExpiredAction) action;
        Game game = castedAction.getGame();
        //Notification sending
        for (PubSubHandler handler : game.getPubSubHandlers()) {
            if (handler.getPlayerToken().equals(game.getCurrentPlayer().getPlayerToken())) {
                PSClientNotification notification = new PSClientNotification();
                notification.setTurnNeedToStart(true);
                notification.setMessage(game.getLastPSclientNotification().getMessage());
                handler.queueNotification(notification);
            }
            else if (handler.getPlayerToken().equals(game.getPreviousPlayer().getPlayerToken())) {
                PSClientNotification notification = new PSClientNotification();
                notification.setTurnNeedToEnd(true);
                notification.setMessage(game.getLastPSclientNotification().getMessage());
                handler.queueNotification(notification);
            }
        }
    }
}

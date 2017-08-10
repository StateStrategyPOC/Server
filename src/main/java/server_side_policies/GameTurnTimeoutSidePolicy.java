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
        PSClientNotification notification = game.getLastPSclientNotification();
        //Notification sending
        for (PubSubHandler handler : game.getPubSubHandlers()) {

            if (handler.getPlayerToken().equals(game.getCurrentPlayer().getPlayerToken())) {
                notification.setTurnNeedToStart(true);
            }
            else if (handler.getPlayerToken().equals(game.getPreviousPlayer().getPlayerToken())) {
                notification.setTurnNeedToEnd(true);
            }
            handler.queueNotification(notification);

        }
    }
}

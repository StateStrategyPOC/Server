package server_side_policies;

import common.PSNotification;
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
                PSNotification notification = new PSNotification(game.getLastPSclientNotification().getMessage(),
                        null,null,false,false,null,
                false,true,false,false,null);
                handler.queueNotification(notification);
            }
            else if (handler.getPlayerToken().equals(game.getPreviousPlayer().getPlayerToken())) {
                PSNotification notification = new PSNotification(game.getLastPSclientNotification().getMessage(),
                        null,null,false,false,null,
                        false,false,false,true,null);

                handler.queueNotification(notification);
            }
        }
    }
}

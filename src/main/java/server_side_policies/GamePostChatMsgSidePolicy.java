package server_side_policies;

import common.PSClientNotification;
import common.RRClientNotification;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GamePostMsgAction;
import server_store_actions.ServerSetNotificationAction;
import server_store_actions.ServerSetResponseAction;

public class GamePostChatMsgSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GamePostMsgAction castedAction = (GamePostMsgAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        PSClientNotification notification = new PSClientNotification();
        notification.setIncomingMsg(castedAction.getMessage());
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),SERVER_STORE.getState().getGames());
        if (game == null){
            SERVER_STORE.propagateAction(new ServerSetResponseAction(new RRClientNotification(false)));
            return;
        }
        SERVER_STORE.propagateAction(new ServerSetResponseAction(new RRClientNotification(true)));
        notification.setGameId(castedAction.getPlayerToken().getGameId());
        for (PubSubHandler handler : game.getPubSubHandlers()){
            handler.queueNotification(notification);
        }
    }
}

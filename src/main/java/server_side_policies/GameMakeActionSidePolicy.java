package server_side_policies;

import common.PSClientNotification;
import common.RRClientNotification;
import common.RemoteMethodCall;
import server.*;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GameMakeActionAction;
import server_store_actions.GamesEndGameAction;
import server_store_actions.ServerSetNotificationAction;
import server_store_actions.ServerSetResponseAction;

import java.util.ArrayList;

public class GameMakeActionSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameMakeActionAction castedAction = (GameMakeActionAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),SERVER_STORE.getState().getGames());

        if (game == null){
            SERVER_STORE.propagateAction(new ServerSetResponseAction(new RRClientNotification(false)));
            return;
        }
        SERVER_STORE.propagateAction(new ServerSetResponseAction(game.getLastRRclientNotification()));
        SERVER_STORE.propagateAction(new ServerSetNotificationAction(game.getLastPSclientNotification()));


        for (PubSubHandler handler : game.getPubSubHandlers()) {
            handler.queueNotification(game.getLastPSclientNotification());

            if (castedAction.getAction().getActionIdentifier().equals("@GAMEACTION_END_TURN") && game.getCurrentPlayer().getPlayerToken().equals(handler.getPlayerToken())) {
                PSClientNotification notification = new PSClientNotification();
                notification.setTurnNeedToStart(true);
                handler.queueNotification(notification);
            }

        }
        if (!game.isDidHumansWin() && !game.isDidAlienWin()) {
            game.getCurrentTimer().schedule(new TurnTimeout(game), state.getTurnTimeout());
        } else {
            SERVER_STORE.propagateAction(new GamesEndGameAction(game));
        }
    }
}

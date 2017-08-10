package server_side_policies;

import common.*;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameStartGameAction;
import server_store_actions.GameOnDemandStartAction;

public class GameOnDemandStartSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameOnDemandStartAction castedAction = (GameOnDemandStartAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(), state.getGames());
        RRClientNotification rrClientNotification;
        if (game == null) {
            return;
        }
        if (game.getCurrentPlayer().getPlayerToken().equals(castedAction.getPlayerToken())) {
            SERVER_STORE.propagateAction(new GameStartGameAction(game));
            rrClientNotification = new RRClientNotification(game.getMapName());

        } else {
            rrClientNotification = new RRClientNotification(false);

        }
        PSClientNotification notification = new PSClientNotification();
        notification.setGameNeedToStart(true);
        notification.setGameMapName(game.getMapName());
        game.setLastRRclientNotification(rrClientNotification);
        for (PubSubHandler handler : game.getPubSubHandlers()){
            if (!handler.getPlayerToken().equals(game.getCurrentPlayer().getPlayerToken())){
                handler.queueNotification(notification);
            }
        }
    }
}

package server_side_policies;

import common.*;
import server.Game;
import server.Helpers;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store_actions.GameStartGameAction;
import server_store_actions.GameOnDemandStartAction;

public class GameOnDemandStartSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameOnDemandStartAction castedAction = (GameOnDemandStartAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(), state.getGames());
        RRNotification rrNotification;
        if (game == null) {
            return;
        }
        if (game.getCurrentPlayer().getPlayerToken().equals(castedAction.getPlayerToken())) {
            SERVER_STORE.propagateAction(new GameStartGameAction(game));
            rrNotification = new RRNotification(true,null,null, null, null, null,null,null,null);

        } else {
            rrNotification = new RRNotification(false,null,null,null, null, null,null,null,null);

        }
        game.setLastRRclientNotification(rrNotification);
    }
}

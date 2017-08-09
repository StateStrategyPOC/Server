package server_side_policies;

import common.RRClientNotification;
import server.Game;
import server.Helpers;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.*;

public class GameSubscribeSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameSubscribeAction castedAction = (GameSubscribeAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(), SERVER_STORE.getState().getGames());
        if (game == null) {
            SERVER_STORE.propagateAction(new ServerSetResponseAction(new RRClientNotification(false)));
            return;
        }
        SERVER_STORE.propagateAction(new ServerTransformChannelAction(game, castedAction.getPlayerToken()));
        if (game.getPlayers().size() == 8) {
            SERVER_STORE.propagateAction(new GameStartGameAction(game));
        } else if (game.getPlayers().size() == 2) {
            SERVER_STORE.propagateAction(new GameStartableGameAction(game, true));
        }


    }
}

package server_state_policies;

import common.Player;
import common.StoreAction;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import server_store_actions.GameRemovePlayerAction;

public class GameRemovePlayerStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(95));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(88));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(78));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(77));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(74));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(69));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(64));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(60));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(39));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(36));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(33));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(16));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(15));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(14));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(13));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(12));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(11));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(9));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(3));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(2));
        GameRemovePlayerAction castedAction = (GameRemovePlayerAction) action;
        Game game = Helpers.findGameById(castedAction.getPlayer().getPlayerToken().getGameId(),state.getGames());
        if (game == null){
            return state;
        }
        PubSubHandler handler = Helpers.findPubSubHandlerByPlayerToken(castedAction.getPlayer().getPlayerToken(),game);
        game.getPlayers().remove(castedAction.getPlayer());
        game.getPubSubHandlers().remove(handler);
        return state;
    }
}

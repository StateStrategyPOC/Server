package server_state_policies;

import common.EndTurnAction;
import effects.EndTurnEffect;
import server.Game;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GameTurnTimeoutExpiredAction;

import java.util.Timer;

public class GameTurnTimeoutStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(97));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(96));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(66));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(65));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(57));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(48));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(24));
        GameTurnTimeoutExpiredAction castedAction = (GameTurnTimeoutExpiredAction) action;
        Game game = castedAction.getGame();
        EndTurnEffect.executeEffect(game, new EndTurnAction());
        game.getCurrentTimer().cancel();
        game.setCurrentTimer(new Timer());
        return state;
    }
}

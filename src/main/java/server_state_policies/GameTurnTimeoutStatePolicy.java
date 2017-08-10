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
        GameTurnTimeoutExpiredAction castedAction = (GameTurnTimeoutExpiredAction) action;
        Game game = castedAction.getGame();
        EndTurnEffect.executeEffect(game, new EndTurnAction());
        game.getCurrentTimer().cancel();
        game.setCurrentTimer(new Timer());
        return state;
    }
}

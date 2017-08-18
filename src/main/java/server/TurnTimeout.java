package server;

import server_store.ServerStore;
import server_store_actions.GameTurnTimeoutExpiredAction;

import java.util.TimerTask;


/**
 * A thread that notifies an associated {@link Game} for the expiration of the turn timer.
 */
public class TurnTimeout extends TimerTask {

    private final ServerStore SERVER_STORE;
    private final Game game;

    public TurnTimeout(Game game) {
        this.game = game;
        this.SERVER_STORE = ServerStore.getInstance();
    }

    @Override
    public void run() {
        SERVER_STORE.propagateAction(new GameTurnTimeoutExpiredAction(game));
    }
}

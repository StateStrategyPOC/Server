package server_side_policies;

import common.EndTurnAction;
import common.PSClientNotification;
import server.*;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GameMakeActionAction;
import server_store_actions.GamesEndGameAction;

public class GameMakeActionSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameMakeActionAction castedAction = (GameMakeActionAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),SERVER_STORE.getState().getGames());

        if (game == null){
            return;
        }
        for (PubSubHandler handler : game.getPubSubHandlers()) {
            handler.queueNotification(game.getLastPSclientNotification());
            if (castedAction.getAction().getClass().equals(EndTurnAction.class) && game.getCurrentPlayer().getPlayerToken().equals(handler.getPlayerToken())) {
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

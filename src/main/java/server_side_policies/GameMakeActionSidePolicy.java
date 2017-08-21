package server_side_policies;

import common.PSNotification;
import common.StoreAction;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
import server.TurnTimeout;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store_actions.GameMakeActionAction;
import server_store_actions.GamesEndGameAction;

public class GameMakeActionSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameMakeActionAction castedAction = (GameMakeActionAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),state.getGames());

        if (game == null){
            return;
        }
        if (!game.getLastRRclientNotification().isActionResult()){
            return;
        }
        for (PubSubHandler handler : game.getPubSubHandlers()) {
            if (castedAction.getInGameAction().getActionIdentifier().equals("@GAMEACTION_END_TURN") && handler.getPlayerToken().equals(game.getCurrentPlayer().getPlayerToken())){
                    PSNotification notification = new PSNotification(game.getLastPSclientNotification().getMessage(), null, null, false, false, null, null, false, true, false, false, null);
                    handler.queueNotification(notification);
            }
            else {
                handler.queueNotification(game.getLastPSclientNotification());
            }

        }
        if (!game.isDidHumansWin() && !game.isDidAlienWin()) {
            game.getCurrentTimer().schedule(new TurnTimeout(game), state.getTurnTimeout());
        } else {
            SERVER_STORE.propagateAction(new GamesEndGameAction(game));
        }
    }
}

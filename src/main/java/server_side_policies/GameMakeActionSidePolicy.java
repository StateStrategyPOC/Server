package server_side_policies;

import common.PSClientNotification;
import common.StoreAction;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
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
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),SERVER_STORE.getState().getGames());

        if (game == null){
            return;
        }
        for (PubSubHandler handler : game.getPubSubHandlers()) {
            if (castedAction.getAction().getActionIdentifier().equals("@GAMEACTION_END_TURN") && handler.getPlayerToken().equals(game.getCurrentPlayer().getPlayerToken())){
                    PSClientNotification notification = new PSClientNotification();
                    notification.setMessage(game.getLastPSclientNotification().getMessage());
                    notification.setTurnNeedToStart(true);
                    handler.queueNotification(notification);
            }
            else {
                handler.queueNotification(game.getLastPSclientNotification());
            }

        }
        if (!game.isDidHumansWin() && !game.isDidAlienWin()) {
            //game.getCurrentTimer().schedule(new TurnTimeout(game), state.getTurnTimeout());
        } else {
            SERVER_STORE.propagateAction(new GamesEndGameAction(game));
        }
    }
}

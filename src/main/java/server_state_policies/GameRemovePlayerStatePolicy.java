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

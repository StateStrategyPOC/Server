package server_side_policies;

import common.RRClientNotification;
import server.Game;
import server.Helpers;
import server.ReqRespHandler;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GameJoinGameAction;
import server_store_actions.ServerSetResponseAction;

public class GameJoinGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GameJoinGameAction castedAction = (GameJoinGameAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();
        Game game = Helpers.findGameById(castedAction.getGameId(),SERVER_STORE.getState().getGames());
        ReqRespHandler handler = Helpers.findReqRespHandlerById(castedAction.getHandlerId(),SERVER_STORE.getState().getReqRespHandlers());
        if (game == null || handler == null){
            SERVER_STORE.propagateAction(new ServerSetResponseAction(new RRClientNotification(false), castedAction.getHandlerId()));
            return;
        }
        //SERVER_STORE.propagateAction(new ServerSetResponseAction(game.getLastRRclientNotification(),castedAction.getHandlerId()));
    }
}

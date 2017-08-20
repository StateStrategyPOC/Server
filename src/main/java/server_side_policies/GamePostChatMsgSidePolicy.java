package server_side_policies;

import common.PSNotification;
import common.Player;
import server.Game;
import server.Helpers;
import server.PubSubHandler;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import common.StoreAction;
import server_store_actions.GamePostMsgAction;

public class GamePostChatMsgSidePolicy implements SidePolicy {
    @Override
    public void apply(ServerState state, StoreAction action) {
        GamePostMsgAction castedAction = (GamePostMsgAction) action;
        ServerStore SERVER_STORE = ServerStore.getInstance();

        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(),state.getGames());
        if (game == null){
            return;
        }
        Player player = Helpers.findPlayerByToken(castedAction.getPlayerToken(),game.getPlayers());
        if (player == null){
            return;
        }
        PSNotification notification = new PSNotification("["+player.getName()+"]:"+castedAction.getMessage(), null,null,
                false,false,null, null, false,false,false,false, null);

        for (PubSubHandler handler : game.getPubSubHandlers()){
            handler.queueNotification(notification);
        }
    }
}

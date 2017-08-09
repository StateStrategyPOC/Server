package server_side_policies;


;

import common.*;
import server.Game;
import server_store.ServerState;
import server_store.ServerStore;
import server_store.SidePolicy;
import server_store.StoreAction;
import server_store_actions.GamesGetGamesAction;
import server_store_actions.ServerSetResponseAction;

import java.util.ArrayList;
import java.util.List;

public class GamesGetGamesSidePolicy implements SidePolicy {

    @Override
    public void apply(ServerState state, StoreAction action) {
        ServerStore SERVER_STORE = ServerStore.getInstance();
        GamesGetGamesAction castedAction = (GamesGetGamesAction) action;
        List<Game> games = SERVER_STORE.getState().getGames();
        ArrayList<GamePublicData> gamesList = new ArrayList<>();
        for (Game game : games) {
            gamesList.add(game.getGamePublicData());
        }
        RRClientNotification response = new RRClientNotification(true,new ArrayList<Card>(),new ArrayList<Sector>(),gamesList,null, "");
        SERVER_STORE.propagateAction(new ServerSetResponseAction(response,castedAction.getHandlerId()));
    }
}

package server_store_actions;

import server_store.StoreAction;

/**
 * Created by giorgiopea on 13/03/17.
 */
public class GameJoinGameAction extends StoreAction {

    private final int gameId;
    private final String playerName;

    public GameJoinGameAction(int gameId, String playerName) {
        super("@SERVER_GAME_JOIN_GAME","@SERVER_GROUP");
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}

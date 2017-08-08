package server_store_actions;

import server_store.StoreAction;

public class ServerJoinGameAction extends StoreAction {

    private final Integer gameId;
    private final String playerName;


    public ServerJoinGameAction(Integer gameId, String playerName) {
        super("@SERVER_JOIN_GAME", "@SERVER_GROUP");
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public Integer getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}

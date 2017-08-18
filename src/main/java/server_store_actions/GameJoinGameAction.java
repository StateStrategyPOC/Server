package server_store_actions;

import common.StoreAction;

import java.util.UUID;

/**
 * An Action for signalling that a Player wants to join a game
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

    @Override
    public String toString() {
        return "GameJoinGameAction{" +
                "gameId=" + gameId +
                ", playerName='" + playerName + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

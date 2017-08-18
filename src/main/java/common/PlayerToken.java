package common;


import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a token that identifies in an unique way a {@link Player} and stores its
 * type.
 */
public class PlayerToken implements Serializable {
    // The universally unique identifier of the player
    private final UUID playerId;
    private final PlayerType playerType;
    private final int gameId;

    public int getGameId() {
        return gameId;
    }

    public PlayerToken(PlayerType playerType, int gameId) {
        this.playerId = UUID.randomUUID();
        this.playerType = playerType;
        this.gameId = gameId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((playerId == null) ? 0 : playerId.hashCode());
        result = prime * result
                + ((playerType == null) ? 0 : playerType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerToken other = (PlayerToken) obj;
        if (playerId == null) {
            if (other.playerId != null)
                return false;
        } else if (!playerId.equals(other.playerId))
            return false;
        return playerType == other.playerType;
    }

    @Override
    public String toString() {
        return "PlayerToken{" +
                "playerId=" + playerId +
                ", playerType=" + playerType +
                ", gameId=" + gameId +
                '}';
    }
}

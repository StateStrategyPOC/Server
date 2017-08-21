package common;


import java.io.Serializable;

/**
 * Represents a generic player in the game
 */
public class Player implements Serializable {
    private int speed;
    private PlayerToken playerToken;
    // Player's current sector
    private Sector currentSector;
    // Player's state
    private PlayerState playerState;
    // Player's public deck
    private final PrivateDeck privateDeck;
    private boolean isSedated;
    private boolean isAdrenalined;
    private boolean hasMoved;
    private final String name;


    public Player(String name) {
        this.playerState = PlayerState.ALIVE;
        this.privateDeck = new PrivateDeck();
        this.isAdrenalined = false;
        this.isSedated = false;
        this.currentSector = null;
        this.name = name;
        this.hasMoved = false;
    }

    public void setPlayerToken(PlayerToken playerToken) {
        this.playerToken = playerToken;
        if (this.playerToken.getPlayerType().equals(PlayerType.HUMAN)) {
            this.speed = 1;
        } else {
            this.speed = 2;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public Sector getCurrentSector() {
        return currentSector;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public PrivateDeck getPrivateDeck() {
        return privateDeck;
    }

    public boolean isSedated() {
        return isSedated;
    }

    public boolean isAdrenalined() {
        return isAdrenalined;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public String getName() {
        return name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCurrentSector(Sector currentSector) {
        this.currentSector = currentSector;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void setSedated(boolean sedated) {
        isSedated = sedated;
    }

    public void setAdrenalined(boolean adrenalined) {
        isAdrenalined = adrenalined;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public String toString() {
        return "Player{" +
                "speed=" + speed +
                ", playerToken=" + playerToken +
                ", currentSector=" + currentSector +
                ", playerState=" + playerState +
                ", privateDeck=" + privateDeck +
                ", isSedated=" + isSedated +
                ", isAdrenalined=" + isAdrenalined +
                ", hasMoved=" + hasMoved +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (speed != player.speed) return false;
        if (isSedated != player.isSedated) return false;
        if (isAdrenalined != player.isAdrenalined) return false;
        if (hasMoved != player.hasMoved) return false;
        if (playerToken != null ? !playerToken.equals(player.playerToken) : player.playerToken != null) return false;
        if (currentSector != null ? !currentSector.equals(player.currentSector) : player.currentSector != null)
            return false;
        if (playerState != player.playerState) return false;
        if (!privateDeck.equals(player.privateDeck)) return false;
        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        int result = speed;
        result = 31 * result + (playerToken != null ? playerToken.hashCode() : 0);
        result = 31 * result + (currentSector != null ? currentSector.hashCode() : 0);
        result = 31 * result + (playerState != null ? playerState.hashCode() : 0);
        result = 31 * result + privateDeck.hashCode();
        result = 31 * result + (isSedated ? 1 : 0);
        result = 31 * result + (isAdrenalined ? 1 : 0);
        result = 31 * result + (hasMoved ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
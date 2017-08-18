package common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a notification to be delivered to all the subscribers of a topic
 * in the logic of the publisher-subscriber pattern.
 */

public class PSNotification implements Serializable {
    private final String message;
    private final ArrayList<PlayerToken> deadPlayers;
    private final ArrayList<PlayerToken> attackedPlayers;
    private final boolean humanWin;
    private final boolean alienWin;
    private final PlayerToken escapedPlayer;
    private final boolean gameNeedsToStart;
    private final boolean turnNeedsToStart;
    private final boolean gameCanBeStarted;
    private final boolean turnNeedsToEnd;
    private final String gameMapName;


    public PSNotification(String message, ArrayList<PlayerToken> deadPlayers, ArrayList<PlayerToken> attackedPlayers, boolean humanWin, boolean alienWin, PlayerToken escapedPlayer, boolean gameNeedsToStart, boolean turnNeedToStart, boolean gameCanBeStarted, boolean turnNeedsToEnd, String gameMapName) {
        this.message = message;
        this.deadPlayers = deadPlayers;
        this.attackedPlayers = attackedPlayers;
        this.humanWin = humanWin;
        this.alienWin = alienWin;
        this.escapedPlayer = escapedPlayer;
        this.gameNeedsToStart = gameNeedsToStart;
        this.turnNeedsToStart = turnNeedToStart;
        this.gameCanBeStarted = gameCanBeStarted;
        this.turnNeedsToEnd = turnNeedsToEnd;
        this.gameMapName = gameMapName;
    }


    public ArrayList<PlayerToken> getDeadPlayers() {
        return deadPlayers;
    }


    public ArrayList<PlayerToken> getAttackedPlayers() {
        return attackedPlayers;
    }


    public PlayerToken getEscapedPlayer() {
        return escapedPlayer;
    }


    public boolean isGameNeedsToStart() {
        return gameNeedsToStart;
    }

    public boolean isTurnNeedsToStart() {
        return turnNeedsToStart;
    }


    public boolean isGameCanBeStarted() {
        return gameCanBeStarted;
    }


    public boolean isTurnNeedsToEnd() {
        return turnNeedsToEnd;
    }


    public String getGameMapName() {
        return gameMapName;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHumanWin() {
        return humanWin;
    }

    public boolean isAlienWin() {
        return alienWin;
    }

    @Override
    public String toString() {
        return "PSNotification{" +
                "message='" + message + '\'' +
                ", deadPlayers=" + deadPlayers +
                ", attackedPlayers=" + attackedPlayers +
                ", humanWin=" + humanWin +
                ", alienWin=" + alienWin +
                ", escapedPlayer=" + escapedPlayer +
                ", gameNeedsToStart=" + gameNeedsToStart +
                ", turnNeedsToStart=" + turnNeedsToStart +
                ", gameCanBeStarted=" + gameCanBeStarted +
                ", turnNeedsToEnd=" + turnNeedsToEnd +
                ", gameMapName='" + gameMapName + '\'' +
                '}';
    }
}

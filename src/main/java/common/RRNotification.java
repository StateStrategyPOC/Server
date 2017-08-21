package common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a message the server sends to the client in response
 * to a received {@link common.ActionOnTheWire} to inform the client
 * about the execution of the requested behaviour
 */
public class RRNotification implements Serializable {
    private final boolean actionResult;
    private final String message;
    //Sector Cards or Object Cards drawn as a result of an in game action
    private final SectorCard drawnSectorCard;
    private final ObjectCard drawnObjectCard;
    private final RescueCard drawnRescueCard;
    //Sectors whose resident Players are revealed as a result of a LightsObjectCard
    private final ArrayList<Sector> lightedSectors;
    private final ArrayList<GamePublicData> availableGames;
    private final PlayerToken playerToken;

    public RRNotification(boolean actionResult,
                          String message, SectorCard drawnSectorCard, ObjectCard drawnObjectCard, RescueCard drawnRescueCard, ArrayList<Sector> lightedSectors, ArrayList<GamePublicData> availableGames, PlayerToken playerToken) {
        this.actionResult = actionResult;
        this.message = message;
        this.drawnSectorCard = drawnSectorCard;
        this.drawnObjectCard = drawnObjectCard;
        this.drawnRescueCard = drawnRescueCard;
        this.lightedSectors = lightedSectors;
        this.availableGames = availableGames;
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public boolean isActionResult() {
        return actionResult;
    }

    public SectorCard getDrawnSectorCard() {
        return drawnSectorCard;
    }

    public ObjectCard getDrawnObjectCard() {
        return drawnObjectCard;
    }

    public ArrayList<Sector> getLightedSectors() {
        return this.lightedSectors;
    }

    public ArrayList<GamePublicData> getAvailableGames() {
        return this.availableGames;
    }

    public String getMessage() {
        return message;
    }

    public RescueCard getDrawnRescueCard() {
        return drawnRescueCard;
    }

    @Override
    public String toString() {
        return "RRNotification{" +
                "actionResult=" + actionResult +
                ", message='" + message + '\'' +
                ", drawnSectorCard=" + drawnSectorCard +
                ", drawnObjectCard=" + drawnObjectCard +
                ", drawnRescueCard=" + drawnRescueCard +
                ", lightedSectors=" + lightedSectors +
                ", availableGames=" + availableGames +
                ", playerToken=" + playerToken +
                '}';
    }
}

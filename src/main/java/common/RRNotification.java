package common;

import javax.smartcardio.Card;
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
    //Sectors whose resident Players are revealed as a result of a LightsObjectCard
    private final ArrayList<Sector> lightedSectors;
    private final ArrayList<GamePublicData> availableGames;
    private final PlayerToken playerToken;
    private final String gameMapName;

    public RRNotification(boolean actionResult,
                          String message, SectorCard drawnSectorCard, ObjectCard drawnObjectCard, ArrayList<Sector> lightedSectors, ArrayList<GamePublicData> availableGames, PlayerToken playerToken, String gameMapName) {
        this.actionResult = actionResult;
        this.message = message;
        this.drawnSectorCard = drawnSectorCard;
        this.drawnObjectCard = drawnObjectCard;
        this.lightedSectors = lightedSectors;
        this.availableGames = availableGames;
        this.playerToken = playerToken;
        this.gameMapName = gameMapName;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public boolean getActionResult() {
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

    public ArrayList<GamePublicData> getGames() {
        return this.availableGames;
    }

    public String getGameMapName() {
        return this.gameMapName;
    }

    public boolean isActionResult() {
        return actionResult;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<GamePublicData> getAvailableGames() {
        return availableGames;
    }
}
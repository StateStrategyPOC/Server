package common;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a notification to be delivered to a single client in response to
 * one of its game actions.
 */
public class RRNotification implements Serializable {
    // A field automatically created for serialization purposes
    private final boolean actionResult;
    private final String message;
    private final ArrayList<Card> drawnCards;
    private final ArrayList<Sector> lightedSectors;
    private final ArrayList<GamePublicData> availableGames;
    private final PlayerToken playerToken;
    private final String gameMapName;

    /**
     * Constructs a notification to be delivered to a single client in response
     * to one of its game actions. This notification is constructed from the
     * result of the action it refers to and from the list of cards the client
     * has drawn by performing the action above mentioned, all along with an
     * empty text message
     *
     * @param actionResult   the result of the action the notification refers to
     * @param message
     * @param drawnCards    the list of cards the client has drawn by performing the
     *                       action referred by the notification
     * @param sectors        the list of sectors to "light" on the GUI after using a light
     * @param availableGames
     * @param gameMapName
     */
    public RRNotification(boolean actionResult,
                          String message, ArrayList<Card> drawnCards, ArrayList<Sector> sectors, ArrayList<GamePublicData> availableGames, PlayerToken playerToken, String gameMapName) {
        this.actionResult = actionResult;
        this.message = message;
        this.drawnCards = drawnCards;
        this.lightedSectors = sectors;
        this.availableGames = availableGames;
        this.playerToken = playerToken;
        this.gameMapName = gameMapName;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    /**
     * Gets the result of the action it refers to
     *
     * @return the result of the action it refers to
     */
    public boolean getActionResult() {
        return actionResult;
    }
    /**
     * Gets the list of cards the client has drawn by performing the action
     * referred by the notification
     *
     * @return the list of cards the client has drawn by performing the action
     * referred by the notification
     */
    public ArrayList<Card> getDrawnCards() {
        return drawnCards;
    }

    /**
     * @return The list of sectors that the client has highlighted during its
     * last move
     */
    public ArrayList<Sector> getLightedSectors() {
        return this.lightedSectors;
    }

    /**
     * Add a new Sector to the list of lighted sectors
     *
     * @param sector the sector to add to the list
     * @throws IllegalArgumentException if sector is null
     */
    public void addSector(Sector sector) {
        this.lightedSectors.add(sector);
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

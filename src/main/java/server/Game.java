package server;

import common.*;
import decks.ObjectDeck;
import decks.RescueDeck;
import decks.SectorDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Represents a generic game.
 *
 */
public class Game {
    // Communication related stuff
    private static int counter = 0;

    private final List<Player> players;
    private ObjectDeck objectDeck;
    private RescueDeck rescueDeck;
    private SectorDeck sectorDeck;
    private Player currentPlayer;
    private Player previousPlayer;
    private int turnNumber;
    private final GamePublicData gamePublicData;
    private final String mapName;
    private GameMap gameMap;
    private StoreAction lastAction;
    private List<String> nextActions;
    private RRNotification lastRRclientNotification;
    private PSNotification lastPSclientNotification;
    private Timer currentTimer;
    private boolean didAlienWin;
    private boolean didHumansWin;
    private final List<PubSubHandler> pubSubHandlers;

    public Game(String gameMapName) {
        counter++;
        this.mapName = gameMapName;
        this.gameMap = null;
        this.players = new ArrayList<>();
        this.gamePublicData = new GamePublicData(counter, "Game_" + counter);
        this.turnNumber = 0;
        this.lastAction = null;
        this.lastRRclientNotification = new RRNotification(false,null,null,null,null,null,null,null);
        this.lastPSclientNotification = new PSNotification(null,null,null,false,false,null, null, false,false,false,false,null);
        this.currentTimer = null;
        this.didHumansWin = false;
        this.didHumansWin = false;
        this.pubSubHandlers = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public ObjectDeck getObjectDeck() {
        return objectDeck;
    }

    public void setObjectDeck(ObjectDeck objectDeck) {
        this.objectDeck = objectDeck;
    }

    public RescueDeck getRescueDeck() {
        return rescueDeck;
    }

    public void setRescueDeck(RescueDeck rescueDeck) {
        this.rescueDeck = rescueDeck;
    }

    public SectorDeck getSectorDeck() {
        return sectorDeck;
    }

    public void setSectorDeck(SectorDeck sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public GamePublicData getGamePublicData() {
        return gamePublicData;
    }

    public String getMapName() {
        return mapName;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public StoreAction getLastAction() {
        return lastAction;
    }

    public void setLastAction(StoreAction lastAction) {
        this.lastAction = lastAction;
    }

    public List<String> getNextActions() {
        return nextActions;
    }

    public void setNextActions(List<String> nextActions) {
        this.nextActions = nextActions;
    }

    public RRNotification getLastRRclientNotification() {
        return lastRRclientNotification;
    }

    public void setLastRRclientNotification(RRNotification lastRRclientNotification) {
        this.lastRRclientNotification = lastRRclientNotification;
    }

    public PSNotification getLastPSclientNotification() {
        return lastPSclientNotification;
    }

    public void setLastPSclientNotification(PSNotification lastPSclientNotification) {
        this.lastPSclientNotification = lastPSclientNotification;
    }

    public Timer getCurrentTimer() {
        return currentTimer;
    }

    public void setCurrentTimer(Timer currentTimer) {
        this.currentTimer = currentTimer;
    }

    public boolean isDidAlienWin() {
        return didAlienWin;
    }

    public void setDidAlienWin(boolean didAlienWin) {
        this.didAlienWin = didAlienWin;
    }

    public boolean isDidHumansWin() {
        return didHumansWin;
    }

    public void setDidHumansWin(boolean didHumansWin) {
        this.didHumansWin = didHumansWin;
    }

    public List<PubSubHandler> getPubSubHandlers() {
        return pubSubHandlers;
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", objectDeck=" + objectDeck +
                ", rescueDeck=" + rescueDeck +
                ", sectorDeck=" + sectorDeck +
                ", currentPlayer=" + currentPlayer +
                ", previousPlayer=" + previousPlayer +
                ", turnNumber=" + turnNumber +
                ", gamePublicData=" + gamePublicData +
                ", mapName='" + mapName + '\'' +
                ", gameMap=" + gameMap +
                ", lastAction=" + lastAction +
                ", nextActions=" + nextActions +
                ", lastRRclientNotification=" + lastRRclientNotification +
                ", lastPSclientNotification=" + lastPSclientNotification +
                ", currentTimer=" + currentTimer +
                ", didAlienWin=" + didAlienWin +
                ", didHumansWin=" + didHumansWin +
                ", pubSubHandlers=" + pubSubHandlers +
                '}';
    }
}

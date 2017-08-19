package effects;

import common.*;
import decks.SectorDeck;
import server.Game;

/**
 * Represents the effect associated to the in game action of drawing a Sector Card
 */
public class DrawSectorCardEffect extends ActionEffect {
    /**
     * Implements the abstract method defined in ActionEffect according to DrawActionFromSector
     *
     * @param game A reference to Decks,map,...
     */
    public static boolean executeEffect(Game game, DrawSectorCardAction action) {
        // Get the reference for the sector deck
        SectorDeck sectorDeck = game.getSectorDeck();
        // Draws a new card from the deck
        SectorCard sectorCard = (SectorCard) sectorDeck.popCard();
        // Notify the client
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(), lastNotification.getMessage(),
                sectorCard, lastNotification.getDrawnObjectCard(), lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(), lastNotification.getAvailableGames(), lastNotification.getPlayerToken()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        String lastMessage = lastNotification.getMessage();
        game.setLastPSclientNotification(new PSNotification(lastMessage + "\n[GLOBAL MESSAGE]: " + game.getCurrentPlayer().getName()
                + " has drawn a sector card", lastPNotification.getDeadPlayers(), lastPNotification.getAttackedPlayers(), lastPNotification.isHumanWin(), lastPNotification.isAlienWin(), lastPNotification.getEscapedPlayer(), lastPNotification.isGameNeedsToStart(), lastPNotification.isTurnNeedsToStart(), lastPNotification.isGameCanBeStarted(), lastPNotification.isTurnNeedsToEnd(), lastPNotification.getGameMapName()));

        sectorDeck.addToDiscard(sectorCard);
        sectorDeck.refill();

        game.setLastAction(action);

        // Now i need to execute the effect of the sector card

        if (sectorCard.isHasObject()) {
            DrawObjectCardEffect.executeEffect(game);
        }
        if (!(sectorCard instanceof GlobalNoiseSectorCard)) {
            game.setLastAction(new UseSectorCardAction(sectorCard));
            UseSectorCardEffect.executeEffect(game, new UseSectorCardAction(sectorCard));
        }
        return true;
    }
}

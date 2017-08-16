package effects;

import common.*;
import decks.SectorDeck;
import server.Game;

import java.util.ArrayList;

/**
 * This class represents the effect associated to a draw action from the sector
 * cards deck
 * 
 * @see ActionEffect
 * @see DrawSectorCardAction
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class DrawSectorCardEffect extends ActionEffect {
	/**
	 * Implements the abstract method defined in ActionEffect according to DrawActionFromSector
	 * @param game A reference to Decks,map,...
	 */
	public static boolean executeEffect(Game game, DrawSectorCardAction action) {
		// Get the reference for the sector deck
		SectorDeck sectorDeck = game.getSectorDeck();
		// Draws a new card from the deck
		SectorCard sectorCard = (SectorCard) sectorDeck.popCard();
		// Notify the client
		RRNotification lastNotification = game.getLastRRclientNotification();
		ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
		drawnCards.add(sectorCard);
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
				drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));

		game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
				+ "\n[GLOBAL MESSAGE]: " + game.getCurrentPlayer().getName()
				+ " has drawn a sector card");

		sectorDeck.addToDiscard(sectorCard);
		sectorDeck.refill();

		game.setLastAction(action);

		// Now i need to execute the effect of the sector card

		if (sectorCard.hasObjectAssociated()) {
			DrawObjectCardEffect.executeEffect(game);
		}
		if (!(sectorCard instanceof GlobalNoiseSectorCard)) {
			game.setLastAction(new UseSectorCardAction(sectorCard));
			UseSectorCardEffect.executeEffect(game,new UseSectorCardAction(sectorCard));
		}
		return true;
	}
}

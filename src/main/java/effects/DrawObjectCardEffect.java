package effects;


import common.Card;
import common.ObjectCard;
import common.RRNotification;
import server.Game;

import java.util.ArrayList;

/**
 * Represents the effect of drawing an object card from the deck containing
 * object cards
 * 
 * @see ActionEffect
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.1
 */
public class DrawObjectCardEffect extends ActionEffect {


	public static boolean executeEffect(Game game) {
		// Get a new card from the correct deck
		ObjectCard objectCard = (ObjectCard) game.getObjectDeck().popCard();
		// Notify the client
		if (objectCard == null) {
			game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
					+ "\n[GLOBAL MESSAGE]: No more object cards");
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(objectCard);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
			game.getCurrentPlayer().getPrivateDeck().addCard(objectCard);
			game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
					+ "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has drawn a object card");
		}
		return true;
	}
}

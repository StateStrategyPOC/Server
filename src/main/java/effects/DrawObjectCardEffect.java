package effects;


import common.ObjectCard;
import common.PSNotification;
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
public class DrawObjectCardEffect {


	public static boolean executeEffect(Game game) {
		// Get a new card from the correct deck
		ObjectCard objectCard = (ObjectCard) game.getObjectDeck().popCard();
		String message = "";
		// Notify the client
		if (objectCard == null) {
			message = "\n[GLOBAL MESSAGE]: No more object cards";
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(objectCard);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards, drawnSectorCard, drawnObjectCard, lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
			game.getCurrentPlayer().getPrivateDeck().addCard(objectCard);
			message = "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has drawn a object card";
		}
        PSNotification lastPNotification = game.getLastPSclientNotification();
		String lastMessage = lastPNotification.getMessage();
        game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

        return true;
	}
}

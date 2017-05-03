package effects;

import common.*;
import decks.ObjectDeck;
import server.Game;

/**
 * Represents the effect of discarding an object card
 *
 */
public class DiscardObjCardEffect extends ActionEffect {
	public static boolean executeEffect(Game game,
										RRClientNotification rrNotification,
										PSClientNotification psNotification, Action action) {
		Player currentPlayer = game.getCurrentPlayer();
		DiscardAction discardAction = (DiscardAction) action;
		ObjectDeck objectDeck = game.getObjectDeck();
		ObjectCard discardedCard = discardAction.getObjectCard();
		currentPlayer.getPrivateDeck().removeCard(discardedCard);
		objectDeck.addToDiscard(discardedCard);
		objectDeck.refill();
		// Notifications setting
		rrNotification.setMessage("You have discarded a "
				+ discardedCard.toString() + " object card");
		psNotification.setMessage("[GLOBAL MESSAGE]: "
				+ currentPlayer.getName() + " has discarded an object card\n");
		//
		game.setLastAction(action);
		return true;
	}
}

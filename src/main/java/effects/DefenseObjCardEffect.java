package effects;

import common.DefenseObjectCard;
import common.ObjectCard;
import server.Game;

/**
 * Represents the effect of a defense card
 * 
 * @see ObjectCardEffect
 * @see DefenseObjectCard
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
class DefenseObjCardEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard card) {
		game.getLastRRclientNotification().setMessage("You've defended from an attack");
		return true;
	}
}

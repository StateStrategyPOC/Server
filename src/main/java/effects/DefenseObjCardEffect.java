package effects;

import common.DefenseObjectCard;
import common.ObjectCard;
import common.RRNotification;
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
public class DefenseObjCardEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard card) {
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You've defended from an attack",lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		return true;
	}
}

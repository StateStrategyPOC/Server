package effects;

import common.ObjectCard;
import common.RRNotification;
import server.Game;

/**
 * Represents the effect of a Defense Object Card
 *
 */
public class DefenseObjCardEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard card) {
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You've defended from an attack",lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(), lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		return true;
	}
}

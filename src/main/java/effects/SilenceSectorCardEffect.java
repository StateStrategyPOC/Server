package effects;


import common.RRNotification;
import common.SectorCard;
import common.SilenceSectorCard;
import server.Game;

/**
 * Represents the effect of silence sector card
 * 
 * @see SectorCardEffect
 * @see SilenceSectorCard
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 *
 */
public class SilenceSectorCardEffect extends SectorCardEffect {


	public static boolean executeEffect(Game game, SectorCard sectorCard) {
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You've said SILENCE",lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
				+ "\n[GLOBAL MESSAGE]: " + game.getCurrentPlayer().getName()
				+ " says SILENCE!");
		return true;
	}

}

package effects;

import common.GlobalNoiseSectorCard;
import common.RRNotification;
import common.Sector;
import common.SectorCard;
import server.Game;

/**
 * Represents the effect associated with a global noise sector card
 * 
 * @see SectorCardEffect
 * @see GlobalNoiseSectorCard
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class GlobalNoiseSectorCardEffect extends SectorCardEffect {
	public static boolean executeEffect(Game game, SectorCard sectorCard) {
		// Notify all the player
		String name = game.getCurrentPlayer().getName();
		Sector target = ((GlobalNoiseSectorCard) sectorCard).getSector();
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You've indicated the sector: "
				+ target.getCoordinate().toString(),lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		game.getLastPSclientNotification().setMessage("[GLOBAL MESSAGE]: "
				+ game.getLastPSclientNotification().getMessage() + name
				+ " has made noise in sector "
				+ target.getCoordinate().toString());
		return true;
	}
}
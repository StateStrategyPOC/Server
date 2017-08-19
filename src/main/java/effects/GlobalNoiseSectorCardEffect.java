package effects;

import common.*;
import server.Game;

/**
 * Represents the effect associated with a Global Noise Sector Card
 *
 */
public class GlobalNoiseSectorCardEffect extends SectorCardEffect {
	public static boolean executeEffect(Game game, SectorCard sectorCard) {
		// Notify all the player
		String name = game.getCurrentPlayer().getName();
		Sector target = ((GlobalNoiseSectorCard) sectorCard).getSector();
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),"You've indicated the sector: "
				+ target.getCoordinate().toString(),lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
		PSNotification lastPNotification = game.getLastPSclientNotification();
		String lastMessage = lastPNotification.getMessage();
		game.setLastPSclientNotification(new PSNotification("[GLOBAL MESSAGE]: "+lastMessage+ " has made noise in sector "
				+ target.getCoordinate().toString(),lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

		return true;
	}
}
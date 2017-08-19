package effects;

import common.PSNotification;
import common.Sector;
import common.SectorCard;
import server.Game;

/**
 * Represents the effect associated with a Local Noise Sector Card
 *
 */
public class LocalNoiseSectorCardEffect extends SectorCardEffect {

	public static boolean executeEffect(Game game, SectorCard card) {
		// The local noise effect could be seen as a global noise effect with a
		// sector that is automatically
		// indicated
		// Notify all the player
		String name = game.getCurrentPlayer().getName();
		Sector target = game.getCurrentPlayer().getCurrentSector();
		PSNotification lastPNotification = game.getLastPSclientNotification();
		String lastMessage = lastPNotification.getMessage();
		game.setLastPSclientNotification(new PSNotification(lastMessage+ "\n[GLOBAL MESSAGE]: " + name
				+ " has made noise in sector "
				+ target.getCoordinate().toString(),lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(), lastPNotification.getEscapingSector(), lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

		return true;
	}
}

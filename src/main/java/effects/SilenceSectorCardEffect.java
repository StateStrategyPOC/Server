package effects;


import common.PSNotification;
import common.RRNotification;
import common.SectorCard;
import server.Game;

/**
 * Represents the effect of a Silence Sector Card
 *
 */
public class SilenceSectorCardEffect extends SectorCardEffect {


	public static boolean executeEffect(Game game, SectorCard sectorCard) {
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),"You've said SILENCE",lastNotification.getDrawnSectorCard(), lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
		PSNotification lastPNotification = game.getLastPSclientNotification();
		String lastMessage = lastPNotification.getMessage();
		game.setLastPSclientNotification(new PSNotification(lastMessage+ "\n[GLOBAL MESSAGE]: " + game.getCurrentPlayer().getName()
				+ " says SILENCE!",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));
		return true;
	}

}

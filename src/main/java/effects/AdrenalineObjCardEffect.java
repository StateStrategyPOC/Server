package effects;


import common.ObjectCard;
import common.PSNotification;
import common.Player;
import common.RRNotification;
import server.Game;

/**
 * Represents the effect of the Adrenaline Object Card
 *
 */
public class AdrenalineObjCardEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard card) {
		Player currentPlayer = game.getCurrentPlayer();
		// Notifications setting
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),"You will moveToSector by two sector this turn\n",lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(), lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        game.setLastPSclientNotification(new PSNotification("[GLOBAL MESSAGE]: "
                + currentPlayer.getName()
                + " has used an adrenaline object card\n",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));
		currentPlayer.setAdrenalined(true);
		return true;
	}
}

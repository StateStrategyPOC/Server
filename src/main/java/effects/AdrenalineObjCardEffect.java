package effects;


import common.ObjectCard;
import common.PSNotification;
import common.Player;
import common.RRNotification;
import server.Game;

/**
 * Represents the effect of the adrenaline object card
 * 
 * @see ObjectCardEffect
 * @see AdrenalineObjCardEffect
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class AdrenalineObjCardEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard card) {
		Player currentPlayer = game.getCurrentPlayer();
		// Notifications setting
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You will moveToSector by two sector this turn\n",lastNotification.getDrawnCards(), drawnSectorCard, drawnObjectCard, lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        game.setLastPSclientNotification(new PSNotification("[GLOBAL MESSAGE]: "
                + currentPlayer.getName()
                + " has used an adrenaline object card\n",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));
		currentPlayer.setAdrenalined(true);
		return true;
	}
}

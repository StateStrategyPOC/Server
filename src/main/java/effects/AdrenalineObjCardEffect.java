package effects;


import common.ObjectCard;
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
        game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You will moveToSector by two sector this turn\n",lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		game.getLastPSclientNotification().setMessage("[GLOBAL MESSAGE]: "
				+ currentPlayer.getName()
				+ " has used an adrenaline object card\n");
		currentPlayer.setAdrenalined(true);
		return true;
	}
}

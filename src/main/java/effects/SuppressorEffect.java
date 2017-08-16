package effects;

import common.ObjectCard;
import common.Player;
import common.RRNotification;
import common.SuppressorObjectCard;
import server.Game;

/**
 * Represents the effect of a suppressor object card
 * 
 * @see ObjectCardEffect
 * @see SuppressorObjectCard
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class SuppressorEffect extends ObjectCardEffect {

	public static boolean executeEffect(Game game, ObjectCard objectCard) {
		Player currentPlayer = game.getCurrentPlayer();
		currentPlayer.setSedated(true);
		RRNotification lastNotification = game.getLastRRclientNotification();
		game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You will not draw any sector card this turn",lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
		game.getLastPSclientNotification()
				.setMessage(game.getLastPSclientNotification().getMessage()
						+ "\n[GLOBAL MESSAGE]: He/she will not draw any sector card this turn");
		return true;
	}
}

package effects;

import common.ObjectCard;
import common.PSClientNotification;
import common.Player;
import common.RRClientNotification;
import server.Game;

/**
 * Represents the effect of the adrenaline object card
 *
 */
public class AdrenalineObjCardEffect extends ObjectCardEffect {
	public static boolean executeEffect(Game game,
										RRClientNotification rrNotification,
										PSClientNotification psNotification, ObjectCard objectCard) {
		Player currentPlayer = game.getCurrentPlayer();
		// Notifications setting
		rrNotification.setMessage("You will move by two sector this turn\n");
		psNotification.setMessage("[GLOBAL MESSAGE]: "
				+ currentPlayer.getName()
				+ " has used an adrenaline object card\n");
		currentPlayer.setAdrenalined(true);
		return true;
	}
}

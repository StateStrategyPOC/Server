package effects;

import common.*;
import server.Game;

import java.util.ArrayList;

/**
 * Represents the effect associated to the action of drawing a rescue card from
 * the deck containing rescue cards
 * 
 * @see ActionEffect
 * @see DrawRescueCardAction
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class DrawRescueCardEffect extends ActionEffect {
	public static boolean executeEffect(Game game) {
		RescueCard card = (RescueCard) game.getRescueDeck().popCard();
		if (card.getType() == RescueType.GREEN) {
			game.getCurrentPlayer().setPlayerState(PlayerState.ESCAPED);
			game.getCurrentPlayer().getCurrentSector()
					.setSectorType(SectorType.CLOSED_RESCUE);
			game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
					+ "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has escaped from aliens!");
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(card);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
			game.getLastPSclientNotification().setEscapedPlayer(game.getCurrentPlayer().getPlayerToken());
			EndTurnEffect.executeEffect(game, new EndTurnAction());
			return true;
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(card);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));			game.getCurrentPlayer().getCurrentSector()
					.setSectorType(SectorType.CLOSED_RESCUE);
			game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage()
					+ "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has not escaped from aliens");
			return false;
		}

	}

}

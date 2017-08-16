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
		String message = "";
        PSNotification lastPNotification = game.getLastPSclientNotification();
        String lastMessage = lastPNotification.getMessage();

        if (card.getType() == RescueType.GREEN) {
			game.getCurrentPlayer().setPlayerState(PlayerState.ESCAPED);
			game.getCurrentPlayer().getCurrentSector()
					.setSectorType(SectorType.CLOSED_RESCUE);
			message = "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has escaped from aliens!";
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(card);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
            game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),game.getCurrentPlayer().getPlayerToken(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

			EndTurnEffect.executeEffect(game, new EndTurnAction());
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			ArrayList<Card> drawnCards = lastNotification.getDrawnCards();
			drawnCards.add(card);
			game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),lastNotification.getMessage(),
					drawnCards,lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));			game.getCurrentPlayer().getCurrentSector()
					.setSectorType(SectorType.CLOSED_RESCUE);
            message = "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has not escaped from aliens";
            game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

        }
        return true;
	}

}

package effects;

import common.*;
import server.Game;

/**
 * Represents the effect associated to the in game action of drawing a Rescue Card
 *
 */
public class DrawRescueCardEffect extends ActionEffect {
	public static boolean executeEffect(Game game) {
		RescueCard card = (RescueCard) game.getRescueDeck().popCard();
		String message;
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
			game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),lastNotification.getMessage(),
					lastNotification.getDrawnSectorCard(), lastNotification.getDrawnObjectCard(), card, lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
            game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),game.getCurrentPlayer().getPlayerToken(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

			EndTurnEffect.executeEffect(game, new EndTurnAction());
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),lastNotification.getMessage(),
					lastNotification.getDrawnSectorCard(), lastNotification.getDrawnObjectCard(), card, lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));game.getCurrentPlayer().getCurrentSector()
					.setSectorType(SectorType.CLOSED_RESCUE);
            message = "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has not escaped from aliens";
            game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

        }
        return true;
	}

}

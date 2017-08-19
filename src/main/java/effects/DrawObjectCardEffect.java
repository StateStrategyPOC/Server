package effects;


import common.ObjectCard;
import common.PSNotification;
import common.RRNotification;
import server.Game;

/**
 * Represents the effect of the in game action of drawing an Object Card
 *
 */
public class DrawObjectCardEffect extends ActionEffect {


	public static boolean executeEffect(Game game) {
		// Get a new card from the correct deck
		ObjectCard objectCard = (ObjectCard) game.getObjectDeck().popCard();
		String message = "";
		// Notify the client
		if (objectCard == null) {
			message = "\n[GLOBAL MESSAGE]: No more object cards";
		} else {
			RRNotification lastNotification = game.getLastRRclientNotification();
			game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),lastNotification.getMessage(),
					lastNotification.getDrawnSectorCard(), objectCard, lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
			game.getCurrentPlayer().getPrivateDeck().addCard(objectCard);
			message = "\n[GLOBAL MESSAGE]: "
					+ game.getCurrentPlayer().getName()
					+ " has drawn a object card";
		}
        PSNotification lastPNotification = game.getLastPSclientNotification();
		String lastMessage = lastPNotification.getMessage();
        game.setLastPSclientNotification(new PSNotification(lastMessage+message,lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

        return true;
	}
}

package effects;

import common.*;
import server.Game;

/**
 * Represents the effect of a Suppressor Object Card
 */
public class SuppressorEffect extends ObjectCardEffect {

    public static boolean executeEffect(Game game, ObjectCard objectCard) {
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.setSedated(true);
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(), "You will not draw any sector card this turn", lastNotification.getDrawnSectorCard(), lastNotification.getDrawnObjectCard(), lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(), lastNotification.getAvailableGames(), lastNotification.getPlayerToken(), lastNotification.getGameMapName()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        String lastMessage = lastPNotification.getMessage();
        game.setLastPSclientNotification(new PSNotification(lastMessage + "\n[GLOBAL MESSAGE]: He/she will not draw any sector card this turn", lastPNotification.getDeadPlayers(), lastPNotification.getAttackedPlayers(), lastPNotification.isHumanWin(), lastPNotification.isAlienWin(), lastPNotification.getEscapedPlayer(), lastPNotification.isGameNeedsToStart(), lastPNotification.isTurnNeedsToStart(), lastPNotification.isGameCanBeStarted(), lastPNotification.isTurnNeedsToEnd(), lastPNotification.getGameMapName()));
        return true;
    }
}

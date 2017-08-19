package effects;

import common.*;
import server.Game;

/**
 * Represents the effect associated to the in game action of ending a turn.
 *
 */

public class EndTurnEffect extends ActionEffect {


    public static boolean executeEffect(Game game, StoreAction action) {
        game.getCurrentPlayer().setAdrenalined(false);
        game.getCurrentPlayer().setSedated(false);
        game.getCurrentPlayer().setHasMoved(false);
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),"\nYou have ended your turn",
                lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
        String message = "\n[GLOBAL MESSAGE]: "
                + game.getCurrentPlayer().getName()
                + " has ended its turn.\n[GLOBAL MESSAGE]: ";
        game.setPreviousPlayer(game.getCurrentPlayer());
        shiftCurrentPlayer(game);
        PSNotification lastPNotification = game.getLastPSclientNotification();
        game.setLastPSclientNotification(new PSNotification(message+game.getCurrentPlayer().getName()+" now is your turn",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(), lastPNotification.getEscapingSector(), lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));
        // Notify the client
        game.setLastAction(action);
        return true;
    }

    private static void shiftCurrentPlayer(Game game) {
        int size = game.getPlayers().size();
        int index = 0;
        while (index != size){
            Player current = game.getPlayers().get(index);
            if (current.equals(game.getCurrentPlayer())){
                if ( index == size - 1){
                    index = 0;
                    break;
                }
                index++;
                break;
            }
            index++;
        }
        Player player = game.getPlayers().get(index);
        if (player.getPlayerState() == PlayerState.ALIVE){
            game.setCurrentPlayer(player);
        }
    }

}

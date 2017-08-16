package effects;

import common.*;
import server.Game;

import java.util.ArrayList;

/**
 * Represents the effect associated to the action of ending a turn.
 *
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.1
 * @see ActionEffect
 * @see EndTurnAction
 */

public class EndTurnEffect extends ActionEffect {


    public static boolean executeEffect(Game game, StoreAction action) {
        game.getCurrentPlayer().setAdrenalined(false);
        game.getCurrentPlayer().setSedated(false);
        game.getCurrentPlayer().setHasMoved(false);
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"\nYou have ended your turn",
                lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
        game.getLastPSclientNotification().setMessage("\n[GLOBAL MESSAGE]: "
                + game.getCurrentPlayer().getName()
                + " has ended its turn.\n[GLOBAL MESSAGE]: ");
        game.setPreviousPlayer(game.getCurrentPlayer());
        shiftCurrentPlayer(game);
        game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage() + game.getCurrentPlayer().getName() + " now is your turn");
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
        game.setCurrentPlayer(game.getPlayers().get(index));
    }

}

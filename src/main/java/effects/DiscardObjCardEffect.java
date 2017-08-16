package effects;

import common.*;
import decks.ObjectDeck;
import server.Game;

/**
 * Represents the effect of discarding an object card
 *
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.2
 * @see ActionEffect
 * @see DiscardAction
 */
public class DiscardObjCardEffect extends ActionEffect {
    public static boolean executeEffect(Game game, StoreAction action) {
        DiscardAction castedAction = (DiscardAction) action;
        Player currentPlayer = game.getCurrentPlayer();
        ObjectDeck objectDeck = game.getObjectDeck();
        ObjectCard discardedCard = castedAction.cardToDiscard;
        currentPlayer.getPrivateDeck().removeCard(discardedCard);
        objectDeck.addToDiscard(discardedCard);
        objectDeck.refill();
        // Notifications setting
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(), "You have discarded a "
                + discardedCard.toString() + " object card", lastNotification.getDrawnCards(), lastNotification.getLightedSectors(), lastNotification.getAvailableGames(), lastNotification.getPlayerToken(), lastNotification.getGameMapName()));
        game.getLastPSclientNotification().setMessage("[GLOBAL MESSAGE]: "
                + currentPlayer.getName() + " has discarded an object card\n");
        //
        game.setLastAction(castedAction);
        return true;
    }
}

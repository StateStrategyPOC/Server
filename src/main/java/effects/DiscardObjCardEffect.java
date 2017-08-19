package effects;

import common.*;
import decks.ObjectDeck;
import server.Game;

/**
 * Represents the effect of the in game action of discarding an Object Card
 *
 */
public class DiscardObjCardEffect extends ActionEffect {
    public static boolean executeEffect(Game game, StoreAction action) {
        DiscardAction castedAction = (DiscardAction) action;
        Player currentPlayer = game.getCurrentPlayer();
        ObjectDeck objectDeck = game.getObjectDeck();
        ObjectCard discardedCard = castedAction.getCardToDiscard();
        currentPlayer.getPrivateDeck().removeCard(discardedCard);
        objectDeck.addToDiscard(discardedCard);
        objectDeck.refill();
        // Notifications setting
        RRNotification lastNotification = game.getLastRRclientNotification();
        game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(), "You have discarded a "
                + discardedCard.toString() + " object card", lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(), lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(), lastNotification.getAvailableGames(), lastNotification.getPlayerToken()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        game.setLastPSclientNotification(new PSNotification("[GLOBAL MESSAGE]: "
                + currentPlayer.getName() + " has discarded an object card\n",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

        //
        game.setLastAction(castedAction);
        return true;
    }
}

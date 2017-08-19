package effects;

import common.*;
import server.Game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the effect associated with the in game action of using an Object Card
 *
 */
public class UseObjCardEffect extends ActionEffect {

    private static final List<Class<? extends ObjectCard>> beforeMoveCards = new ArrayList<>();
    private static final List<Class<? extends ObjectCard>> afterMoveCards = new ArrayList<>();

    private static void produceUtilsDataStructure() {
        beforeMoveCards.add(AdrenalineObjectCard.class);
        beforeMoveCards.add(SuppressorObjectCard.class);
        beforeMoveCards.add(LightsObjectCard.class);
        beforeMoveCards.add(TeleportObjectCard.class);
        beforeMoveCards.add(DefenseObjectCard.class);
        beforeMoveCards.add(AttackObjectCard.class);

        afterMoveCards.add(LightsObjectCard.class);
        afterMoveCards.add(TeleportObjectCard.class);
    }


    public static boolean executeEffect(Game game, StoreAction action) {
        UseObjAction castedAction = (UseObjAction) action;
        if (beforeMoveCards.size() == 0 && afterMoveCards.size() == 0) {
            produceUtilsDataStructure();
        }
        //PSNotification psNotification = new PSNotification(message, deadPlayers, attackedPlayers, humanWins, alienWins, escapedPlayer, gameNeedToStart, gameId, turnNeedToStart, gameCanBeStarted, turnNeedToEnd, gameMapName);
        // Checks if the card can be played before moveToSector or after moveToSector
        if (!game.getCurrentPlayer().isHasMoved()) {
            if (!beforeMoveCards.contains(castedAction.getObjectCard().getClass()))
                return false;
        } else {
            if (!afterMoveCards.contains(castedAction.getObjectCard().getClass()))
                return false;
        }

        try {
            RRNotification lastNotification = game.getLastRRclientNotification();
            game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),"You have used a "
                    + castedAction.getObjectCard().toString(),lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));
            String message = "[GLOBAL MESSAGE]: "+ game.getCurrentPlayer().getName() + " has used a "
                    + castedAction.getObjectCard().toString();
            PSNotification psNotification = new PSNotification(message, null,null,false,false,null,false,false,false,false,null);
            game.getObjectDeck().addToDiscard(castedAction.getObjectCard());
            game.getCurrentPlayer().getPrivateDeck()
                    .removeCard(castedAction.getObjectCard());
            game.setLastAction(action);
            game.setLastPSclientNotification(psNotification);
            Method executeMethod = ObjectCardsMapper.getInstance().getEffect(castedAction.getObjectCard()).getMethod("executeEffect", Game.class, ObjectCard.class);
            return (boolean) executeMethod.invoke(null,game, castedAction.getObjectCard());

        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

}

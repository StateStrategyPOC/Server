package effects;

import common.*;
import server.Game;

/**
 * Represents the effect associated to the action of drawing a rescue card from
 * the deck containing rescue cards
 *
 */
public class DrawRescueCardEffect extends ActionEffect {

    public static boolean executeEffect(Game game,
                                        RRClientNotification rrNotification,
                                        PSClientNotification psNotification) {
        RescueCard card = (RescueCard) game.getRescueDeck().popCard();
        Player currentPlayer = game.getCurrentPlayer();
        if (card.getType() == RescueType.GREEN) {
            currentPlayer.setPlayerState(PlayerState.ESCAPED);
            currentPlayer.getCurrentSector()
                    .setSectorType(SectorType.CLOSED_RESCUE);
            psNotification.setMessage(psNotification.getMessage()
                    + "\n[GLOBAL MESSAGE]: "
                    + game.getCurrentPlayer().getName()
                    + " has escaped from aliens!");
            rrNotification.addCard(card);
            psNotification.setEscapedPlayer(currentPlayer.getPlayerToken());
            EndTurnEffect.executeEffect(game, rrNotification, psNotification, new EndTurnAction());
            return true;
        } else {
            rrNotification.addCard(card);
            currentPlayer.getCurrentSector()
                    .setSectorType(SectorType.CLOSED_RESCUE);
            psNotification.setMessage(psNotification.getMessage()
                    + "\n[GLOBAL MESSAGE]: "
                    + game.getCurrentPlayer().getName()
                    + " has not escaped from aliens");
            return false;
        }

    }

}

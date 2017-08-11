package common;

/**
 * Represents the action of discarding an object card in the game
 * 
 * @see Action
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class DiscardAction extends StoreAction {

    public final ObjectCard cardToDiscard;

    public DiscardAction(ObjectCard objectCard) {
        super("@GAMEACTION_DISCARD_OBJ_CARD","@COMMON_GROUP");
        this.cardToDiscard = objectCard;
    }

    public ObjectCard getCardToDiscard() {
        return cardToDiscard;
    }
}

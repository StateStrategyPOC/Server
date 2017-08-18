package common;

/**
 * Represents the in game action of discarding an Object Card
 *
 */
public class DiscardAction extends StoreAction {

    private final ObjectCard cardToDiscard;

    public DiscardAction(ObjectCard objectCard) {
        super("@GAMEACTION_DISCARD_OBJ_CARD","@COMMON_GROUP");
        this.cardToDiscard = objectCard;
    }

    public ObjectCard getCardToDiscard() {
        return cardToDiscard;
    }
}

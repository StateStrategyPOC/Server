package common;

/**
 * Represents the in game action of using an Object Card
 *
 */
public class UseObjAction extends StoreAction {

    private final ObjectCard objectCard;

    public UseObjAction(ObjectCard objectCard) {
        super("@GAMEACTION_USE_OBJ_CARD","@COMMON_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}

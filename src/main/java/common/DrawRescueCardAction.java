package common;

/**
 * Represents the action of drawing a Rescue Card from the Rescue Cards deck
 */
public class DrawRescueCardAction extends StoreAction {

    public DrawRescueCardAction() {
        super("@GAMEACTION_DRAW_RESCUE_CARD", "@COMMON_GROUP");
    }
}

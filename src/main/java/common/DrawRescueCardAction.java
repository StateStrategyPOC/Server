package common;

/**
 * Represents the action of drawing a rescue card from the rescue card deck
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 *
 */
class DrawRescueCardAction extends StoreAction {

	public DrawRescueCardAction() {
        super("@GAMEACTION_DRAW_RESCUE_CARD","@COMMON_GROUP");
	}
}

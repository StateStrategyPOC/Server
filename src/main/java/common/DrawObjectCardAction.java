package common;

import server_store.StoreAction;

/**
 * Represents the action of drawing an object card from the object card deck
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
class DrawObjectCardAction extends StoreAction {

	public DrawObjectCardAction() {
        super("@GAMEACTION_DRAW_OBJ_CARD");
	}
}

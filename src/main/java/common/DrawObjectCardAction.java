package common;

/**
 * Represents the action of drawing an object card from the object card deck
 *
 */
public class DrawObjectCardAction extends Action {
	// A field used only for serialization purposes
	private static final long serialVersionUID = 1L;

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "DrawActionFromObject []";
	}
}

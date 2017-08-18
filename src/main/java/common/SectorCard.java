package common;

import java.io.Serializable;

/**
 * Represents a generic Sector Card in the game
 */
public abstract class SectorCard implements Serializable {

	// Indicates if the Sector Card is associated with an Object Card
	protected final boolean hasObject;

	public SectorCard(boolean hasObject) {
		this.hasObject = hasObject;
	}


	public boolean isHasObject() {
		return hasObject;
	}
}

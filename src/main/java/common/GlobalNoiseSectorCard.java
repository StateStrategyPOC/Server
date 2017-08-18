package common;

/**
 * Represents a global noise sector card
 *
 *
 */
public class GlobalNoiseSectorCard extends SectorCard {
	// Represents the sector of noise
	private final Sector sector;

	public GlobalNoiseSectorCard(boolean hasObject, Sector sector) {
		super(hasObject);
		this.sector = sector;
	}

	public Sector getSector() {
		return sector;
	}


	public String toString() {
		return "GlobalNoiseSectorCard";
	}
}
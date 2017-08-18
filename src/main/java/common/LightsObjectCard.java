package common;

/**
 * Represents a Lights Object Card
 *
 */
public class LightsObjectCard extends ObjectCard {
	// The target of the lights effect
	private final Sector centralSector;


	public LightsObjectCard(Sector centralSector) {
		this.centralSector = centralSector;
	}

	public Sector getTarget() {
		return this.centralSector;
	}

	@Override
	public String toString() {
		return "LightObjectCard";
	}
}

package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sector in the game
 *
 */
public class Sector implements Serializable {
	// The coordinate of the sector
	private final Coordinate coordinate;
	// The sector type(Safe, Dangerous,...)
	private SectorType sectorType;
	// The Legality of the sector(only for human, only for alien, ...)
	private SectorLegality legality;
	// The list of player in this sector
	private final ArrayList<Player> players;
	private boolean hasBeenChecked;

	public boolean isHasBeenChecked() {
		return hasBeenChecked;
	}

	public void setHasBeenChecked(boolean hasBeenChecked) {
		this.hasBeenChecked = hasBeenChecked;
	}

	/**
	 * Constructs a sector from a coordinate and from and a sector type.The
	 * sector's legality is inferred from its type, and an empty set of players
	 * is associated to the sector as well
	 * 
	 * @param coordinate
	 *            the coordinate of the sector
	 * @param sectorType
	 *            the type of sector
	 * @see SectorType
	 * @see SectorLegality
	 */
	public Sector(Coordinate coordinate, SectorType sectorType) {
		this.coordinate = coordinate;
		this.sectorType = sectorType;
		this.hasBeenChecked = false;
		players = new ArrayList<>();

		switch (this.sectorType.toString()) {
		case "DANGEROUS":
			this.legality = SectorLegality.ALL;
			break;
		case "SAFE":
			this.legality = SectorLegality.ALL;
			break;
		case "OPEN_RESCUE":
			this.legality = SectorLegality.HUMAN;
			break;
		case "CLOSED_RESCUE":
			this.legality = SectorLegality.HUMAN;
			break;
		case "HUMAN":
			this.legality = SectorLegality.NONE;
			break;
		case "ALIEN":
			this.legality = SectorLegality.NONE;
			break;

		}
	}


	public Coordinate getCoordinate() {
		return coordinate;
	}

	public SectorType getSectorType() {
		return this.sectorType;
	}

	public void setSectorType(SectorType sectorType) {
		this.sectorType = sectorType;
	}


	public SectorLegality getSectorLegality() {
		return this.legality;
	}


	public List<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Adds a new player to the sector
	 * 
	 * @param player
	 *            the player to be added to the sector
	 * @throws IllegalArgumentException
	 *             if player is null
	 */
	public void addPlayer(Player player) {
		if (player == null)
			throw new IllegalArgumentException("player must not be null");
		this.players.add(player);
	}

	/**
	 * Removes a player from the sector
	 * 
	 * @param player
	 *            the player to be removed from the sector
	 * @throws IllegalArgumentException
	 *             if player is null, or the player is not in this sector
	 */
	public void removePlayer(Player player) {
		if (!this.players.remove(player))
			throw new IllegalArgumentException();
	}

	/**
	 * @return True if the specified player is in this sector
	 */
	public boolean containsPlayer(Player player) {
		return this.players.contains(player);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sector other = (Sector) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sector [coordinate=" + coordinate + ", sectorType="
				+ sectorType + ", legality=" + legality + "]";
	}

}

package common;

import java.io.Serializable;

/**
 * Represents a generic coordinate in the game. In the game a generic coordinate
 * is a pair: character(x), integer(y)
 *
 */

public class Coordinate implements Serializable {
	private final char x;
	private final int y;

	public Coordinate(char x, int y) {
		this.x = x;
		this.y = y;
	}

	public char getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return x + "" + y;
	}

}

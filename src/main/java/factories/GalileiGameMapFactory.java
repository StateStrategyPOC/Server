package factories;

import common.GameMap;

import java.io.File;

/**
 * Represents a factory of Galilei game maps
 *
 */
public class GalileiGameMapFactory extends GameMapFactory {

	@Override
	public GameMap makeMap() {
		return new GameMap(this.makeGraph(new File("Galileo_map.txt")), 97, 1,
				23, 14, "GALILEI");
	}
}

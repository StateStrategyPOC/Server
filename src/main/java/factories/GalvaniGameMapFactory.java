package factories;


import common.GameMap;

import java.io.File;

/**
 * Represents a factory of Galvani game maps
 *
 */
public class GalvaniGameMapFactory extends GameMapFactory {

	@Override
	public GameMap makeMap() {
		return new GameMap(this.makeGraph(new File("maps/Galvani_map.txt")), 97, 1,
				23, 14, "GALVANI");
	}

}

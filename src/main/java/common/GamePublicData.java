package common;

import java.io.Serializable;

/**
 * Represents a container of public data concerning a game
 *
 */
public class GamePublicData implements Serializable {
	// A field automatically created for serialization purposes
	private static final long serialVersionUID = 1L;
	// The id of the game
	private final int id;
	// The name of the game
	private final String name;
	// The number of players in the game
	private int numberOfPlayers;
	// Status of the game
	private GameStatus status;

	public GamePublicData(int id, String name) {
		this.id = id;
		this.name = name;
		this.numberOfPlayers = 0;
		this.status = GameStatus.OPEN;
	}

	/**
	 * Increases the number of players of the game this container refers to
	 */
	public void addPlayer() {
		this.numberOfPlayers++;
	}

	public int getId() {
		return id;
	}


	public GameStatus getStatus() {
		return status;
	}


	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public int getPlayersCount() {
		return this.numberOfPlayers;
	}

	@Override
	public String toString() {
		return "[name= " + name + ", numberOfPlayers= " + numberOfPlayers
				+ ", status= " + status.toString() + " ]";
	}

}

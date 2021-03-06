package decks;

import common.Card;

import java.util.List;

/**
 * Represents a generic deck in the game
 *
 */
public abstract class Deck {
	// The set of cards contained in the deck
	final List<Card> content;

	/**
	 * Constructs a deck from a set of cards
	 * 
	 * @param content
	 *            the set of cards to insert in the deck
	 */
	Deck(List<Card> content) {
		this.content = content;
	}

	/**
	 * Checks if the deck is empty
	 *
	 * @return true if the deck is empty, otherwise false
	 */
	public boolean isEmpty() {
		return this.content.isEmpty();
	}


	public List<Card> getContent() {
		return this.content;
	}

	/**
	 * Pops a card from the top of the deck(LIFO strategy)
	 *
	 * @return the card popped from the top of the deck
	 */
	public abstract Card popCard();

}

package decks;

import common.Card;

import java.util.List;

/**
 * Represents a deck containing rescue cards
 *
 */
public class RescueDeck extends Deck {
	/**
	 * Constructs a deck containing rescue cards from a set of rescue cards
	 * 
	 * @param content
	 *            the set of cards to insert in the object deck
	 */
	public RescueDeck(List<Card> content) {
		super(content);
	}

	@Override
	public Card popCard() {
		if (!this.content.isEmpty()) {
			Card card = this.content.get(0);
			this.content.remove(0);
			return card;
		}
		return null;

	}
}

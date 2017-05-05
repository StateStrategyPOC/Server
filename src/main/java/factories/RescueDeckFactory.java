package factories;

import common.Card;
import common.RescueCard;
import common.RescueType;
import decks.Deck;
import decks.RescueDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a factory of rescue card's decks
 *
 */
public class RescueDeckFactory extends DeckFactory {

	@Override
	public Deck makeDeck() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 3; i++) {
			deck.add(new RescueCard(RescueType.GREEN));
		}
		for (int i = 0; i < 3; i++) {
			deck.add(new RescueCard(RescueType.RED));
		}
		return new RescueDeck(deck);
	}

}

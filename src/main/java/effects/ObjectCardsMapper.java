package effects;

import common.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the entity that maps an object card with its effect. This entity
 * has been defined due to the fact that the client exchanges with the server
 * plain object cards that don't embed any logic, so an association has to be
 * made between these objects and their logic/effects.
 *
 */
public class ObjectCardsMapper {
	private final Map<Class<? extends ObjectCard>, Class<? extends ObjectCardEffect>> fromObjectCardToObjectCardEffect;
	private static ObjectCardsMapper instance;

	public static ObjectCardsMapper getInstance(){
		if (instance == null){
			instance = new ObjectCardsMapper();
		}
		return instance;
	}

	/**
	 * Constructs a object card - object card effect mapper. This mapper is
	 * implemented as an hash map that maps object cards class types to object
	 * cards effect class types, then, using reflection, from an object card is
	 * possible to get an actual object card effect object
	 */
	private ObjectCardsMapper() {
		fromObjectCardToObjectCardEffect = new HashMap<>();
		fromObjectCardToObjectCardEffect.put(TeleportObjectCard.class,
				TeleportObjCardEffect.class);
		fromObjectCardToObjectCardEffect.put(AttackObjectCard.class,
				AttackObjCardEffect.class);
		fromObjectCardToObjectCardEffect.put(LightsObjectCard.class,
				LightsObjectCardEffect.class);
		fromObjectCardToObjectCardEffect.put(SuppressorObjectCard.class,
				SuppressorEffect.class);
		fromObjectCardToObjectCardEffect.put(AdrenalineObjectCard.class,
				AdrenalineObjCardEffect.class);
		fromObjectCardToObjectCardEffect.put(DefenseObjectCard.class,
				DefenseObjCardEffect.class);
	}

	/**
	 * Produces the object card effect mapped to an object card
	 * 
	 * @param objectCard
	 *            the object card for which retrieve the effect
	 * @return the effect associated with the object card
	 */
	public Class<? extends ObjectCardEffect> getEffect(ObjectCard objectCard) {
		return this.fromObjectCardToObjectCardEffect.get(objectCard.getClass());
	}

}
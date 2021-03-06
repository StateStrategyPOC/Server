package effects;

import common.GlobalNoiseSectorCard;
import common.LocalNoiseSectorCard;
import common.SectorCard;
import common.SilenceSectorCard;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the entity that maps a sector card with its effect. This entity
 * has been defined due to the fact that the client exchanges with the server
 * plain sector cards that don't embed any logic, so an association has to be
 * made between these objects and their logic/effects.
 *
 */
public class SectorCardsMapper {
    private final Map<Class<? extends SectorCard>, Class<? extends SectorCardEffect>> fromSectorToSectorEffect;
    private static final SectorCardsMapper instance = new SectorCardsMapper();

    public static SectorCardsMapper getInstance() {
        return instance;
    }

    /**
     * Constructs a sector card - effect mapper. This mapper is implemented as
     * an hash map that maps sector card class types to sector card effect class
     * types, then, using reflection, from a sector card is possible to get an
     * actual sector card effect object
     */
    private SectorCardsMapper() {
        fromSectorToSectorEffect = new HashMap<>();
        fromSectorToSectorEffect.put(LocalNoiseSectorCard.class,
                LocalNoiseSectorCardEffect.class);
        fromSectorToSectorEffect.put(GlobalNoiseSectorCard.class,
                GlobalNoiseSectorCardEffect.class);
        fromSectorToSectorEffect.put(SilenceSectorCard.class,
                SilenceSectorCardEffect.class);
    }

    /**
     * Produces the sector card effect mapped to a sector card
     *
     * @param sectorCard the sector card for which retrieve the effect
     * @return the effect associated with the sector card
     */
    public Class<? extends SectorCardEffect> getEffect(SectorCard sectorCard) {
        return fromSectorToSectorEffect.get(sectorCard.getClass());
    }
}

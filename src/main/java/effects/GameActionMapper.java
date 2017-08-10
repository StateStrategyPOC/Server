package effects;

import common.*;
import common.StoreAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the entity that maps an action with its effect. This entity has
 * been defined due to the fact that the client exchanges with the server plain
 * action objects that don't embed any logic, so an association has to be made
 * between these objects and their logic/effects.
 * 
 * @see Action
 * @see ActionEffect
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class GameActionMapper {

	private static final GameActionMapper instance = new GameActionMapper();

    private final Map<Class<? extends StoreAction>, Class<? extends ActionEffect>> fromActionToActionEffect;

    public static GameActionMapper getInstance(){
        return instance;
    }
	// The mapper


	/**
	 * Constructs a action-effect mapper. This mapper is implemented as an hash
	 * map that maps action class types to action-effect class types, then,
	 * using reflection, from an action is possible to get an actual
	 * action-effect object
	 */
	private GameActionMapper() {
		// Mapper init
		fromActionToActionEffect = new HashMap<>();
		// Mapper filling
		fromActionToActionEffect.put(MoveAction.class, MoveActionEffect.class);
		fromActionToActionEffect.put(DrawSectorCardAction.class,
				DrawSectorCardEffect.class);
		fromActionToActionEffect.put(DrawObjectCardAction.class,
				DrawObjectCardEffect.class);
		fromActionToActionEffect.put(DiscardAction.class,
				DiscardObjCardEffect.class);
		fromActionToActionEffect
				.put(UseObjAction.class, UseObjCardEffect.class);
		fromActionToActionEffect.put(MoveAttackAction.class,
				MoveAttackActionEffect.class);
		fromActionToActionEffect.put(EndTurnAction.class, EndTurnEffect.class);
		fromActionToActionEffect.put(UseSectorCardAction.class,
				UseSectorCardEffect.class);
	}


	public Class<? extends ActionEffect> getEffect(Class<? extends StoreAction> actionType){
		return fromActionToActionEffect.get(actionType);
	}

}
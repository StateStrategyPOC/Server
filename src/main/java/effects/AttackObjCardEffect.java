package effects;

import common.AttackObjectCard;
import common.MoveAttackAction;
import common.ObjectCard;
import common.Sector;
import server.Game;

/**
 * Represents the effect of the Attack Object Card
 *
 */
public class AttackObjCardEffect extends ObjectCardEffect {
	public static boolean executeEffect(Game game, ObjectCard objectCard) {
		Sector sectorToAttack = ((AttackObjectCard) objectCard).getAttackTarget();
		// Executing an attack object card action effect is like executing a
		// move and attack action effect
		return MoveAttackActionEffect.executeEffect(game,new MoveAttackAction(sectorToAttack));
	}
}

package common;

/**
 * Represents an attack object card in the game
 *
 */
public class AttackObjectCard extends ObjectCard {
	//Sector to attack
	private final Sector attackTarget;

	public AttackObjectCard(Sector attackTarget) {
		this.attackTarget = attackTarget;
	}

	public Sector getAttackTarget() {
		return this.attackTarget;
	}

	@Override
	public String toString() {
		return "AttackObjectCard";
	}
}

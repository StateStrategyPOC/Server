package common;

/**
 * Represents the in game action of moving to a Sector and attack whoever is in there
 *
 */
public class MoveAttackAction extends StoreAction {

    private final Sector targetSector;

	public MoveAttackAction(Sector target) {
        super("@GAMEACTION_MOVE_ATTACK","@COMMON_GROUP");
        this.targetSector= target;
	}

    public Sector getTargetSector() {
        return targetSector;
    }
}

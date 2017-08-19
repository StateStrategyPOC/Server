package common;

/**
 * Represents the in game action of moving to a Sector
 *
 */
public class MoveAction extends StoreAction {

    private final Sector targetSector;

    public MoveAction(Sector target) {
        super("@GAMEACTION_MOVE","@COMMON_GROUP");
        this.targetSector = target;
    }

    public Sector getTargetSector() {
        return targetSector;
    }

}

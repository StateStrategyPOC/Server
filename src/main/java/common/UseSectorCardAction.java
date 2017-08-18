package common;

/**
 * Represents the in game action of using a Sector Card
 *
 */
public class UseSectorCardAction extends StoreAction {

    private final SectorCard sectorCard;

    public UseSectorCardAction(SectorCard sectorCard) {
        super("@GAMEACTION_USE_SECTOR_CARD","@COMMON_GROUP");
        this.sectorCard = sectorCard;
    }

    public SectorCard getSectorCard() {
        return sectorCard;
    }
}

package common;

/**
 * Represents the action of using a sector card in the game
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
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

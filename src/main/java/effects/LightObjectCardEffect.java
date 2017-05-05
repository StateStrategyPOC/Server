package effects;

import common.LightsObjectCard;
import common.ObjectCard;
import common.Player;
import common.Sector;
import server.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the effect of a lights effect
 * 
 * @see ObjectCardEffect
 * @see LightsObjectCard
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.1
 */
public class LightObjectCardEffect extends ObjectCardEffect {
	public static boolean executeEffect(Game game, ObjectCard objectCard) {
		LightsObjectCard castedObjectCard = (LightsObjectCard) objectCard;
		Sector targetSector = castedObjectCard.getTarget();
		List<Sector> neighboorSectors = game.getGameMap().getSearchableGraph()
				.neighborListOf(targetSector);
		neighboorSectors.add(targetSector);
		List<Sector> incriminatedSectors = new ArrayList<>();
		String globalMessage = "\n[GLOBAL MESSAGE]: Players spotted:";
        String msg = "";
		for (Sector sector : neighboorSectors) {
			if (!sector.getPlayers().isEmpty()){
                incriminatedSectors.add(sector);
                game.getLastRRclientNotification().addSector(sector);
            }
		}
		for (Sector sector : incriminatedSectors){
            for (Player player : sector.getPlayers()){
                if (msg.equals("")){
                    msg += player.getName() + " in sector "+sector.getCoordinate().toString();
                }
                else {
                    msg += ", "+player.getName() + " in sector "+sector.getCoordinate().toString();
                }

            }
        }
		if ( msg.equals("")){
            msg = "none";
        }
        game.getLastRRclientNotification().setMessage(game.getLastRRclientNotification().getMessage()+". Players spotted: "+msg);
		game.getLastPSclientNotification().setMessage(game.getLastPSclientNotification().getMessage() + globalMessage+msg);
		return true;
	}
}

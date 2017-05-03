package effects;

import common.*;
import server.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the effect of a lights effect
 *
 */
public class LightsObjectCardEffect extends ObjectCardEffect {
	public static boolean executeEffect(Game game,
										RRClientNotification rrNotification,
										PSClientNotification psNotification, ObjectCard objectCard) {
		LightsObjectCard castedObjectCard = (LightsObjectCard) objectCard;
		Sector targetSector = castedObjectCard.getTarget();
		List<Sector> neighboorSectors = game.getMap().getSearchableGraph()
				.neighborListOf(targetSector);
		neighboorSectors.add(targetSector);
		List<Sector> incriminatedSectors = new ArrayList<>();
		String globalMessage = "\n[GLOBAL MESSAGE]: Players spotted:";
		String msg = "";
		for (Sector sector : neighboorSectors) {
			if (!sector.getPlayers().isEmpty()){
				incriminatedSectors.add(sector);
				rrNotification.addSector(sector);
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
		rrNotification.setMessage(rrNotification.getMessage()+". Players spotted: "+msg);
		psNotification.setMessage(psNotification.getMessage() + globalMessage+msg);
		return true;
	}
}

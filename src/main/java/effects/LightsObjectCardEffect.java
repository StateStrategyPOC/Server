package effects;

import common.*;
import server.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the effect of a lights effect
 *
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.1
 * @see ObjectCardEffect
 * @see LightsObjectCard
 */
public class LightsObjectCardEffect extends ObjectCardEffect {
    public static boolean executeEffect(Game game, ObjectCard objectCard) {
        LightsObjectCard castedObjectCard = (LightsObjectCard) objectCard;
        Sector targetSector = castedObjectCard.getTarget();
        List<Sector> neighboorSectors = game.getGameMap().getSearchableGraph()
                .neighborListOf(targetSector);
        neighboorSectors.add(targetSector);
        ArrayList<Sector> incriminatedSectors = new ArrayList<>();
        String globalMessage = "\n[GLOBAL MESSAGE]: Players spotted:";
        StringBuilder msg = new StringBuilder();
        for (Sector sector : neighboorSectors) {
            if (!sector.getPlayers().isEmpty()) {
                incriminatedSectors.add(sector);
            }
        }
        for (Sector sector : incriminatedSectors) {
            for (Player player : sector.getPlayers()) {
                if (msg.toString().equals("")) {
                    msg.append(player.getName()).append(" in sector ").append(sector.getCoordinate().toString());
                } else {
                    msg.append(", ").append(player.getName()).append(" in sector ").append(sector.getCoordinate().toString());
                }

            }
        }
        if (msg.toString().equals("")) {
            msg = new StringBuilder("none");
        }
        RRNotification lastNotification = game.getLastRRclientNotification();
        String message = lastNotification.getMessage();
        message += ". Players spotted: " + msg;
        game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(), message, lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), incriminatedSectors, lastNotification.getAvailableGames(), lastNotification.getPlayerToken()));
        PSNotification lastPNotification = game.getLastPSclientNotification();
        String lastMessage = lastPNotification.getMessage();
        game.setLastPSclientNotification(new PSNotification(lastMessage + globalMessage + msg, lastPNotification.getDeadPlayers(), lastPNotification.getAttackedPlayers(), lastPNotification.isHumanWin(), lastPNotification.isAlienWin(), lastPNotification.getEscapedPlayer(), lastPNotification.isGameNeedsToStart(), lastPNotification.isTurnNeedsToStart(), lastPNotification.isGameCanBeStarted(), lastPNotification.isTurnNeedsToEnd(), lastPNotification.getGameMapName()));
        return true;
    }
}

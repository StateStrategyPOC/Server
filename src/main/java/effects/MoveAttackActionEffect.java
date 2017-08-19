package effects;

import common.*;
import server.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the effect of moving a player and attacking the sector in which
 * the player has arrived. It is important that after the move the current
 * player doesn't draw any cards
 *
 */
public class MoveAttackActionEffect extends ActionEffect {
    private static boolean verifyMoveLegality(Sector source, Sector target, PlayerType playerType){
        if (source.equals(target)){
            return false;
        }
        if (playerType.equals(PlayerType.HUMAN) &&
                (target.getSectorLegality().equals(SectorLegality.NONE))){
            return false;
        }
        else if (playerType.equals(PlayerType.ALIEN) &&
                (target.getSectorLegality().equals(SectorLegality.NONE)
                        || target.getSectorLegality().equals(SectorLegality.HUMAN))){
            return false;
        }
        return true;

    }
    public static boolean executeEffect(Game game, StoreAction action) {
        MoveAttackAction castedAction = (MoveAttackAction) action;

        Sector sourceSector = game.getCurrentPlayer().getCurrentSector();
        Sector targetSector = game.getGameMap().getSectorByCoords(
                castedAction.getTargetSector().getCoordinate());
        Player currentPlayer = game.getCurrentPlayer();
        StringBuilder rrMessage = new StringBuilder();
        StringBuilder psMessage = new StringBuilder();
        List<Player> deadPlayers = new ArrayList<>();
        PSNotification lastPNotification;
        int adrenalineBooster = 0;
        if (currentPlayer.isAdrenalined()){
            adrenalineBooster++;
        }

        if (!sourceSector.equals(castedAction.getTargetSector())) {
            if (game.getGameMap().checkSectorAdiacency(sourceSector,targetSector,currentPlayer.getSpeed()+adrenalineBooster,currentPlayer.isAdrenalined())
                    && verifyMoveLegality(sourceSector,targetSector,currentPlayer.getPlayerToken().getPlayerType())) {

                // For each player contained in the target sector
                for (Player player : targetSector.getPlayers()) {
                    PrivateDeck playerPrivateDeck = player.getPrivateDeck();
                    ArrayList<ObjectCard> privateDeckContent = new ArrayList<>(
                            playerPrivateDeck.getContent());
                    Iterator<ObjectCard> iterator = privateDeckContent
                            .iterator();
                    boolean defenseFound = false;
                    if (player.getPlayerToken().getPlayerType().equals(PlayerType.HUMAN)) {
                        while (iterator.hasNext()) {
                            ObjectCard objectCard = iterator.next();
                            if (objectCard instanceof DefenseObjectCard) {
                                playerPrivateDeck.removeCard(objectCard);
                                defenseFound = true;
                            }
                        }
                    }
                    // If no defense card has been found for p, then p is dead
                    if (!defenseFound) {
                        deadPlayers.add(player);
                        player.setPlayerState(PlayerState.DEAD);
                        player.setCurrentSector(null);
                        // Notify the rest of the players
                        lastPNotification = game.getLastPSclientNotification();
                        ArrayList<PlayerToken> lastDeadPlayers = lastPNotification.getDeadPlayers();
                        lastDeadPlayers.add(player.getPlayerToken());
                        game.setLastPSclientNotification(new PSNotification(psMessage.toString(),lastDeadPlayers,lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

                        rrMessage.append("You have attacked sector ").append(targetSector.getCoordinate().toString()).append(" and so ").append(player.getName()).append(" is dead.");
                        psMessage.append("[GLOBAL MESSAGE]: ").append(currentPlayer.getName()).append(" has attacked sector ").append(targetSector.getCoordinate().toString()).append(" and so ").append(player.getName()).append(" is dead.");
                    } else {
                        if (game.getCurrentPlayer().getPlayerToken().getPlayerType().equals(PlayerType.ALIEN) ){
                            game.getCurrentPlayer().setSpeed(3);
                        }
                        // Otherwise p has been attacked
                        lastPNotification = game.getLastPSclientNotification();
                        ArrayList<PlayerToken> lastAttackedPlayers = lastPNotification.getAttackedPlayers();
                        lastAttackedPlayers.add(player.getPlayerToken());
                        game.setLastPSclientNotification(new PSNotification(psMessage.toString(),lastPNotification.getDeadPlayers(),lastAttackedPlayers,lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));
                        rrMessage.append("You have attacked sector ").append(targetSector.getCoordinate().toString()).append(" and so ").append(player.getName()).append(" has defended.");

                        psMessage.append("[GLOBAL MESSAGE]: ").append(currentPlayer.getName()).append(" has attacked sector ").append(targetSector.getCoordinate().toString()).append(" and so ").append(player.getName()).append(" has defended.");
                    }
                }
                if (rrMessage.toString().equals("")) {
                    rrMessage.append("You have attacked sector ").append(targetSector.getCoordinate().toString()).append(" but it contained no players.");
                    psMessage.append("[GLOBAL MESSAGE]: ").append(currentPlayer.getName()).append(" has attacked sector ").append(targetSector.getCoordinate().toString()).append(" but it contained no players.");
                }
                RRNotification lastNotification = game.getLastRRclientNotification();
                game.setLastRRclientNotification(new RRNotification(lastNotification.isActionResult(),rrMessage.toString(),lastNotification.getDrawnSectorCard(),lastNotification.getDrawnObjectCard(),lastNotification.getDrawnRescueCard(), lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken()));

                lastPNotification = game.getLastPSclientNotification();
                game.setLastPSclientNotification(new PSNotification(psMessage.toString(),lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

                for (Player player : deadPlayers){
                    targetSector.removePlayer(player);
                }
                // Move the player that has attacked to the target sector
                sourceSector.removePlayer(currentPlayer);
                currentPlayer.setCurrentSector(targetSector);
                targetSector.addPlayer(currentPlayer);
                game.setLastAction(action);
                currentPlayer.setHasMoved(true);
                return true;
            }
        }

        return false;
    }
}

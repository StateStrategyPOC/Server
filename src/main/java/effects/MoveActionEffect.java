package effects;

import common.*;
import server.Game;
import common.StoreAction;

/**
 * Represents the effect of the moving a player
 *
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.1
 * @see ActionEffect
 * @see MoveAction
 */
public class MoveActionEffect extends ActionEffect {


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
        MoveAction moveAction = (MoveAction) action;
        // Retrieve a reference of the map
        GameMap map = game.getGameMap();
        Player currentPlayer = game.getCurrentPlayer();
        int adrenalineBooster = 0;
        if (currentPlayer.isAdrenalined()){
            adrenalineBooster++;
        }
        // Checks the source != target
        if (!currentPlayer.getCurrentSector().equals(moveAction.getTargetSector())) {
            // Retrieve the "true" reference of source and target
            Sector sourceSector = map.getSectorByCoords(currentPlayer
                    .getCurrentSector().getCoordinate());
            Sector targetSector = map.getSectorByCoords(moveAction.getTargetSector()
                    .getCoordinate());
            // Checks that source and target are adjacent according to the speed
            // of the player
            if (map.checkSectorAdiacency(sourceSector, targetSector,
                    currentPlayer.getSpeed()+adrenalineBooster,currentPlayer.isAdrenalined())
                    && verifyMoveLegality(sourceSector,targetSector,currentPlayer.getPlayerToken().getPlayerType()) ) {
                // This two lines implements the move
                sourceSector.removePlayer(currentPlayer);
                currentPlayer.setCurrentSector(targetSector);
                targetSector.addPlayer(currentPlayer);
                RRNotification lastNotification = game.getLastRRclientNotification();
                game.setLastRRclientNotification(new RRNotification(lastNotification.getActionResult(),"You have moved to sector "
                        + targetSector.getCoordinate().toString(),lastNotification.getDrawnCards(), drawnSectorCard, drawnObjectCard, lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
                PSNotification lastPNotification = game.getLastPSclientNotification();
                game.setLastPSclientNotification(new PSNotification("[GLOBAL MESSAGE]: "
                        + currentPlayer.getName() + " has moved.",lastPNotification.getDeadPlayers(),lastPNotification.getAttackedPlayers(),lastPNotification.isHumanWin(),lastPNotification.isAlienWin(),lastPNotification.getEscapedPlayer(),lastPNotification.isGameNeedsToStart(),lastPNotification.isTurnNeedsToStart(),lastPNotification.isGameCanBeStarted(),lastPNotification.isTurnNeedsToEnd(),lastPNotification.getGameMapName()));

                // If the target sector is a dangerous sector continue the
                // execution
                // of the action
                game.setLastAction(action);
                if (targetSector.getSectorType() == SectorType.DANGEROUS
                        && !currentPlayer.isSedated()) {
                    game.setLastAction(new DrawSectorCardAction());
                    DrawSectorCardEffect.executeEffect(game, new DrawSectorCardAction());
                } else if (targetSector.getSectorType() == SectorType.OPEN_RESCUE) {
                    game.setLastAction(new DrawRescueCardAction());
                    DrawRescueCardEffect.executeEffect(game);
                }
                game.getCurrentPlayer().setHasMoved(true);
                return true;
            }

        }
        return false;
    }

}

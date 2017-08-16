package server_state_policies;

import common.*;
import effects.GameActionMapper;
import server.AlienTurn;
import server.Game;
import server.Helpers;
import server.HumanTurn;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GameMakeActionAction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class GameMakeActionStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GameMakeActionAction castedAction = (GameMakeActionAction) action;
        StoreAction gameAction = castedAction.getAction();
        Game game = Helpers.findGameById(castedAction.getPlayerToken().getGameId(), state.getGames());
        boolean gameActionResult = false;
        if (game == null){
            return state;
        }
        if (!game.getCurrentPlayer().getPlayerToken().equals(castedAction.getPlayerToken())){
            RRNotification notification = new RRNotification(false, "You cannot perform this action",null,null,null,null,null);
            game.setLastRRclientNotification(notification);
            return state;
        }
        if (!game.getNextActions().contains(gameAction.getActionIdentifier())){
            RRNotification notification = new RRNotification(false, "You cannot perform this action",null,null,null,null,null);
            game.setLastRRclientNotification(notification);
            return state;
        }
        // Executes the action's associated logic and get the result
        try {
            Method executeMethod = GameActionMapper.getInstance().getEffect(gameAction.getClass()).getMethod("executeEffect", Game.class, StoreAction.class);
            gameActionResult = (boolean) executeMethod.invoke(null,game, castedAction.getAction());
            RRNotification lastNotification = game.getLastRRclientNotification();
            game.setLastRRclientNotification(new RRNotification(gameActionResult,lastNotification.getMessage(),lastNotification.getDrawnCards(),lastNotification.getLightedSectors(),lastNotification.getAvailableGames(),lastNotification.getPlayerToken(),lastNotification.getGameMapName()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (!gameActionResult) {
            RRNotification notification = new RRNotification(false, "You cannot perform this action",null,null,null,null,null);
            game.setLastRRclientNotification(notification);
            return state;
        }
        if (!game.getLastAction().getActionIdentifier().equals("@GAMEACTION_END_TURN")) {
            if (game.getCurrentPlayer().getPlayerToken().getPlayerType().equals(PlayerType.HUMAN)) {
                game.setNextActions(HumanTurn.nextAction(game.getLastAction(), game.getCurrentPlayer()));
            } else {
                game.setNextActions(AlienTurn.nextAction(game.getLastAction(), game.getCurrentPlayer()));
            }

        } else {
            if (game.getCurrentPlayer().getPlayerToken().getPlayerType().equals(PlayerType.ALIEN)) {
                game.setNextActions(AlienTurn.getInitialActions());
            } else {
                game.setNextActions(HumanTurn.getInitialActions());
            }
            game.setTurnNumber(game.getTurnNumber() + 1);
        }
        game.setDidHumansWin(checkWinConditions(PlayerType.HUMAN, game));
        game.setDidAlienWin(checkWinConditions(PlayerType.ALIEN, game));
        //game.getCurrentTimer().cancel();
        //game.setCurrentTimer(new Timer());
        PSNotification lastNotification;
        if (game.isDidHumansWin()) {
            lastNotification = game.getLastPSclientNotification();
            game.setLastPSclientNotification(new PSNotification(
                    lastNotification.getMessage()+"\n[GLOBAL MESSAGE]: The game has ended, HUMANS WIN!",
                    lastNotification.getDeadPlayers(),
                    lastNotification.getAttackedPlayers(),
                    true,
                    false,
                    lastNotification.getEscapedPlayer(),
                    lastNotification.isGameNeedsToStart(),
                    lastNotification.isTurnNeedsToStart(),
                    lastNotification.isGameCanBeStarted(),
                    lastNotification.isTurnNeedsToEnd(),
                    lastNotification.getGameMapName()
            ));
        }
        if (game.isDidAlienWin()) {
            lastNotification = game.getLastPSclientNotification();
            game.setLastPSclientNotification(new PSNotification(
                    lastNotification.getMessage()+"\n[GLOBAL MESSAGE]: The game has ended, ALIENS WIN!",
                    lastNotification.getDeadPlayers(),
                    lastNotification.getAttackedPlayers(),
                    lastNotification.isHumanWin(),
                    true,
                    lastNotification.getEscapedPlayer(),
                    lastNotification.isGameNeedsToStart(),
                    lastNotification.isTurnNeedsToStart(),
                    lastNotification.isGameCanBeStarted(),
                    lastNotification.isTurnNeedsToEnd(),
                    lastNotification.getGameMapName()
            ));

        }


       return state;
    }
    /**
     * Decides if the Humans or Aliens have won the game.
     * Aliens win if:
     * <ul>
     * <li>All human players are dead</li>
     * <li>Some human player is still alive but the turn number is 39</li>
     * <li>Some human player is still alive but no escape point is available</li>
     * </ul>
     * <br/>
     * Humans win if:
     * <ul>
     * <li>They have all escaped</li>
     * <li>No alien is left, at least one human is still alive and at least one escape point exists/li>
     * <li>At least one human has escaped but no more alive players exist</li>
     * </ul>
     *
     * @param playerType The type of the faction we want to check if has won or not.
     * @param game       The game we are considering.
     * @return True if the faction has won, false otherwise.
     */
    private boolean checkWinConditions(PlayerType playerType, Game game) {
        boolean allDeadHumans = this.checkStateAll(PlayerType.HUMAN, PlayerState.DEAD, game.getPlayers());
        boolean allEscapedHumans = this.checkStateAll(PlayerType.HUMAN, PlayerState.ESCAPED, game.getPlayers());
        boolean allDeadAliens = this.checkStateAll(PlayerType.ALIEN, PlayerState.DEAD, game.getPlayers());
        boolean existEscapes = game.getGameMap().existEscapes();
        if (playerType == PlayerType.HUMAN) {
            // If all human players are escaped then Human wins!
            if (allEscapedHumans) {
                return true;
            } else if (!allDeadHumans && allDeadAliens) {
                return true;
            }
            return false;
        } else {
            // If all human player are all dead, alien wins!
            if (allDeadHumans) {
                return true;
            } else if (game.getTurnNumber() == 39 && !allEscapedHumans) {
                return true;
            } else if (!allEscapedHumans && !existEscapes) {
                return true;
            }
            return false;
        }
    }
    /**
     * Check if all the players of a given faction obey to a given status.
     *
     * @param playerType The faction we are interested in.
     * @param state      The status we are interested in.
     * @param players    All the players of all factions.
     * @return True if the players of the given faction obey to the given status, false otherwise.
     */
    private boolean checkStateAll(PlayerType playerType, PlayerState state, List<Player> players) {
        for (Player player : players) {
            if (!player.getPlayerState().equals(state)
                    && player.getPlayerToken().getPlayerType().equals(playerType))
                return false;
        }
        return true;
    }
}

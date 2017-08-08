package server_state_policies;

import common.Player;
import common.PlayerToken;
import common.PlayerType;
import common.RRClientNotification;
import server.Game;
import server.Helpers;
import server_store.ServerState;
import server_store.StatePolicy;
import server_store.StoreAction;
import server_store_actions.GameJoinGameAction;

public class GameJoinGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GameJoinGameAction castedAction = (GameJoinGameAction) action;
        PlayerToken playerToken;
        Game game = Helpers.findGameById(castedAction.getGameId(),state.getGames());
        if (game != null){
            PlayerType playerType = assignTypeToPlayer(game.getPlayers().size() + 1);
            playerToken = new PlayerToken(playerType, castedAction.getGameId());
            Player player = new Player(castedAction.getPlayerName());
            player.setPlayerToken(playerToken);
            game.getPlayers().add(player);
            game.getGamePublicData().addPlayer();
            if (game.getCurrentPlayer() == null) {
                game.setCurrentPlayer(player);
            }
            game.setLastRRclientNotification(new RRClientNotification(playerToken));
        }
        return state;
    }
    /**
     * Produces a player type based on the number of players already in game.
     * If the number of players already in game is even, the returned player
     * type is "HUMAN", otherwise is "ALIEN". This procedure is adopted in order
     * to guarantee a balanced number of aliens and humans.
     *
     * @param numberOfPlayers The number of players already in game.
     * @return A player type, either "HUMAN" or "ALIEN".
     */
    private PlayerType assignTypeToPlayer(int numberOfPlayers) {
        if (numberOfPlayers % 2 == 0) {
            return PlayerType.HUMAN;
        } else {
            return PlayerType.ALIEN;
        }

    }
}

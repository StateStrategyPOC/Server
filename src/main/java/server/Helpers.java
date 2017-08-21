package server;

import common.Player;
import common.PlayerToken;

import java.util.List;

public class Helpers {
    public static Game findGameById(Integer gameId, List<Game> games){
        for (Game game : games){
            if (game.getGamePublicData().getId() == gameId){
                return game;
            }
        }
        return null;
    }

    public static PubSubHandler findPubSubHandlerByPlayerToken(PlayerToken playerToken, Game game){
        for (PubSubHandler handler : game.getPubSubHandlers()){
            if (handler.getPlayerToken().equals(playerToken)){
                return handler;
            }
        }
        return null;
    }

    public static Player findPlayerByToken(PlayerToken playerToken, List<Player> players) {
        for (Player player : players){
            if (playerToken.equals(player.getPlayerToken())){
                return player;
            }
        }
        return null;
    }
}

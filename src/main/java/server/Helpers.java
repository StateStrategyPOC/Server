package server;

import common.PSNotification;
import common.Player;
import common.PlayerToken;

import java.util.List;
import java.util.UUID;

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
}

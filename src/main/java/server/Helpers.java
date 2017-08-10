package server;

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
    public static ReqRespHandler findReqRespHandlerById(UUID handlerId, List<ReqRespHandler> handlers){
        for (ReqRespHandler handler : handlers){
            if (handler.getHandlerId().equals(handlerId)){
                return handler;
            }
        }
        return null;
    }

}

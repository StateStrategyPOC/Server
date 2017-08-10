package server_store;

import server_side_policies.*;
import server_state_policies.*;

public class ServerGroupResolver extends Resolver {
    @Override
    protected void fillPoliciesMap() {
        this.policiesMap.put("@SERVER_GAME_JOIN_GAME",
                new PolicyCouple(new GameJoinGameStatePolicy(), new GameJoinGameSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_MAKE_ACTION",
                new PolicyCouple(new GameMakeActionStatePolicy(), new GameMakeActionSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_ON_DEMAND_START",
                new PolicyCouple(null, new GameOnDemandStartSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_POST_MSG",
                new PolicyCouple(null, new GamePostChatMsgSidePolicy()));
        this.policiesMap.put("@SERVER_GAMES_ADD_GAME",
                new PolicyCouple(new GamesAddGameStatePolicy(), null));
        this.policiesMap.put("@SERVER_GAMES_END_GAME",
                new PolicyCouple(new GamesEndGameStatePolicy(), new GamesEndGameSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_STARTABLE_GAME",
                new PolicyCouple(new GameStartableGameStatePolicy(), new GameStartableGameSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_START_GAME",
                new PolicyCouple(new GameStartGameStatePolicy(), new GameStartGameSidePolicy()));
        this.policiesMap.put("@@SERVER_GAME_TURNTIMEOUT_EXPIRED",
                new PolicyCouple(new GameTurnTimeoutStatePolicy(), new GameTurnTimeoutSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_SET_PS",
                new PolicyCouple(new GameSetPSHandlersStatePolicy(), new GameSetPSHandlersSidePolicy()));
        this.policiesMap.put("@SERVER_GAMES_GET_GAMES",
                new PolicyCouple(null, new GamesGetGamesSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_SUBSCRIBE",
                new PolicyCouple(null, new GameSubscribeSidePolicy()));
        this.policiesMap.put("@SERVER_ADD_REQRESP_HANDLER",new PolicyCouple(new ServerAddReqRespStatePolicy(),null));
    }
}

package server_store;

import server_side_policies.*;
import server_state_policies.*;

/**
 * Represents a mapper between {@link common.StoreAction#actionIdentifier} and {@link PolicyCouple}
 */
public class ServerGroupResolver extends Resolver {
    @Override
    protected void fillPoliciesMap() {
        this.policiesMap.put("@SERVER_GAME_JOIN_GAME",
                new PolicyCouple(new GameJoinGameStatePolicy(),null));
        this.policiesMap.put("@SERVER_GAME_MAKE_ACTION",
                new PolicyCouple(new GameMakeActionStatePolicy(), new GameMakeActionSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_ON_DEMAND_START",
                new PolicyCouple(null, new GameOnDemandStartSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_POST_MSG",
                new PolicyCouple(null, new GamePostChatMsgSidePolicy()));
        this.policiesMap.put("@SERVER_GAMES_ADD_GAME",
                new PolicyCouple(new GamesAddGameStatePolicy(), null));
        this.policiesMap.put("@SERVER_GAMES_END_GAME",
                new PolicyCouple(new GamesEndGameStatePolicy(),null));
        this.policiesMap.put("@SERVER_GAME_STARTABLE_GAME",
                new PolicyCouple(null, new GameStartableGameSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_START_GAME",
                new PolicyCouple(new GameStartGameStatePolicy(), new GameStartGameSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_TURNTIMEOUT_EXPIRED",
                new PolicyCouple(new GameTurnTimeoutStatePolicy(), new GameTurnTimeoutSidePolicy()));
        this.policiesMap.put("@SERVER_GAME_SET_PS",
                new PolicyCouple(new GameSetPSHandlerStatePolicy(), new GameSetPSHandlerSidePolicy()));
        this.policiesMap.put("@ERROR_ACTION", new PolicyCouple(new BlankPolicy(),null));
        this.policiesMap.put("@SERVER_GAME_REMOVE_PLAYER",new PolicyCouple(new GameRemovePlayerStatePolicy(),null));
    }
}

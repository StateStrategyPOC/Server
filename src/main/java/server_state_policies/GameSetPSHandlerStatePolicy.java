package server_state_policies;

import server.PubSubHandler;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GameSetPSHandlerAction;

public class GameSetPSHandlerStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
        GameSetPSHandlerAction castedAction = (GameSetPSHandlerAction) action;
        castedAction.getGame().getPubSubHandlers().add(new PubSubHandler(castedAction.getSocket(),castedAction.getOutputStream(),castedAction.getPlayerToken()));
        return state;
    }
}

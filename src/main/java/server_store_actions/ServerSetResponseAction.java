package server_store_actions;

import common.RRClientNotification;
import server_store.StoreAction;

import java.util.UUID;

public class ServerSetResponseAction extends StoreAction {

    private final RRClientNotification response;
    private final UUID handlerId;

    public ServerSetResponseAction(RRClientNotification response, UUID handlerId) {
        super("@SERVER_SET_RESPONSE", "@SERVER_GROUP");
        this.response = response;
        this.handlerId = handlerId;
    }

    public RRClientNotification getResponse() {
        return response;
    }

    public UUID getHandlerId() {
        return handlerId;
    }
}

package server_store_actions;

import common.RRClientNotification;
import server_store.StoreAction;

public class ServerSetResponseAction extends StoreAction {

    private final RRClientNotification response;

    public ServerSetResponseAction(RRClientNotification response) {
        super("@SERVER_SET_RESPONSE", "@SERVER_GROUP");
        this.response = response;
    }

    public RRClientNotification getResponse() {
        return response;
    }
}

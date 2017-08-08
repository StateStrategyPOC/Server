package server_store_actions;

import common.PSClientNotification;
import server_store.StoreAction;

public class ServerSetNotificationAction extends StoreAction {

    private final PSClientNotification notification;

    public ServerSetNotificationAction(PSClientNotification notification) {
        super("@SERVER_SET_NOTIFICATION","@SERVER_GROUP");
        this.notification = notification;
    }

    public PSClientNotification getNotification() {
        return notification;
    }
}

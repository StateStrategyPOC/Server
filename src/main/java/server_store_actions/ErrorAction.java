package server_store_actions;

import common.StoreAction;

public class ErrorAction extends StoreAction {

    private final int id;
    public ErrorAction(int id) {
        super("@ERROR_ACTION", "@SERVER_GROUP");
        this.id = id;
    }

    @Override
    public String toString() {
        return "ErrorAction{" +
                "id=" + id +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

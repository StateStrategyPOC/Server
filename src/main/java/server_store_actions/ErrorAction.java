package server_store_actions;

import common.StoreAction;

/**
 * An Action to signal a generic error
 */
public class ErrorAction extends StoreAction {

    private final int id;
    public ErrorAction(int id) {
        super("@ERROR_ACTION", "@ERROR_GROUP");
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

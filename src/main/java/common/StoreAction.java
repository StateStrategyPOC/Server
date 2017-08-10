package common;

import java.io.Serializable;

/**
 * Created by giorgiopea on 14/03/17.
 */
public abstract class StoreAction implements Serializable {
    protected String actionIdentifier;
    protected String actionGroupIdentifier;

    public StoreAction(String actionIdentifier, String actionGroupIdentifier) {
        this.actionIdentifier = actionIdentifier;
        this.actionGroupIdentifier = actionGroupIdentifier;
    }

    public String getActionIdentifier() {
        return actionIdentifier;
    }

    public String getActionGroupIdentifier() {
        return actionGroupIdentifier;
    }
}

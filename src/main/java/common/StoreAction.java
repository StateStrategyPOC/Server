package common;

import java.io.Serializable;

/**
 * The implementation of a lambda MVC Action
 */
public abstract class StoreAction implements Serializable {
    protected final String actionIdentifier;
    protected final String actionGroupIdentifier;

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

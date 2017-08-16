package common;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionOnTheWire implements Serializable {

    private final String actionIdentifier;
    private final ArrayList<Object> parameters;

    public ActionOnTheWire(String actionIdentifier, ArrayList<Object> parameters) {
        this.actionIdentifier = actionIdentifier;
        this.parameters = parameters;
    }

    public String getActionIdentifier() {
        return actionIdentifier;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }
}

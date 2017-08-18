package common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a message that the client application sends to the server application
 * to make a specific behaviour be executed.
 */
public class ActionOnTheWire implements Serializable {
    //Identifier of the behaviour to be executed
    private final String actionIdentifier;
    //Data parameters
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

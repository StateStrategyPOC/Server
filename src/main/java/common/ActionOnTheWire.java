package common;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionOnTheWire implements Serializable {

    private final String identifierMapper;
    private final ArrayList<Object> parameters;

    public ActionOnTheWire(String identifierMapper, ArrayList<Object> parameters) {
        this.identifierMapper = identifierMapper;
        this.parameters = parameters;
    }

    public String getIdentifierMapper() {
        return identifierMapper;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }
}

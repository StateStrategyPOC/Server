package server_store;

public class ErrorGroupResolver extends Resolver {

    @Override
    protected void fillPoliciesMap() {
        this.policiesMap.put("@ERROR_ACTION",new PolicyCouple(new BlankPolicy(),null));
    }
}

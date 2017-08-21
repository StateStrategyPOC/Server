package server_store;



import common.StoreAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * Represents a lambda MVC Store
 */
public class ServerStore {
    private final Map<String, Resolver> actionGroupToResolver;
    private final ObservableServerState observableState;
    private final static ServerStore instance = new ServerStore();
    private final StoreLogger STORE_LOGGER;


    public static ServerStore getInstance(){
        return instance;
    }

    private ServerStore(){
        this.actionGroupToResolver = new HashMap<>();
        this.STORE_LOGGER = StoreLogger.getInstance();
        this.observableState = new ObservableServerState(new ServerState());
        this.fillResolverMap();
    }

    /**
     * Fills the map between {@link StoreAction#actionGroupIdentifier} and {@link Resolver}
     */
    private void fillResolverMap(){
        this.actionGroupToResolver.put("@SERVER_GROUP", new ServerGroupResolver());
        this.actionGroupToResolver.put("@ERROR_GROUP", new ErrorGroupResolver());
    }


    public ServerState getState() {
        return this.observableState.getServerState();
    }


    public void observeState(Observer observer) {
        this.observableState.addObserver(observer);
    }


    /**
     * Performs lambda MVC Action propagation
     * @param action The Action to be propagated
     */
    public synchronized void propagateAction(StoreAction action) {
        Resolver resolver = this.actionGroupToResolver.get(action.getActionGroupIdentifier());
        try {
            PolicyCouple policyCouple = resolver.resolve(action);
            this.STORE_LOGGER.logPreStatePropagation(action,this.getState());
            if (policyCouple.getStatePolicy() != null){
                this.observableState.setServerState(policyCouple.getStatePolicy().apply(this.getState(), action),action);
            }
            this.STORE_LOGGER.logPostStatePropagation(this.getState());
            if (policyCouple.getSidePolicy() != null){
                policyCouple.getSidePolicy().apply(this.getState(),action);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


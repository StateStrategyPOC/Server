package server_store;



import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * Created by giorgiopea on 09/03/17.
 *
 */
public class ServerStore {

    private final Map<String, Resolver> actionGroupToResolver;
    private final static ServerStore instance = new ServerStore();
    private ObservableServerState observableState;


    public static ServerStore getInstance(){
        return instance;
    }

    private ServerStore(){
        this.actionGroupToResolver = new HashMap<>();
        this.produceInitialState();
        this.fillResolverMap();
    }
    private void fillResolverMap(){
        this.actionGroupToResolver.put("@SERVER_GROUP", new ServerGroupResolver());
    }


    public ServerState getState() {
        return this.observableState.getServerState();
    }


    public void observeState(Observer observer) {
        this.observableState.addObserver(observer);
    }

    private void init(ServerState initialState) {
        this.observableState = new ObservableServerState(initialState);
    }

    private void produceInitialState(){
        ServerState initialState = new ServerState();
        this.init(initialState);
    }

    /**
     * Propagates an
     * @param action
     */
    public void propagateAction(StoreAction action) {
        Resolver resolver = this.actionGroupToResolver.get(action.getActionGroupIdentifier());
        try {
            PolicyCouple policyCouple = resolver.resolve(action);
            //PRE_LOG
            if (policyCouple.getStatePolicy() != null){
                this.observableState.setServerState(policyCouple.getStatePolicy().apply(this.getState(), action),action);
            }
            //POST_LOG
            if (policyCouple.getSidePolicy() != null){
                policyCouple.getSidePolicy().apply(this.getState(),action);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


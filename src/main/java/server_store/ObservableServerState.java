package server_store;

import java.util.Observable;

/**
 * Created by giorgiopea on 11/03/17.
 *
 */
public class ObservableServerState extends Observable {

    private ServerState serverState;

    public ObservableServerState(ServerState serverState) {
        this.serverState = serverState;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public void setServerState(State serverState, StoreAction lastAction) {
        this.serverState = (ServerState) serverState;
        this.setChanged();
        this.notifyObservers(lastAction);
    }

    @Override
    public String toString() {
        return serverState.toString();
    }
}
package server_store;

import common.StoreAction;

import java.util.Observable;

/**
 * Represents a wrapper to the {@link ServerState} so that its changes can be observed by means of propagated Actions
 */
class ObservableServerState extends Observable {

    private ServerState serverState;

    public ObservableServerState(ServerState serverState) {
        this.serverState = serverState;
    }

    public ServerState getServerState() {
        return serverState;
    }

    /**
     * Sets the new {@link ServerState} for the application and notifies observers with the {@link StoreAction} that
     * has produced the new {@link ServerState}
     *
     * @param serverState The new {@link ServerState}
     * @param lastAction  The {@link StoreAction} that
     *                    has produced the new {@link ServerState}
     */
    public void setServerState(ServerState serverState, StoreAction lastAction) {
        this.serverState = serverState;
        this.setChanged();
        this.notifyObservers(lastAction);
    }

    @Override
    public String toString() {
        return serverState.toString();
    }
}

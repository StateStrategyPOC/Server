package server_store;

import server.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giorgiopea on 11/03/17.
 *
 */
public class ServerState implements Serializable {

    private List<Game> games;
    private int tcpPort;
    private final long turnTimeout;
    private boolean isServerListening;

    public ServerState() {
        this.games = new ArrayList<>();
        this.tcpPort = 29999;
        this.turnTimeout = 60*5*1000;
        this.isServerListening = true;
    }

    public boolean isServerListening() {
        return isServerListening;
    }

    public void setServerListening(boolean serverListening) {
        isServerListening = serverListening;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public long getTurnTimeout() {
        return turnTimeout;
    }

    @Override
    public String toString() {
        return "ServerState{" +
                "games=" + games +
                ", tcpPort=" + tcpPort +
                ", turnTimeout=" + turnTimeout +
                ", isServerListening=" + isServerListening +
                '}';
    }
}

package server;

import server_store.ServerStore;
import server_store_actions.ServerAddReqRespHandlerAction;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages the network communication between the client and the server by spawning threads
 * as necessary.
 */
public class ConnectionListener{

    private final ServerStore SERVER_STORE;
    private final ExecutorService reqRespThreadPool;
    private final ExecutorService pubSubThreadPool;

    private static final ConnectionListener instance = new ConnectionListener();

    public static ConnectionListener getInstance(){
        return instance;
    }

    private ConnectionListener() {
        this.SERVER_STORE = ServerStore.getInstance();
        this.reqRespThreadPool = Executors.newCachedThreadPool();
        this.pubSubThreadPool = Executors.newCachedThreadPool();
    }
    /**
     * Inits listening on the wire on the given port and start spawning threads to manage
     * incoming requests.
     *
     * @throws IOException Networking problem.
     */
    public void init() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.SERVER_STORE.getState().getTcpPort());
        Socket socket;
        while(this.SERVER_STORE.getState().isServerListening()){
            socket = serverSocket.accept();
            ReqRespHandler reqRespHandler = new ReqRespHandler(socket);
            this.SERVER_STORE.propagateAction(new ServerAddReqRespHandlerAction(handler));
            this.reqRespThreadPool.submit(reqRespHandler);
        }
    }
    public void registerPubSubHandler(PubSubHandler handler){
        this.pubSubThreadPool.submit(handler);
    }
}

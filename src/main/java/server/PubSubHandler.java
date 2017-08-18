package server;

import common.PSNotification;
import common.PlayerToken;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Manages a persistent connection with a client in the logic of the publisher/subscriber pattern
 */
public class PubSubHandler extends Thread {

    // The socket associated to the handler
    private final Socket socket;
    // A queue of messages to send to the subscriber
    private final Queue<PSNotification> notificationsQueue;
    private final PlayerToken playerToken;
    // The object output stream used to perform the remote method call on the
    // subscriber
    private final ObjectOutputStream objectOutputStream;
    private boolean runningFlag;

    /**
     * Constructs a subscriber handler from the socket used to perform remote
     * method calls on the subscriber. An empty queue of remote method calls for
     * the handler is automatically created as well.
     *
     * @param socket The socket used to perform remote method calls on the subscriber.
     *
     * @param outputStream The output stream used to perform remote method calls on the subscriber.
     *
     * @param playerToken The token that identifies a player along with the game is playing.
     *
     */
    public PubSubHandler(Socket socket, ObjectOutputStream outputStream, PlayerToken playerToken)  {
        this.socket = socket;
        this.playerToken = playerToken;
        this.notificationsQueue = new ArrayBlockingQueue<>(10);
        this.objectOutputStream = outputStream;
        this.runningFlag = true;
    }

    public void setRunningFlag(boolean runningFlag) {
        this.runningFlag = runningFlag;
    }

    /**
     * Sends a received notification to the client
     * @param notification The received notification
     * @throws IOException Network problem
     */
    private void sendNotification(PSNotification notification) throws IOException {
        this.objectOutputStream.writeObject(notification);
        this.objectOutputStream.flush();
    }


    /**
     * Runs the thread defined in this class. The thread waits until the
     * messages queue has a new message, and then sends the new message to the client.
     */
    @Override
    public void run() {
        while (this.runningFlag) {
            PSNotification outboundNotification = notificationsQueue.poll();
            if (outboundNotification != null){
                try {
                    this.sendNotification(outboundNotification);
                } catch (IOException e) {
                    this.runningFlag = false;
                    try {
                        this.objectOutputStream.close();
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }

            else {
                try {
                    // If there are no incoming remote method calls the thread
                    // waits
                    synchronized (notificationsQueue) {
                        notificationsQueue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            this.objectOutputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    /**
     * Enques a notification to be sent to the client
     * @param psNotification The notification to be sent to the client
     */
    public void queueNotification(PSNotification psNotification) {
        notificationsQueue.add(psNotification);
        synchronized (notificationsQueue) {
            notificationsQueue.notify();
        }
    }
}
package server;

import java.io.IOException;

/**
 * Inits the entire application.
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            CommunicationHandler.getInstance().init(29999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package server;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        try {
            ConnectionListener.getInstance().listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

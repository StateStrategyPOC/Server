package common;

/**
 * A container of identifier for different requestable behaviour by the client to the server
 *
 */
public class EncodedBehaviourIdentifiers {


    public static String subscribe(){
        return "$subscribe";
    }

    public static String onDemandGameStart() {
        return "$onDemandGameStart";
    }

    public static String makeAction() {
        return "$makeAction";
    }

    public static String joinNewGame() {
        return "$joinNewGame";
    }
    public static String joinGame(){
        return "$joinGame";
    }

    public static String publishChatMsg() {
        return "$pushChatMessage";
    }

    public static String getGames() {
        return "$getAvailableGames";
    }
}

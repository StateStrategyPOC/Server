package server;

/**
 * Created by giorgiopea on 25/04/17.
 *
 */
public class ServerMethodsNameProvider {


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
        return "$getGames";
    }
}

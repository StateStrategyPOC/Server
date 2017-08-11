package server_store_actions;

import common.PlayerToken;
import common.StoreAction;

import java.util.UUID;

/**
 * Created by giorgiopea on 04/05/17.
 *
 */
public class GamePostMsgAction extends StoreAction {

    private final PlayerToken playerToken;
    private final String message;

    public GamePostMsgAction(String message, PlayerToken playerToken) {
        super("@SERVER_GAME_POST_MSG","@SERVER_GROUP");
        this.playerToken = playerToken;
        this.message = message;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GamePostMsgAction{" +
                "playerToken=" + playerToken +
                ", message='" + message + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

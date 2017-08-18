package server_store_actions;

import common.PlayerToken;
import common.StoreAction;

/**
 * An Action for signalling that an in game inGameAction must be performed
 */
public class GameMakeActionAction extends StoreAction {

    private final StoreAction inGameAction;
    //Player who wants to do the action
    private final PlayerToken playerToken;

    public GameMakeActionAction(PlayerToken playerToken, StoreAction inGameAction) {
        super("@SERVER_GAME_MAKE_ACTION","@SERVER_GROUP");
        this.playerToken = playerToken;
        this.inGameAction = inGameAction;
    }

    public StoreAction getInGameAction() {
        return inGameAction;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }

    @Override
    public String toString() {
        return "GameMakeActionAction{" +
                "inGameAction=" + inGameAction +
                ", playerToken=" + playerToken +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}

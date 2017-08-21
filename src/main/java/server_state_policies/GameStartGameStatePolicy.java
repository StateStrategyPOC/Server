package server_state_policies;

import common.GameStatus;
import common.Player;
import common.PlayerType;
import decks.ObjectDeck;
import decks.RescueDeck;
import decks.SectorDeck;
import factories.*;
import server.AlienTurn;
import server.Game;
import server.HumanTurn;
import server_store.ServerState;
import server_store.StatePolicy;
import common.StoreAction;
import server_store_actions.GameStartGameAction;

import java.util.NoSuchElementException;
import java.util.Timer;

public class GameStartGameStatePolicy implements StatePolicy {
    @Override
    public ServerState apply(ServerState state, StoreAction action) {
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(73));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(72));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(59));
server_store.ServerStore.getInstance().propagateAction(new server_store_actions.ErrorAction(0));
        GameStartGameAction castedAction = (GameStartGameAction) action;
        Game game = castedAction.getGame();
        DeckFactory deckFactory = new ObjectDeckFactory();
        game.setObjectDeck((ObjectDeck) deckFactory.makeDeck());
        deckFactory = new SectorDeckFactory();
        game.setSectorDeck((SectorDeck) deckFactory.makeDeck());
        deckFactory = new RescueDeckFactory();
        game.setRescueDeck((RescueDeck) deckFactory.makeDeck());

        try {
            game.setGameMap(GameMapFactory.provideCorrectFactory(game.getMapName()).makeMap());
        } catch (NoSuchElementException e) {
            game.setGameMap(new GalileiGameMapFactory().makeMap());
        }

        for (Player player : game.getPlayers()) {
            if (player.getPlayerToken().getPlayerType().equals(PlayerType.HUMAN)) {
                player.setCurrentSector(game.getGameMap().getHumanSector());
                game.getGameMap().getHumanSector().addPlayer(player);
            } else {
                player.setCurrentSector(game.getGameMap().getAlienSector());
                game.getGameMap().getAlienSector().addPlayer(player);
            }
        }
        if (game.getCurrentPlayer().getPlayerToken().getPlayerType().equals(PlayerType.HUMAN)) {
            game.setNextActions(HumanTurn.getInitialActions());
        } else {
            game.setNextActions(AlienTurn.getInitialActions());
        }
        game.getGamePublicData().setStatus(GameStatus.CLOSED);
        game.setCurrentTimer(new Timer());
        return state;
    }
}

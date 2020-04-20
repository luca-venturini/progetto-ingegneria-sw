package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.MinotaurCard;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicWinCheckTest {
    private Match instance;
    private Player player;

    @BeforeEach
    void setUp() {
        instance= new Match();
        Point x= new Point(0,0);
        try {
            instance.addPlayer("player");
            instance.getPlayers().get(0).placeWorker(1,x,instance.getBoard());
            player = instance.getPlayers().get(0);
        } catch (NoMorePlayersException e) {
            e.printStackTrace();
        } catch (AlreadyPresentPlayerException e) {
            e.printStackTrace();
        } catch (NoPlayerException e) {
            e.printStackTrace();
        }

        instance.setTurn();
        instance.getTurn().setActualWorker(player.selectWorker(0));
    }

    @Test
    void hasWon() throws IllegalBlockUpdateException {
        player.setCard(new ApolloCard());
        try {
            assertFalse(player.hasWon(instance.getBoard(), instance.getTurn().getCurrentWorker(), instance.getPlayers()));
            Point p= new Point(0,1);
            instance.getTurn().getCurrentWorker().setPosition(p);
            instance.getBoard().setBlockLevel(p,3);
            assertTrue(player.hasWon(instance.getBoard(), instance.getTurn().getCurrentWorker(), instance.getPlayers()));
            player.setCard(new MinotaurCard());
            Point p2= new Point(0,2);
            instance.getTurn().getCurrentWorker().setPosition(p2);
            instance.getBoard().setBlockLevel(p2,3);
            assertFalse(player.hasWon(instance.getBoard(), instance.getTurn().getCurrentWorker(), instance.getPlayers()));
        } catch (NoPlayerException e) {
            e.printStackTrace();
        }
    }
}
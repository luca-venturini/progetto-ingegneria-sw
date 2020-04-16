package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.PanCard;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanWinCheckTest {
    private Match instance;
    private Player player;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point x= new Point(0,0);
        player.placeWorker(1,x,instance.getBoard());
    }

    @Test
    void hasWon() throws IllegalBlockUpdateException {
        player.setCard(new PanCard());
        assertFalse(player.hasWon(instance.getBoard()));
        Point p1= new Point(1,0);
        Point p2= new Point(0,0);
        instance.getBoard().setBlockLevel(p1,3);
        player.selectWorker(0).setPosition(p1);
        assertTrue(player.hasWon(instance.getBoard()));
        player.selectWorker(0).setPosition(p2);
        assertTrue(player.hasWon(instance.getBoard()));
    }
}
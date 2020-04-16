package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.MinotaurCard;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
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
        player= new Player("player",instance);
        Point x= new Point(0,0);
        player.placeWorker(1,x,instance.getBoard());
    }

    @Test
    void hasWon() throws IllegalBlockUpdateException {
        player.setCard(new ApolloCard());
        assertFalse(player.hasWon(instance.getBoard()));
        Point p= new Point(0,0);
        instance.getBoard().setBlockLevel(p,3);
        assertTrue(player.hasWon(instance.getBoard()));
        player.setCard(new MinotaurCard());
        assertTrue(player.hasWon(instance.getBoard()));
    }
}
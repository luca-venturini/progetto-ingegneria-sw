package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.ChronusCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.MinotaurCard;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyPresentPlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoMorePlayersException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoPlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChronusWinCheckTest {
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
        } catch (NoMorePlayersException | AlreadyPresentPlayerException | NoPlayerException e) {
            e.printStackTrace();
        }
        player.setCard(new ChronusCard());
        instance.setTurn();
        instance.getTurn().setActualWorker(player.selectWorker(0));
    }

    @Test
    void hasWon() {
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
        } catch (NoPlayerException | IllegalBlockUpdateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void outOfTurnWon() throws IllegalBlockUpdateException {
        ChronusWinCheck winCheck= new ChronusWinCheck();
        assertFalse(winCheck.outOfTurnWon(instance.getBoard()));
        Point p1= new Point(2,2);
        Point p2= new Point(1,2);
        Point p3= new Point(3,2);
        Point p4= new Point(4,2);
        Point p5= new Point(4,4);
        instance.getBoard().setBlockLevel(p1,4);
        instance.getBoard().setBlockLevel(p2,4);
        instance.getBoard().setBlockLevel(p3,4);
        instance.getBoard().setBlockLevel(p4,4);
        instance.getBoard().setBlockLevel(p5,4);
        assertTrue(winCheck.outOfTurnWon(instance.getBoard()));
    }
}
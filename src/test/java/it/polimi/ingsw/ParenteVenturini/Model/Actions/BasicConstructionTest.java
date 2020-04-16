package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicConstructionTest {
    private Match instance;
    private Player player;
    private Action tester;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point x= new Point(1,1);
        player.placeWorker(1,x,instance.getBoard());
        tester= new BasicConstruction();
    }

    @Test
    void doAction() throws IllegalBuildingException, IllegalMovementException, IllegalBlockUpdateException {
        Point p1= new Point(2,1);
        Point p2= new Point(-1,1);
        Point p3= new Point(1,1);
        Point p4= new Point(3,0);
        //check if worker build on his own position
        assertThrows(IllegalBuildingException.class,()->tester.doAction(p3,instance.getBoard(),player.selectWorker(0)));

        //check if worker build on a valid position
        int i=instance.getBoard().blockLevel(p1);
        tester.doAction(p1,instance.getBoard(),player.selectWorker(0));
        assertEquals(i+1,instance.getBoard().blockLevel(p1));
        instance.getBoard().setBlockLevel(p1,3);
        tester.doAction(p1,instance.getBoard(),player.selectWorker(0));
        assertTrue(instance.getBoard().isThereDome(p1));

        //check if worker build on invalid positions
        assertThrows(IllegalBuildingException.class,()->tester.doAction(p2,instance.getBoard(),player.selectWorker(0)));
        assertThrows(IllegalBuildingException.class,()->tester.doAction(p4,instance.getBoard(),player.selectWorker(0)));

        //check if worker build on a position occupied by another worker
        Point p5= new Point(1,0);
        player.placeWorker(1,p5,instance.getBoard());
        assertThrows(IllegalBuildingException.class,()->tester.doAction(p5,instance.getBoard(),player.selectWorker(0)));

        //check if worker build on a position with dome
        Point p6= new Point(0,1);
        instance.getBoard().setDome(p6, true);
        assertThrows(IllegalBuildingException.class,()->tester.doAction(p6,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void isValid() {
        Point p1= new Point(2,1);
        Point p2= new Point(-1,1);
        Point p3= new Point(1,1);
        Point p4= new Point(1,0);
        instance.getBoard().setDome(p4,true);
        assertTrue(tester.isValid(p1,instance.getBoard(),player.selectWorker(0)));
        assertFalse(tester.isValid(p2,instance.getBoard(),player.selectWorker(0)));
        assertFalse(tester.isValid(p3,instance.getBoard(),player.selectWorker(0)));
        assertFalse(tester.isValid(p4,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void getPossibleActions() {
        List<Point> positions=tester.getPossibleActions(instance.getBoard(),player.selectWorker(0));
        assertEquals(8, positions.size());
        assertTrue(positions.get(0).getX()==0 &&positions.get(0).getY()==0);
        assertTrue(positions.get(1).getX()==0 &&positions.get(1).getY()==1);
        assertTrue(positions.get(2).getX()==0 &&positions.get(2).getY()==2);
        assertTrue(positions.get(3).getX()==1 &&positions.get(3).getY()==0);
        assertTrue(positions.get(4).getX()==1 &&positions.get(4).getY()==2);
        assertTrue(positions.get(5).getX()==2 &&positions.get(5).getY()==0);
        assertTrue(positions.get(6).getX()==2 &&positions.get(6).getY()==1);
        assertTrue(positions.get(7).getX()==2 &&positions.get(7).getY()==2);

    }
}
package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrometheusMoveTest {
    private Match instance;
    private Player player;
    private Move tester;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point x= new Point(0,0);
        player.placeWorker(1,x,instance.getBoard());
        tester= new PrometheusMove();
    }

    @Test
    void walk_test1() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        Point p3= new Point(2,2);
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p1,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void walk_test2() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        tester.build(p1,instance.getBoard(),player.selectWorker(0));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p1,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void build_test1() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        assertThrows(endedMoveException.class,()->tester.build(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void build_test2() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertFalse(tester.forcedMovement());
        tester.build(p1,instance.getBoard(),player.selectWorker(0));
        assertTrue(tester.forcedMovement());
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.build(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleMovements() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        Point p1= new Point(1,1);
        assertNotNull(tester.possibleMovements(instance.getBoard(), player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.possibleMovements(instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleBuildings() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertNotNull(tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertNotNull(tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyBuiltException.class,()->tester.possibleBuildings(instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleBuildingsFirst() throws IllegalBlockUpdateException {
        instance.getBoard().setBlockLevel(new Point(0,1), 1);
        assertThrows(OutOfOrderMoveException.class,()->tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)) );
    }

    @Test
    void hasEnded() throws endedMoveException, IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, AlreadyWalkedException, AlreadyBuiltException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        tester.build(p1,instance.getBoard(),player.selectWorker(0));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void build_3() throws IllegalBlockUpdateException, OutOfOrderMoveException, AlreadyWalkedException, AlreadyBuiltException {
        Point p1= new Point(1,1);
        instance.getBoard().setBlockLevel(p1, 1);
        assertThrows(IllegalBuildingException.class,()->tester.build(new Point(1,1),instance.getBoard(),player.selectWorker(0)) );
        instance.getBoard().setBlockLevel(new Point(0,0), 3);
        assertEquals(3, tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)).size());
    }



}
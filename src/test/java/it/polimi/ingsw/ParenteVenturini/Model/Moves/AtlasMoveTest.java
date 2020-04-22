package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtlasMoveTest {
    private Match instance;
    private Player player;
    private Move tester;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point x= new Point(0,0);
        player.placeWorker(1,x,instance.getBoard());
        tester= new AtlasMove();
    }

    @Test
    void walk() throws endedMoveException, IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p1,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void build() throws AlreadyWalkedException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException, AlreadyBuiltException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertThrows(OutOfOrderMoveException.class,()->tester.build(p1,instance.getBoard(),player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.build(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void specialBuild() throws AlreadyWalkedException, IllegalBuildingException, endedMoveException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertThrows(OutOfOrderMoveException.class,()->tester.build(p1,instance.getBoard(),player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.specialBuild(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.build(p2,instance.getBoard(),player.selectWorker(0)));
        assertThrows(endedMoveException.class,()->tester.specialBuild(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleMovements() throws AlreadyWalkedException, IllegalBuildingException, endedMoveException, IllegalMovementException, AlreadyBuiltException {
        Point p1= new Point(1,1);
        assertNotNull(tester.possibleMovements(instance.getBoard(), player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.possibleMovements(instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleBuildings() throws AlreadyWalkedException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException, AlreadyBuiltException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertThrows(OutOfOrderMoveException.class,()->tester.possibleBuildings(instance.getBoard(),player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertNotNull(tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyBuiltException.class,()->tester.possibleBuildings(instance.getBoard(),player.selectWorker(0)));
    }
}
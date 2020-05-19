package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.EndMoveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TritonMoveSecondTest {
    private Match instance;
    private Player player;
    private Move tester;
    private static Point point = new Point(0,0);
    private Point centerPoint = new Point(1,1);

    public TritonMoveSecondTest() {
        tester = new TritonMove();
        instance= new Match();
        player= new Player("player",instance);
        player.placeWorker(1, point, instance.getBoard());
        player.placeWorker(1, centerPoint, instance.getBoard());
    }

    @DisplayName("Repeated walk test")
    @RepeatedTest(value = 4)
    void repeatedWalkTest() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        Point next = new Point(point.getX(), point.getY()+1);
        tester.walk(next, instance.getBoard(), player.getWorkers().get(0));
        point = next;
    }

    @DisplayName("Center walk test")
    @Test
    void repeatedWalkInCenterTest() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point next = new Point(1,0);
        tester.walk(next, instance.getBoard(), player.getWorkers().get(1));
        next = new Point(2,0);
        tester.walk(next, instance.getBoard(), player.getWorkers().get(1));
        next = new Point(3,0);
        tester.walk(next, instance.getBoard(), player.getWorkers().get(1));
        next = new Point(3,1);
        tester.walk(next, instance.getBoard(), player.getWorkers().get(1));
        assertThrows(AlreadyWalkedException.class, ()->tester.walk(new Point(3,0), instance.getBoard(), player.getWorkers().get(1)));
        tester.build(new Point(3,0), instance.getBoard(), player.getWorkers().get(1));
        assertThrows(endedMoveException.class, ()->tester.build(new Point(3,2), instance.getBoard(), player.getWorkers().get(1)) );
    }

    @Test
    void forcedBuilding() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        tester.walk(new Point(1,2), instance.getBoard(), player.getWorkers().get(1));
        assertTrue(tester.forcedBuilding());
    }

    @Test
    void notForcedBuilding() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        tester.walk(new Point(1,0), instance.getBoard(), player.getWorkers().get(1));
        tester.walk(new Point(2,0), instance.getBoard(), player.getWorkers().get(1));
        tester.walk(new Point(3,0), instance.getBoard(), player.getWorkers().get(1));
        assertTrue(!tester.forcedBuilding());
    }
}
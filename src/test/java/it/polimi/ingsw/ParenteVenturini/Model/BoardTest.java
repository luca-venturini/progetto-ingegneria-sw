package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Match instance;
    private Board tester;
    private Player player;

    @BeforeEach
    void setUp() {
        instance= new Match();
        tester= instance.getBoard();
        player= new Player("player",instance);
    }

    @Test
    void isOccupied() {
        Point p1= new Point(0,0);
        Point p2= new Point(1,1);
        Worker w= new Worker(p1,player,1);
        tester.setWorker(w);
        assertTrue(tester.isOccupied(p1));
        assertFalse(tester.isOccupied(p2));
        assertTrue(tester.isOccupied(0,0));
        assertFalse(tester.isOccupied(1,0));
    }

    @Test
    void setBlockLevel() throws IllegalBlockUpdateException {
        Point p1= new Point(0,0);
        Point p2= new Point(1,1);
        tester.setBlockLevel(p1,1);
        tester.setBlockLevel(p2,2);
        assertEquals(1,tester.blockLevel(p1));
        assertEquals(2,tester.blockLevel(p2));
        assertEquals(0,tester.blockLevel(2,2));
        assertThrows(IllegalBlockUpdateException.class,()->tester.setBlockLevel(p1,5));
        assertThrows(IllegalBlockUpdateException.class,()->tester.setBlockLevel(p1,-3));
    }

    @Test
    void setDoom() {
        Point p1= new Point(0,0);
        Point p2= new Point(1,1);
        tester.setDome(p1,true);
        tester.setDome(p2,false);
        assertTrue(tester.isThereDome(p1));
        assertFalse(tester.isThereDome(p2));
        tester.setDome(p1,false);
        assertFalse(tester.isThereDome(0,0));
    }

    @Test
    void findByPosition() {
        Point p1= new Point(0,0);
        Point p2= new Point(1,1);
        Point p3= new Point(2,3);
        Worker w1= new Worker(p1,player,1);
        Worker w2= new Worker(p2,player,2);
        tester.setWorker(w1);
        tester.setWorker(w2);
        assertNull(tester.findByPosition(p3));
        assertEquals(w1,tester.findByPosition(p1));
        assertEquals(w2,tester.findByPosition(p2));
    }

    @Test
    void isValidPoint() {
        Point p1= new Point(0,0);
        Point p2= new Point(3,5);
        Point p3= new Point(-2,-3);
        Point p4= new Point(-12,32);
        assertTrue(tester.isValidPoint(p1));
        assertFalse(tester.isValidPoint(p2));
        assertFalse(tester.isValidPoint(p3));
        assertFalse(tester.isValidPoint(p4));
    }

    @Test
    void isPerimeterPoint() {
        Point p1= new Point(0,0);
        Point p2= new Point(3,4);
        Point p3= new Point(-2,-3);
        Point p4= new Point(2,2);
        assertTrue(tester.isPerimeterPoint(p1));
        assertTrue(tester.isPerimeterPoint(p2));
        assertFalse(tester.isPerimeterPoint(p3));
        assertFalse(tester.isPerimeterPoint(p4));
    }
}
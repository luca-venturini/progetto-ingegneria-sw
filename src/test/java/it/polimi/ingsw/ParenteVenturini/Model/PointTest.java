package it.polimi.ingsw.ParenteVenturini.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point tester;

    @BeforeEach
    void setUp() {
        tester= new Point(0,0);
    }

    @Test
    void setPoint() {
        tester.setPoint(1,2);
        assertEquals(1,tester.getX());
        assertEquals(2,tester.getY());
    }

    @Test
    void testSetPoint() {
        Point p= new Point(1,1);
        tester.setPoint(p);
        assertEquals(p.getX(),tester.getX());
        assertEquals(p.getY(),tester.getY());
    }

    @Test
    void testEquals() {
        Point p1= new Point(1,1);
        Point p2= new Point(0,0);
        assertFalse(tester.equals(p1));
        assertTrue(tester.equals(p2));
    }
}
package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {
    private Match instance;
    private Worker worker;

    @BeforeEach
    void setUp() {
        instance= new Match();
        Player p= new Player("player",instance);
        Point x= new Point(0,0);
        worker= new Worker(x,p,1);
    }

    @Test
    void getPosition() {
        assertNotNull(worker.getPosition());
    }

    @Test
    void setPosition() {
        Point p1= new Point(1,1);
        Point p2= new Point(1,1);
        worker.setPosition(p1);
        assertEquals(p1, worker.getPosition());
        worker.setPosition(p2);
        assertEquals(p1, worker.getLastPosition());
    }

    @Test
    void setLastPosition() {
        Point p1= new Point(1,1);
        worker.setLastPosition(p1);
        assertEquals(p1, worker.getLastPosition());
    }
}
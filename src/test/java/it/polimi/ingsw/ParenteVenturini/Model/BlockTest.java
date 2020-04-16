package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    private Block tester;

    @BeforeEach
    void setUp() {
        tester= new Block();
    }

    @Test
    void getLevel() {
        assertTrue(tester.getLevel()==0);
    }

    @Test
    void isDome() {
        assertEquals(false, tester.isDome());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-2,5,6,8})
    void updateLevel(int value) throws IllegalBlockUpdateException {
        assertThrows(IllegalBlockUpdateException.class,()->tester.updateLevel(value));
        tester.updateLevel(4);
        assertTrue(tester.isDome());
    }

    @Test
    void setDome() {
        tester.setDome(true);
        assertTrue(tester.isDome());
        tester.setDome(false);
        assertFalse(tester.isDome());
    }
}
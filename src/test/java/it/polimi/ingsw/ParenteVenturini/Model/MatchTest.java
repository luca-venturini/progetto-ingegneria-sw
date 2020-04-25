package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.AthenaCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.ChronusCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.DemeterCard;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class MatchTest {
    private Match instance;

    @BeforeEach
    void setUp() {
        instance= new Match();
    }

    @Test
    void addPlayer() throws NoMorePlayersException, InvalidTypeOfMatch, AlreadyPresentPlayerException {
        instance.addPlayer("player1");
        assertEquals(1, instance.getNumPlayers());
        assertThrows(AlreadyPresentPlayerException.class,()->instance.addPlayer("player1"));
        instance.addPlayer("player2");
        assertEquals(2, instance.getNumPlayers());
        assertThrows(NoMorePlayersException.class,()->instance.addPlayer("player3"));
        instance.setTypeOfMatch(3);
        instance.addPlayer("player3");
        assertEquals(3, instance.getNumPlayers());
        assertThrows(NoMorePlayersException.class,()->instance.addPlayer("player4"));
    }

    @Test
    void gameOver() throws NoMorePlayersException, AlreadyPresentPlayerException, IllegalBlockUpdateException {
        instance.addPlayer("player1");
        instance.selectPlayer("player1").setCard(new DemeterCard());
        instance.setTurn();
        Point p1= new Point(0,0);
        Point p2= new Point(1,0);
        Point p3= new Point(0,1);
        Point p4= new Point(1,1);
        Point p5= new Point(0,4);
        Point p6= new Point(1,4);
        Point p7= new Point(1,3);
        Point p8= new Point(0,3);
        instance.selectPlayer("player1").placeWorker(1,p1,instance.getBoard());
        instance.selectPlayer("player1").placeWorker(1,p5,instance.getBoard());
        instance.selectPlayer("player1").setMove(instance.selectPlayer("player1").callMove());
        instance.getBoard().setBlockLevel(p2,4);
        instance.getBoard().setBlockLevel(p3,4);
        instance.getBoard().setBlockLevel(p4,4);
        assertFalse(instance.gameOver());
        instance.getBoard().setBlockLevel(p6,4);
        instance.getBoard().setBlockLevel(p7,4);
        instance.getBoard().setBlockLevel(p8,4);
        assertTrue(instance.gameOver());
    }

    @Test
    void directGameOver() throws NoMorePlayersException, AlreadyPresentPlayerException, IllegalBlockUpdateException, AlreadyBuiltException, AlreadyWalkedException, NotPossibleEndMoveException, IllegalBuildingException, endedMoveException, OpponentEffectException, IllegalMovementException {
        instance.addPlayer("player1");
        instance.selectPlayer("player1").setCard(new DemeterCard());
        instance.setTurn();
        Point p1= new Point(0,0);
        Point p2= new Point(1,0);
        Point p3= new Point(0,1);
        Point p4= new Point(1,1);
        instance.selectPlayer("player1").placeWorker(1,p2,instance.getBoard());
        instance.selectPlayer("player1").setMove(instance.selectPlayer("player1").callMove());
        instance.getTurn().setActualWorker(instance.selectPlayer("player1").selectWorker(0));
        instance.selectPlayer("player1").walk(p1);
        assertTrue(instance.selectPlayer("player1").getMove().forcedBuilding());
        assertFalse(instance.directGameOver());
        instance.getBoard().setBlockLevel(p2,4);
        instance.getBoard().setBlockLevel(p3,4);
        instance.getBoard().setBlockLevel(p4,4);
        assertTrue(instance.directGameOver());
    }

    @Test
    void getOpponentEffectContainer() {
        assertNotNull(instance.getOpponentEffectContainer());
    }

    @Test
    void getTypeOfMatch() throws InvalidTypeOfMatch {
        assertEquals(2,instance.getTypeOfMatch());
        instance.setTypeOfMatch(3);
        assertEquals(3,instance.getTypeOfMatch());
        instance.setTypeOfMatch(2);
        assertEquals(2,instance.getTypeOfMatch());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,-1,4,120,-2,-3})
    void setTypeOfMatch(int value) throws InvalidTypeOfMatch {
        instance.setTypeOfMatch(3);
        assertEquals(3,instance.getTypeOfMatch());
        assertThrows(InvalidTypeOfMatch.class,()->instance.setTypeOfMatch(value));
    }

    @Test
    void setChallenger() throws NoMorePlayersException, NoPlayerException, AlreadyPresentPlayerException {
        assertNull( instance.getChallenger() );
        assertThrows(NoPlayerException.class,()->instance.setChallenger());
        instance.addPlayer("player1");
        instance.setChallenger();
        assertNotNull( instance.getChallenger());
        instance.addPlayer("player2");
        instance.setChallenger();
        assertNotNull( instance.getChallenger());
        assertTrue(instance.getPlayers().contains(instance.getChallenger()));
    }

    @Test
    void selectPlayer() throws NoMorePlayersException, AlreadyPresentPlayerException {
        assertNull(instance.selectPlayer("Player"));
        instance.addPlayer("player1");
        assertNull(instance.selectPlayer("Player"));
        assertNotNull(instance.selectPlayer("player1"));
    }

    @Test
    void selectCardFromDeck() throws InvalidCardException, NoMoreCardsException {
        assertThrows(InvalidCardException.class,()->instance.selectCardFromDeck("card"));
        instance.selectCardFromDeck("Apollo");
        assertEquals(1, instance.getChosenCards().size());
        assertThrows(InvalidCardException.class,()->instance.selectCardFromDeck("Apollo"));
        instance.selectCardFromDeck("Minotaur");
        assertThrows(NoMoreCardsException.class,()->instance.selectCardFromDeck("Pan"));

    }

    @Test
    void selectStarter() throws NoMorePlayersException, AlreadyPresentPlayerException, AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException {
        assertThrows(NoPlayerException.class,()->instance.selectStarter("player"));
        instance.addPlayer("player1");
        instance.addPlayer("player2");
        assertThrows(InvalidNamePlayerException.class,()->instance.selectStarter("player"));
        instance.selectStarter("player1");
        assertNotNull(instance.getStarter());
        assertThrows(AlreadyChosenStarterException.class,()->instance.selectStarter("player2"));
    }

    @Test
    void orderPlayers() throws NoMorePlayersException, AlreadyPresentPlayerException, AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException {
        assertThrows(NoPlayerException.class,()->instance.orderPlayers());
        instance.addPlayer("player1");
        assertThrows(NoStarterException.class,()->instance.orderPlayers());
        instance.addPlayer("player2");
        instance.selectStarter("player2");
        assertEquals(instance.selectPlayer("player2"),instance.getPlayers().get(0));
    }

    @Test
    void getBoard() {
        assertNotNull(instance.getBoard());
    }

    @Test
    void getChallenger() throws NoMorePlayersException, NoPlayerException, AlreadyPresentPlayerException {
        assertNull( instance.getChallenger() );
        instance.addPlayer("player1");
        instance.setChallenger();
        assertNotNull(instance.getChallenger());
    }

    @Test
    void getPlayers() throws NoMorePlayersException, AlreadyPresentPlayerException, NoPlayerException {
        assertThrows(NoPlayerException.class,()->instance.getPlayers());
        instance.addPlayer("player1");
        assertEquals(1, instance.getPlayers().size());
        instance.addPlayer("player2");
        assertEquals(2, instance.getPlayers().size());

    }

    @Test
    void getNumPlayers() throws NoMorePlayersException, AlreadyPresentPlayerException {
        assertEquals(0,instance.getNumPlayers());
        instance.addPlayer("player1");
        assertEquals(1,instance.getNumPlayers());
        instance.addPlayer("player2");
        assertEquals(2,instance.getNumPlayers());
    }

    @Test
    void outOfTurnWin() throws NoMorePlayersException, AlreadyPresentPlayerException, IllegalBlockUpdateException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        Point p3= new Point(0,1);
        Point p4= new Point(3,1);
        Point p5= new Point(4,1);
        instance.addPlayer("player1");
        instance.addPlayer("player2");
        instance.selectPlayer("player1").setCard(new ChronusCard());
        instance.selectPlayer("player2").setCard(new AthenaCard());
        assertNull(instance.outOfTurnWin());
        instance.getBoard().setBlockLevel(p1,4);
        instance.getBoard().setBlockLevel(p2,4);
        instance.getBoard().setBlockLevel(p3,4);
        instance.getBoard().setBlockLevel(p4,4);
        instance.getBoard().setBlockLevel(p5,4);
        assertEquals("player1", instance.outOfTurnWin().getNickname());
    }

}
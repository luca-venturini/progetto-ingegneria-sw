package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.*;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.AthenaEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ApolloMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HephaestusMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Match instance;
    private Player testplayer;

    @BeforeEach
    void setUp() throws NoMorePlayersException, AlreadyPresentPlayerException, NoPlayerException {
        instance= new Match();
        instance.addPlayer("player");
        testplayer=instance.getPlayers().get(0);
    }

    @Test
    void setCard() {
        testplayer.setCard(new ApolloCard());
        assertTrue(testplayer.getCard() instanceof ApolloCard);
    }

    @Test
    void placeWorker_selectWorker() {
        Point p1= new Point(3,3);
        Point p2= new Point(2,2);
        testplayer.placeWorker(1,p1,instance.getBoard());
        testplayer.placeWorker(1,p2,instance.getBoard());
        assertEquals(testplayer.selectWorker(0),instance.getBoard().findByPosition(p1));
        assertEquals(testplayer.selectWorker(1),instance.getBoard().findByPosition(p2));
    }

    @Test
    void callMove() {
        testplayer.setCard(new ApolloCard());
        assertTrue(testplayer.callMove() instanceof ApolloMove);
        testplayer.setCard(new PanCard());
        assertTrue(testplayer.callMove() instanceof PanMove);
        testplayer.setCard(new HephaestusCard());
        assertTrue(testplayer.callMove() instanceof HephaestusMove);
    }

    @Test
    void setNickname() {
        testplayer.setNickname("PLAYER");
        assertEquals("PLAYER",testplayer.getNickname());
    }

    @Test
    void chooseCard() throws InvalidCardException, NoMoreCardsException {
        instance.selectCardFromDeck("Apollo");
        instance.selectCardFromDeck("Zeus");
        assertThrows(InvalidCardException.class,()->testplayer.chooseCard("Apollo-False"));
        assertThrows(InvalidCardException.class,()->testplayer.chooseCard("Minotaur"));
    }

    @Test
    void hasWon() throws IllegalBlockUpdateException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException, NoPlayerException {
        Point p1= new Point(2,2);
        Point p2= new Point(1,1);
        testplayer.setCard(new ArtemisCard());
        instance.setTurn();
        testplayer.placeWorker(1,p1,instance.getBoard());
        instance.getBoard().setBlockLevel(p1,2);
        instance.getBoard().setBlockLevel(p2,3);
        instance.getTurn().setActualWorker(testplayer.selectWorker(0));
        assertFalse(testplayer.hasWon(instance.getBoard(),testplayer.selectWorker(0),instance.getPlayers()));
        testplayer.walk(p2);
        assertTrue(testplayer.hasWon(instance.getBoard(),testplayer.selectWorker(0),instance.getPlayers()));
    }

    @Test
    void getNickname() {
        assertNotNull(testplayer.getNickname());
    }

    @Test
    void getOpponentEffectPlayer() {
        testplayer.setCard(new ApolloCard());
        assertNull(testplayer.getOpponentEffectPlayer());
        testplayer.setCard(new AthenaCard());
        assertTrue(testplayer.getOpponentEffectPlayer() instanceof AthenaEffect);
    }

    @Test
    void walk() throws IllegalBlockUpdateException, NoMorePlayersException, AlreadyPresentPlayerException, AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException {
        Point p0= new Point(2,2);
        Point p1= new Point(1,1);
        instance.addPlayer("player2");
        testplayer.setCard(new ApolloCard());
        instance.selectStarter("player");
        instance.setTurn();
        testplayer.placeWorker(1,p0,instance.getBoard());
        instance.getBoard().setBlockLevel(p1,2);
        instance.setTurn();
        instance.getTurn().setActualWorker(testplayer.selectWorker(0));
        instance.getOpponentEffectContainer().addEffect(new AthenaEffect());
        instance.getOpponentEffectContainer().switchToNewTurn();
        assertThrows(OpponentEffectException.class,()->testplayer.walk(p1));
    }

    @Test
    void build() throws AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException, IllegalBlockUpdateException, NoMorePlayersException, AlreadyPresentPlayerException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException, OutOfOrderMoveException {
        Point p0= new Point(2,2);
        Point p1= new Point(1,1);
        Point p2= new Point(1,2);
        instance.addPlayer("player2");
        testplayer.setCard(new HestiaCard());
        instance.selectStarter("player");
        instance.setTurn();
        testplayer.placeWorker(1,p0,instance.getBoard());
        instance.setTurn();
        instance.getTurn().setActualWorker(testplayer.selectWorker(0));
        testplayer.walk(p1);
        testplayer.build(p2);
        assertTrue(instance.getBoard().blockLevel(p2)==1);
    }

    @Test
    void specialBuild() throws NoMorePlayersException, AlreadyPresentPlayerException, AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException, OutOfOrderMoveException {
        Point p0= new Point(2,2);
        Point p1= new Point(1,1);
        Point p2= new Point(1,2);
        instance.addPlayer("player2");
        testplayer.setCard(new PanCard());
        instance.selectStarter("player");
        instance.setTurn();
        testplayer.placeWorker(1,p0,instance.getBoard());
        instance.setTurn();
        instance.getTurn().setActualWorker(testplayer.selectWorker(0));
        testplayer.walk(p1);
        testplayer.specialBuild(p2);
        assertTrue(instance.getBoard().blockLevel(p2)==1);
    }

    @Test
    void endMove() {
        Point p1= new Point(2,2);
        Point p2= new Point(1,1);
        Point p3= new Point(2,1);
        assertThrows(NotPossibleEndMoveException.class,()->testplayer.endMove());
        testplayer.setCard(new ArtemisCard());
        instance.setTurn();
        testplayer.placeWorker(1,p1,instance.getBoard());
        testplayer.callMove();
        assertThrows(NotPossibleEndMoveException.class,()->testplayer.endMove());
    }


}
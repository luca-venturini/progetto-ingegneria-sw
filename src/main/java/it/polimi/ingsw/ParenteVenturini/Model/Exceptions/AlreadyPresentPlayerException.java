package it.polimi.ingsw.ParenteVenturini.Model.Exceptions;

/**
 * exception thrown if a player with the same characteristics already exists in the match
 */
public class AlreadyPresentPlayerException extends Exception {
    /** init the exception class */
    public AlreadyPresentPlayerException() {
        super();
    }
}

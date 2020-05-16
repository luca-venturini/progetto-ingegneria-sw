package it.polimi.ingsw.ParenteVenturini.Model.Exceptions;

/**
 * exception thrown if the selected move can not be done in that moment
 */
public class OutOfOrderMoveException extends Exception {
    /** init the exception class */
    public OutOfOrderMoveException() {
        super();
    }
}

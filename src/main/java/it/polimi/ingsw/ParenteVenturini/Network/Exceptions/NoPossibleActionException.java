package it.polimi.ingsw.ParenteVenturini.Network.Exceptions;

public class NoPossibleActionException extends Exception {
    private String errorMessage="";

    public NoPossibleActionException() {
        super();
    }

    public NoPossibleActionException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

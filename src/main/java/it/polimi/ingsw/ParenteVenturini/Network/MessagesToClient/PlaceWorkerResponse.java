package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class PlaceWorkerResponse implements MessageToClient {

    private boolean isSet;
    private boolean hasFinished;
    private String message;
    private Point point;

    public PlaceWorkerResponse(boolean isSet, boolean hasFinished, String message, Point point) {
        this.isSet = isSet;
        this.hasFinished = hasFinished;
        this.message = message;
        this.point = point;
    }

    public boolean isSet() {
        return isSet;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public String getMessage() {
        return message;
    }

    public Point getSettedPoint() {
        return point;
    }

    @Override
    public MessageType getMessageType() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return null;
    }

    @Override
    public void accept(ClientMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

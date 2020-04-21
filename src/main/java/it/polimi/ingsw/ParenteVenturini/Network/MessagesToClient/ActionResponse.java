package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class ActionResponse implements  MessageToClient{
    private boolean done;
    private List<Point> points;
    private String message;

    public ActionResponse(List<Point> points, String message, boolean done) {
        this.points = points;
        this.message = message;
        this.done = done;
    }

    public List<Point> getPoints() {
        return points;
    }

    public String getMessage() {
        return message;
    }

    public boolean isDone() {
        return done;
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

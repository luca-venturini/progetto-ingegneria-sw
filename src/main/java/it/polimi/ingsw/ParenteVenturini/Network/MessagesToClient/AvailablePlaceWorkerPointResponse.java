package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class AvailablePlaceWorkerPointResponse implements MessageToClient {

    private List<Point> points;
    private String actualPlayer;

    public AvailablePlaceWorkerPointResponse(List<Point> points, String actualPlayer) {
        this.actualPlayer = actualPlayer;
        this.points = points;
    }

    public String getActualPlayer() {
        return actualPlayer;
    }

    public List<Point> getPoints() {
        return points;
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

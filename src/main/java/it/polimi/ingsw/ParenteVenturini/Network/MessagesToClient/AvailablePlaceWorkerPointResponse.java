package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class AvailablePlaceWorkerPointResponse implements MessageToClient {

    private List<Point> points;
    private String actualPlayer;
    private List<Point> workersPoint;
    private List<Integer> workersColor;

    public AvailablePlaceWorkerPointResponse(List<Point> points, String actualPlayer, List<Point> workersPoint, List<Integer> workersColor) {
        this.points = points;
        this.actualPlayer = actualPlayer;
        this.workersPoint = workersPoint;
        this.workersColor = workersColor;
    }

    public List<Point> getWorkersPoint() {
        return workersPoint;
    }

    public List<Integer> getWorkersColor() {
        return workersColor;
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

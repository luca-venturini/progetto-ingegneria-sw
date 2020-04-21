package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.List;

public class ActionPointRequest implements MessageToServer {
    private Point point;
    private String nickname;

    public ActionPointRequest(Point point, String nickname) {
        this.point = point;
        this.nickname = nickname;
    }

    public Point getPoint() {
        return point;
    }

    public String getNickname() {
        return nickname;
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
    public void accept(ServerMessageHandler msgHandler) {

    }
}

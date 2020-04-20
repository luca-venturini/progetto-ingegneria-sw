package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.List;

public class EndMoveRequest implements MessageToServer {
    private String nickname;

    public EndMoveRequest(String nickname) {
        this.nickname = nickname;
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

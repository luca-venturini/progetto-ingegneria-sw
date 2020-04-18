package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.Arrays;
import java.util.List;

public class SetStartingPlayerRequest implements MessageToServer {

    private String nickname;
    private String startingPlayer;

    public SetStartingPlayerRequest(String nickname, String startingPlayer) {
        this.nickname = nickname;
        this.startingPlayer = startingPlayer;
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
        return Arrays.asList(startingPlayer);
    }

    @Override
    public void accept(ServerMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

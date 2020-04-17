package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.Arrays;
import java.util.List;

public class SetPlayerCardRequest implements MessageToServer {

    private String nickname;
    private String cardName;

    public SetPlayerCardRequest(String nickname, String cardName) {
        this.nickname = nickname;
        this.cardName = cardName;
    }

    @Override
    public MessageType getMessageType() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return Arrays.asList(cardName);
    }

    @Override
    public void accept(ServerMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.ArrayList;
import java.util.List;

public class AccessGameMessageRequest implements MessageToServer {
    MessageType messageType = MessageType.REQUEST_LOGIN;

    String nickname;
    String numOfPeople;

    public AccessGameMessageRequest(String nickname, String numOfPeople) {
        this.nickname = nickname;
        this.numOfPeople = numOfPeople;
    }

    public String getNickname() {
        return nickname;
    }

    public List<String> getValues(){
        List<String> values = new ArrayList<String>();
        values.add(numOfPeople);
        return values;
    }

    @Override
    public void accept(ServerMessageHandler msgVisitor) {
        msgVisitor.visit(this);
    }


    public MessageType getMessageType() {
        return messageType;
    }
}

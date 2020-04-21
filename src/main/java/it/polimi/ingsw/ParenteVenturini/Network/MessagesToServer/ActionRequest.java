package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.List;

public class ActionRequest implements MessageToServer {
    private String nickname;
    private String typeOfAction;

    public ActionRequest(String nickname,String typeOfAction) {
        this.nickname = nickname;
        this.typeOfAction = typeOfAction;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTypeOfAction() {
        return typeOfAction;
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
        msgHandler.visit(this);
    }
}

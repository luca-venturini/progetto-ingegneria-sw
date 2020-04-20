package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.List;

public class SelectWorkerRequest implements MessageToServer {
    private String nickname;
    private String index;

    public SelectWorkerRequest(String nickname, String index) {
        this.nickname = nickname;
        this.index = index;
    }

    public int getIndex(){
        return Integer.parseInt(index);
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

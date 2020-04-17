package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.ArrayList;
import java.util.List;

public class StoreSelectedCardsRequest implements MessageToServer {

    private List<String> choosenCards;
    private String nickname;

    public StoreSelectedCardsRequest(String nickname, List<String> choosenCards) {
        this.nickname = nickname;
        this.choosenCards = choosenCards;
    }

    public StoreSelectedCardsRequest(String nickname, String choosenCards) {
        this.nickname = nickname;
        this.choosenCards = new ArrayList<>();
        this.choosenCards.add(choosenCards);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SELECT_CARD;
    }

    @Override
    public List<String> getValues() {
        return choosenCards;
    }

    public String getNickname(){
        return nickname;
    }

    @Override
    public void accept(ServerMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

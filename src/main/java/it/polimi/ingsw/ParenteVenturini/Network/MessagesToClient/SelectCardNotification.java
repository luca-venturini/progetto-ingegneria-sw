package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class SelectCardNotification implements MessageToClient {

    private MessageType messageType = MessageType.SELECT_CARD;
    private int numberOfCardsRequired;
    private List<String> cardsName;

    public SelectCardNotification(List<String> cardsName, int numberOfCardsRequired) {
        this.cardsName = cardsName;
        this.numberOfCardsRequired = numberOfCardsRequired;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public List<String> getValues() {
        return cardsName;
    }

    public int numberOfCardsRequired(){
        return numberOfCardsRequired;
    }

    @Override
    public void accept(ClientMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

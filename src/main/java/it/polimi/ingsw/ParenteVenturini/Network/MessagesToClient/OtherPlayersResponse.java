package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class OtherPlayersResponse implements MessageToClient {

    private List<String> nicknames;
    private List<String> cards;
    private List<Integer> colors;

    public OtherPlayersResponse(List<String> nicknames, List<String> cards, List<Integer> colors) {
        this.nicknames = nicknames;
        this.cards = cards;
        this.colors = colors;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public List<String> getPlayersCards() {
        return cards;
    }

    public List<Integer> getPlayersColors() {
        return colors;
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

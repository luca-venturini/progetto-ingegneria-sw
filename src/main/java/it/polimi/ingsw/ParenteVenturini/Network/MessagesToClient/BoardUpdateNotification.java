package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class BoardUpdateNotification implements MessageToClient {
    private Block[][] blocks;
    private List<Point> workerpositions;
    private List<String> colours;
    private List<String> index;

    public BoardUpdateNotification(Block[][]blocks,List<Point>workerpositions,List<String>colours, List<String> index){
        this.blocks=blocks;
        this.workerpositions=workerpositions;
        this.colours=colours;
        this.index=index;
        /*
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                System.out.println("level: "+blocks[i][j].getLevel());
            }
        }
        
         */
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public List<Point> getWorkerpositions() {
        return workerpositions;
    }

    public List<String> getColours() {
        return colours;
    }

    public List<String> getIndex() {
        return index;
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

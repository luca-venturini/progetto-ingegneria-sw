package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

public class BasicConstruction implements Action{

    @Override
    public void doAction(Point point, Board board, Worker worker){
        if(isValid(point, board, worker)){
            int i=0;
        }
        else
            try {
                throw new IllegalBuildingException();
            } catch (IllegalBuildingException e) {
                e.printStackTrace();
            }
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {

        return false;
    }
}

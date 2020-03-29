package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import com.sun.tools.javac.util.List;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

public class AtlasMove extends Move {

    @Override
    public void walk(Board board, Worker worker) {

    }

    @Override
    public void build(Board board, Worker worker) {

    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker) {
        return null;
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) {
        return null;
    }
}

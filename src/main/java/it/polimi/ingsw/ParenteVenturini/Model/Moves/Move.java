package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import com.sun.tools.javac.util.List;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;


public abstract class Move {

    public abstract void walk(Board board, Worker worker);

    public abstract void build(Board board, Worker worker);

    public abstract List<Point> possibleMovements(Board board, Worker worker);

    public abstract List<Point> possibleBuildings(Board board, Worker worker);

}

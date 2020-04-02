package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;

import java.util.List;

public interface OpponentEffect {

    List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board);
    List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board);
}

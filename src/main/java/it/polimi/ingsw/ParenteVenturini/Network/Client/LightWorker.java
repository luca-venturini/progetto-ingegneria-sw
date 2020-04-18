package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Model.Point;

public class LightWorker {

    private Point position;

    public LightWorker(Point position) {
        this.position = position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}

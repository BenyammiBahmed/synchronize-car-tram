package UIclass;

import Animation.Point;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;


public class TramUi extends Pane {
    final private Rectangle tram = new Rectangle(50, 15);
    final private Point pointStaet,pointFin;

    public Rectangle getTram() {
        return tram;
    }

    public Point getPointStaet() {
        return pointStaet;
    }

    public Point getPointFin() {
        return pointFin;
    }

    public TramUi(Point pointStaet, Point pointFin) {
        this.pointStaet = pointStaet;
        this.pointFin = pointFin;
        tram.setX(pointStaet.getX());
        tram.setY(pointStaet.getY());
        tram.setFill(Color.BLUE);
        this.setWidth(tram.getWidth());
        this.setHeight(tram.getHeight());
        this.getChildren().add(tram);

    }
}

package UIclass;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class RoadCar extends Pane {
    private final Rectangle blakRoad=new Rectangle(60,600);
    private final Rectangle white =new Rectangle(4,600);
    private final Rectangle edgeRoadLeft =new Rectangle(2,600);
    private final Rectangle edgeRoadRight =new Rectangle(2,600);
    private int postX,postY;

    public Rectangle getBlakReod() {
        return blakRoad;
    }

    public Rectangle getWhite() {
        return white;
    }

    public int getPostX() {
        return postX;
    }

    public void setPostX(int postX) {
        this.postX = postX;
    }

    public int getPostY() {
        return postY;
    }

    public void setPostY(int postY) {
        this.postY = postY;
    }

    public RoadCar(int postX, int postY) {
        this.postX = postX;
        this.postY = postY;
        blakRoad.setFill(Color.BLACK );
        blakRoad.setX(postX);
        blakRoad.setY(postY);
        white.setFill(Color.WHITE);
        white.setX(postX+blakRoad.getWidth()/2);
        white.setY(postY);
        edgeRoadLeft.setFill(Color.GREEN);
        edgeRoadRight.setFill(Color.GREEN);
        edgeRoadLeft.setX(postX+blakRoad.getWidth());
        edgeRoadRight.setX(postX);
        edgeRoadLeft.setY(postY);
        edgeRoadRight.setY(postY);
        this.getChildren().addAll(blakRoad,white,edgeRoadLeft,edgeRoadRight);


    }

    public RoadCar(int postX, int postY, Node... children) {
        super(children);
        this.postX = postX;
        this.postY = postY;
    }
}

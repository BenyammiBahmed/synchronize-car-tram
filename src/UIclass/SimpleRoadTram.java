package UIclass;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SimpleRoadTram extends Pane {
    final private Rectangle road = new Rectangle();
    final private Rectangle lineElectrque = new Rectangle();
    final private Rectangle backgraend = new Rectangle();
    final private Group group=new Group();
    private int postX;
    private int postY;
    private int width;

    public SimpleRoadTram(int postX, int postY, int width) {
        this.postX = postX;
        this.postY = postY;
        this.width=width;
        road.setWidth(width);
        road.setHeight(4);
        lineElectrque.setWidth(width);
        lineElectrque.setHeight(1);
        backgraend.setWidth(width);
        backgraend.setHeight(15);
        road.setX(postX);
        road.setY(postY);
        backgraend.setX(postX);
        backgraend.setY(postY-backgraend.getHeight());
        lineElectrque.setX(postX);
        lineElectrque.setY(postY-backgraend.getHeight()-lineElectrque.getHeight());
        road.setFill(Color.GRAY);
        lineElectrque.setFill(Color.GRAY);
        backgraend.setFill(Color.YELLOW);
        group.getChildren().addAll(road,backgraend,lineElectrque);
        this.getChildren().addAll(group);
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


    public Rectangle getRoad() {
        return road;
    }

    public Rectangle getLineElectrque() {
        return lineElectrque;
    }

    public Rectangle getBackgraend() {
        return backgraend;
    }


}

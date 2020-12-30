package UIclass;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObliqueRoadTram extends Pane {
    final private Rectangle road = new Rectangle();
    final private Rectangle lineElectrque = new Rectangle();
    final private Rectangle backgraend = new Rectangle();
    final private Group group=new Group();

    public Rectangle getRoad() {
        return road;
    }

    public Group getGroup() {
        return group;
    }



    public void setHeight(int height) {
        Height = height;
    }

    private int postX;
    private int postY;
    private int Height;

    public ObliqueRoadTram(int postX, int postY, int Height) {
        this.postX = postX;
        this.postY = postY;
        this.Height = Height;
        road.setHeight(Height);
        road.setWidth(4);
        lineElectrque.setHeight(Height);
        lineElectrque.setWidth(1);
        backgraend.setHeight(Height);
        backgraend.setWidth(15);
        road.setX(postX);
        road.setY(postY);
        backgraend.setX(postX+road.getWidth());
        backgraend.setY(postY);
        lineElectrque.setX(postX+backgraend.getWidth()+road.getWidth());
        lineElectrque.setY(postY);
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


    public Rectangle getRoadpRight() {
        return road;
    }

    public Rectangle getLineElectrque() {
        return lineElectrque;
    }

    public Rectangle getBackgraend() {
        return backgraend;
    }

}

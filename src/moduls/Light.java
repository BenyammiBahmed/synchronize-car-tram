package moduls;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.concurrent.Semaphore;

public class Light extends Pane {
    private Circle circle=new Circle();
    private int postX,postY;

    public Semaphore getSemaphore() {
        return semaphore;
    }

    private Semaphore semaphore=new Semaphore(1,true);

    public Light(int postX ,int postY) {
        this.postX=postX;
        this.postY=postY;
        circle.setCenterX(postX);
        circle.setCenterY(postY);
        circle.setRadius(5);


        circle.setFill(Color.GREEN);
        this.getChildren().add(circle );

    }


   public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
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

    public void chagneColor(){
        if (circle.getFill()==Color.RED)
            circle.setFill(Color.GREEN);
        else
            circle.setFill(Color.RED);
    }

}

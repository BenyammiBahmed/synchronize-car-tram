package UIclass;

import Animation.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.concurrent.Semaphore;

public class CarUi extends Pane  {
    final private Point pointStart,pointFin;
    final private Image car = new Image("Image\\cartoBotton1.png");
    final private ImageView imageView = new ImageView(car);
    // Defines this Vehicle's position
      private int postY;




    public Image getCar() {
        return car;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Point getPointStart() {
        return pointStart;
    }

    public Point getPointFin() {
        return pointFin;
    }

    public int getPostY() {
        return postY;
    }

    public void setPostY(int postY) {
        this.postY = postY;
    }

    public CarUi(Point pointStart, Point pointFin) {
        this.pointStart = pointStart;
        this.pointFin = pointFin;
        postY=pointStart.getY();
        imageView.setX(pointStart.getX());
        imageView.setY(pointStart.getY());
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        this.setWidth(car.getWidth());
        this.setHeight(car.getHeight());
        this.getChildren().addAll(imageView);

    }


}

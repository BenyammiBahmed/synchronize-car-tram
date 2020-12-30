package Animation;

import Threads.Car;
import Threads.MyThread;
import UIclass.CarUi;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import moduls.Light;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CarAnimation extends AnimationTimer {

    private Car car;
    private CarUi carUi;
    private Pane root;
    private Light[] lights = new Light[2];
    private Semaphore[] strack;
    private ArrayList<MyThread> threads;

    public CarAnimation(Semaphore[] sTrack, CarUi carUi, Pane root, ArrayList<MyThread> threads,Light... lights) {
//
        this.threads=threads;
        this.lights[0] = lights[0];
        this.lights[1] = lights[1];
        this.strack = sTrack;
        car = new Car(carUi.getPointStart(), carUi.getPointFin(), strack, (int) carUi.getImageView().getFitHeight(), this.lights);
        threads.add(car);
        car.setRun(true);
        this.carUi = carUi;

        this.root = root;
        this.start();
    }

    @Override
    public void handle(long now) {
        if (car.isAlive())
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    carUi.getImageView().setY(car.getPostY());
                }
            });

        else
        {
            threads.remove(car);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    root.getChildren().remove(carUi);
                    stop();
                }
            });}


    }

}

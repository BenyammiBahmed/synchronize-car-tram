package Animation;

import Threads.MyThread;
import Threads.Tramcar1;
import UIclass.SimpleRoadTram;
import UIclass.TramUi;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import moduls.Light;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Tram1Animation extends AnimationTimer {
    private TramUi tram;
    private Group road;
    private int postX, postY;
    Tramcar1 tramcar1;

    public TramUi getTram() {
        return tram;
    }

    public void setTram(TramUi tram) {
        this.tram = tram;
    }

    public Tram1Animation(Group road, TramUi t, SimpleRoadTram roadSc, Semaphore ScTram, Light[]Lights, ArrayList<MyThread> threads) {

        this.tram = t;
        this.road = road;
        tramcar1 =new Tramcar1(road,tram.getPointStaet(),tram.getPointFin() , (int) tram.getTram().getWidth(), roadSc,ScTram,Lights);
        tramcar1.start();
        threads.add(tramcar1);
        this.start();
    }

    @Override
    public void handle(long now)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tram.getTram().setX(tramcar1.getPostX());
                tram.getTram().setY(tramcar1.getPostY());
                tram.getTram().setRotate(tramcar1.getRotat());
            }
        });


    }

}

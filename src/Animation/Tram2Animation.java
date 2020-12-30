package Animation;

import Threads.MyThread;
import Threads.Tramcar1;
import Threads.Tramcar2;
import UIclass.SimpleRoadTram;
import UIclass.TramUi;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import moduls.Light;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Tram2Animation extends AnimationTimer {
    private TramUi tram;
    private Group road;
    private int postX, postY;
    Tramcar2 tramcar;

    public TramUi getTram() {
        return tram;
    }

    public void setTram(TramUi tram) {
        this.tram = tram;
    }

    public Tram2Animation(Group road, TramUi t, SimpleRoadTram roadSc , Semaphore ScTram ,Light[]Lights,ArrayList <MyThread> threads){
        this.start();
        this.tram = t;
        this.road = road;
        tramcar =new Tramcar2(road,tram.getPointStaet(),tram.getPointFin() , (int) tram.getTram().getWidth(), roadSc,ScTram,Lights);
        tramcar.start();
        threads.add(tramcar);
    }

    @Override
    public void handle(long now)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tram.getTram().setX(tramcar.getPostX());
                tram.getTram().setY(tramcar.getPostY());
                tram.getTram().setRotate(tramcar.getRotat());
            }
        });

    }
}

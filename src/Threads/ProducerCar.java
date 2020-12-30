package Threads;

import Animation.CarAnimation;
import Animation.Point;
import UIclass.CarUi;
import UIclass.RoadCar;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import moduls.Light;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
public class ProducerCar extends MyThread {
    // road of car
    RoadCar roadCarRight;
    RoadCar roadCarLeft;
    // semaphore of road car
    Semaphore [] road1 ;
    Semaphore [] road2;
    Semaphore [] road3;
    Semaphore [] road4;
    // semaphore of light
    Light[]lights;
    // first pane of interfac
    Pane root;
    ArrayList<MyThread> threads;
   public ProducerCar(RoadCar roadCarRight, RoadCar roadCarLeft, Pane root, ArrayList <MyThread> threads, Light[] lights,Semaphore[]road1,Semaphore[]road2,Semaphore[]road3,Semaphore[]road4){
        this.roadCarRight=roadCarRight;
        this.roadCarLeft=roadCarLeft;
        this.root=root;
        this.threads=threads;
        this.road1=road1;
        this.road2=road2;
        this.road3=road3;
        this.road4=road4;
        this.lights=lights;
   }
    @Override
    public void run()
    {

        while (true){
            //test button start clicked
           TestRun();

            try {
            sleep((long) (Math.random()*1000));
        int r= (int) ((Math.random()*100)%4);
        switch (r){
            // produce car in road1
        case 0:

                road1[0].acquire();

             CarUi carUi =new CarUi(new Point(roadCarRight.getPostX(), 45),new Point(roadCarRight.getPostX(), 645) );
             CarAnimation carAnimation=new CarAnimation(road1,carUi,root,threads,lights[0],lights[1]);
            CarUi finalCarUi3 = carUi;
            //add car a first pane
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    root.getChildren().add(finalCarUi3);
                }
            });


            road1[0].release();

            break;
            // produce car in road2

            case 1:
                road2[road2.length-1].acquire();
            carUi =new CarUi(new Point(roadCarRight.getPostX()+30, 645),new Point(roadCarRight.getPostX(), 45) );
            carAnimation=new CarAnimation(road2,carUi,root,threads,lights[2],lights[3]);

            CarUi finalCarUi2 = carUi;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    root.getChildren().add(finalCarUi2);
                }
            });
                road2[road2.length-1].release();
            break;
            // produce car in road3

            case 2:
            road3[0].acquire();
            carUi =new CarUi(new Point(roadCarLeft.getPostX(), 45),new Point(roadCarRight.getPostX()+5, 645) );
            carAnimation=new CarAnimation(road3,carUi,root,threads,lights[4],lights[5]);

            CarUi finalCarUi1 = carUi;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    root.getChildren().add(finalCarUi1);
                }
            });
            road3[0].release();
            break;
            // produce car in road4

            case 3:
                road4[road4.length-1].acquire();
            carUi =new CarUi(new Point(roadCarLeft.getPostX()+30, 645),new Point(roadCarRight.getPostX(), 45) );
            carAnimation=new CarAnimation(road4,carUi,root,threads,lights[6],lights[7]);

            CarUi finalCarUi = carUi;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    root.getChildren().add(finalCarUi);
                }
            });
                road4[road4.length-1].release();
            break;
        }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}


    }
}

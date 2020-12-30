package Threads;

//import moduls.Direction;
//import moduls.Light;

import Animation.Point;
import UIclass.ObliqueRoadTram;
import UIclass.SimpleRoadTram;
import javafx.scene.Group;
import moduls.Light;

import java.util.concurrent.Semaphore;

public class Tramcar1 extends MyThread {
    // position of tramway
    private int postX,postY,rotat=0;
    //speed of trame
    private int speed=8;
    //    private Semaphore leftLight;
    private Light[] lights;
    //width of tram
    private int width;
// road char bettween trams
    private Semaphore ScTrams;
    //road of tram
    final private Group road;
    //point of start and point of fin
    final private Point pointStart,pointFin;
    //semaphore of  road char bettween trams
    final private SimpleRoadTram sc;
    // position test semaphore of road char bettween trams
    private int positionScTest;
    public int getRotat() {
        return rotat;
    }

    private static int c=0;
    public int getPostX() {
        return postX;
    }
    public int getPostY() {
        return postY;
    }

    public void setPostY(int postY) {
        this.postY = postY;
    }

    private Semaphore[] sTrack;


    public void setPostX(int postX) {
        this.postX = postX;
    }



    public Tramcar1(Group road, Point pointStart, Point pointFin, int width, SimpleRoadTram sc,Semaphore ScTrams,Light []lights) {
        this.road = road;
        this.ScTrams=ScTrams;
        this.lights=lights;
        this.pointStart = pointStart;
        this.pointFin = pointFin;
        postX=pointStart.getX();
        postY=pointStart.getY();
        this.width=width;
        this.sc = sc;
        positionScTest= (int) (((ObliqueRoadTram)road.getChildren().get(1)).getBackgraend().getHeight()/2)+200;



    }


    @Override
    public void run() {
    tranfor();


    }
    public void tranfor (){
      while (true){
          forward();
          try {
              sleep(400);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          back();
      }

    }

    private void forward() {


        // tset tramCar starts running
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(1)).getBackgraend().getX()-width+15)){
            TestRun();
            try{
                sleep(speed);
                // First, test posistion equls position light
                if (postX==lights[0].getPostX()-width)
                {
                    // First, wait for the first semaphore light.
                    lights[0].getSemaphore().acquire();
                    lights[0].chagneColor();
//                    First, wait for the seconde semaphore light.
                    lights[1].getSemaphore().acquire();
                    lights[1].chagneColor();
                }
                // Release for the other cars
                if (postX==lights[1].getPostX())
                {
                    lights[0].chagneColor();
                    lights[0].getSemaphore().release();
                    lights[1].chagneColor();
                    lights[1].getSemaphore().release();
                }

                postX++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        postX= (int) ((ObliqueRoadTram) road.getChildren().get(1)).getBackgraend().getX()-17;
        rotat=90;



        while (!(postY==sc.getLineElectrque().getY()+1)){
            TestRun();

            try{

                sleep(speed);
                postY++;
                if (postY==positionScTest)
                    ScTrams.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=0;
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(2)).getPostX()-15)){
            TestRun();
            try{
                sleep(speed);
                postX++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=90;
        while (!(postY==((SimpleRoadTram)road.getChildren().get(3)).getBackgraend().getY())){
            TestRun();
            try{
                sleep(speed);
                postY--;
                if (postY==positionScTest+20)
                    ScTrams.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        rotat=0;
        while (!(postX==((SimpleRoadTram)road.getChildren().get(3)).getBackgraend().getX()-width+((SimpleRoadTram)road.getChildren().get(3)).getBackgraend().getWidth())){
            TestRun();
            try{
                sleep(speed);
                // First, test posistion equls position light
                if (postX==lights[2].getPostX()-width)
                {
                    // First, wait for the first semaphore light.
                    lights[2].getSemaphore().acquire();
                    lights[2].chagneColor();
                    // First, wait for the seconde semaphore light.
                    lights[3].getSemaphore().acquire();
                    lights[3].chagneColor();
                }
                // Release for the other cars
                if (postX==lights[3].getPostX())
                {
                    lights[2].chagneColor();
                    lights[2].getSemaphore().release();
                    lights[3].chagneColor();
                    lights[3].getSemaphore().release();
                }
                postX++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void back() {
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(2)).getBackgraend().getX()-width+15)){
            TestRun();
            try{
                sleep(speed);
                postX--;
                // First, test posistion equls position light
                if (postX==lights[3].getPostX())
                {
                    // First, wait for the first semaphore light.
                    lights[2].getSemaphore().acquire();
                    lights[2].chagneColor();
                    // First, wait for the seconde semaphore light.
                    lights[3].getSemaphore().acquire();
                    lights[3].chagneColor();
                }
                // Release for the other cars
                if (postX==lights[2].getPostX())
                {
                    lights[2].chagneColor();
                    lights[2].getSemaphore().release();
                    lights[3].chagneColor();
                    lights[3].getSemaphore().release();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        postX= (int) ((ObliqueRoadTram) road.getChildren().get(2)).getBackgraend().getX()-21;
        rotat=90;

        while (!(postY==sc.getLineElectrque().getY()+1)){
            TestRun();
            try{

                sleep(speed);
                postY++;
                if (postY==positionScTest)
                    ScTrams.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=0;
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(1)).getPostX()-13)){
            TestRun();
            try{
                sleep(speed);

                postX--;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=90;
        while (!(postY==((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getY())){
            TestRun();
            try{
                sleep(speed);

                postY--;
                if (postY==positionScTest+20)
                    ScTrams.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        rotat=0;
        while (!(postX==pointStart.getX())){
            TestRun();
            try{
                sleep(speed);
                // First, test posistion equls position light
                if (postX==lights[1].getPostX())
                {
                    // First, wait for the first semaphore light.
                    lights[1].getSemaphore().acquire();
                    lights[1].chagneColor();
                    // First, wait for the seconde semaphore light.
                    lights[0].getSemaphore().acquire();
                    lights[0].chagneColor();
                }
                // Release for the other cars
                if (postX==lights[0].getPostX())
                {
                    lights[1].chagneColor();
                    lights[1].getSemaphore().release();
                    lights[0].chagneColor();
                    lights[0].getSemaphore().release();
                }
                postX--;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

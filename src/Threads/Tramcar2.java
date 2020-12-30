package Threads;

import Animation.Point;
import UIclass.ObliqueRoadTram;
import UIclass.SimpleRoadTram;
import javafx.scene.Group;
import moduls.Light;

import java.util.concurrent.Semaphore;

public class Tramcar2 extends MyThread {
    private static final long TRAMCAR_DURATION = 500;
    private int postX,postY,rotat=0;
    private int speed=8;
    private Light[]lights;

    public int getRotat() {
        return rotat;
    }

    private static int c=0;
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

    private String tramCarName;
//    private Direction direction;

//    private Light light;

    private Semaphore[] sTrack;

    //    private Semaphore leftLightMutex;
//    private Semaphore leftLight;
    private int width;
    private Semaphore ScTrams;
    private Semaphore rightLight;
    final private Group road;
    final private Point pointStart,pointFin;
    final private SimpleRoadTram sc;
    private int positionScTest;
    public Tramcar2(Group road, Point pointStart, Point pointFin, int width, SimpleRoadTram sc,Semaphore ScTrams,Light[]lights) {
        this.road = road;
        this.pointStart = pointStart;
        this.pointFin = pointFin;
        this.lights=lights;
        postX=pointStart.getX();
        postY=pointStart.getY();
        this.width=width;
        this.sc = sc;
//        this.sTrack = sTrack;
//        this.leftLightMutex = leftLightMutex;
//        this.leftLight = leftLight;
        this.ScTrams = ScTrams;
//        this.rightLight = rightLight;
        positionScTest= (int) ( ((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getY()-((ObliqueRoadTram)road.getChildren().get(1)).getBackgraend().getHeight()/2);


    }

//    public Tramcar1(String tramCarName, Direction direction, Light light, Semaphore[] sTrack, Semaphore leftLightMutex, Semaphore leftLight, Semaphore rightLightMutex, Semaphore rightLight) {
//        this.tramCarName = tramCarName;
//        this.direction = direction;
//        this.light = light;
//        this.sTrack = sTrack;
//        this.leftLightMutex = leftLightMutex;
//        this.leftLight = leftLight;
//        this.rightLightMutex = rightLightMutex;
//        this.rightLight = rightLight;
//
//       Start

//    }

    @Override
    public void run() {
        tranfor();
//        try {
//
//            // tramCar starts running
//            System.out.println("Starting " + tramCarName);
//
//            if (this.direction == Direction.Left) {
//
//                // First, wait for the first light to be green.
//                System.out.println(tramCarName + " is waiting for the lights to be green.");
//
//                leftLightMutex.acquire();
//                leftLight.acquire();
//
//                // Change the light to red
//                light.setColorRed();
//
//                System.out.println(tramCarName + " the lights are red, you are free to go now.");
//
//                System.out.println(tramCarName + " is wating for the car to go.");
//
//                // Acquire the intersection with the cars
//                sTrack[1].acquire();
//
//                // Todo move the tramcar
//                Thread.sleep(TRAMCAR_DURATION); // To simulate moving the tramcar
//
//                // Change the light back to green
//                light.setColorGreen();
//
//                // Release for the other cars
//                leftLight.release();
//                leftLightMutex.release();
//                sTrack[1].release();
//
//                // Lastly, see if there's an other tramcar in the intersection
//                System.out.println(tramCarName + " is waiting for the intersection to be free.");
//
//                sTrack[2].acquire();
//
//                System.out.println(tramCarName + " the intersection is free, you are free to go now.");
//
//                // Todo move the tramcar
//                Thread.sleep(TRAMCAR_DURATION); // To simulate moving the tramcar
//
//                // Release the road for other tramcars
//                sTrack[2].release();
//
//            } else {
//
//                // First, wait for the first light to be green.
//                System.out.println(tramCarName + " is waiting for the lights to be green.");
//
//                rightLightMutex.acquire();
//                rightLight.acquire();
//
//                // Change the light to red
//                light.setColorRed();
//
//                System.out.println(tramCarName + " the lights are red, you are free to go now.");
//
//                System.out.println(tramCarName + " is wating for the car to go.");
//
//                // Acquire the intersection with the cars
//                sTrack[1].acquire();
//
//                // Todo move the tramcar
//                Thread.sleep(TRAMCAR_DURATION); // To simulate moving the tramcar
//
//                // Change the light back to green
//                light.setColorGreen();
//
//                // Release for the other cars
//                rightLight.release();
//                rightLightMutex.release();
//                sTrack[1].release();
//
//                // Lastly, see if there's an other tramcar in the intersection
//                System.out.println(tramCarName + " is waiting for the intersection to be free.");
//
//                sTrack[2].acquire();
//
//                System.out.println(tramCarName + " the intersection is free, you are free to go now.");
//
//                // Todo move the tramcar
//                Thread.sleep(TRAMCAR_DURATION); // To simulate moving the tramcar
//
//                // Release the road for other tramcars
//                sTrack[2].release();
//
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
    public void tranfor (){
        while (true){
            forward();
            back();
        }

    }

    private void back() {

        System.out.println("roadt size ="+road.getChildren().size());

        while (!(postX==((ObliqueRoadTram)road.getChildren().get(2)).getBackgraend().getX()-width+15)){
            TestRun();
            try{
                sleep(speed);
                if (postX==lights[3].getPostX()-width)
                {
                    lights[2].getSemaphore().acquire();
                    lights[2].chagneColor();
                    lights[3].getSemaphore().acquire();
                    lights[3].chagneColor();
                }
                if (postX==lights[2].getPostX())
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
        postX= (int) ((ObliqueRoadTram) road.getChildren().get(2)).getBackgraend().getX()-21;
        rotat=90;



        while (!(postY==sc.getLineElectrque().getY()+1)){
            TestRun();

            try{

                sleep(speed);
                postY--;
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
                postX++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=90;
        while (!(postY==((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getY())){
            TestRun();
            try{
                if (postY==positionScTest-20)
                    ScTrams.release();
                sleep(speed);
                postY++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        rotat=0;
        System.out.println(((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getX()-width+((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getWidth());
        while (!(postX==((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getX()-width+((SimpleRoadTram)road.getChildren().get(0)).getBackgraend().getWidth())){
            TestRun();
            try{
                sleep(speed);
                if (postX==lights[1].getPostX()-width)
                {
                    lights[1].getSemaphore().acquire();
                    lights[1].chagneColor();
                    lights[0].getSemaphore().acquire();
                    lights[0].chagneColor();
                }
                if (postX==lights[0].getPostX())
                {
                    lights[1].chagneColor();
                    lights[1].getSemaphore().release();
                    lights[0].chagneColor();
                    lights[0].getSemaphore().release();
                }
                postX++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void forward() {
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(1)).getBackgraend().getX()-width+15)){
            TestRun();
            try{
                sleep(speed);
                postX--;
                if (postX==lights[0].getPostX())
                {
                    lights[0].getSemaphore().acquire();
                    lights[0].chagneColor();
                    lights[1].getSemaphore().acquire();
                    lights[1].chagneColor();
                }
                if (postX==lights[1].getPostX())
                {
                    lights[0].chagneColor();
                    lights[0].getSemaphore().release();
                    lights[1].chagneColor();
                    lights[1].getSemaphore().release();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        postX= (int) ((ObliqueRoadTram) road.getChildren().get(1)).getBackgraend().getX()-17;
        rotat=90;
//        speed=15;
        while (!(postY==sc.getLineElectrque().getY()+1)){
            TestRun();
            try{

                sleep(speed);
                postY--;
                if (postY==positionScTest)
                    ScTrams.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=0;
        while (!(postX==((ObliqueRoadTram)road.getChildren().get(2)).getPostX()-16)){
            TestRun();
            try{

                sleep(speed);
                postX--;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rotat=90;
        System.out.println(postY);
        System.out.println(((SimpleRoadTram)road.getChildren().get(3)).getBackgraend().getY());
        while (!(postY==((SimpleRoadTram)road.getChildren().get(3)).getBackgraend().getY())){
            TestRun();
            try{
                sleep(speed);
                if (postY==positionScTest-20)
                    ScTrams.release();
                postY++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        rotat=0;
        while (!(postX==pointFin.getX())){
            TestRun();
            try{
                sleep(speed);
                if (postX==lights[2].getPostX())
                {
                    lights[2].getSemaphore().acquire();
                    lights[2].chagneColor();
                    lights[3].getSemaphore().acquire();
                    lights[3].chagneColor();
                }
                if (postX==lights[3].getPostX())
                {
                    lights[2].chagneColor();
                    lights[2].getSemaphore().release();
                    lights[3].chagneColor();
                    lights[3].getSemaphore().release();
                }
                postX--;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

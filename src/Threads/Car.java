package Threads;

import Animation.Point;
import UIclass.CarUi;
import moduls.Light;

import java.util.concurrent.Semaphore;

public class Car extends MyThread {
    final private Point pointStart,pointFin;
    private int postY;
    // Semaphore to access the car road when no car is in it
    private Semaphore[] sTrack;


    // Defines the block this vehicle is currently occupying
    private int currentBlock = 0;
    private int oldBlock = 0;
    private int imageHeight;
    private Light[] lights;

    public int getPostY() {
        return postY;
    }

    public Car(Point pointStart, Point pointFin, Semaphore[] sTrack, int imageHeight,Light[] lights) {
        this.pointStart = pointStart;
        this.pointFin = pointFin;
        this.lights=lights;
        postY=pointStart.getY();

        this.imageHeight=imageHeight;


        this.sTrack = sTrack;


        // Start
        start();
    }

    @Override
    public void run()
    {

        transfor();
    }
    public void transfor(){
       int y=postY;
        if (pointStart.getY()<pointFin.getY())
        {
            while (postY!=pointFin.getY())
//                TestRun();
            {

                try {
                    Thread.currentThread().sleep(10);
                    // Calculating the current block based on the position (position in pixels
                    // divided by the default size for vehicles)

                    oldBlock = (int) ((postY-pointStart.getY()) /(imageHeight+5));
                    y++;
                    currentBlock = (int) ((postY-pointStart.getY()) /(imageHeight+5));



                    // checking if the vehicle is entering a new block
                    if (postY -pointStart.getY()/ imageHeight > currentBlock) {
                        // Checking if the vehicle has reached the end of the Intersection
                        if (currentBlock == sTrack.length) {

                            // if yes, releases the last semaphore from the array and return (exit) from the
                            // method
                            // run
                            sTrack[sTrack.length - 1].release();
                            return;
                        }}
                       //test lights
                        if (postY==(lights[0].getPostY()-30))
                        {
                            lights[0].getSemaphore().acquire();}
                        if (postY==lights[0].getPostY()+20)
                            lights[0].getSemaphore().release();
                        if (postY==(lights[1].getPostY()-30))

                            lights[1].getSemaphore().acquire();
                        if (postY==lights[1].getPostY()+20)
                            lights[1].getSemaphore().release();
                        // First, wait for the next car to free the road.
//                        System.out.println(carName + " is waiting for the " + postY + " to be free.");

                        if ((currentBlock!=oldBlock)||(postY==pointStart.getY()-1)){
                            sTrack[currentBlock].acquire();}

                        // The track is free, the light is green, the tramcar road is free.
//                        System.out.println(Thread.currentThread() + " is free to move to " + postY + ".");

                         postY=y;

                        // Release for the other cars
                        if(currentBlock!=oldBlock)
                        {

                        sTrack[currentBlock-1].release();}


//                    System.out.println(imageView.getY());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }else
        {

            while (postY!=pointFin.getY())
            {
//                TestRun();
                try {
                    Thread.currentThread().sleep(10);
                    // Calculating the current block based on the position (position in pixels
                    // divided by the default size for vehicles)
                    oldBlock = currentBlock;

                    y--;
                    currentBlock =  (y /(imageHeight+5));



                    // checking if the vehicle is entering a new block

                        // Checking if the vehicle has reached the end of the Intersection
                        if (currentBlock <0) {

                            // if yes, releases the last semaphore from the array and return (exit) from the
                            // method
                            // run
                            sTrack[sTrack.length +1].release();
                            return;
                        }

                    if (postY==(lights[1].getPostY()+12))
                        lights[1].getSemaphore().acquire();
                    if (postY==lights[1].getPostY()-50)
                        lights[1].getSemaphore().release();
                    if (postY==(lights[0].getPostY()+12))
                        lights[0].getSemaphore().acquire();
                    if (postY==lights[0].getPostY()-50)
                        lights[0].getSemaphore().release();
                   // First, wait for the next car to free the road.
//                        System.out.println(carName + " is waiting for the " + postY + " to be free.");

                    if ((currentBlock!=oldBlock)||(postY==pointStart.getY()+1))
                        sTrack[currentBlock].acquire();
                   postY=y;
                    // The track is free, the light is green, the tramcar road is free.

//                        System.out.println(carName + " is free to move to " + postY + ".");
                    // Release for the other cars
                    if(currentBlock!=oldBlock)
                    {
                        System.out.println(oldBlock);
                        System.out.println(sTrack.length);
                    sTrack[oldBlock].release();}


//                    System.out.println(imageView.getY());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
    }
}}

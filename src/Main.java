import Animation.Point;
import Animation.Tram1Animation;
import Animation.Tram2Animation;
import Threads.MyThread;
import Threads.ProducerCar;
import UIclass.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import moduls.Light;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;


public class Main extends Application {
    Pane root = new Pane();
    Scene scene = new Scene(root, 1280, 700);
    RoadTram1 roadTram1 = new RoadTram1();
    RoadTram2 roadTram2 = new RoadTram2();
    private Light [] lights=new Light[8];
    private Semaphore[] roadCar1;
    private Semaphore[] roadCar2;
    private Semaphore[] roadCar3;
    private Semaphore[] roadCar4;
    private Semaphore ScTram;
    ArrayList<MyThread> Threads=new ArrayList();

    RoadCar roadCarRight = new RoadCar(100, 45);
    RoadCar roadCarLeft = new RoadCar(1100, 45);

    SimpleRoadTram simpleRoadTramSC = new SimpleRoadTram(530, 350, 220);


    @Override
    public void start(Stage primaryStage) throws Exception {
        Button start = new Button("start");
        start.setOnAction(event -> start(event,Threads));
        Button stop= new Button("stop");
        stop.setOnAction(event -> stop(event, Threads));
        start.setLayoutX(600);
        start.setLayoutY(45);
        stop.setLayoutX(650);
        stop.setLayoutY(45);

        root.getChildren().addAll(start,stop);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

//    public static void onClickB(ActionEvent event, Stage p) {
//        p.close();
//    }

    public synchronized void init() {

        int numbreSemaphore = 690 / 35;
        roadCar1 = new Semaphore[numbreSemaphore];
        Arrays.fill(roadCar1, new Semaphore(1));
        roadCar2 = new Semaphore[numbreSemaphore];
        Arrays.fill(roadCar2, new Semaphore(1));
        roadCar3 = new Semaphore[numbreSemaphore];
        Arrays.fill(roadCar3, new Semaphore(1));
        roadCar4 = new Semaphore[numbreSemaphore];
        Arrays.fill(roadCar4, new Semaphore(1));
        lights[0]=new Light(90, 170);
        lights[1]=new Light(90, 470);
        lights[2]=new Light(170, 220);
        lights[3]=new Light(170, 520);
        lights[4]=new Light(1090, 170);
        lights[5]=new Light(1090, 470);
        lights[6]=new Light(1170, 220);
        lights[7]=new Light(1170, 520);

        ScTram = new Semaphore(1);



        System.out.println("road size 2 =" + roadTram1.getChildren().size());
        root.getChildren().addAll(roadCarLeft, roadCarRight, roadTram1, roadTram2, simpleRoadTramSC);
        for (Light l:lights)
            root.getChildren().add(l);

        initThread();
    }

    public void initThread() {

        ProducerCar producerCar=new ProducerCar(roadCarRight, roadCarLeft, root,Threads ,lights,roadCar1,roadCar2,roadCar3,roadCar4);
        producerCar.start();
        Threads.add(producerCar);
        Light []tram1Lights={lights[0],lights[2],lights[4],lights[6]};
        Light []tram2Lights={lights[7],lights[5],lights[3],lights[1]};
        TramUi tram1 = new TramUi(new Point(0, (int) (((SimpleRoadTram) roadTram1.getChildren().get(0)).getLineElectrque().getY() + 1)), new Point(730, 501));
        Tram1Animation tram1Animation = new Tram1Animation(roadTram1, tram1, simpleRoadTramSC, ScTram,tram1Lights,Threads);
        TramUi tram2 = new TramUi(new Point(1230, (int) (((SimpleRoadTram) roadTram2.getChildren().get(0)).getLineElectrque().getY() + 1)), new Point(0, 501));
        Tram2Animation tram2Animation = new Tram2Animation(roadTram2, tram2, simpleRoadTramSC, ScTram,tram2Lights,Threads);
        System.out.println("yes  " +lights.length);

        root.getChildren().add(root.getChildren().size(), tram1);
        root.getChildren().add(root.getChildren().size(), tram2);
    }
    public void start(ActionEvent e,ArrayList<MyThread> t){
        for (MyThread myThread:t)
        {

            try {
                myThread.getMutex().acquire();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            myThread.setRun(true);
            myThread.getMutex().release();}
        System.out.println("fin start");

    }
    public void stop(ActionEvent e,ArrayList<MyThread> t){
        for (MyThread myThread:t)
        {
            try {
                myThread.getMutex().acquire();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            myThread.setRun(false);
           myThread.getMutex().release();
        }
    }
}

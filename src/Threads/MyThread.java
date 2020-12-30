package Threads;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private boolean run=false;
    Semaphore Mutex =new Semaphore(1);



    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public Semaphore getMutex() {
        return Mutex;
    }

    public void setMutex(Semaphore mutex) {
        Mutex = mutex;
    }

    public void TestRun(){

        while (!this.isRun())
            try {
                sleep(1);
            }catch (InterruptedException e){}
    }

}

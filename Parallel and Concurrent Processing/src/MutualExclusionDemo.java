/**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.*; // import the Lock package

class Shopper extends Thread {

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();   //create new instance of a concrete ReentrantLock.
    //lock is static so there is only 1 lock instance, shared by all threads. 

    @Override
    public void run() {
        for (int i=0; i<5; i++) {
            pencil.lock();
            garlicCount++;
            pencil.unlock();
            System.out.println(Thread.currentThread().getName() + " is thinking."); //mutex lock limited to operations that alter shared static variable only.
            try {
                Thread.sleep(500); //mutex lock exclude thread.sleep(). Other threads do not wait for this thread to finish sleeping.
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

public class MutualExclusionDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}
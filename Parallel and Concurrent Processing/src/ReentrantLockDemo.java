/**
 * Two shoppers adding garlic and potatoes to a shared notepad
 */

import java.util.concurrent.locks.*;

class Shopper extends Thread {

    static int garlicCount, potatoCount = 0;
    static ReentrantLock pencil = new ReentrantLock(); //more specific ReentractLock constructor

    private void addGarlic() {
        pencil.lock();
        System.out.println("Hold count: " + pencil.getHoldCount()); // to monitor the number of times the lock is held. 
        garlicCount++;
        pencil.unlock();
    }
    /*
    getHoldCount() value is 1 when addGarlic() method called directly in line 12. 
    getHoldCount() value is 2 when addGarlic() method is called from within addPotato() method in line 22. Locked twice.
    */
    
    private void addPotato() {
        pencil.lock();
        potatoCount++;
        addGarlic(); //call to addGarlic() method nested pencil.lock(). When addPitato() method was called, it pencil.lock() on line 20. addGarlic() method on this line will pencil.lock() a 2nd time.
        pencil.unlock();
    }

    @Override
    public void run() {
        for (int i=0; i<10_000; i++) {
            addGarlic();
            addPotato();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        System.out.println("We should buy " + Shopper.potatoCount + " potatoes.");
    }
}
/*
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.atomic.*;

class Shopper extends Thread {

    static AtomicInteger garlicCount = new AtomicInteger(0); //static for 1 garlicCount instance shared and used by all shoppers (threads).

    @Override
    public void run() {
        for (int i=0; i<10_000_000; i++)
            garlicCount.incrementAndGet(); //increment garlicCount by 1. Similar to garlicCount++.
    }
}

public class AtomicVariableDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic."); //Shopper.garlicCount because garlicCount is an instance, not a variable. garlicCount instance automatically access atomic variable of same name.
    }
}

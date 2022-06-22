/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0; // shared variable. static means only one copy of this variable is shared by all threads.

    private static synchronized void addGarlic() { //synchronized means thread executing this method will acquire intrinsic lock on Shopper class object.
        garlicCount++;  // static means the method belongs to the Shopper class, is shared by all threads/instances. There is only 1.
    }

    @Override
    public void run() {
        for (int i=0; i<10_000_000; i++)
            addGarlic();
    }
}

public class SynchronizedMethodDemo {
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

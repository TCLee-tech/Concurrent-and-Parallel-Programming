/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0; //counter, initialized to zero

    @Override    
    public void run() {
        for (int i=0; i<10_000_000; i++) 
            garlicCount++;
    }
}

public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join(); //wait for barron's thread to finish before continuing
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}

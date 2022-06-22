/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    @Override
    public void run() {
        for (int i=0; i<10_000_000; i++)
            synchronized (Shopper.class) {
                garlicCount++;
            }
    }
}

public class SynchronizedStatementDemo {
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
/"
synchronized (this) {
    garlicCount++;
}

=> will give wrong result.
"this" keyword refers to the current instance of the object. The method or constructor of the current instance is being called.
The 2 threads will acquire and release separate instrinsic locks on  their own instances. Not synchronized. Has data race.

synchronized (garlicCount) {
    garlicCount++;
}
=> will give wrong result.
garlicCount is primitive integer variable. Represent raw values, not object.

static Integer garlicCount = 0;
...
synchronized (garlicCount) {
    garlicCount++;
}
=> will give wrong result.
An Integer class is a wrapper class. A wrapper class creates an object with a field for storing a single primitive value.
The object is immutable, means cannot change its value. 
Every time Java executes this section of code, it instantiates a new Integer object with a differebt PID.

*/

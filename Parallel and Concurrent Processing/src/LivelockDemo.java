/**
 * A polite philosopher makes sure their friends eat first
 */

import java.util.concurrent.locks.*;
import java.util.Random;

class Philosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;
    private Random rps = new Random(); //rps - rock, paper, scissors. creating an instance of the Random class. https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Random.html

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) { //constructor
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    @Override
    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            if (! secondChopstick.tryLock()) { // tryLock() alone for 2nd mutex lock in circular locking chain. this alone converts deadlock into a livelock.
                System.out.println(this.getName() + " released their first chopstick.");
                firstChopstick.unlock(); // if not successful in acquiring 2nd lock, release 1st lock. 
                try {
                    Thread.sleep(rps.nextInt(3)); //thread sleeps for random length of time to break synchronisation. nextInt() method of Random class.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    // take a piece of sushi
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // put down chopsticks
                    secondChopstick.unlock();
                    firstChopstick.unlock();
                }
            }
        }
    }
}

public class LivelockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start(); //start Barron thread
        new Philosopher("Olivia", chopstickB, chopstickC).start(); //start Olivia thread
        new Philosopher("Steve", chopstickC, chopstickA).start(); //start Steve thread
    }
}
/*
Deadlock if codes acquire mutex lock in circular chain only.

firstChopstick.lock();
secondChopstick.lock();

... critical section ...

firstChopstick.unlock();
secondChopstick.unlock();

lock sequence: chopstick A + B -> chopstick B + C -> chopstick C + A
 */

/*
Livelock if threads keep trying to acquire 2nd lock (trylock) in circular chain; Release 1st lock if 2nd lock acquisition fails.

firstChopstick.lock();
if (!secondChopstick.trylock();) {
    firstChopstick.unlock();
    System.out.println(this.getName() + "released the first Chopstick");
}

... critical section

lock sequence: chopstick A + B -> chopstick B + C -> chopstick C + A
 */
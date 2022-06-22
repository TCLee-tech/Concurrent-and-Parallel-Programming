/**
 * Three philosophers, thinking and eating sushi
 */

import java.util.concurrent.locks.*;
class Philosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    @Override
    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            // put down chopsticks
            secondChopstick.unlock();
            firstChopstick.unlock();
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickA, chopstickC).start(); // lock acquired in order of priority of lock
    }
}

/*
The mutex locks are arranged in a circular chain in the codes below.
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickC, chopstickA).start();

It causes concurrency problems, such as starvation of certain threads and deadlock after a number of iterations.
(Can refer to notes for screebgrabs)

The mutex locks are arranged in order of priority in the codes below.
The priority order is - 
chopstickA > chopstickB > chopstickC

        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickA, chopstickC).start();

Note: This program only has 1 shared resource, sushiCount.
So it needed only 1 mutex lock (chopstick).
The other 2 chopsticks are only present to reproduce dining philosophers problem

 */
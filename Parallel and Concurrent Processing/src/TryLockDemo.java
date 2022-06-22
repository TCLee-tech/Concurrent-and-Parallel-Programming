/**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.*;

class Shopper extends Thread {

    private int itemsToAdd = 0; // items this shopper is waiting to add. instance variable, 1 for each Shopper.
    private static int itemsOnNotepad = 0; // total items on shared notepad
    private static Lock pencil = new ReentrantLock();

    public Shopper(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (itemsOnNotepad <= 20){
            if ((itemsToAdd > 0) && pencil.tryLock()) { // add item(s) to shared notepad. java executes Left to Right, so will check if have itemsToAdd before tryLock.
                try {
                    itemsOnNotepad += itemsToAdd;
                    System.out.println(this.getName() + " added " + itemsToAdd + " item(s) to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300); // time spent writing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pencil.unlock();
                }
            } else { // look for other things to buy
                try {
                    Thread.sleep(100); // time spent searching
                    itemsToAdd++;
                    System.out.println(this.getName() + " found something to buy.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper("Barron");
        Thread olivia = new Shopper("Olivia");
        long start = System.currentTimeMillis();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (float)(finish - start)/1000 + " seconds");
    }
}
/*
When run, in terminal output, can see that threads sometimes run else block (itemsToAdd++) when lock was not available.
Elapsed time is shorter as threads are not blocked and waiting.
 */
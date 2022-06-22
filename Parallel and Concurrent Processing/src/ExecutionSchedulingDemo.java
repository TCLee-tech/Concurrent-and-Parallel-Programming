/**
 * Two threads chopping vegetables
 * Shows OS scheduling can impact execution
 */

class VegetableChopper extends Thread{ //each instance of VegetableChopper class will run as separate thread. So 'Barron' and 'Olivia' are 2 threads.

    public int vegetable_count = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while(chopping) {
            System.out.println(this.getName() + " chopped a vegetable!");
            vegetable_count++; //increase value of instance variable
        }
    }
}

public class ExecutionSchedulingDemo {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper barron = new VegetableChopper("Barron"); //instantiate barron and olivia as threads
        VegetableChopper olivia = new VegetableChopper("Olivia");

        barron.start();                    // Barron start chopping
        olivia.start();                    // Olivia start chopping
        Thread.sleep(1000);          // continue chopping for 1 second
        VegetableChopper.chopping = false; // stop chopping

        barron.join();
        olivia.join();
        System.out.format("Barron chopped %d vegetables.%n", barron.vegetable_count);
        System.out.format("Olivia chopped %d vegetables.%n", olivia.vegetable_count); 
    } //terminal shows different count for Barron and Olivia every time Java program runs. Shows OS schedule each thread to run for different unequal number of times.
    // Cannot rely on OS scheduler to run threads in particular order, or equal number of times.
}
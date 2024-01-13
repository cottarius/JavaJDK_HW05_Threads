import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
   // private String name;
    private int number;
    private int count = 3;
    private Fork leftFork;
    private Fork rightFork;
    private CountDownLatch cdl;

    public Philosopher(int number, Fork leftFork, Fork rightFork, CountDownLatch cdl) {
        this.number = number;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cdl = cdl;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        try {
            while (count != 0){
                think();
                eat();
            }
            System.out.printf("Философ №%s пообедал 3 раза!\n", number);
        } catch(InterruptedException e){
            e.fillInStackTrace();
        }
        cdl.countDown();

    }
    private void eat() throws InterruptedException {
        if(leftFork.getFree() && rightFork.getFree()){
            leftFork.setFree(false);
            leftFork.setFree(false);
            System.out.printf("Философ №%s начал обедать...\n", number);
            sleep((long) (Math.random() * 5000));
            leftFork.setFree(true);
            leftFork.setFree(true);
            System.out.printf("Филосов №%s пообедал и начал размышлять...\n", number);
            count--;
        }
    }
    private void think() throws InterruptedException {
        sleep((long) (Math.random() * 3000));
    }
}

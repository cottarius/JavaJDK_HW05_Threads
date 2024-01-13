import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private final int NUMBER = 5;
    private List<Philosopher> philosophers;
    private List<Fork> forks;
    private CountDownLatch cdl = new CountDownLatch(NUMBER);

    public Table() {
        forks = createListOfForks(NUMBER);
        philosophers = createListOfPhilosophers(NUMBER);

    }
    private List<Philosopher> createListOfPhilosophers(int number){
        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < number; i++){
            Fork leftFork = forks.get(i);
            Fork rightFork = forks.get((i + 1) % forks.size());
            philosophers.add(new Philosopher(i + 1, leftFork, rightFork, cdl));
        }
        return philosophers;
    }
    private List<Fork> createListOfForks(int number){
        List<Fork> forks = new ArrayList<>();
        for (int i = 0; i < number; i++){
            forks.add(new Fork(true));
        }
        return forks;
    }

    @Override
    public void run() {
        try {
            for (Philosopher philosopher: philosophers) {
                philosopher.start();
            }
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Все философы поели");


    }

}



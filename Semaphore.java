public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void waitSem() throws InterruptedException {
        while (permits <= 0) wait();
        permits--;
    }

    public synchronized void signal() {
        permits++;
        notifyAll();
    }
}

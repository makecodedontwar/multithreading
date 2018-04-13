package threads;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());

        new MyThread().start();

        new Thread(new MyRunnable()).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

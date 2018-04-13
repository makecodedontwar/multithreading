package sync;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Resource resource = new Resource();
        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(new MyThread(resource, true)));
        threadList.add(new Thread(new MyThread(resource, true)));
        threadList.add(new Thread(new MyThread(resource, false)));
        threadList.add(new Thread(new MyThread(resource, false)));

        threadList.forEach(Thread::start);
        threadList.forEach(e-> {
            try {
                e.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        System.out.println(resource.getI());
        System.out.println(resource.getJ());
    }
}

class Resource {
    private int i;
    private int j;

    void incI() {
        i++;
    }

    synchronized void incJ() {
        j++;
    }

    int getI() {
        return i;
    }

    int getJ() {
        return j;
    }
}

class MyThread implements Runnable {

    private Resource resource;
    private boolean isSync;

    MyThread(Resource resource, boolean isSync) {
        this.resource = resource;
        this.isSync = isSync;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            if (!isSync) {
                resource.incI();
            } else {
                resource.incJ();
            }
        }
    }
}

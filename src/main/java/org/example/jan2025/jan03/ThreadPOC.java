package org.example.jan2025.jan03;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPOC {
    public static void main(String[] args) throws InterruptedException {
        int n=100;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ArrayList<ThreadOps> threadOps = new ArrayList<>();

        for (int i=0;i<n;i++) {
            threadOps.add(new ThreadOps(i,threadOps,atomicInteger,n));
        }

        for (ThreadOps t: threadOps) {
            t.start();
        }

        Thread.sleep(1000);

        for (ThreadOps t: threadOps) {
            t.interrupt();
        }
        Thread.sleep(10000);
    }
}


//16:23
class ThreadOps extends Thread {
    int index;
    ArrayList<ThreadOps> threads;
    int numThreads;
    final AtomicInteger num;

    public ThreadOps(int index, ArrayList<ThreadOps> threads, AtomicInteger num, int n) {
        this.index=index;
        this.numThreads = n;
        this.threads = threads;
        this.num = num;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (num) {
                if (num.intValue() % numThreads != index) {
                    try {
                        num.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread "+index+" interrupted");
                        return;
                    }
                } else {
                    System.out.println(num.intValue() + "\t" + index + "\t" + (num.intValue() % numThreads));
                    num.incrementAndGet();
                    num.notifyAll();
                }
            }
        }
    }

}
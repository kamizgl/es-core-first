package com.roncoo.es.score.first;

public class TreadB extends Thread{


    public TreadB(String name) {
        super(name);
    }

    public void run(){
        synchronized (this) {
            notify();
            System.out.println(Thread.currentThread().getName() + "call notify");

        }
    }
}

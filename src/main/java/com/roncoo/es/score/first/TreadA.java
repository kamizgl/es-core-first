package com.roncoo.es.score.first;

public class TreadA extends Thread {

    public TreadA(String name) {
        super(name);
    }

    public void run(){
        synchronized (this) {
            notify();
            System.out.println(Thread.currentThread().getName() + "call notify");

        }
    }

}


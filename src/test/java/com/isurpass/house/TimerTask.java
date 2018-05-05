package com.isurpass.house;

import java.util.Timer;

public class TimerTask extends java.util.TimerTask {
    @Override
    public void run() {
        System.out.println("1231...");
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        long delay = 2 * 1000;
        long period = 1000;
        timer.schedule(new TimerTask(),delay,period);
    }
}

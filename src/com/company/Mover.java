package com.company;

import java.util.Random;

class Mover extends Thread {
    private final double angle;
    private final Cart cart;
    private final int sCoefficient;
    private final Random rnd = new Random();


    /**
     * Creature (mover) constructor, generates values for angles and S coefficients
     * also initializes the cart and name
     *
     * @param degrees Predefined angles in degrees at which the movers will be moving the cart
     * @param cart    Cart which will be pulled
     */
    public Mover(int degrees, String moverName, Cart cart) {
        this.setName(moverName);
        this.cart = cart;
        this.angle = Math.toRadians(degrees);
        this.sCoefficient = rnd.nextInt() % 10;
    }


    /**
     * Overloaded method of the Runnable interface, which defines the behaviour of this object. (Pulling the cart, resting)
     * It works for 25 seconds from the start of the program.
     */
    @Override
    public void run() {
        while ((System.currentTimeMillis() - Main.startTime) < 25000) {
            int sleepTime = rnd.nextInt(4000) + 1000;
//            cart.Move(angle, sCoefficient, sleepTime, getName());
//            с более информативным выводом, но по свецификации его нет, так что оставлю так
            cart.Move(angle, sCoefficient);
            try {
                sleep(sleepTime);
                if (System.currentTimeMillis() - Main.startTime + sleepTime > 25000) {
                    // waking up the thread if it's sleep time will make the
                    // work time of the program longer than 25 seconds
                    interrupt();
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
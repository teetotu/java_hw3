package com.company;

class Printer extends Thread {
    Cart cart;

    /**
     * Printer constructor
     *
     * @param cart cart the position of which needs to be printed to the console
     */
    public Printer(Cart cart) {
        this.cart = cart;
    }

    /**
     * Overloaded method of the Runnable interface, which defines the behaviour of this object.
     * (Printing, 2 sec time-out)
     */
    @Override
    public void run() {
        while ((System.currentTimeMillis() - Main.startTime) < 25000) {
            System.out.printf("%d: The cart is at x = %.2f, y = %.2f%n",
                    System.currentTimeMillis() - Main.startTime, cart.getPosX(), cart.getPosY());
            try {
                // the value is not exactly 2000 because we need to compensate for the time that
                // may have added up because the cpu might not have enough threads (cores)
                sleep(2000 - (System.currentTimeMillis() - Main.startTime) % 2000);
                if (System.currentTimeMillis() - Main.startTime + 2000 > 25000) {
                    interrupt();
                }
            } catch (InterruptedException e) {
                return;
            }
        }

        interrupt();
    }
}

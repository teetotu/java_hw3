package com.company;

class Cart {
    private double posX, posY;

    public Cart() {
        posX = 0;
        posY = 0;
    }

    public Cart(double coordinate) {
        posX = coordinate;
    }

    public Cart(double x, double y) {
        posX = x;
        posY = y;
    }

    /**
     * Synchronized method that handles the carts movement
     *
     * @param angle        The value of the angle in radians relative to the front of the cart,
     *                     defines the direction in which the cart needs to be moved
     * @param sCoefficient Mover's S coefficient from the equation
     *                     х_T = х_{T - 1} + S_i * cos a_i
     *                     y_T = y_{T - 1} + S_i * sin a_i
     */
    public synchronized void Move(double angle, int sCoefficient) {
        posX += sCoefficient * Math.cos(angle);
        posY += sCoefficient * Math.sin(angle);
    }

    // если понадобится удобный вывод для проверки работы потоков
    public synchronized void Move(double angle, int sCoefficient, int sleepTime, String moverName) {
        posX += sCoefficient * Math.cos(angle);
        posY += sCoefficient * Math.sin(angle);
        System.out.printf("%d %s moved the cart and went to sleep for %d%n",
                System.currentTimeMillis() - Main.startTime, moverName, sleepTime);
    }


    public synchronized double getPosX() {
        return posX;
    }

    public synchronized double getPosY() {
        return posY;
    }
}

package com.company;

class Main {
    static long startTime;

    private static Cart CreateCart(String[] args) {
        switch (args.length) {
            case 0:
                return new Cart();
            case 1:
                try {
                    return new Cart(Double.parseDouble(args[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input data");
                    throw new NumberFormatException("My number format exception");
                }
            case 2:
                try {
                    return new Cart(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input data");
                    throw new NumberFormatException("My number format exception");
                }
            default:
                System.out.println("Incorrect input data");
                throw new NumberFormatException("My number format exception");
        }
    }

    public static void main(String[] args) {

        Cart cart;
        try {
            cart = CreateCart(args);
        } catch (NumberFormatException e) {
            return;
        }
        Mover[] movers = new Mover[3];

        movers[0] = new Mover(60, "Рак", cart);
        movers[1] = new Mover(180, "Лебедь", cart);
        movers[2] = new Mover(300, "Щука", cart);

        Printer printer = new Printer(cart);

        startTime = System.currentTimeMillis();

        printer.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Mover mover : movers) {
            mover.start();
        }

        try {
            printer.join();
            for (Mover mover : movers) {
                mover.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Чтобы проверить, не ожидает ли основной поток спящие потоки муверов,
//        для проверки программы можоно использовать этот цикл
//        System.out.println(System.currentTimeMillis() - startTime + " " + printer.getState());
//        for (Mover mover : movers) {
//            System.out.println(System.currentTimeMillis() - startTime + " " + mover.getState());
//        }

        System.out.printf("Final position x = %.2f, y = %.2f%n",
                cart.getPosX(),
                cart.getPosY());
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Создаем салон, который в свою очередь делает производителя
        CarShowroom carShowroom = new CarShowroom();

        Thread thread1 = new Thread(null, carShowroom::sellCar, "Покупатель 1");
        Thread thread2 = new Thread(null, carShowroom::sellCar, "Покупатель 2");
        Thread thread3 = new Thread(null, carShowroom::sellCar, "Покупатель 3");

        Thread productionCars = new Thread(null, carShowroom::carIsReady, "DeLorean");

        thread1.start();
        thread2.start();
        thread3.start();
        productionCars.start();

        productionCars.join();
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();

    }
}
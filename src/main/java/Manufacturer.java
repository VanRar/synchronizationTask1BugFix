public class Manufacturer {
    //здесь будет производство машин и обработка покупателя
    //обозначим автосалон для нашего производства
    private CarShowroom carShowroom;

    public Manufacturer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    //создадим синхронизированный метод выпуска авто
    //он только берет список из салона и наполняет его выпускаемым авто и выдаёт об этом сообщение
    public synchronized void releaseCar() {
        carShowroom.getCars().add(new Car()); //производим новый авто
        System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
        notify();
        //сообщаем в автосалон, что авто готов
        // carShowroom.carIsReady();//и тут тогда этот метод вызывать не надо, сообщение будет выведено когда будет выполнен метод
    }

    public synchronized Car sellCar() {
        System.out.println(" Покупатель " + Thread.currentThread().getName() + " зашел в автосалон");
        while (carShowroom.getCars().size() == 0) {
            System.out.println("Машин нет");
            //carIsReady(); //manufacturer.releaseCar();//хотя даже не здесь, а заявку передаем через метод
            //а нет, опять не прав, мы же в потоках методы создаём
            //и получается тут уже ждем производства авто
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Производство машин на сегодня остановлено");
                return null;
            }
            //после этого сообщаем что покупатель уехал
            System.out.println("Покупатель " + Thread.currentThread().getName() + "  уехал на новеньком авто");
            carShowroom.setSalesPlan();
        }
        return carShowroom.getCars().remove(carShowroom.getCars().size() - 1);//удалим купленный авто
    }

}
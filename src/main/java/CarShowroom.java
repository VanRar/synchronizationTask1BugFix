import java.util.ArrayList;
import java.util.List;

public class CarShowroom {
    //здесь будет просто вызов методов производства машин и обслуживания покупателя
    //добавим ещё все паузы и условия работы магазина

    private static int SALES_PLAN = 10; //план по продаже кол-ва авто
    private static final int SLEEP_TIME_RECEIVE = 3000; //пауза между производством авто
    private static final int SLEEP_TIME_SELL = 2000; //пауза между продажами авто

    //подключаем производителя к нашему салону, который в свою очередь использует данный автосалон
    Manufacturer manufacturer = new Manufacturer(this);

    //создаем, так сказать, место для хранеия наших авто
    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    //создадим метод продажи авто, который обращается к производителю авто, если авто нет в наличии
    public void sellCar() {
        while (!Thread.currentThread().isInterrupted()) {
            if (SALES_PLAN == 0) {
                break;
            }
            manufacturer.sellCar();
            try {
                Thread.sleep(SLEEP_TIME_SELL);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void carIsReady() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(SLEEP_TIME_RECEIVE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (SALES_PLAN == 0) {
                break;
            }
            manufacturer.releaseCar();
        }
    }

    public static void setSalesPlan() {
        SALES_PLAN -= 1;
    }
}
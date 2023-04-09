import java.util.ArrayList;

class Road {

    public ArrayList<Car> cars = new ArrayList<>();

    public double calcAWWTime() {//Calculate average weighted waiting time
        double TWWTime = 0;// total weighted wait time
        int order = 1;//weight, i.e. the order in the queue
        for (Car car : cars) {
            TWWTime += car.waitingTime * order++;
        }
        return TWWTime / cars.size();//return average weighted waiting time
    }

    public void updateWaitingTime(int time) {
        for (Car car : cars) {
            car.waitingTime = time - car.enterTime;
        }
    }
}

class Car {
    public final int enterTime;
    public int waitingTime = 0;
    public String direction;
    public String carNumber;

    Car(String carNumber, int enterTime, String direction) {
        this.enterTime = enterTime;
        this.carNumber = carNumber;
        this.direction = direction;
    }
}


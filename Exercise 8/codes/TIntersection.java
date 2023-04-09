import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

class TIntersection {
    private final Road roadAE = new Road();
    private final Road roadAW = new Road();
    private final Road roadB = new Road();

    // 指示哪一个路是绿灯
    // Indicate which road is green
    private boolean X = true;

    // 时间设定为以秒为单位
    // The time is set in seconds
    private int time = -1;


    public void timer() {
        // 每次时间加一秒
        // Add one second to each time
        time++;
        // 扫描添加车辆
        // Scan and Add Vehicle
        scannerCars();
        // 更新所有车辆的等待时间
        // Update waiting times for all vehicles
        updateWTime();
        // 更新红绿灯的状态
        // Update the status of traffic lights
        lightControl();

        // 打印车辆移动前的状态图
        // Print the state diagram before the vehicle moves
        System.out.println(StringUtils.center("BEFORE", 122));
        printingStates();

        System.out.println("\n" + Task5.starLine);
        System.out.println(StringUtils.center("Cars action", 122));

        if (X)//如果是true，则A路绿灯 If it is true, the green light on Route A
            roadAGreenLight();
        else
            roadBGreenLight();

        //打印车辆移动后的状态图
        // Print the state diagram of the vehicle after moving
        System.out.println(Task5.starLine);
        System.out.println("\n" + StringUtils.center("AFTER", 122));
        printingStates();
    }

    //更新所有车辆的等待时间
    //Update waiting times for all vehicles
    private void updateWTime() {
        roadAE.updateWaitingTime(time);
        roadAW.updateWaitingTime(time);
        roadB.updateWaitingTime(time);
    }

    //更新红绿灯的状态
    //Update the status of traffic lights
    private void lightControl() {
        if (roadAW.cars.isEmpty() && roadAE.cars.isEmpty()) {
            //如果A路全空，则B路绿灯，并且不计算权值
            // If route A is completely empty,
            // route B will have a green light and no weight will be calculated
            X = false;
            return;
        } else if (roadB.cars.isEmpty()) {
            //反之亦然
            //vice versa
            X = true;
            return;
        }

        // Calculate average weighted waiting time of each road
        // 计算每条道路的平均加权等待时间
        double AWWTimeOfAE = roadAE.calcAWWTime();
        double AWWTimeOfAW = roadAW.calcAWWTime();
        double AWWTimeOfB = roadB.calcAWWTime();

        //B路权值比较大则B路绿灯，否则A路绿灯
        // If the weight value of route B is relatively large,
        // route B will have a green light, otherwise route A will have a green light
        X = !(AWWTimeOfB > (AWWTimeOfAE + AWWTimeOfAW) / 2);
    }

    private void roadBGreenLight() {
        Iterator<Car> itB = roadB.cars.iterator();
        Iterator<Car> itAW = roadAE.cars.iterator();

        if (itB.hasNext()) {
            Car carB = itB.next();
            String line;
            if (carB.direction.equals("L")) {
                line = "Car No." + carB.carNumber + " on Road B turns left to the west side of Road A.";
            } else {
                line = "Car No." + carB.carNumber + " on Road B turns right to the east side of Road A.";
            }
            System.out.println(StringUtils.center(line, 122));
            itB.remove();
        }

        if (itAW.hasNext()) {//A路车辆允许右转 Road A vehicles are allowed to turn right
            Car carAW = itAW.next();
            if (carAW.direction.equals("R")) {
                String line = "Car No." + carAW.carNumber + " on the west side of road A turns right and goes on Road B.";
                System.out.println(StringUtils.center(line, 122));
                itAW.remove();
            }
        }
    }

    private void roadAGreenLight() {
        if (!roadB.cars.isEmpty()) {//B路上的车右转 The car on Road B turns right
            Car carB = roadB.cars.get(0);
            if (carB.direction.equals("R")) {
                String line = "Car No." + carB.carNumber + " on Road B turns right to the east side of Road A.";
                System.out.println(StringUtils.center(line, 122));
                roadB.cars.remove(0);
            }
        }

        if (!roadAE.cars.isEmpty() && !roadAW.cars.isEmpty()) {
            Car carAE = roadAE.cars.get(0);
            Car carAW = roadAW.cars.get(0);
            if (carAE.direction.equals("L") && carAW.direction.equals("R")) {
                //AE左转，AW右转，死锁。解决方案为右转让左转。
                // AE left turn, AW right turn, deadlock.
                // The solution is to transfer right and turn left.
                String line = "Car No." + carAE.carNumber + " on the east side of road A turns left into road B.";
                System.out.println(StringUtils.center(line, 122));
                roadAE.cars.remove(0);
                return;
            }
        }

        if (!roadAE.cars.isEmpty()) {
            Car carAE = roadAE.cars.get(0);
            String line;
            if (carAE.direction.equals("L")) {//AE车辆左转 AE vehicle turning left
                line = "Car No." + carAE.carNumber + " on the east side of road A turns left and goes on Road B.";
            } else {//直行 straight
                line = "Car No." + carAE.carNumber + " on the east side of road A goes straight to the west of Road A.";
            }
            System.out.println(StringUtils.center(line, 122));
            roadAE.cars.remove(0);
        }

        if (!roadAW.cars.isEmpty()) {
            Car carAW = roadAW.cars.get(0);
            String line;
            if (carAW.direction.equals("R")) {//AW车辆右转 AW vehicle turns right
                line = "Car No." + carAW.carNumber + " on the west side of road A turns right and goes on Road B.";
            } else {//直行 straight
                line = "Car No." + carAW.carNumber + " on the west side of road A goes straight to the east of Road A.";
            }
            System.out.println(StringUtils.center(line, 122));
            roadAW.cars.remove(0);
        }
    }

    private void scannerCars() {//扫描车辆 Scan the vehicle
        Iterator<String[]> itOfCarList = Task5.carList.iterator();

        while (itOfCarList.hasNext()) {
            String[] car = itOfCarList.next();

            String carNumber = car[0];
            int enterTime = Integer.parseInt(car[1]);
            String road = car[2];
            String direction = car[3];

            if (enterTime == time) {
                Car newCar = new Car(carNumber, enterTime, direction);

                if (road.equals("AE")) {
                    roadAE.cars.add(newCar);
                } else if (road.equals("AW")) {
                    roadAW.cars.add(newCar);
                } else {
                    roadB.cars.add(newCar);
                }

                itOfCarList.remove();
            }
        }
    }

    public void printingStates() {//打印状态图 Print Status Chart
        String timeLine = "Time: " + time + " X: " + X;
        System.out.println(StringUtils.center(timeLine, 122));

        Iterator<Car> itAE = roadAE.cars.iterator();
        Iterator<Car> itAW = roadAW.cars.iterator();
        Iterator<Car> itB = roadB.cars.iterator();

        System.out.println(Task5.dashLine);

        System.out.printf("||%s||%s||%s||%s||%s||\n",
                StringUtils.center("Road  A  West", 30),
                StringUtils.center("", 10),
                StringUtils.center("RoadB", 30),
                StringUtils.center("", 10),
                StringUtils.center("Road  A  East", 30));

        int lineLength = Math.max(Math.max(roadAE.cars.size(), roadAW.cars.size()), roadB.cars.size());

        while (lineLength-- != 0) {
            linesPrint(itAW);
            linesPrint(itB);
            linesPrint(itAE);
            System.out.println();
        }

        System.out.println("\n" + Task5.dashLine);
    }

    private void linesPrint(Iterator<Car> it) {
        if (it.hasNext()) {
            var car = it.next();
            String linePart = "No." + car.carNumber + ", WTime: " + car.waitingTime + " " + car.direction;
            System.out.printf("||%s||", StringUtils.center(linePart, 30));
            System.out.print(StringUtils.center("", 10));
        } else {
            System.out.print(StringUtils.center("", 44));
        }
    }
}

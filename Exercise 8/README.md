
The answer for task5 is a Java program that simulates the traffic at a T-intersection with three roads: roadAE, roadAW, and roadB. The simulation is based on a set of rules that govern the behavior of the cars at the intersection.

Algorithm rules

1. At each time step (1 second), the simulation checks if there are any cars waiting to enter the intersection from the roads (roadAE, roadAW or roadB). If there are no cars waiting on the roadB, then the traffic light switches to green for the east-west road (roadA). And if there are no cars waiting on the roadA, then the traffic light switches to green for the roadB. If there are cars waiting on both roads, then the simulation calculates the average weighted waiting time (AWWTime) for each road.

2. The AWWTime (average weighted waiting time) is calculated by taking the sum of the waiting time for each car on the road multiplied by the distance that the car is from the intersection. The waiting time for each car is the number of seconds that the car has been waiting to enter the intersection. The traffic light is switched to green for the road with the higher AWWTime. For example, If the AWWTime for roadB is greater than the average of the AWWTimes for roadAE and roadAW, then the traffic light switches to green for the roadB. The reverse is also the same.

3. When the traffic light is green for a road, the cars on that road are allowed to move. But in some cases, a car from another road will also be allowed to move. The special circumstances are as follows: When the light on road A is green, vehicles on road B can turn right to the east side of road A at the same time. When the light on road B is green, cars on the west side of road A can turn right to road B at the same time.

4. If there are multiple cars waiting to enter the intersection at the same time, the left-turning car has the right of way.This is reflected in that if the light on road A is green, the vehicles on the east side wants turn left, and the vehicles on the west side wants turn right, then the vehicles on the east side are allowed to turn, and the vehicles on the west side continue to wait.

Program Structure

1. The program reads in a file named "Cars.txt" that contains information about the cars that will be entering the intersection. Each line of the file represents a car and contains the following information:  a) Car number: a unique integer that identifies the car. b)Enter time c)Road name: "AE", "AW", or "B". d)Direction: "L" (left), "R" (right) or "S" (straight).

2. An ArrayList of cars called "carList" and uses it to keep track of the cars that are waiting to enter the intersection.

3. Class TIntersection is used to keep the roads and control lights.

4. Class Road is used to keep cars.

5. Class Car is defined with enterTime, waitingTime, direction and carNumber.

Files

The Car file is for testing, and the simulation is performed by modifying the contents of this file.
The deadlock data file is data containing deadlock situations, and the random data file contains randomly generated data.

Others

Due to limited energy, I only include the deadlock solution in this program.  For other emergencies such as traffic accidents, other methods must be adopted, such as immediately stopping the normal operation of traffic lights and notifying the police.

The program also uses a lot of approximations, such as setting the time for each scanning of vehicles and vehicles passing through the intersection to 1 second, etc.

These approximations are only used for simulation algorithms and are not advisable in practice.

In addition, if necessary, I can demonstrate the operation of the program in the tutorial exercise seasons.

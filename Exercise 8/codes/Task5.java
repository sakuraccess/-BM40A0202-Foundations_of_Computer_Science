//@author Yinuo Zhao

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task5 {
    static String fileName = "Cars.txt";
    static ArrayList<String[]> carList = new ArrayList<>();

    static String starLine = new String(new char[122]).replace('\0', '*');
    static String dashLine = new String(new char[122]).replace('\0', '-');

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                carList.add(parts);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        TIntersection tIntersection = new TIntersection();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(StringUtils.center("Please enter the number of seconds you want to advance:(use 0 if you want to exit)", 122));

            if (sc.hasNext()) {
                int seconds = Integer.parseInt(sc.nextLine());

                if (seconds == 0) {
                    return;
                }

                while (seconds-- >= 0) {
                    tIntersection.timer();
                }
            }
        }

    }

}



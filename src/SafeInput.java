import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner scanner, String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    public static int min(int values[]) {
        int minValue = values[0];
        for (int value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static int max(int values[]) {
        int maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static int occurrenceScan(int values[], int target) {
        int count = 0;
        for (int value : values) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }

    public static int sum(int values[]) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    public static boolean contains(int values[], int target) {
        for (int value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Task 2a: Prompt and input an int value between 1 and 100
        int userValue = getRangedInt(scanner, "Enter an integer between 1 and 100: ", 1, 100);

        // Additional code for Task 2b, Task 2c, Task 2d, and Task 2e
        int[] dataPoints = {34, 55, 96, 76, 34, 45};

        // Task 2b: Code a loop that counts how many times the user’s value is found within the array
        int count = occurrenceScan(dataPoints, userValue);
        System.out.println("The user's value is found " + count + " times in the array.");

        // Task 2c: Prompt the user again for a value and find its position in the array
        int userValue2 = getRangedInt(scanner, "Enter another integer between 1 and 100: ", 1, 100);
        int position = -1;
        for (int i = 0; i < dataPoints.length; i++) {
            if (dataPoints[i] == userValue2) {
                position = i;
                break; // Short circuit (break) when the value is found
            }
        }

        // Display the result for Task 2c
        if (position != -1) {
            System.out.println("The value " + userValue2 + " was found at array index " + position + ".");
        } else {
            System.out.println("The value " + userValue2 + " is not in the array.");
        }

        // Task 2d: Find the minimum and maximum values in the array
        int min = min(dataPoints);
        int max = max(dataPoints);

        // Display the result for Task 2d
        System.out.println("Minimum value in the array: " + min);
        System.out.println("Maximum value in the array: " + max);

        // Task 2e: Call the static method to get the average and display the result
        int sum = sum(dataPoints);
        double average = (double) sum / dataPoints.length;
        System.out.println("Average of dataPoints is: " + average);

        // Testing the additional array methods
        System.out.println("Min value in the array: " + min(dataPoints));
        System.out.println("Max value in the array: " + max(dataPoints));
        System.out.println("Occurrences of " + userValue + " in the array: " + occurrenceScan(dataPoints, userValue));
        System.out.println("Sum of values in the array: " + sum(dataPoints));
        System.out.println("Contains " + userValue + " in the array: " + contains(dataPoints, userValue));
    }



}
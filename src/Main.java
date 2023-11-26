import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static boolean needsToBeSaved = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;
        do {
            displayMenuAndList();
            choice = getString("[AaDdPpQqOoSsCcVv]", "Enter your choice (A/D/V/O/S/C/Q): ").charAt(0);
            switch (choice) {
                case 'A':
                    addItemToList();
                    break;
                case 'D':
                    deleteItemFromList();
                    break;
                case 'V':
                    printList();
                    break;
                case 'O':
                    openListFromFile();
                    break;
                case 'S':
                    saveListToFile();
                    break;
                case 'C':
                    clearList();
                    break;
                case 'Q':
                    quitProgram();
                    break;
            }
        } while (choice != 'Q');
        scanner.close();
    }

    private static void displayMenuAndList() {
        printList();
        System.out.println("Menu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("V - View the list");
        System.out.println("O - Open a list from disk");
        System.out.println("S - Save the current list to disk");
        System.out.println("C - Clear the current list");
        System.out.println("Q - Quit");
    }

    private static void addItemToList() {
        String newItem = getString("Enter the item to add: ");
        myArrList.add(newItem);
        needsToBeSaved = true;
    }

    private static void deleteItemFromList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        int indexToDelete = getInt("Enter the number of the item to delete: ", 1, myArrList.size());
        myArrList.remove(indexToDelete - 1);
        needsToBeSaved = true;
    }

    private static void printList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Current List:");
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
    }

    private static void openListFromFile() {
        if (needsToBeSaved) {
            System.out.println("You have an unsaved list. Please save it before loading a new list.");
            return;
        }
        String fileName = getString("Enter the filename to open: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            myArrList.clear();
            while ((line = reader.readLine()) != null) {
                myArrList.add(line);
            }
            System.out.println("List loaded successfully from " + fileName);
            needsToBeSaved = false;
        } catch (IOException e) {
            System.out.println("Error loading list from file: " + e.getMessage());
        }
    }

    private static void saveListToFile() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to save.");
            return;
        }
        String fileName = getString("Enter the filename to save: ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String item : myArrList) {
                writer.write(item);
                writer.newLine();
            }
            System.out.println("List saved successfully to " + fileName);
            needsToBeSaved = false;
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }

    private static void clearList() {
        if (!myArrList.isEmpty()) {
            int confirmation = getInt("Are you sure you want to clear the list? (1 for Yes, 0 for No): ", 0, 1);
            if (confirmation == 1) {
                myArrList.clear();
                needsToBeSaved = true;
                System.out.println("List cleared.");
            }
        } else {
            System.out.println("The list is already empty.");
        }
    }

    private static void quitProgram() {
        if (needsToBeSaved) {
            int confirmation = getInt("You have an unsaved list. Do you want to save it before quitting? (1 for Yes, 0 for No): ", 0, 1);
            if (confirmation == 1) {
                saveListToFile();
            }
        }
        System.out.println("Exiting the program. Goodbye!");
    }

    private static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String getString(String regex, String prompt) {
        String input;
        do {
            input = getString(prompt);
        } while (!input.matches(regex));
        return input;
    }

    private static int getInt(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                System.out.print(prompt);
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } while (value < min || value > max);
        return value;
    }
}

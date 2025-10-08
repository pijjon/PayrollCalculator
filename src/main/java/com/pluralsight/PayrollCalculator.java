package com.pluralsight;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollCalculator {
    // declare static scanner so static methods can access it
    public static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {

        // prompt user for file names
        String inputFile = askUserStr("Enter the name of the employee file to process: ");
        String outputFile = askUserStr("Enter the name of payroll file to create: ");

        // invoke createEmployees to begin read/write
        createEmployees(inputFile, outputFile);

    }

    public static void createEmployees(String inputFile, String outputFile) {

        try (
                // resources initialized in try with resources will be close automatically
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
        ) {

            String input = bufferedReader.readLine(); // kill the first line header in the input file
            bufferedWriter.write("id|name|gross pay"); // include a header for the output file

            // assign input to the nextLine of the buffer, if it is not empty then run code
            while ((input = bufferedReader.readLine()) != null) {
                // parse through input file data strings
                System.out.println("New employee: ");
                String[] employeeData = input.split("\\|");

                // instantiate new Employee objects using the data from the input file we parsed
                Employee employee = new Employee(Integer.parseInt(employeeData[0]), employeeData[1], Double.parseDouble(employeeData[2]), Double.parseDouble(employeeData[3]));
                displayEmployee(employee); // display the employee data

                // write data to new output file
                System.out.println("Saving to " + outputFile);
;               bufferedWriter.write("\n" + employee.getEmployeeId() + "|" + employee.getName() + "|" + employee.getGrossPay());
            }

        } catch (IOException e) {
            System.out.println("IO Error?");
            e.printStackTrace();
        }
    }

    // static method for displaying employee data to console
    public static void displayEmployee(Employee employee) {
        System.out.printf("""
                Employee ID: %d
                Name: %s
                Gross pay: $%.2f
                
                """, employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
    }

    // static method for prompting user for input, just pass in the question you want to ask
    public static String askUserStr(String question) {
        String output = null;
        try {
            System.out.println(question);
            output = myScanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return output;
    }
}

package com.pluralsight;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollCalculator {
    public static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) {

        String inputFile = askUserStr("Enter the name of the employee file to process: ");
        String outputFile = askUserStr("Enter the name of payroll file to create: ");

        createEmployees(inputFile, outputFile);

    }
    public static void displayEmployee(Employee employee) {
        System.out.printf("""
                Employee ID: %d
                Name: %s
                Gross pay: $%.2f
                
                """, employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
    }

    public static void createEmployees(String inputFile, String outputFile) {

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
        ) {

            String input = bufferedReader.readLine();
            bufferedWriter.write("id|name|gross pay");

            while ((input = bufferedReader.readLine()) != null) {
                // do stuff
                System.out.println("New employee: ");
                String[] employeeData = input.split("\\|");
                Employee employee = new Employee(Integer.parseInt(employeeData[0]), employeeData[1], Double.parseDouble(employeeData[2]), Double.parseDouble(employeeData[3]));
                displayEmployee(employee);

                System.out.println("Saving to payroll.csv");
;               bufferedWriter.write("\n" + employee.getEmployeeId() + "|" + employee.getName() + "|" + employee.getGrossPay());

            }

        } catch (IOException e) {
            System.out.println("IO Error?");
            e.printStackTrace();
        }
    }
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

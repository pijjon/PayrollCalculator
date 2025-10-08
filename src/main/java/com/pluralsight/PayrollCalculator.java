package com.pluralsight;

import java.io.*;

public class PayrollCalculator {
    public static void main(String[] args) {

        createEmployees();

    }
    public static void displayEmployee(Employee employee) {
        System.out.printf("""
                Employee ID: %d
                Name: %s
                Gross pay: $%.2f
                
                """, employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
    }

    public static void createEmployees() {

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader("employees.csv"));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("payroll.csv"))
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
}

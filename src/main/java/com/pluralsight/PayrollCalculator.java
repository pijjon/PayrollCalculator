package com.pluralsight;

import java.io.*;

public class PayrollCalculator {
    public static void main(String[] args) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("employees.csv"));
            String input = bufferedReader.readLine();

            while ((input = bufferedReader.readLine()) != null) {
                // do stuff
                System.out.println("New employee: ");
                String[] employeeData = input.split("\\|");
                Employee employee = new Employee(Integer.parseInt(employeeData[0]), employeeData[1], Double.parseDouble(employeeData[2]), Double.parseDouble(employeeData[3]));
                displayEmployee(employee);

            }

        } catch (IOException e) {
            System.out.println("IO Error?");
            e.printStackTrace();
        }

    }
    public static void displayEmployee(Employee employee) {
        System.out.printf("""
                Employee ID: %d
                Name: %s
                Gross pay: $%.2f
                
                """, employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
    }
}

package com.tbell;

import com.tbell.helpers.atmManager;
import com.tbell.model.Transactions;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");


        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:transactions.db")) {
            atmManager atm = new atmManager(connection);
//            atm.CreateAtmTable();
            WelcomeMenu(atm);

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
            ex.printStackTrace();
        }
    }

    public static void WelcomeMenu(atmManager atm) throws SQLException{

        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("Welcome to SQL ATM 7779311. What is your name? ");
        String userName = scanner.nextLine();
        System.out.printf("What would you like to do %s?\n", userName);
        System.out.println("1) Make a deposit");
        System.out.println("2) Make a withdrawal");
        System.out.println("3) Get account balance");
        System.out.println("================================");


        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                System.out.println("How much would you like to deposit?");
                double deposit = scanner.nextDouble();

                new Transactions(userName, deposit, atm.getStatement()).Save();

                break;
            case 2:
                System.out.println("How much would you like to withdraw?");
                double withdraw = scanner.nextDouble();
                double negWithdraw = -withdraw;

                new Transactions(userName, negWithdraw, atm.getStatement()).Save();
                break;
            case 3:

                List<Transactions> results = Transactions.findAll(atm);
                double totalBalance = 0;
                for (Transactions transactions : results) {
                    totalBalance = totalBalance + transactions.getAmount();
                    System.out.println(transactions);
                }
                System.out.println("----------------------------------");
                System.out.println("Your account balance is: " + totalBalance);
                System.out.println("----------------------------------");
                break;
            default:
                System.out.println("Sorry, Invalid input.");
                break;
        }
        System.out.println("Do you have another transaction? y/n");
        String repeatChoice = scanner.next();
        switch(repeatChoice){
            case "y":
                WelcomeMenu(atm);
                break;
            default:
                System.out.printf("Thank you for using SQL ATM 7779311.");
                System.out.println("Have A Nice Day!");
                break;
        }

    }

}

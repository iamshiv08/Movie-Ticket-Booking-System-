package com.ticket2cinema;
import java.util.*;

public class Ticket2Cinema1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("-----Welcome to Ticket2Cinema-----");
        System.out.println();
        while (true) {
            System.out.println();
            System.out.println("1 -> User Login");
            System.out.println("2 -> Create New Account");
            System.out.println("3 -> Admin Login");
            System.out.println("4 -> Exit");
            System.out.println();
            System.out.print("Enter Your Choice: ");
            String firstInput = sc.next();

            sc.nextLine();

            if (firstInput.equals("1")) {// User Login
                UserLogin userlogin = new UserLogin();
                userlogin.userLogin();
            } else if (firstInput.equals("2")) {// Create User Account
                CreateUserAccount create = new CreateUserAccount();
                create.createUserAccount();
            } else if (firstInput.equals("3")) {// Admin Login
                AdminLogin admin = new AdminLogin();
                admin.adminLogin();
            } else if (firstInput.equals("4")) {//exit
                System.out.println("You Are Logged Out");
                System.exit(1);
                break;
            } else {
                System.out.println("Enter Valid Choice!");
                System.out.println();
            }
        }
    }
}

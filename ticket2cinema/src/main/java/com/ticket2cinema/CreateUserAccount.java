package com.ticket2cinema;

import java.sql.*;
import java.util.*;

abstract class UserAccount {
    String firstName;
    String lastName;
    String fullName;
    String userMobile = "";
    String userName;
    String userPassword;
    Scanner sc = new Scanner(System.in);
    Connection con;

    public UserAccount() throws Exception {
        DBConnection connector = new DBConnection();
        this.con = connector.getCon();
        if (con == null) {
            throw new SQLException("Connection not successful");
        }
    }

    public abstract void createUserAccount() throws Exception;
}

public class CreateUserAccount extends UserAccount {

    public CreateUserAccount() throws Exception {
        super(); 
    }

    @Override
    public void createUserAccount() throws Exception {

        System.out.println("Enter First Name: ");
        firstName = sc.next();

        sc.nextLine(); 

        System.out.println("Enter Last Name: ");
        lastName = sc.nextLine();

        fullName = firstName + " " + lastName;

        boolean mobileCheck = true;
        while (mobileCheck) {
            System.out.println("Enter Mobile Number: ");
            userMobile = sc.nextLine();
            if (userMobile.matches("\\d+")) {

                if (userMobile.length() == 10) {
                    mobileCheck = false;
                } else {
                    System.out.println("Enter Valid 10 Digit Number!");
                    System.out.println();
                }
            } else {
                System.out.println("Please, Enter Only Digits");
                System.out.println();
            }
        }

        System.out.println("Create Username: ");
        userName = sc.nextLine();

        System.out.println("Create Password: ");
        userPassword = sc.nextLine();

        System.out.println("Enter E-mail Address :");
        String userEmail = sc.nextLine();

        String insertSQL = "INSERT INTO user (firstname, lastname, username, userpassword, mobile_no, email_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst2 = con.prepareStatement(insertSQL);
        pst2.setString(1, firstName);
        pst2.setString(2, lastName);
        pst2.setString(3, userName);
        pst2.setString(4, userPassword);
        pst2.setString(5, userMobile);
        pst2.setString(6, userEmail);
        int rs2 = pst2.executeUpdate();

        if (rs2 > 0) {
            System.out.println();
            System.out.println("Successfully Created Your Account " + fullName);
            System.out.println("Please Login Below");
            System.out.println();
        } else {
            System.out.println("Failed To Create Your Account!");
        }
    }
}

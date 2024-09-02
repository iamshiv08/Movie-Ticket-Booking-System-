package com.ticket2cinema;

import java.sql.*;
import java.util.*;

abstract class AdminBase {
    Scanner sc = new Scanner(System.in);
    Connection con;

    public AdminBase() throws Exception {
        DBConnection connector = new DBConnection();
        this.con = connector.getCon();
        if (con == null) {
            throw new SQLException("Connection not successful");
        }
    }

    // Abstract methods to be implemented by subclasses
    public abstract void addMovie() throws Exception;

    public abstract void removeMovie() throws Exception;

    public abstract void updateMovie() throws Exception;

    public abstract void displayMovie() throws Exception;

    public abstract void adminLogin() throws Exception;
}

public class AdminLogin extends AdminBase {

    public AdminLogin() throws Exception {
        super(); // Call the constructor of the abstract class
    }

    @Override
    public void addMovie() throws Exception {
        System.out.println();
        System.out.println("Enter Movie Name:");
        String movieName = sc.nextLine();
        System.out.println("Enter Price of " + movieName + ":");
        Double moviePrice = sc.nextDouble();
        System.out.println("Enter Time of " + movieName + ":");
        String movieTime = sc.next();
        sc.nextLine();

        String movieSQL = "INSERT INTO movie (movie_name, movie_price, movie_time) VALUES (?, ?, ?)";
        try (PreparedStatement pst3 = con.prepareStatement(movieSQL)) {
            pst3.setString(1, movieName);
            pst3.setDouble(2, moviePrice);
            pst3.setString(3, movieTime);
            int rs4 = pst3.executeUpdate();

            if (rs4 > 0) {
                System.out.println();
                System.out.println(movieName + " Added Successfully");
                System.out.println();
            } else {
                System.out.println("Failed To Add Movie!");
            }
        }
    }

    @Override
    public void removeMovie() throws Exception {
        System.out.println("Enter Movie Name:");
        String delMovie = sc.nextLine();
        String delmovieSQL = "DELETE FROM movie WHERE movie_name = ?";
        try (PreparedStatement pst4 = con.prepareStatement(delmovieSQL)) {
            pst4.setString(1, delMovie);
            int rs5 = pst4.executeUpdate();
            if (rs5 > 0) {
                System.out.println();
                System.out.println(delMovie + " is Deleted");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("Failed or Movie Not Found!");
                System.out.println();
            }
        }
    }

    @Override
    public void updateMovie() throws Exception {
        System.out.println("Enter Movie Id:");
        int movieId = sc.nextInt();
        sc.nextLine();
        String checkmovieidSQL = "SELECT * FROM movie WHERE movie_id = ?";

        PreparedStatement preparedStatement1 = con.prepareStatement(checkmovieidSQL);
        preparedStatement1.setInt(1, movieId);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        if (resultSet1.next()) {
            boolean continueUpdating = true;
            while (continueUpdating) {
                System.out.println("What would you like to update?");
                System.out.println("1 - Update Movie Name");
                System.out.println("2 - Update Movie Price");
                System.out.println("3 - Update Movie Time");
                System.out.println("4 - Exit");
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("1")) {
                    System.out.println("Enter New Movie Name:");
                    String updateMovieName = sc.nextLine();
                    String updatemovienameSQL = "UPDATE movie SET movie_name=? WHERE movie_id=?";
                    try (PreparedStatement pst5 = con.prepareStatement(updatemovienameSQL)) {
                        pst5.setString(1, updateMovieName);
                        pst5.setInt(2, movieId);
                        int rs6 = pst5.executeUpdate();
                        if (rs6 > 0) {
                            System.out.println("Movie Name Updated");
                        } else {
                            System.out.println("Failed to update Movie Name!");
                        }
                    }
                } else if (choice.equalsIgnoreCase("2")) {
                    System.out.println("Enter New Price:");
                    Double updateMoviePrice = sc.nextDouble();
                    sc.nextLine();
                    String updatemoviepriceSQL = "UPDATE movie SET movie_price=? WHERE movie_id=?";
                    try (PreparedStatement pst6 = con.prepareStatement(updatemoviepriceSQL)) {
                        pst6.setDouble(1, updateMoviePrice);
                        pst6.setInt(2, movieId);
                        int rs7 = pst6.executeUpdate();
                        if (rs7 > 0) {
                            System.out.println("Movie Price Updated");
                        } else {
                            System.out.println("Failed to update Movie Price!");
                        }
                    }
                } else if (choice.equalsIgnoreCase("3")) {
                    System.out.println("Enter New Time:");
                    String updateMovieTime = sc.nextLine();
                    String updatemovietimeSQL = "UPDATE movie SET movie_time=? WHERE movie_id=?";
                    try (PreparedStatement pst7 = con.prepareStatement(updatemovietimeSQL)) {
                        pst7.setString(1, updateMovieTime);
                        pst7.setInt(2, movieId);
                        int rs8 = pst7.executeUpdate();
                        if (rs8 > 0) {
                            System.out.println("Movie Time Updated");
                        } else {
                            System.out.println("Failed to update Movie Time!");
                        }
                    }
                } else if (choice.equalsIgnoreCase("4")) {
                    continueUpdating = false;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            }
        } else {
            System.out.println("Invalid Movie ID");
        }

    }

    @Override
    public void displayMovie() throws Exception {
        System.out.println();
        String viewmovieSQL = "SELECT * FROM movie";
        try (PreparedStatement pst8 = con.prepareStatement(viewmovieSQL)) {
            ResultSet resultSet2 = pst8.executeQuery();
            while (resultSet2.next()) {
                System.out.println("Movie ID    : " + resultSet2.getInt(1));
                System.out.println("Movie Name  : " + resultSet2.getString(2));
                System.out.println("Movie Price : " + resultSet2.getDouble(3));
                System.out.println("Movie Time  : " + resultSet2.getString(4));
                System.out.println("------------------------");
            }
        }
    }

    @Override
    public void adminLogin() throws Exception {
        System.out.println("Enter Username:");
        String adminUserName = sc.nextLine();
        System.out.println("Enter Password:");
        String adminPassword = sc.nextLine();

        String adminSQL = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(adminSQL)) {
            preparedStatement.setString(1, adminUserName);
            preparedStatement.setString(2, adminPassword);
            ResultSet rs3 = preparedStatement.executeQuery();

            if (rs3.next()) {
                System.out.println();
                System.out.println("===== Admin Login Successful! =====");
                System.out.println();
                while (true) {
                    System.out.println();
                    System.out.println("1 - Add Movies");
                    System.out.println("2 - Remove Movies");
                    System.out.println("3 - Update Movies");
                    System.out.println("4 - View Movies");
                    System.out.println("5 - Back To Main Menu");
                    System.out.println();
                    System.out.print("Enter Your Choice: ");
                    String inputAdmin = sc.nextLine();
                    if (inputAdmin.equals("5")) {
                        break;
                    } else if ((inputAdmin.equalsIgnoreCase("1"))) {
                        addMovie();
                        break;
                    } else if ((inputAdmin.equalsIgnoreCase("2"))) {
                        removeMovie();
                        break;
                    } else if ((inputAdmin.equalsIgnoreCase("3"))) {
                        updateMovie();
                        break;
                    } else if ((inputAdmin.equalsIgnoreCase("4"))) {
                        displayMovie();

                    } else {
                        System.out.println();
                        System.out.println("Invalid Username or Password.");
                        System.out.println();
                    }
                }
            }
        }
    }
}

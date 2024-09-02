package com.ticket2cinema;

import java.sql.*;
import java.util.*;

public class UserLogin {

    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String purple = "\u001B[35m";
    String yellow = "\u001B[33m";
    String reset = "\u001B[0m";
    SimplyLL ob = new SimplyLL();
    Payment p = new Payment();
    Connection con;
    int bookedSeats = 0;
    int r = 9;
    int c = 10;
    int ch;
    char chance = 'y';
    String[][] a = new String[r][c];
    int row, colum;
    boolean check = true;

    public UserLogin() throws Exception {
        DBConnection connector = new DBConnection();
        this.con = connector.getCon();
        if (con == null) {
            try {
                throw new SQLException("Connected not succesfull");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void fillArray(String[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = green + "* " + reset;
            }
        }
    }

    void printArray(String[][] a) {
        System.out.println("1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]);
            }
            System.out.print((char) ('A' + i) + " ");
            System.out.println();
        }
    }

    void printArraySection(String[][] a, int startRow, int endRow) {
        System.out.println("1 2 3 4 5 6 7 8 9 10\n");
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]);
            }
            System.out.print((char) ('A' + i) + " ");
            System.out.println();
        }
    }

    void bookShow(String loginUserName, String loginPassword) throws Exception {
        Scanner sc = new Scanner(System.in);
        ob.display();
        System.out.println("Choose Theatre : ");
        String theatreName = sc.nextLine();
        boolean found = ob.search(theatreName);
        if (found) {
            System.out.println("You Choose " + theatreName);
            System.out.println();
            String viewmovieSQL = "select * from movie";
            PreparedStatement pst8 = con.prepareStatement(viewmovieSQL);
            ResultSet resultSet2 = pst8.executeQuery();
            while (resultSet2.next()) {

                System.out.println(
                        "Movie Name: " + resultSet2.getString(2) + "\t" +
                                "Movie Price: " + resultSet2.getDouble(3) + "\t" +
                                "Movie Time: " + resultSet2.getString(4));
                System.out.println(
                        "-----------------------------------------------------------------------------------------------------------------------");
            }
            System.out.println();
            System.out.println("Choose Movie : ");
            String chooseMovie = sc.nextLine();

            String sql = "select movie_name from movie";
            PreparedStatement ps11 = con.prepareStatement(sql);
            ResultSet rs = ps11.executeQuery();

            boolean found1 = false;

            while (rs.next()) {
                if (chooseMovie.equalsIgnoreCase(rs.getString(1))) {
                    found1 = true;
                    break;
                }
            }

            if (found1) {
                System.out.println(chooseMovie + " Selected");
                System.out.println();
                System.out.println("Please,Select Your Seats...");
                fillArray(a);
                printArray(a);
                System.out.println(purple + "--------------------" + reset);
                System.out.println(yellow + " all eyes are here  " + reset);
                System.out.println();
                while (check) {
                    System.out.println("enter choice : ");
                    System.out.println("1. Recliner");
                    System.out.println("2. Prime");
                    System.out.println("3. Classic");
                    ch = sc.nextInt();
                    switch (ch) {
                        case 1:
                            ArrayList<String> bookedSeatList1 = new ArrayList<>();
                            System.out.println("you selected Recliner");
                            System.out.println();
                            printArraySection(a, 0, 3);
                            System.out.println(purple + "--------------------" + reset);
                            System.out.println(yellow + " all eyes are here  " + reset);
                            System.out.println();
                            System.out.print("input row index (A-C): ");
                            char rowChar = sc.next().toUpperCase().charAt(0);
                            while (rowChar < 'A' || rowChar > 'C') {
                                System.out.println("input row letter between A-C ");
                                rowChar = sc.next().toUpperCase().charAt(0);
                            }
                            row = rowChar - 'A';
                            System.out.print("enter column index (1-10): ");
                            colum = sc.nextInt();
                            while (colum > 10 || colum < 1) {
                                System.out.println("enter column number between 1-10");
                                colum = sc.nextInt();
                            }
                            System.out.println();
                            if (a[row][colum - 1].equals(red + "# " + reset)) {
                                System.out.println("this seat is Already booked");
                            } else {
                                a[row][colum - 1] = red + "# " + reset;
                                bookedSeats++;
                                bookedSeatList1.add(rowChar + "-" + colum);
                                printArraySection(a, 0, 3);
                            }
                            boolean innerCheck = true;
                            while (innerCheck) {
                                System.out.println(
                                        "for booking more tickets press y othrewise n");
                                chance = sc.next().charAt(0);
                                if (chance == 'y' || chance == 'Y') {
                                    check = true;
                                    innerCheck = false;
                                } else if (chance == 'n' || chance == 'N') {
                                    System.out.println();
                                    printArray(a);
                                    System.out.println();
                                    System.out.println("ticket you booked respected by #");
                                    // System.out.println("Your Selected Seats are " +
                                    // String.join(", ", bookedSeatList1));
                                    System.out.println("--------------------------------------------------------------");
                                    System.out.println("Total seats booked: " + bookedSeats);
                                    String priceSQL = "select movie_price from movie where movie_name=?";
                                    PreparedStatement pst12 = con.prepareStatement(priceSQL);
                                    pst12.setString(1, chooseMovie);
                                    ResultSet rs10 = pst12.executeQuery();
                                    rs10.next();
                                    Double price = rs10.getDouble(1);
                                    Double totalPayment = price * bookedSeats;
                                    System.out.println("Your Total Payment is " + totalPayment);
                                    System.out.println("--------------------------------------------------------------");

                                    String viewSQL = "SELECT * FROM user WHERE username = ? AND userpassword = ?";
                                    PreparedStatement pst1 = con.prepareStatement(viewSQL);
                                    pst1.setString(1, loginUserName);
                                    pst1.setString(2, loginPassword);
                                    String viewSQL1 = "SELECT * FROM movie WHERE movie_name = ?";
                                    PreparedStatement pst11 = con.prepareStatement(viewSQL1);
                                    pst11.setString(1, chooseMovie);

                                    ResultSet rs1 = pst1.executeQuery();
                                    ResultSet rs11 = pst11.executeQuery();
                                    if (rs1.next() && rs11.next()) {
                                        String first = rs1.getString("firstname");
                                        String last = rs1.getString("lastname");
                                        String emailSend = rs1.getString("email_id");
                                        String mailName = first + " " + last;
                                        String mailMovie = chooseMovie.toUpperCase();
                                        String mailTime = rs11.getString("movie_time")
                                                .toUpperCase();
                                        String mailTheatre = theatreName.toUpperCase();
                                        // String mailSeat = String.join(", ", bookedSeatList1);
                                        int mailTotalSeats = bookedSeats;
                                        String seatType = "Recliner";
                                        p.payment(mailName, mailMovie, mailTime, mailTheatre,
                                                mailTotalSeats, seatType, emailSend);
                                    } else {
                                        System.out.println(
                                                "Error retrieving user or movie details.");
                                    }
                                    innerCheck = false;
                                    check = false;
                                } else {
                                    System.out.println("invalid enter again !");
                                    innerCheck = true;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("you selected Prime");
                            printArraySection(a, 3, 6);
                            System.out.println(purple + "--------------------" + reset);
                            System.out.println(yellow + " all eyes are here  " + reset);
                            System.out.println();
                            System.out.print("input row index (D-F): ");
                            rowChar = sc.next().toUpperCase().charAt(0);
                            while (rowChar < 'D' || rowChar > 'F') {
                                System.out.println("input row letter between D-F ");
                                rowChar = sc.next().toUpperCase().charAt(0);
                            }
                            row = rowChar - 'A';
                            System.out.print("enter column index (1-10): ");
                            colum = sc.nextInt();
                            while (colum > 10 || colum < 1) {
                                System.out.println("enter column number between 1-10");
                                colum = sc.nextInt();
                            }
                            System.out.println();
                            if (a[row][colum - 1].equals(red + "# " + reset)) {
                                System.out.println("this seat is booked");
                            } else {
                                a[row][colum - 1] = red + "# " + reset;
                                bookedSeats++;
                                printArraySection(a, 3, 6);
                            }
                            boolean innerCheck1 = true;
                            while (innerCheck1) {
                                System.out.println(
                                        "for booking more tickets press y othrewise n");
                                chance = sc.next().charAt(0);
                                if (chance == 'y' || chance == 'Y') {
                                    check = true;
                                    innerCheck1 = false;
                                } else if (chance == 'n' || chance == 'N') {
                                    System.out.println();
                                    printArray(a);
                                    System.out.println();
                                    System.out.println("ticket you booked respected by #");
                                    System.out.println("--------------------------------------------------------------");

                                    System.out.println("Total seats booked: " + bookedSeats);
                                    String priceSQL = "select movie_price from movie where movie_name=?";
                                    PreparedStatement pst12 = con.prepareStatement(priceSQL);
                                    pst12.setString(1, chooseMovie);
                                    ResultSet rs10 = pst12.executeQuery();
                                    rs10.next();
                                    Double price = rs10.getDouble(1);
                                    Double totalPayment = price * bookedSeats;
                                    System.out.println("Your Total Payment is " + totalPayment);
                                    System.out.println("--------------------------------------------------------------");

                                    String viewSQL = "SELECT * FROM user WHERE username = ? AND userpassword = ?";
                                    PreparedStatement pst1 = con.prepareStatement(viewSQL);
                                    pst1.setString(1, loginUserName);
                                    pst1.setString(2, loginPassword);
                                    String viewSQL1 = "SELECT * FROM movie WHERE movie_name = ?";
                                    PreparedStatement pst11 = con.prepareStatement(viewSQL1);
                                    pst11.setString(1, chooseMovie);

                                    ResultSet rs1 = pst1.executeQuery();
                                    ResultSet rs11 = pst11.executeQuery();
                                    if (rs1.next() && rs11.next()) {
                                        String first = rs1.getString("firstname");
                                        String last = rs1.getString("lastname");
                                        String emailSend = rs1.getString("email_id");

                                        String mailName = first + " " + last;
                                        String mailMovie = chooseMovie.toUpperCase();
                                        String mailTime = rs11.getString("movie_time")
                                                .toUpperCase();
                                        String mailTheatre = theatreName.toUpperCase();
                                        // String mailSeat = String.join(", ", bookedSeatList1);
                                        int mailTotalSeats = bookedSeats;
                                        String seatType = "Prime";

                                        p.payment(mailName, mailMovie, mailTime, mailTheatre,
                                                mailTotalSeats, seatType, emailSend);
                                    } else {
                                        System.out.println(
                                                "Error retrieving user or movie details.");
                                    }
                                    innerCheck = false;
                                    innerCheck1 = false;
                                    check = false;
                                } else {
                                    System.out.println("invalid enter again !");
                                    innerCheck1 = true;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("you select Classic");
                            printArraySection(a, 6, 9);
                            System.out.println(purple + "--------------------" + reset);
                            System.out.println(yellow + " all eyes are here  " + reset);
                            System.out.println();
                            System.out.print("input row index (G-I): ");
                            rowChar = sc.next().toUpperCase().charAt(0);
                            while (rowChar < 'G' || rowChar > 'I') {
                                System.out.println("input row letter between G-I ");
                                rowChar = sc.next().toUpperCase().charAt(0);
                            }
                            row = rowChar - 'A';
                            System.out.print("enter column index (1-10): ");
                            colum = sc.nextInt();
                            while (colum > 10 || colum < 1) {
                                System.out.println("enter column number between 1-10");
                                colum = sc.nextInt();
                            }
                            System.out.println();
                            if (a[row][colum - 1].equals(red + "# " + reset)) {
                                System.out.println("this seat is booked");
                            } else {
                                a[row][colum - 1] = red + "# " + reset;
                                bookedSeats++;
                                printArraySection(a, 6, 9);
                            }
                            boolean innerCheck2 = true;
                            while (innerCheck2) {
                                System.out.println(
                                        "for booking more tickets press y othrewise n");
                                chance = sc.next().charAt(0);
                                if (chance == 'y' || chance == 'Y') {
                                    check = true;
                                    innerCheck2 = false;
                                } else if (chance == 'n' || chance == 'N') {
                                    System.out.println();
                                    printArray(a);
                                    System.out.println();
                                    System.out.println("ticket you booked respected by #");
                                    System.out.println("--------------------------------------------------------------");

                                    System.out.println("Total seats booked: " + bookedSeats);
                                    String priceSQL = "select movie_price from movie where movie_name=?";
                                    PreparedStatement pst12 = con.prepareStatement(priceSQL);
                                    pst12.setString(1, chooseMovie);
                                    ResultSet rs10 = pst12.executeQuery();
                                    rs10.next();
                                    Double price = rs10.getDouble(1);
                                    Double totalPayment = price * bookedSeats;
                                    System.out.println("Your Total Payment is " + totalPayment);
                                    System.out.println("--------------------------------------------------------------");

                                    String viewSQL = "SELECT * FROM user WHERE username = ? AND userpassword = ?";
                                    PreparedStatement pst1 = con.prepareStatement(viewSQL);
                                    pst1.setString(1, loginUserName);
                                    pst1.setString(2, loginPassword);
                                    String viewSQL1 = "SELECT * FROM movie WHERE movie_name = ?";
                                    PreparedStatement pst11 = con.prepareStatement(viewSQL1);
                                    pst11.setString(1, chooseMovie);

                                    ResultSet rs1 = pst1.executeQuery();
                                    ResultSet rs11 = pst11.executeQuery();
                                    if (rs1.next() && rs11.next()) {
                                        String first = rs1.getString("firstname");
                                        String last = rs1.getString("lastname");
                                        String emailSend = rs1.getString("email_id");

                                        String mailName = first + " " + last;
                                        String mailMovie = chooseMovie.toUpperCase();
                                        String mailTime = rs11.getString("movie_time")
                                                .toUpperCase();
                                        String mailTheatre = theatreName.toUpperCase();
                                        // String mailSeat = String.join(", ", bookedSeatList1);
                                        int mailTotalSeats = bookedSeats;
                                        String seatType = "Classic";

                                        p.payment(mailName, mailMovie, mailTime, mailTheatre,
                                                mailTotalSeats, seatType, emailSend);
                                    } else {
                                        System.out.println(
                                                "Error retrieving user or movie details.");
                                    }
                                    innerCheck = false;
                                    innerCheck2 = false;
                                    check = false;
                                } else {
                                    System.out.println("invalid enter again !");
                                    innerCheck2 = true;
                                }
                            }
                            break;
                        default:
                            System.out.println("enter valid choice !");
                            break;
                    }
                }
            } else {
                System.out.println(chooseMovie + " Not Found");
            }
        } else {
            System.out.println("Theatre " + theatreName + " Not Found");

        }
    }

    void viewProfile(String loginUserName, String loginPassword) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ? AND userpassword = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, loginUserName);
        pstmt.setString(2, loginPassword);
    
        ResultSet rs1 = pstmt.executeQuery();
        if (rs1.next()) {
            System.out.println("=================================================");
            System.out.println();
            System.out.println("User id       : " + rs1.getInt(1));
            System.out.println("First Name    : " + rs1.getString("firstname"));
            System.out.println("Last Name     : " + rs1.getString("lastname"));
            System.out.println("Username      : " + rs1.getString("username"));
            System.out.println("Mobile Number : " + rs1.getString("mobile_no"));
            System.out.println("Email id      : " + rs1.getString("email_id"));
            System.out.println();
            System.out.println("=================================================");
        } else {
            System.out.println("Profile not found.");
        }
    }

    void updateProfile(String loginUserName) throws Exception {
        Scanner sc = new Scanner(System.in);
        String checkusernameSQL = "SELECT * FROM user WHERE username = ? ";
        PreparedStatement pst8 = con.prepareStatement(checkusernameSQL);
        pst8.setString(1, loginUserName);
        ResultSet resultSet3 = pst8.executeQuery();
        System.out.println();
        if (resultSet3.next()) {
            while (true) {
                System.out.println("Select an option to update:");
                System.out.println("1. Update First Name");
                System.out.println("2. Update Last Name");
                System.out.println("3. Update Mobile Number");
                System.out.println("4. Update Email Id");
                System.out.println("5. Exit");
                System.out.println();
                System.out.println("Enter Your Choice :");
                String ask = sc.next();
                sc.nextLine();

                if (ask.equalsIgnoreCase("1")) {
                    System.out.println("Enter New First Name:");
                    String updateUserFirstName = sc.nextLine();
                    String updateuserfirstnameSQL = "update user set firstname=? where username=?";
                    PreparedStatement pst9 = con.prepareStatement(updateuserfirstnameSQL);
                    pst9.setString(1, updateUserFirstName);
                    pst9.setString(2, loginUserName);
                    int rs9 = pst9.executeUpdate();
                    if (rs9 > 0) {
                        System.out.println("User First Name Updated");
                    } else {
                        System.out.println("Failed or User Not Found !");
                    }
                } else if (ask.equalsIgnoreCase("2")) {
                    System.out.println("Enter New Last Name:");
                    String updateUserLastName = sc.nextLine();
                    String updateuserlastnameSQL = "update user set lastname=? where username=?";
                    PreparedStatement pst10 = con.prepareStatement(updateuserlastnameSQL);
                    pst10.setString(1, updateUserLastName);
                    pst10.setString(2, loginUserName);
                    int rs10 = pst10.executeUpdate();
                    if (rs10 > 0) {
                        System.out.println("User Last Name Updated");
                    } else {
                        System.out.println("Failed or User Not Found !");
                    }
                } else if (ask.equalsIgnoreCase("3")) {
                    boolean mobileCheck = true;
                    String updateUserMobile = "";
                    while (mobileCheck) {
                        System.out.println("Enter New Mobile Number:");
                        updateUserMobile = sc.nextLine();
                        if (updateUserMobile.matches("\\d+")) {

                            if (updateUserMobile.length() == 10) {
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

                    String updateusermobileSQL = "update user set mobile_no=? where username=?";
                    PreparedStatement pst10 = con.prepareStatement(updateusermobileSQL);
                    pst10.setString(1, updateUserMobile);
                    pst10.setString(2, loginUserName);
                    int rs10 = pst10.executeUpdate();
                    if (rs10 > 0) {
                        System.out.println("User MobileNumber Updated");
                    } else {
                        System.out.println("Failed or User Not Found !");
                    }
                } else if (ask.equalsIgnoreCase("4")) {
                    System.out.println("Enter New Email Id:");
                    String updateUserEmailId = sc.nextLine();
                    String updateuseremailSQL = "update user set email_id=? where username=?";
                    PreparedStatement pst10 = con.prepareStatement(updateuseremailSQL);
                    pst10.setString(1, updateUserEmailId);
                    pst10.setString(2, loginUserName);
                    int rs10 = pst10.executeUpdate();
                    if (rs10 > 0) {
                        System.out.println("User Email Id Updated");
                    } else {
                        System.out.println("Failed or User Not Found !");
                    }
                } else if (ask.equalsIgnoreCase("5")) {
                    return;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Enter Valid Choice !");
        }
    }

    void userLogin() throws Exception {
        ob.insertLast("PVR");
        ob.insertLast("INOX");
        ob.insertLast("Rajhans Cinema");
        ob.insertLast("Cinepolis");
        ob.insertLast("City Gold");
        ob.insertLast("IMAX");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String loginUserName = sc.nextLine();

        System.out.println("Enter Password: ");
        String loginPassword = sc.nextLine();

        String loginSQL = "SELECT * FROM user WHERE username = ? AND userpassword = ?";
        PreparedStatement preparedStatement = con.prepareStatement(loginSQL);
        preparedStatement.setString(1, loginUserName);
        preparedStatement.setString(2, loginPassword);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println();
            System.out.println("===== Login successful! =====");
            System.out.println();
            while (true) {
                System.out.println("1 -> Book Show");
                System.out.println("2 -> View Profile");
                System.out.println("3 -> Update Profile");
                System.out.println("4 -> Back To Menu");
                System.out.print("Enter Your Choice: ");
                String userInput = sc.next();

                sc.nextLine();
                if (userInput.equals("4")) {
                    break;
                }
                if (userInput.equals("1")) {// book
                    bookShow(loginUserName, loginPassword);

                } else if (userInput.equals("2")) {// view profile
                    viewProfile(loginUserName, loginPassword);

                } else if (userInput.equals("3")) {// Update User Profile
                    updateProfile(loginUserName);
                }
            }
        } else {
            System.out.println();
            System.out.println("Invalid Username or Password.");
            System.out.println();
        }
    }
}
package com.ticket2cinema;

import java.awt.Container;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Payment {
    int a;
    Scanner sc = new Scanner(System.in);

    void OTP() {
        JFrame OTP = new JFrame();
        OTP.setBounds(500, 350, 200, 200);
        OTP.setTitle("OTP-One Time Password");
        // OTP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = OTP.getContentPane();
        a = (int) (Math.random() * 10000);
        int ans = 4;
        if (ans == 3) {
            a *= 10;
        }
        if (ans == 2) {
            a *= 100;
        }
        if (ans == 1) {
            a *= 1000;
        }
        if (ans == 0) {
            a *= 10000;
        }
        JLabel otp = new JLabel();
        otp.setText("OTP: " + a);
        c.add(otp);
        OTP.setVisible(true);
    }

    void payment(String mailName, String mailMovie, String mailTime, String mailTheatre, int mailTotalSeats,
            String seatType, String emailSend) throws Exception {
        System.out.println("Enter 1 -> For Debit Card");
        System.out.println("Enter 2 -> For UPI Payment ");
        String online = sc.nextLine();

        if (online.equalsIgnoreCase("1")) {
            System.out.println();
            System.out.println("Enter Card Details :");
            System.out.println();
            while (true) {
                int flag2 = 0;
                System.out.println("Enter your 12 Digit Debit Card Number");
                String card = sc.next();

                if (card.length() == 12) {
                    for (int i = 0; i < card.length(); i++) {
                        if (card.charAt(i) >= '0' && card.charAt(i) <= '9') {
                            flag2 = 1;
                            continue;
                        } else {
                            System.out.println("Enter Digit of Debit Card Number between 0 to 9");
                            flag2 = 0;
                            break;
                        }
                    }
                } else {
                    System.out.println("Please, Enter 12 Digit Debit Card Number");
                    System.out.println();
                    continue;
                }
                if (flag2 == 1) {
                    System.out.println("Enter 3 Digit CVV: ");
                    String cvv = sc.next();

                    if (cvv.length() == 3) {
                        for (int i = 0; i < cvv.length(); i++) {
                            if (card.charAt(i) >= '0' && card.charAt(i) <= '9') {
                                flag2 = 1;
                                continue;
                            } else {
                                System.out.println("Enter Digit of CVV between 0 to 9");
                                flag2 = 0;
                                break;
                            }
                        }
                    } else {
                        System.out.println("Re-Enter 3 Digit CVV");
                        flag2 = 0;
                        continue;
                    }
                }

                if (flag2 == 1) {
                    OTP();
                    System.out.println("Enter OTP: ");
                    int otp_card = sc.nextInt();

                    if (otp_card == a) {
                        flag2 = 1;
                        System.out.println();
                        System.out
                                .println("Your Payment Successfully Received");

                        GEmailer gEmailSender = new GEmailer();
                        String to = emailSend;
                        String from = "mrengineer420notfound@gmail.com";
                        String subject = "Your Movie Ticket Booking Confirmation";
                        String text = "Dear " + mailName + " \n" + //
                                "\n" + //
                                "Thank you for booking with Ticket2Cinema! Your movie ticket has been successfully booked.\n"
                                + //
                                "\n" + //
                                "Booking Details:\n" + //
                                "\n" + //
                                "Movie        : " + mailMovie + " \n" + //
                                "Time          : " + mailTime + "\n" + //
                                "Theatre     : " + mailTheatre + "\n" + //
                                // "Seat : "+mailSeat+"\n" + //
                                "Total Seat : " + mailTotalSeats + "\n" + //
                                "Seat Type  : " + seatType + "\n" + "\n" + //
                                "Please arrive at the cinema at least 15 minutes before the showtime.\n" + //
                                "\n" + //
                                "Note: Bring a copy of this email or your booking ID to the ticket counter for verification.\n"
                                + //
                                "\n" + //
                                "We hope you enjoy the movie!\n" + //
                                "\n" + //
                                "Best regards,\n" + //
                                "The Ticket2Cinema Team\n" + //
                                "\n" + //
                                "Contact us on :\n" + //
                                "www.ticket2cinema.com";
                        boolean b = gEmailSender.sendEmail(to, from, subject, text);
                        if (b) {
                            System.out.println("Email is sent successfully");
                        } else {
                            System.out.println("There is problem in sending email");
                        }
                        System.out.println();
                        System.out
                        .println("                   ---Thank You For Booking---                           ");
                        System.out.println();
                        break;
                    } else {
                        System.out.println("Recieved OTP did'n match");
                        System.out.println("Your Transication Is Failed Try Again !");
                        continue;
                    }
                }

            }
        } else if (online.equalsIgnoreCase("2")) {

            boolean flag2 = true;
            while (flag2) {
                System.out.println("Enter Your 4 Digit Pin ");
                String pin = sc.nextLine();
                if (pin.length() == 4) {
                    flag2 = false;
                    break;
                } else {
                    System.out.println("Please, Enter 4 Digit Number");
                }
            }
            System.out.println("Your Payment Successfully Received");
            System.out.println();
            GEmailer gEmailSender = new GEmailer();
            String to = emailSend;
            String from = "mrengineer420notfound@gmail.com";
            String subject = "Your Movie Ticket Booking Confirmation";
            String text = "Dear " + mailName + " \n" + //
                    "\n" + //
                    "Thank you for booking with Ticket2Cinema! Your movie ticket has been successfully booked.\n"
                    + //
                    "\n" + //
                    "Booking Details:\n" + //
                    "\n" + //
                    "Movie        : " + mailMovie + " \n" + //
                    "Time          : " + mailTime + "\n" + //
                    "Theatre     : " + mailTheatre + "\n" + //
                    // "Seat : "+mailSeat+"\n" + //
                    "Total Seat : " + mailTotalSeats + "\n" + //
                    "Seat Type  : " + seatType + "\n" + "\n" + //
                    "Please arrive at the cinema at least 15 minutes before the showtime.\n" + //
                    "\n" + //
                    "Note: Bring a copy of this email or your booking ID to the ticket counter for verification.\n"
                    + //
                    "\n" + //
                    "We hope you enjoy the movie!\n" + //
                    "\n" + //
                    "Best regards,\n" + //
                    "The Ticket2Cinema Team\n" + //
                    "\n" + //
                    "Contact us on :\n" + //
                    "www.ticket2cinema.com";
            boolean b = gEmailSender.sendEmail(to, from, subject, text);
            if (b) {
                System.out.println("Email is sent successfully");
            } else {
                System.out.println("There is problem in sending email");
            }
            System.out.println();
            System.out.println("---Thank You For Booking---                           ");
            System.out.println();
        } else {
            System.out.println("Your Transication Is Failed Try Again !");
        }
    }
}

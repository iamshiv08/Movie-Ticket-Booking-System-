# BookMyShow
## Overview
The BookMyShow project is a Java-based application that simulates an online movie ticket booking system. It allows users to create accounts, log in, update profiles, and book movie tickets. The system supports choosing theaters, selecting preferred seats, and making payments via UPI or debit cards. Once a ticket is booked, it is automatically sent to the user's email using the Java Mail API. The admin section allows managing movies and theaters.

## Features
### User Functionality
Account Creation: Users can create an account by providing a username, password, and email address.

Login: Users can log in with their username and password.

Profile Management: Users can update their profile information.

Movie Booking:

  1. Select a theater.

  2. Choose a movie and preferred showtime.

  3. Select seats.

  4. Payment options include UPI and debit card.

Ticket Confirmation: After booking, the ticket is sent to the user's email.

### Admin Functionality

Login: Admin can log in to manage the system.

Manage Movies: Admin can add, update, or remove movies from the system.

Manage Theaters: Admin can add, update, or remove theaters using a linked list implementation for efficient management.

## Technologies Used
Java: Core programming language.

Jakarta Mail API: For sending email confirmations.

JDBC: For database connectivity and operations.

Linked List: Used for managing the list of theaters.

Maven/Gradle: Project build and dependency management.
## Usage
User Account Creation: Sign up with a valid email, username, and password.

Booking a Movie: After logging in, select a theater, choose a movie, pick seats, and make the payment.

Admin Management: Log in as an admin to manage movies and theaters.

## Future Enhancements

Enhanced Payment Gateway Integration: Adding support for more payment methods.

Notification System: SMS notifications along with email.

Recommendation Engine: Suggest movies based on user preferences.
## Note

Before Run The Code Set Up Environment Variables

Open Environment Variables Window:

Open the Start Search, type in "env", and select "Edit the system environment variables".
In the System Properties window, click the "Environment Variables…" button.
Add New Environment Variables:

Under "System variables", click "New…" to add a new variable.
Enter GMAIL_USERNAME for the variable name and your Gmail address (e.g., your_email@gmail.com) for the variable value.
Repeat the above step to add GMAIL_APP_PASSWORD with your app password as the value.
Verify:

Open a new command prompt and type echo %GMAIL_USERNAME% and echo %GMAIL_APP_PASSWORD% to verify that the environment variables are set correctly.

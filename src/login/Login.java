/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import FileHandling.File;
import book.Cabs;
import book.Location;
import cabbookingsystem.CabBookingSystem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import static javafx.application.Platform.exit;

/**
 *
 * @author Monish De
 */
public class Login {

    private String title, firstName, lastName, password;
    private long mobileNumber;
    private int index;
    ArrayList<Cabs> cabsList;
    Cabs cabs;
    private final FileInputStream in = null;

    public void accountDetails(Login user) {
        System.out.println(String.format("%s %s %s", user.title, user.firstName, user.lastName));
        System.out.println(String.format("Mobile Number : %s", user.mobileNumber));
    }

    public boolean verifyUser(long mobileNumber, String password) {
        return (this.getMobileNumber() == mobileNumber && this.getPassword().equals(password));
    }

    public void userPortal(int index, ArrayList<Login> userList,
            ArrayList<Location> locationList,
            ArrayList<Cabs> cabList, File file) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Welcome %s %s",
                userList.get(index).getTitle(),
                userList.get(index).getLastName()));
        while (true) {
            System.out.println("1. View/Edit your account details");
            System.out.println("2. Book a cab");
            System.out.println("3. Exit");
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    accountDetails(userList.get(index));
                    break;
                case 2:
                    String source;
                    String destination;
                    boolean cabsFound = false;
                    boolean inputValidSource = false;
                    boolean inputValidDestination = false;
                    while (inputValidSource != true && inputValidDestination != true) {
                        locationList = file.readLocation();
                        System.out.println("Enter source or it's location "
                                + "code -->");
                        source = scanner.next();
                        for (Location loc : locationList) {
                            if (loc.getName().equalsIgnoreCase(source)) {
                                inputValidSource = true;
                            }
                        }
                        System.out.println("Entet destination or it's location "
                                + "code -->");
                        destination = scanner.next();
                        for (Location loc : locationList) {
                            if (loc.getName().equalsIgnoreCase(destination)) {
                                inputValidDestination = true;
                            }
                        }
                        //UI FEEDBACK
                        if (inputValidSource == false) {
                            System.out.println("Enter a valid source or it's "
                                    + "location code.");
                            continue;
                        } else if (inputValidDestination == false) {
                            System.out.println("Enter a valid destination or "
                                    + "it's location code.");
                            continue;
                        }
                        cabList = file.readCabs();
                        ArrayList<Integer> nCabs = new ArrayList<>();
                        try {
                            for (int i = 0; i < 2; ++i) {
                                System.out.print("Looking for cabs around you");
                                for (int j = 0; j < 3; j++) {
                                    System.out.print(".");
                                    Thread.sleep(400);
                                }
                                System.out.print("\b");
                            }
                        } catch (InterruptedException e) {
                            System.out.println(e.getCause());
                        }
                        for (Cabs c : cabList) {
                            if (c.searchCabs(source, destination) == true) {
                                cabsFound = true;
                                nCabs.add(c.getIndex());
                            }
                        }
                        if (cabsFound == false) {
                            System.out.println("Sorry couldn't find any cabs "
                                    + "in this route.");
                            System.out.println("");
                        } else {
                            System.out.println("We found the following cabs "
                                    + "in this route");
                            System.out.println("");
                            for (int n : nCabs) {
                                System.out.println(n + 1 + ".");
                                cabList.get(n).printDetails();
                            }
                            System.out.println("Choose your cab");
                            System.out.println("");
                            int selection = scanner.nextInt();
                            selection -= 1;
                            System.out.println("You have selected ..");
                            cabList.get(selection).printDetails();
                            System.out.println("");
                            System.out.println("Do you wanna confirm this "
                                    + "cabs?");
                            String choice = scanner.next();
                            choice = choice.toLowerCase();
                            if (choice.equals("yes") || choice.equals("y")) {
                                System.out.println("Your cab will arrive to your location.");
                            }
                        }
                    }
                    break;
                case 3:
                    CabBookingSystem cb = new CabBookingSystem();
                    String[] args = new String[2]; 
                    cb.main(args);
                    break;
                default:
                    break;
            }
        }
    }

    public void performLogin(ArrayList<Login> userList, ArrayList<Location> locationList,
            ArrayList<Cabs> cabList, File file) throws IOException {
        boolean validUser = false;
        Scanner scanner = new Scanner(System.in);

        String localPassword;
        long localMobileNumber;
        int localIndex = 0;

        while (validUser != true) {
            System.out.println("Enter Your Mobile Number : ");
            localMobileNumber = scanner.nextLong();
            System.out.println("Enter your password : ");
            localPassword = scanner.next();
            for (Login l : userList) {
                validUser = l.verifyUser(localMobileNumber, localPassword);
                localIndex = l.getIndex();
                try {
                    for (int i = 0; i < 2; ++i) {
                        System.out.print("Loading");
                        for (int j = 0; j < 3; j++) {
                            System.out.print(".");
                            Thread.sleep(400);
                        }
                        System.out.print("\b");
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getCause());
                }
                if (validUser == true) {
                    break;
                }
            }
            if (validUser == false) {
                //UI FEEDBACK
                System.out.println("Enter valid login");
            } else {
                localIndex -= 2;
                userPortal(localIndex, userList, locationList, cabList, file);
            }
        }
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the mobileNumber
     */
    public long getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}

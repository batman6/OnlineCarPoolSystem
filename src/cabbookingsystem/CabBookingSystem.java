/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabbookingsystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import FileHandling.File;
import login.Login;
import login.SignUp;
import book.Cabs;
import book.Location;
/**
 *
 * @author Monish De
 */
public class CabBookingSystem {

    static Scanner scanner;
    static ArrayList<Login> userList;
    static ArrayList<Location> locationList;
    static ArrayList<Cabs> cabList;
    static File file;
    static SignUp signUp;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String choice;
        System.out.println("Welcome to Wayne Enterprise Cab booking system");
        System.out.println("Are you an existing customer ?");
        scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        choice = choice.toLowerCase();
        file = new File();
        userList = file.readUser();
        while (true) {
            switch (choice) {
                case "yes":
                case "y":
                    Login l = new Login();
                    l.performLogin(userList, locationList, cabList, file);
                    break;
                case "no":
                case "n":
                    signUp = new SignUp();
                    signUp.prepSignUp(userList.size() + 1);
                    userList = file.readUser();
                    choice = "yes";
                    break;
                default:
                    //UI FEEDBACK
                    System.out.println("Try again enter a valid choice");
                    break;
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import book.Cabs;
import book.Location;
import login.Login;
import login.SignUp;
/**
 *
 * @author Monish De
 */
public class File {
    private final FileInputStream in = null;
    ArrayList<Login> userList;
    ArrayList<Location> locationList;
    ArrayList<Cabs> cabsList;
    Login login;
    Cabs cabs;
    Location location;

    public ArrayList<Login> readUser() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("UserList.txt"));
            login = new Login();
            String line;
            int counter = 2;
            StringBuilder builder = new StringBuilder(Integer.toString(counter));
            builder.append(".");
            login.setIndex(1);
            userList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Title : ")) {
                    login.setTitle(line.substring(8));
                } else if (line.startsWith("First Name : ")) {
                    login.setFirstName(line.substring(13));
                } else if (line.startsWith("Last Name : ")) {
                    login.setLastName(line.substring(12));
                } else if (line.startsWith("Password : ")) {
                    login.setPassword(line.substring(11));
                } else if (line.startsWith("Mobile Number : ")) {
                    login.setMobileNumber(Long.parseLong(line.substring(16)));
                } else if (line.startsWith(builder.toString())) {
                    userList.add(login);
                    login.setIndex(counter++);
                    login = new Login();
                    builder = new StringBuilder(Integer.toString(counter));
                    builder.append(".");
                }
            }
            login.setIndex(counter++);
            userList.add(login);
            login = new Login();

        } finally {
            if (in != null) {
                in.close();
            }
        }
        return userList;
    }

    public ArrayList<Location> readLocation() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("LocationList.txt"));
            locationList = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                location = new Location();
                String[] data = new String[1];
                data = line.split(" ");
                location.setName(data[0]);
                locationList.add(location);
                
            }

        } finally {
            if (in != null) {
                in.close();
            }
        }
        return locationList;
    }

    public ArrayList<Cabs> readCabs() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("CabsList.txt"));
            String line;
            cabsList = new ArrayList<>();
            cabs = new Cabs();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Source : ")) {
                    cabs.setSource(line.substring(9));
                } else if (line.startsWith("Destination : ")) {
                    cabs.setDestination(line.substring(14));
                } else if (line.startsWith("Cabs Code : ")) {
                    cabs.setCabsNo(line.substring(12));
                }else if (line.startsWith("Cabs Time : ")){
                    cabs.setArrivalTime(line.substring(12));
                }else if (line.startsWith("Index : ")) {
                    cabs.setIndex(Integer.parseInt(line.substring(8)));
                    cabsList.add(cabs);
                    cabs = new Cabs();
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return cabsList;
    }

    public void signUp(int index, String title, String firstName, String lastName,
             String password, long mobileNumber)
            throws IOException {

        try (FileWriter fw = new FileWriter("UserList.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(Integer.toString(index) + ".");
            out.println("Title : " + title);
            out.println("First Name : " + firstName);
            out.println("Last Name : " + lastName);
            out.println("Password : " + password);
            out.println("Mobile Number : " + Long.toString(mobileNumber));
        } finally {
            System.out.println("User creation Successful, now you can login "
                    + "using your Mobile Number and Password");
        }
    }
    
}

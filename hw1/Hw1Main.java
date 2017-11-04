
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author onder
 */
@SuppressWarnings("unchecked")
public class Hw1Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            boolean exit = false;

            while (!exit) {
              BufferedReader tara = new BufferedReader(new InputStreamReader(System.in));
                Person.updateUserData();
                System.out.println("Enter User name");
                System.out.println("-1 for exit");
                String name2 = tara.readLine();
                if(name2.equals("-1")){
                     exit=true;
                     break;
                }  
                System.out.println("Enter Password");
                String pass=tara.readLine();
                IPerson client = Person.searchUser(name2);
                if (client != null) {
                    if(client.getPassword().equals(pass))
                        client.printMenu();
                    else 
                        System.out.println("Wrong Password");
                } else {
                    System.err.println("Wrong User");
                    System.out.println("-1 for exit;");
                    System.out.println("1 for relogin");
                    Scanner tara2=new Scanner(System.in);
                    int input = tara2.nextInt();
                    if (input == -1) {
                        exit = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Users can not loaded");
        }
    }

}

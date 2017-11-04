package Hw2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 * This User class only can take a book or give back the book
 *
 */
@SuppressWarnings("unchecked")
public class User extends PersonArrayList {

    public User(int id, String username, String password) throws Exception {
        this.userid = id;
        this.username = username;
        this.password = password;
    }

    /**
     * This is the main menu for the User
     */
    @Override
    public void printMenu() {
        try {
            PersonArrayList.updateBookData();

            boolean exit = false;
            while (!exit) {
                System.out.println("------Please Select top:--------");
                System.out.println("1)Take a book");
                System.out.println("2)Give back  book");
                System.out.println("3)Exit");
                Scanner tara = new Scanner(System.in);
                int input = tara.nextInt();
                int input2 = 0;
                if (input == 1) {
                 
                    printBooksinLib();
                    System.out.println("Please select bookid:");
                    System.out.println("-1 for exit;");
                    input2 = tara.nextInt();
                    if (input2 != -1) {
                        Book inform = null;
                        inform = searchBook(input2);
                        if (inform == null) {
                            System.err.println("there is no that book");
                        } else if (inform.gettakenUser() == null) {
                     
                            lendBook(inform);
                        
                        } else {
                            System.err.println("the book has been given already!");
                        }

                    }
                } else if (input == 2) {
                    double startTime = System.nanoTime();
                    printBooksAtUser();
                    double endTime = System.nanoTime();
                    double estimatedTime = endTime - startTime;
                    System.out.println("printBooksAtUser fonksiyonun çalışma süresi=" + estimatedTime);
                    System.out.println("Please select bookid:");
                    System.out.println("-1 for exit;");
                    input2 = tara.nextInt();
                    if (input2 != -1) {
                        startTime = System.nanoTime();
                        Book book = searchBook(input2);
                        endTime = System.nanoTime();
                        estimatedTime = endTime - startTime;
                        if (book != null) {
                            GiveBackBook(book);
                        } else {
                            System.err.println("There is no book as that!");
                        }

                    }
                } else if (input == 3) {

                    exit = true;
                    break;
                }
                System.out.flush();

            }
        } catch (Exception ex) {
            System.err.println("Books can not loaded!");
        }

    }

}

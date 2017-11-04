

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.Console;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This User has all permission such as
 *  Add book
 *  Remove book
 *  add user
 *  Take a book
 *  Give back book for all users
 * 
 */
@SuppressWarnings("unchecked")
public class Administrator extends Person {

    public Administrator(int id, String username, String password) {
        this.userid = id;
        this.username = username;
        this.password = password;
    }
    /**
     *  This function add the user
     * @param nuser keep the new user information
     * @throws Exception when file is not found
     */
    public void adduser(IPerson nuser) throws Exception {
        PrintWriter output = null;
        output = new PrintWriter(new FileWriter("data/Users.csv", true));
        if (searchUser(nuser.getUsername()) != null) {
            System.err.println("There is a same name user,"
                    + "please change that");
        } else {
            int usertype=1;
            if(nuser instanceof Administrator)
                usertype=2;
            output.printf("%d;%s;%s;%d\n", nuser.getUserid(), nuser.getUsername(), nuser.getPassword(),usertype);
            userlist.add(nuser);
            System.out.println("User added.");
        }
        output.close();

    }
    /**
     * This function add the book
     * @param nbook keep the new Book information
     * @throws Exception when file processing has some problem
     */
    public void addbook(Book nbook) throws Exception {
        PrintWriter output = null;
        output = new PrintWriter(new FileWriter("data/Books.csv", true));
        output.printf("%s\n", nbook.Bookinform());
        booklist.add(nbook);
        output.close();

    }
    /**
     * This function remove the book
     * @param book which is removed
     * @throws Exception  when file processing has some problem
     */
    public void removebook(Book book) throws Exception {
        PrintWriter output = null,lentoutput=null;
        output = new PrintWriter(new FileWriter("data/Books.csv"));
        lentoutput=new PrintWriter(new FileWriter("data/LendBooks.csv"));
        booklist.remove(book);
        for (int i = 0; i < booklist.size(); i++) {
                Book nbook = booklist.get(i);
                 output.printf("%d;%s;%s;%s\n", i+1,nbook.getName(),nbook.getAuthor(),nbook.getPublisher());
             if (booklist.get(i).gettakenUser()!=null) {
                 lentoutput.printf("%d;%d\n", nbook.gettakenUser().getUserid(),i+1);
            }
        }
        output.close();
        lentoutput.close();
        updateBookData();
    }
    /**
     * This is override from Person abstract class
     * This print all books which is taken by user and 
     * print user inform who take that
     * 
     */
    @Override
    public void printBooksAtUser() {
      System.out.println("---------At User----");
        System.out.println("id-Book Name- User Name");
        ArrayList<Book> temparr = getBooklist();
        boolean flag = false;
        for (int i = 0; i < temparr.size(); i++) {
            if (temparr.get(i).gettakenUser() != null ) {
                Book book=temparr.get(i);
                System.out.println(book.getid()+" , "+book.getName()+" , "+ book.gettakenUser().getUsername());
                flag = true;
            }

        }
        if (!flag) {
            System.out.println("There is no book to give back.");
        };
    }
    /**
     * This is the main menu for the Administrator 
     */
    @Override
    public void printMenu() {
        try {
            Person.updateBookData();

            boolean exit = false;
            while (!exit) {
                System.out.println("------Please Select top:--------");
                System.out.println("1)Take a book");
                System.out.println("2)Give back book");
                System.out.println("3)Add new Book:");
                System.out.println("4)Remove Book:");
                System.out.println("5)Add new User:");
                System.out.println("6)Exit");

                Scanner tara = new Scanner(System.in);
                int input = tara.nextInt();
                int input2 = 0;
                switch (input) {
                    case 1:
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
                                System.out.println("you took the book.");
                            } else {
                                System.err.println("the book has been given already!");
                            }

                        }
                        break;
                    case 2:
                        printBooksAtUser();
                        System.out.println("Please select bookid:");
                        System.out.println("-1 for exit;");
                        input2 = tara.nextInt();
                        if (input2 != -1) {
                            Book book = searchBook(input2);
                            GiveBackBook(book);

                        }
                        break;
                    case 3:
                           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Book Name:");
                        String name = br.readLine();
                        System.out.println("Author:");
                        String author = br.readLine();
                        System.out.println("Publisher:");
                        String pub = br.readLine();
                        addbook(new Book(booklist.size() + 1, name, author, null, pub));
                        break;
                    case 4:
                        printBooksinLib();
                        printBooksAtUser();
                        System.out.println("Please select bookid:");
                        System.out.println("-1 for exit;");
                        input2 = tara.nextInt();
                        if (input2 != -1) {
                            Book inform = null;
                            inform = searchBook(input2);
                            if (inform == null) {
                                System.err.println("there is no that book");
                            } else {
                                if(inform.gettakenUser()!=null)
                                    GiveBackBook(inform);
                                removebook(inform);
                                System.out.println("you remove the book.");
                            }

                        }
                        break;
                    case 5:
                          BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Enter User name");
                        String name2 = br2.readLine();
                        System.out.println("Enter Password");
                        Console console = System.console();
                        String pass =  br2.readLine();
                        System.out.println("select id for User type;");
                        System.out.println("1)User");
                        System.out.println("2)Staff");
                        int type = tara.nextInt();
                        Person newper = null;
                        if (type == 1) {
                            newper = new User(userlist.size() + 1, name2, pass);
                        } else if (type == 2) {
                            newper = new Administrator(userlist.size() + 1, name2, pass);
                        } else {
                            System.err.println("Wrong type");
                        }
                        adduser(newper);
                        break;
                    case 6:
                        exit = true;
                    default:
                        break;
                }
                System.out.flush();

            }
        } catch (Exception ex) {
            System.err.println("Books can not loaded!");
        }

    }

}

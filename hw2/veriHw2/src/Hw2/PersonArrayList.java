package Hw2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This is abstract because some functions same all class
 * so it was implemented in there
 */
@SuppressWarnings("unchecked")
public abstract class PersonArrayList implements IPerson {
    /**
     *  All user sub class has user id as integer
     */
    protected int userid;
     /**
     *  All user sub class has user name as string
     */
    protected String username;
      /**
     *  All user sub class has user password as string
     */
    protected String password;
    /**
     * This save the all book information in list
     */
    protected static ArrayList<Book> booklist = new ArrayList<>();
    /**
     * This save the all user information in list
     */
    protected static ArrayList<IPerson> userlist = new ArrayList<>();
    /**
     * 
     * @return User id
     */
    @Override
    public int getUserid() {
        return userid;
    }
    /**
     * 
     * @return User name
     */
    @Override
    public String getUsername() {
        return username;

    }
    /**
     * 
     * @return User password
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * This function print the book which is in library
     */
    public void printBooksinLib() {
        System.out.println("---------In library----");
        System.out.println("id-Book Name-Authors-Publisher");
        ArrayList<Book> temparr = getBooklist();
        boolean flag = false;
        for (int i = 0; i < temparr.size(); i++) {
            if (temparr.get(i).gettakenUser() == null) {
                flag = true;
                System.out.println(temparr.get(i));
            }

        }
        if (!flag) {
            System.out.println("there is no book in Lib.!");
        }
    }
    
      /**
     * This function print the book which is taken from the login user
     */
    public void printBooksAtUser() {
        System.out.println("---------At User----");
        System.out.println("id-Book Name-Authors-Publisher");
        ArrayList<Book> temparr = getBooklist();
        boolean flag = false;
        for (int i = 0; i < temparr.size(); i++) {
            if (temparr.get(i).gettakenUser() != null && equals(temparr.get(i).gettakenUser())) {
                System.out.println(temparr.get(i));
                flag = true;
            }

        }
        if (!flag) {
            System.out.println("There is no book to give back.");
        };
    }
    /**
     * 
     * @return Book list
     */
    public ArrayList<Book> getBooklist() {
        return booklist;
    }
    /**
     * 
     * @return User list
     */
    public ArrayList<IPerson> getUserlist() {
        return userlist;
    }
    /**
     * 
     * @param bookid id to find the book information
     * @return The book as a Book class
     */
    public Book searchBook(int bookid) {

        for (int i = 0; i < booklist.size(); i++) {
            if (booklist.get(i).getid() == bookid) {
                return booklist.get(i);
            }
        }
        return null;
    }
    /**
     * 
     * @param usname user name
     * @return The User which user name same as parameter
     * @throws Exception when file is not found
     */
    public static IPerson searchUser(String usname) throws Exception {

        for (int i = 0; i < userlist.size(); i++) {
            String temp = userlist.get(i).getUsername();
            if (temp.equals(usname)) {
                return userlist.get(i);

            }

        }
        return null;
    }
    /**
     * when user might take a book, this function is using
     * @param book  book information
     * 
     */
    public void lendBook(Book book) {
        try {
            if (book.gettakenUser() == null) {
                PrintWriter output = null;
                output = new PrintWriter(new FileWriter("data/LendBooks.csv", true));
                 book.editUser(this);
                output.printf("%s\n", book.LendBookinform());
                output.close();
                System.out.println("You took book.");
            } else {
                System.err.println("The Book is given previously!");
            }
        } catch (Exception ex) {

        }
    }
    /**
     * When user might give back book,this function is using
     * @param book information
     */
    public void GiveBackBook(Book book) {
        if (book.gettakenUser() != null) {
            try {
                PrintWriter output = null;
                output = new PrintWriter(new FileWriter("data/LendBooks.csv"));
                book.editUser(null);
                for (int i = 0; i < getBooklist().size(); i++) {
                    Book nbook = getBooklist().get(i);
                    if (nbook.gettakenUser() != null) {
                        //userid ;kitapid yazılır dosyaya
                        output.printf("%s\n", nbook.LendBookinform());

                    }
                }

                output.close();
                System.out.println("You gave the book.");
            } catch (IOException ex) {
                System.err.println("File does not opened");
            }
        } else {
            System.err.println("The book in lib already!");
        }

    }
    /**
     * When program is running the user data must be loaded from file
     * This function is using for that.
     * @throws Exception when File is not found
     */
    public static void updateUserData() throws Exception {
        File file = null;
        Scanner filescan = null;
        userlist.clear();
        file = new File("data/Users.csv");
        filescan = new Scanner(file);
        IPerson newuser;

        while (filescan.hasNext()) {
            String line = filescan.next();
            String seperated[] = line.split(";");
            int tempid = Integer.parseInt(seperated[0]);

            if (seperated[3].equals("1")) {
                newuser = new User(tempid, seperated[1], seperated[2]);
            } else if (seperated[3].equals("2")) {
                newuser = new Administrator(tempid, seperated[1], seperated[2]);
            } else {
                throw new Exception("Invalid User Type");

            }

            userlist.add(newuser);

        }
        filescan.close();
    }
     /**
     * When program is running the book data must be loaded from file
     * This function is using for that.
     * @throws Exception when File is not found
     */
    public static void updateBookData() throws Exception {
        File file = null;
        Scanner filescan = null;
        file = new File("data/Books.csv");
        filescan = new Scanner(file);
        Book nbook;
        booklist.clear();

        while (filescan.hasNextLine()) {
            try {
                String line = filescan.nextLine();
                String seperated[] = line.split(";");
                int bookid = Integer.parseInt(seperated[0]);
                IPerson usr = isLentedBook(bookid);
                Book yenikitap = new Book(bookid, seperated[1], seperated[2], usr, seperated[3]);
                booklist.add(yenikitap);
            } catch (Exception ex) {
                throw new Exception("The Book data wrong!");
            }

        }
        filescan.close();

    }
    /**
     *  
     * @param bookid Book id for find the book
     * @return if the any user was take the book , this return user informations 
     * @throws Exception when file is not found
     */
    protected static IPerson isLentedBook(int bookid) throws Exception {
        File file = null;
        Scanner filescan = null;
        try {
            file = new File("data/LendBooks.csv");
            filescan = new Scanner(file);
            while (filescan.hasNext()) {
                String line = filescan.next();
                String seperated[] = line.split(";");
                int tbid = Integer.parseInt(seperated[1]);
                int userid = Integer.parseInt(seperated[0]);
                if (tbid == bookid) {
                    return searchUser(userid);
                }
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        }
        filescan.close();
        return null;

    }
    /**
     * 
     * @param usid means user id to find user information
     * @return Person informations
     */
    public static IPerson searchUser(int usid) {

        for (int i = 0; i < userlist.size(); i++) {
            int tempid = userlist.get(i).getUserid();
            if (tempid == usid) {
                return userlist.get(i);

            }

        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    /**
     * This check only person id , if they are same this return true
     * @param obj Other object
     * @return if they are samo ,return true
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final PersonArrayList other = (PersonArrayList) obj;
        if (this.userid != other.userid) {
            return false;
        }
        return true;
    }
}

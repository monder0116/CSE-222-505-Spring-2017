

/**
 *
 * @author onder
 */
@SuppressWarnings("unchecked")
public class Book {

    protected String bookname;
    protected int bookid;
    protected String author;
    protected IPerson takeUser;
    protected String publisher;

    public Book(int bookidd, String bookname, String author, IPerson takeUser, String publisher) {
        this.bookname = bookname;
        this.bookid = bookidd;
        this.author = author;
        this.takeUser = takeUser;
        this.publisher = publisher;
    }

    /**
     *
     * @return the user which this book taken by
     */
    public IPerson gettakenUser() {
        return takeUser;
    }

    /**
     *
     * @return Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }
    /**
     * 
     * @return Book name
     */
    public String getName() {
        return bookname;
    }
    /**
     * 
     * @return Book id
     */
    public int getid() {
        return bookid;
    }
    /**
     * 
     * @return the book inform for console
     */
    @Override
    public String toString() {
        String msj = this.getid() + ","
                + this.getName() + "," + this.getAuthor() + "," + this.getPublisher();
        return msj;
    }
    /**
     * when This book lent to user,this function return string to write file
     * the lending informations
     * @return User id and book id inform
     */
    public String LendBookinform() {
        if (takeUser != null) {
            return String.format("%d;%d", takeUser.getUserid(), getid());
        }
        return new String("");

    }
    /**
     * When this book write to Books file this function return the informs
     * 
     * @return Book information for books file
     */
    public String Bookinform() {
        return String.format("%d;%s;%s;%s", getid(), getName(), getAuthor(), getPublisher());

    }
    /**
     * 
     * @param other other object
     * @return if the all variable is same ,return true
     */
    @Override
    public boolean equals(Object other) {
        Book other2 = (Book) other;
        return this.getid() == other2.getid() && this.getName() == other2.getName()
                && this.getPublisher() == other2.getPublisher();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.bookid;
        return hash;
    }
    /**
     * This change the user which take this book
     * @param usr user inform
     */
    public void editUser(Person usr) {
        takeUser = usr;
    }
}

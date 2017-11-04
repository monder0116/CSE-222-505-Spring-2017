package Hw2;


/**
 * 
 * This interface for all users.
 * Have some functions
 * 
 */
@SuppressWarnings("unchecked")
public interface IPerson {
    /**
     * 
     * @return This return user id
     */
    int getUserid();
    /**
     * 
     * @return This return user name
     */
    String getUsername();
    /**
     * 
     * @return This return user password
     */
    String getPassword();
    /**
     * This will print the menu depend on 
     *  users permission
     */
    void printMenu() ;
    
    
}

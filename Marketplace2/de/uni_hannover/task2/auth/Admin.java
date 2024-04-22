package de.uni_hannover.task2.auth;

import de.uni_hannover.task2.offerings.Item;
/**
 * This class represents an Admin.
 * 
 * It can later have a different access to the marketplace than a user.
 * 
 * @author Nico Grösche
 * @version 09/05/2023
 */
public class Admin extends User{
	// Stores if the User is Admin
	private final boolean isAdmin = true;
	
	/**
	 *Getter for isAdmin attribute.
	 * 
	 * @author Nico Grösche
	 */
	public boolean getIsAdmin(){
		return this.isAdmin;
	}
	
	/**
	 * Constructor for the Admin class (extension of User Class)
	 * 
	 * @author Nico Grösche
	 */
	public Admin(String username, String password){
        super(username, password);
    }
}
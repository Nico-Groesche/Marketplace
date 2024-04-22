package de.uni_hannover.task2.auth;

import de.uni_hannover.task2.offerings.Item;

/**
 * Represents a user in the marketplace.
 * Users are able to offer items on the marketplace
 * A user is associated with a username and password.
 * 
 * @author Nico Grösche
 * @version 03/05/2023
 */
public class User {
	// Stores if the User is an Admin
	private final boolean isAdmin = false;
    // Stores the username
    protected String username;
    // Stores the password (extremly unsafe)
    protected String password;
    // Stores the items that are offered. Some fields may be empty
    protected Item[] items;

    /**
     * Constructs an user object with a given username
     * and password. User created with this method can
     * offer up to 10 items at a time.
     * 
     * @author Nico Grösche
     * @param username Username of the user
     * @param password Password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.items = new Item[10];
    }
	/**
	 * Getter for getIsAdmin
	 * 
	 * @author Nico Grösche
	 * @version 09/05/2023
	 */
	public boolean getIsAdmin(){
		return this.isAdmin;
	}

    /**
     * Returns the username.
     * 
     * @author Nico Grösche
     * @return Username of the user
     */
	public String getUsername() {
		return this.username;
	}

    /**
     * Sets the username.
     * 
     * @author Nico Grösche
     * @param username New username of the user
     */
	public void setUsername(String username) {
		this.username = username;
	}

    /**
     * Returns the password.
     * 
     * @author Nico Grösche
     * @return Password of the user
     */
	public String getPassword() {
		return this.password;
	}

    /**
     * Sets the password.
     * 
     * @author Nico Grösche
     * @param password New password of the user
     */
	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Returns the items offered by the user.
     * 
     * @author Nico Grösche
     * @return Items that the user is offering
     */
	public Item[] getItems() {
		return this.items;
	}

    /**
     * Removes an item from the offered items.
     * 
     * @author Nico Grösche
     * @param item Item that shall be removed
     * @return False if the item was not found, true otherwise.
     */
    public boolean removeItem(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if(item == this.items[i]) {
                this.items[i] = null;
                return true;
            }
        }

        return false;
    }

    /**
     * Adds an item from the offered items.
     * 
     * @author Nico Grösche
     * @param item Item that shall be added
     * @return False if item could not be added, true otherwise.
     */
    public boolean addItem(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if(this.items[i] == null) {
                this.items[i] = item;
                return true;
            }
            
        }

        return false;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @author Nico Grösche
     * @return String representation of the object.
     */
    public String str() {
        // Password sollte vielleicht nicht ausgegeben werden.
        String res = this.username + " offers:\n";

        for(int i = 0; i < this.items.length; i++) {
            if(this.items[i] == null) {
                continue;
            }
            
            res += this.items[i].str();
        }

        return res;
    }
}
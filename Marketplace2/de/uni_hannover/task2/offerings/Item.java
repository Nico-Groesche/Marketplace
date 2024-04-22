package de.uni_hannover.task2.offerings;


import de.uni_hannover.task2.auth.User;

/**
 * This class represents an item that
 * can be offered on a digital marketplace.
 * 
 * Items are offered by an user. Stores 
 * attributes such as name, price, description
 * and the category
 * 
 * @author Nico Grösche
 * @version 03/05/2023
 */
public class Item {
    // item name
    private String name;
    // item price
    private float price;
    // owner of the object
    private User owner; 
    // description of the item
    private String description;
    // category the item belongs to
    private Category category;

    /**
     * Constructs the object with all its attributes.
     * 
     * @author Nico Grösche
     * @param name Name of the item
     * @param price Price of the item
     * @param owner Owner of the item
     * @param description Description of the item
     * @param category Category of the item.
     */
    public Item(String name, float price, User owner, String description, Category category) {
        this.name = name;
        this.price = price;
        this.owner = owner;
        this.description = description;
        this.category = category;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @author Nico Grösche
     * @return String representation of the object.
     */
    public String str() {
        return String.format(
            "Item: %s; Price: %.2f, User: %s, Description: %s, Category: %s\n",
            name,
            price,
            owner.getUsername(),
            description,
            category.str()
        );
    }

    /**
     * Gets the name of the item.
     * 
     * @author Nico Grösche
     * @return Name of the item
     */
	public String getName() {
		return this.name;
	}

    /**
     * Sets the name of the object if the callee
     * of this function is the same as the owner.
     * 
     * @author Nico Grösche
     * @param name New name of the item
     * @param callee User that is calling the function
     */
    public void setName(String name, User callee) {
        if(callee != owner) {
            return;
        }

		this.name = name;
	}

    /**
     * Gets the price of the item
     * 
     * @author Nico Grösche
     * @return Price of the item.
     */
	public float getPrice() {
		return this.price;
	}

    /**
     * Sets the price of the item if the callee
     * of this function is the same as the owner.
     * 
     * @author Nico Grösche
     * @param price New price of the item
     * @param callee User that is calling the function
     */
	public void setPrice(float price, User callee) {
        if(callee != owner || price < 0.0) {
            return;
        }

		this.price = price;
	}

    /**
     * Gets the owner of the item
     * 
     * @author Nico Grösche
     * @return Owner of the item.
     */
	public User getUser() {
		return this.owner;
	}

    /**
     * Gets the category of the item
     * 
     * @author Nico Grösche
     * @return Category of the item.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Sets the category of the object if the callee
     * of this function is the same as the owner.
     * 
     * @author Nico Grösche
     * @param category New category of the item
     * @param callee User that is calling the function
     */
    public void setCategory(Category category, User callee) {
        if(callee != owner) {
            return;
        }

		this.category = category;
    }

    /**
     * Gets the description of the item
     * 
     * @author Nico Grösche
     * @return Description of the item.
     */
	public String getDescription() {
		return this.description;
	}

    /**
     * Sets the description of the object if the callee
     * of this function is the same as the owner.
     * 
     * @author Nico Grösche
     * @param description New description of the item
     * @param callee User that is calling the function
     */
	public void setDescription(String description, User callee) {
        if(callee != owner) {
            return;
        }
        
		this.description = description;
	}

}
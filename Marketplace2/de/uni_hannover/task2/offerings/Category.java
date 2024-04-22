package de.uni_hannover.task2.offerings;

/**
 * This class represents the categories 
 * a item can be in the setting of a 
 * digital marketplace. 
 * 
 * Each enum value represents a category
 * with a unique string representation.
 * 
 * @author Nico Grösche
 * @version 03/05/2023
 */
public enum Category {
    FURNITURE("Furniture"), 
    ELECTRONICS("Electronics"), 
    SERVICES("Services"), 
    CLOTHES("Clothes"), 
    ANIMALS("Animals");

    // Stores the string representation.
    private final String repr;

    /**
     * Constructs the object.
     * 
     * @author Nico Grösche
     * @param repr Representation of the object.
     */
    private Category(String repr) {
        this.repr = repr;
    }

    /**
     * Returns the string representation of the object.
     * 
     * @author Nico Grösche
     * @return String representation of the object.
     */
    public String str() {
        return this.repr;
    }

    /**
     * Checks if the given item is within the same
     * category as the enum value.
     * 
     * @author Nico Grösche
     * @param item Item that shall be compared to the enum value
     * @return True if the items is within the same category, otherwise false.
     */
    public boolean isSameCategory(Item item) {
        return item.getCategory() == this;
    }
}

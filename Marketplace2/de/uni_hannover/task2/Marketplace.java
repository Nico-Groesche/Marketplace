package de.uni_hannover.task2;

import de.uni_hannover.task2.auth.*;
import de.uni_hannover.task2.offerings.*;

/**
 * This class represents a digital marketplace.
 * 
 * Items are offered on this marketplace by user,
 * which can be added to this marketplace.
 * 
 * @author Nico Grösche
 * @version 09/05/2023
 */
public class Marketplace {
    // users of this marketplace
    private User[] users;

    /**
     * Constructs a marketplace object with 
     * maximum 10 users.
     * 
     * @author Nico Grösche
     */
    public Marketplace() {
        this.users = new User[10];
    }

    /**
     * Returns a string representation of the marketplace
     * 
     * @author Nico Grösche
     * @return String representation of the item.
     */
    public String str() {
        String res = "";
        
        for(int i = 0; i < this.users.length; i++) {
            if(this.users[i] == null) {
                continue;
            }
            
            res += this.users[i].str();
            res += "\n\n";
        }

        return res;
    }

    /**
     * Removes an user with all offered items from the 
     * marketplace.
     * 
     * @author Nico Grösche
     * @param user User that shall be removed
     * @return False if user could not be found, else true.
     */
    public boolean removeUser(User user) {
        for (int i = 0; i < this.users.length; i++) {
            if(user == this.users[i]) {
                this.users[i] = null;
                return true;
            }
        }

        return false;
    }

    /**
     * Adds an user to the marketplace.
     * 
     * @author Nico Grösche
     * @param user User that shall be added.
     * @return False if user limit has been reached, else true.
     */
    public boolean addUser(User user) {
        for (int i = 0; i < this.users.length; i++) {
            if(this.users[i] == null) {
                this.users[i] = user;
                return true;
            }
            
        }

        return false;
    }

    /**
     * Returns string of all items that belong to as the given category.
     * Iff category is null all items will be printed like
     * in the str() function.
     * 
     * @author Nico Grösche
     * @param category Category that shall be printed. Can be null. If null all items will be printed.
     * @return String of all the items that belong to the given category.
     */
    public String filterMarket(Category category) {
        if(category == null) {
            return this.str();
        }

        String res = "";
        for (int i = 0; i < users.length; i++) {
            if(this.users[i] == null) {
                continue;
            }

            Item[] items = this.users[i].getItems();
            for (int j = 0; j < items.length; j++) {
                if (items[j] == null) {
                    continue;
                }

                if (category.isSameCategory(items[j])) {
                    res += items[j].str();
                }
            }

            res += "\n";
        }

        return res;
    }
	
	/**
	 * This method represents the Login.
	 * 
	 * It decides if u have access to the marketplace as a user or an Admin.
	 * 
	 * @author Nico Grösche
	 * @version 09/05/2023
	 */
	public void login(){
		
		for(int loginTry = 0; loginTry < 3; loginTry++){
			System.out.println("Enter Username:");
			String username = new java.util.Scanner( System.in ).nextLine();
			System.out.println("Enter Password:");
			String password = new java.util.Scanner( System.in ).nextLine();

			for(int i = 0; i < this.users.length; i++){
				if(this.users[i] != null){
					if(username.equals(this.users[i].getUsername())){
						if(password.equals(this.users[i].getPassword())){
							System.out.println("Login Sucessfull");
							boolean testAdmin = this.users[i].getIsAdmin();
							System.out.println(testAdmin);
							if(testAdmin){
								handleAdmin();
							} else {							
								handleUser(this.users[i]);
								System.exit(0);
							}
						}	
					}
				}
			}
		}
		System.out.println("Too many tries!");
		System.exit(0);
	}
	
	/**
	 * This class represents the User access to the Marketplace.
	 * 
	 * Here the User is able to add to or remove an item from the users offered items.
	 * In addition the user can look at the Marketplace and the offerings of other users.
	 * 
	 * @author Nico Grösche
	 * @version 09/05/2023
	 */
	public void handleUser(User user){
		int next = 0;
		String StrUsername = user.getUsername();
		String StrUser;
		
		while(true){                                         
			System.out.println("\033[H\033[2J");			//clear console
			System.out.flush();
			
			System.out.println("Home:");
			
			StrUser = "\n\n" + user.str() + "\n\n";
			StrUser = StrUser.replace(StrUsername, "You");
			StrUser = StrUser.replace("offers", "offer");
			
			System.out.println(StrUser);
			
			System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Item hinzufügen\n2. Item entfernen\n3. Marketplace ansehen\n4. Programm beenden\n\nInput:");
			
			// Input handling for first request
			while(true){
				String input = new java.util.Scanner( System.in ).nextLine();
				if(input.equals("1")){
					next = 1;
					break;
				} else if(input.equals("2")){
					next = 2;
					break;
				} else if(input.equals("3")){
					next = 3;
					break;
				} else if(input.equals("4")){
					next = 4;
					break;
				}
				System.out.println("Wrong input, try again");
			}
			
			// depending on the User Input, executes the different task
			switch(next){
				// Add an Item to the Users Offerings
				case 1:
					Category nwCat = Category.FURNITURE;
					String nwName;
					float nwPrice;
					String nwDescr;
					
					int case1brk = 0;
					while(case1brk == 0){
						System.out.println("\033[H\033[2J");
						System.out.flush();
						System.out.println("In Welcher Kategorie solle das Item angezeigt werden:\n\n1. Möbel\n2. Elektronik\n3. Dienstleistungen\n4. Kleidung\n5. Tiere\n\n6. Aktion abbrechen. Zurueck zum Homescreen\n\nInput:");
						
						int c1brk = 0;
						int stp = 0;
						while(c1brk == 0){
							System.out.println("\033[H\033[2J");
							System.out.flush();
							System.out.println("In Welcher Kategorie solle das Item angezeigt werden:\n\n1. Möbel\n2. Elektronik\n3. Dienstleistungen\n4. Kleidung\n5. Tiere\n\n6. Aktion abbrechen. Zurueck zum Homescreen\n\nInput:");
							String c1input = new java.util.Scanner( System.in ).nextLine();
							
							if(c1input.equals("1")){
								nwCat = Category.FURNITURE;
								c1brk = 1;
							} else if(c1input.equals("2")){
								nwCat = Category.ELECTRONICS;
								c1brk = 1;
							} else if(c1input.equals("3")){
								nwCat = Category.SERVICES;
								c1brk = 1;
							} else if(c1input.equals("4")){
								nwCat = Category.CLOTHES;
								c1brk = 1;
							} else if(c1input.equals("5")){
								nwCat = Category.ANIMALS;
								c1brk = 1;
							} else if(c1input.equals("6")){
								stp = 1;
								c1brk = 1;
							} else {
								System.out.println("Wrong input, try again");
							}
						}
						
						if(stp == 1){break;}
						
						System.out.println("\033[H\033[2J");
						System.out.flush();
						
						System.out.println("Name des Items:\n");
						nwName = new java.util.Scanner( System.in ).nextLine();
						
						System.out.println("\033[H\033[2J");
						System.out.flush();
						
						System.out.println("Preis des Items:\n");
						nwPrice = new java.util.Scanner( System.in ).nextFloat();
						
						System.out.println("\033[H\033[2J");
						System.out.flush();
						
						System.out.println("Beschreibung des Items:\n");
						nwDescr = new java.util.Scanner( System.in ).nextLine();
						
						System.out.println("\033[H\033[2J");
						System.out.flush();   
						
						Item newItem = new Item(nwName, nwPrice, user, nwDescr, nwCat);
						
						System.out.println(newItem.str());
						System.out.println("\nWirklich hinzufügen?\n1. Ja\n2. Nein (Zurueck zum Homescreen)\n\nInput:\n");
						
						int c1brk3 = 0;
						while(c1brk3 == 0){
							String c1input3 = new java.util.Scanner( System.in ).nextLine();
							if(c1input3.equals("1")){
								c1brk3 = 1;
							} else if(c1input3.equals("2")){
								stp = 1;
								c1brk3 = 1;
							} else {
								System.out.println("Wrong input, try again");
							}
						}
						
						if(stp == 1){break;}
						
						System.out.println("\033[H\033[2J");
						System.out.flush();
						
						if(newItem != null && user.addItem(newItem)){
							System.out.println("Item hinzugefügt.\n\nWie wollen Sie fortfahren?\n1. Weiters Item hinzufügen\n2. Homescreen\n\nInput:\n");
							
							int c1brk2 = 0;
							while(c1brk2 == 0){
								String c1input2 = new java.util.Scanner( System.in ).nextLine();
								if(c1input2.equals("1")){
									c1brk2 = 1;
								} else if(c1input2.equals("2")){
									case1brk = 1;
									c1brk2 = 1;
								} else {
									System.out.println("Wrong input, try again");
								}
							}
						} else {
							System.out.println("Ups, etwas scheint schief gelaufen zu sein. Versuch es nochmal.");
						}
					}
					break;
				// Remove an Item from the Users Offerings
				case 2:                       
					int case2brk = 0;
					while(case2brk == 0){
						int c2brk = 0;
						int cnt = 0;
						int itmpos[] = new int[user.getItems().length];
						Item remItem[] = new Item[user.getItems().length];
						
						for(int j = 0; j < itmpos.length; j++){
							itmpos[j] = -1;
							remItem[j] = null;
						}
						while(c2brk == 0){
							System.out.println("\033[H\033[2J");
							System.out.flush();
							cnt = 0;
							StrUser = "\n\n" + user.str() + "\n\n";
							StrUser = StrUser.replace(StrUsername, "You");
							StrUser = StrUser.replace("offers", "offer");
				
							System.out.println(StrUser);
				
							System.out.println("\nWelches Item soll entfernt werden?\n\n0. Homescreen\n\nInput(Name des Items):\n");
							String c2input = new java.util.Scanner( System.in ).nextLine();
							
							if(c2input.equals("0")){
								c2brk = 1;
								case2brk = 1;
							}
							
							for(int i = 0; i < user.getItems().length; i++){
								if(user.getItems()[i] == null){
									continue;
								} else if(user.getItems()[i].getName().equals(c2input)){
									itmpos[i] = i;
									remItem[i] = user.getItems()[i];
									cnt++;
								}
							}
							if(cnt == 0){
								System.out.println("No Item with entered Name found. Try again.");
								c2brk = 1;
								
							} else if (cnt > 1){
								System.out.println("\033[H\033[2J");
								System.out.flush();
							
								System.out.println("Ein paar Items haben den selben Namen. Welches davon meinst du?\n");
								
								int cnt2 = 1;
								for(int x = 0; x < remItem.length; x++){
									if(itmpos[x] != -1){
										System.out.println(cnt2 + ". " + remItem[itmpos[x]].str());
										itmpos[x] = cnt2;
										cnt2++;
									}
								}
								
								System.out.println("Input:\n");
								
								String c2input2 = new java.util.Scanner( System.in ).nextLine();
								
								for(int y = 0; y < remItem.length; y++){
									if(("" + itmpos[y]).equals(c2input2)){
										user.removeItem(remItem[y]);
									}
								}
								
							} else if (cnt == 1){
								int ipos = 0; 
								
								for(int x = 0; x < remItem.length; x++){
									if(itmpos[x] != -1){
										System.out.println(remItem[itmpos[x]].str());
										ipos = x;
									}
								}
								
								System.out.println("Item wirklich entfernen?\n1. Ja\n2. Nein\n\nInput:\n");
								String c2input3 = new java.util.Scanner( System.in ).nextLine();
								
								if(c2input3.equals("1")){
									user.removeItem(remItem[ipos]);
									c2brk = 1;
								} else if (c2input3.equals("2")){
									c2brk = 1;
									continue;
								}
							}
						}
					}
					break;
				// Look at the Items the other Users  are offering
				case 3: 
					int case3brk = 0;
					while(case3brk == 0){
						System.out.println("\033[H\033[2J");
						System.out.flush();
						System.out.println("Welche Kategorie/n sollen angezeigt werden:\n\n1. Alle\n2. Möbel\n3. Elektronik\n4. Dienstleistungen\n5. Kleidung\n6. Tiere\n7. Homescreen\n\nInput:");
						String c3input = new java.util.Scanner( System.in ).nextLine();
						
						int c3brk = 0;                                                  
						while(c3brk == 0){
							if(c3input.equals("1")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.str());
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                         
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("2")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.filterMarket(Category.FURNITURE));
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                          
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("3")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.filterMarket(Category.ELECTRONICS));
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                         
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("4")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.filterMarket(Category.SERVICES));
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                          
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("5")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.filterMarket(Category.CLOTHES));
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                           
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("6")){
								System.out.println("\033[H\033[2J");
								System.out.flush();
								System.out.println(this.filterMarket(Category.ANIMALS));
								
								System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Zurueck\n2. Homescreen\n\nInput:");
								String c3input2 = new java.util.Scanner( System.in ).nextLine();
								
								if(c3input2.equals("1")){
									c3brk = 1;
									case3brk = 0;                           
								} else if (c3input2.equals("2")){
									c3brk = 1;
									case3brk = 1;
								}
								
							} else if(c3input.equals("7")){
								c3brk = 1;
								case3brk = 1;
								
							} else {
								System.out.println("Wrong input, try again");
								c3brk = 1;
							}
						}
					}
					break;
				// Exit the Programm
				case 4:
					System.exit(0);
					break;
			}
		}
	}
	
	/**
	 * This class represents the Admin access to the Marketplace
	 *
	 * The Admin is able to delete an user or an Item of a User
	 * 
	 * @author Nico Grösche
	 */
	public void handleAdmin(){
		
		int next = 0;
		
		while(true){		
			System.out.println("\033[H\033[2J");
			System.out.flush();
			
			System.out.println("Home:");
			
			System.out.println(this.str());
			
			System.out.println("\n\nWählen sie eine der folgenden Otionen:\n1. Nutzer entfernen\n2. Item entfernen\n3. Programm beenden\n\nInput:");
			
			// Input handling for first request
			while(true){
				String input = new java.util.Scanner( System.in ).nextLine();
				if(input.equals("1")){
					next = 1;
					break;
				} else if(input.equals("2")){
					next = 2;
					break;
				} else if(input.equals("3")){
					next = 3;
					break;
				}
				System.out.println("Wrong input, try again");
			}
			
			// depending on the Admin Input, executes the different task
			switch(next){
			// Deleting a User
			case 1:
				int case1brk = 0;
				while(case1brk == 0){
					int c1brk = 0;
					int cnt = 0;
					int usrpos[] = new int[this.users.length];
					User remUser[] = new User[this.users.length];
					
					for(int j = 0; j < usrpos.length; j++){
						usrpos[j] = -1;
						remUser[j] = null;
					}
					
					while(c1brk == 0){
						System.out.println("\033[H\033[2J");
						System.out.flush();
						cnt = 0;
			
						System.out.println(this.str());
			
						System.out.println("\nWelcher User soll entfernt werden?\n\n\"Zurueck zum Homescreen\" -> Homescreen\n\nInput(Name des Users):\n");
						String c1input = new java.util.Scanner( System.in ).nextLine();
						
						if(c1input.equals("Zurueck zum Homescreen")){
							c1brk = 1;
							case1brk = 1;
						}
						
						for(int i = 0; i < this.users.length; i++){
							if(this.users[i] == null){
								continue;
							} else if(this.users[i].getUsername().equals(c1input)){
								usrpos[i] = i;
								remUser[i] = this.users[i];
								cnt++;
							}
						}
						if(cnt == 0){
							System.out.println("No User with entered Name found. Try again.");
							c1brk = 1;
							
						} else if (cnt > 1){
							System.out.println("\033[H\033[2J");
							System.out.flush();
						
							System.out.println("Ein paar User haben den selben Namen. Welchen davon meinst du?\n");
							
							int cnt2 = 1;
							for(int x = 0; x < remUser.length; x++){
								if(usrpos[x] != -1){
									System.out.println(cnt2 + ". " + remUser[usrpos[x]].str());
									usrpos[x] = cnt2;
									cnt2++;
								}
							}
							
							System.out.println("Input:\n");
							
							String c1input2 = new java.util.Scanner( System.in ).nextLine();
							
							for(int y = 0; y < remUser.length; y++){
								if(("" + usrpos[y]).equals(c1input2)){
									this.removeUser(remUser[y]);
								}
							}	
							
						} else if (cnt == 1){
							
							int upos = 0; 
							
							for(int x = 0; x < remUser.length; x++){
								if(usrpos[x] != -1){
									System.out.println(remUser[usrpos[x]].str());
									upos = x;
								}
							}
							
							System.out.println("Item wirklich entfernen?\n1. Ja\n2. Nein\n\nInput:\n");
							String c1input3 = new java.util.Scanner( System.in ).nextLine();
							
							if(c1input3.equals("1")){
								this.removeUser(remUser[upos]);
								c1brk = 1;
							} else if (c1input3.equals("2")){
								c1brk = 1;
								continue;
							}
						}
					}
				}
				break;
			// Deleting an Item
			case 2:
				int case2brk = 0;
				while(case2brk == 0){
					User user = null;
					int c2brk = 0;
					int cnt = 0;
					int usrcnt = 0;
					
					int usrpos[] = new int[this.users.length];
					User chsUser[] = new User[this.users.length];
					for(int o = 0; o < usrpos.length; o++){
						usrpos[o] = -1;
						chsUser[o] = null;
					}
					
					while(c2brk == 0){
						System.out.println("\033[H\033[2J");
						System.out.flush();
						cnt = 0;
						usrcnt = 0;
						System.out.println(this.str());
			
						System.out.println("\nWelchem User soll ein Item entfernt werden?\n\n\"Zurueck zum Homescreen\" -> Homescreen\n\nInput(Name des Users):\n");
						String c2input = new java.util.Scanner( System.in ).nextLine();
						
						if(c2input.equals("Zurueck zum Homescreen")){
							c2brk = 1;
							case1brk = 1;
						}
						
						for(int o = 0; o < this.users.length; o++){
							if(this.users[o] == null){
								continue;
							} else if(this.users[o].getUsername().equals(c2input)){
								chsUser[o] = this.users[o];
								usrpos[o] = o;
								usrcnt++;
							}
						}
						
						if(usrcnt == 0){
							System.out.println("No User with entered Name found. Try again.");
							c2brk = 1;
							
						} else if (usrcnt > 1){
							System.out.println("\033[H\033[2J");
							System.out.flush();
						
							System.out.println("Ein paar User haben den selben Namen. Welchen davon meinst du?\n");
							
							int usrcnt2 = 1;
							for(int x = 0; x < chsUser.length; x++){
								if(usrpos[x] != -1){
									System.out.println(usrcnt2 + ". " + chsUser[usrpos[x]].str());
									usrpos[x] = usrcnt2;
									usrcnt2++;
								}
							}
							
							System.out.println("Input:\n");
							
							String c2input2 = new java.util.Scanner( System.in ).nextLine();
							
							for(int y = 0; y < chsUser.length; y++){
								if(("" + usrpos[y]).equals(c2input2)){
									user = chsUser[y];
								}
							}	
							
						} else if (usrcnt == 1){
							int ipos = 0; 
							
							for(int x = 0; x < chsUser.length; x++){
								if(usrpos[x] != -1){
									System.out.println(chsUser[usrpos[x]].str());
									ipos = x;
								}
							}
							
							System.out.println("Diesen User auswählen?\n1. Ja\n2. Nein\n\nInput:\n");
							String c2input3 = new java.util.Scanner( System.in ).nextLine();
							
							if(c2input3.equals("1")){
								user = chsUser[ipos];
								c2brk = 1;
							} else if (c2input3.equals("2")){
								c2brk = 1;
								continue;
							}
						}
						
						int itmpos[] = new int[user.getItems().length];
						Item remItem[] = new Item[user.getItems().length];
						
						for(int j = 0; j < itmpos.length; j++){
							itmpos[j] = -1;
							remItem[j] = null;
						}
			
						System.out.println(user.str());
		
						System.out.println("\nWelches Item soll entfernt werden?\n\n\"Zurueck zum Homescreen\" -> Homescreen\n\nInput(Name des Items):\n");
						String c2input4 = new java.util.Scanner( System.in ).nextLine();
						
						if(c2input4.equals("Zurueck zum Homescreen")){
							c2brk = 1;
							case2brk = 1;
						}
						
						for(int i = 0; i < user.getItems().length; i++){
							if(user.getItems()[i] == null){
								continue;
							} else if(user.getItems()[i].getName().equals(c2input4)){
								itmpos[i] = i;
								remItem[i] = user.getItems()[i];
								cnt++;
							}
						}
						if(cnt == 0){
							System.out.println("No Item with entered Name found. Try again.");
							c2brk = 1;
							
						} else if (cnt > 1){
							System.out.println("\033[H\033[2J");
							System.out.flush();
						
							System.out.println("Ein paar Items haben den selben Namen. Welches davon meinst du?\n");
							
							int cnt2 = 1;
							for(int x = 0; x < remItem.length; x++){
								if(itmpos[x] != -1){
									System.out.println(cnt2 + ". " + remItem[itmpos[x]].str());
									itmpos[x] = cnt2;
									cnt2++;
								}
							}
							
							System.out.println("Input:\n");
							
							String c2input2 = new java.util.Scanner( System.in ).nextLine();
							
							for(int y = 0; y < remItem.length; y++){
								if(("" + itmpos[y]).equals(c2input2)){
									user.removeItem(remItem[y]);
								}
							}
							
						} else if (cnt == 1){
							
							int ipos = 0; 
							
							for(int x = 0; x < remItem.length; x++){
								if(itmpos[x] != -1){
									System.out.println(remItem[itmpos[x]].str());
									ipos = x;
								}
							}
							
							System.out.println("Item wirklich entfernen?\n1. Ja\n2. Nein\n\nInput:\n");
							String c2input3 = new java.util.Scanner( System.in ).nextLine();
							
							if(c2input3.equals("1")){
								user.removeItem(remItem[ipos]);
								c2brk = 1;
							} else if (c2input3.equals("2")){
								c2brk = 1;
								continue;
							}
						}
					}
				}
				break;
			// Exit Programm
			case 3:
				System.exit(0);
				break;
			}
			
		}
	}
	
    /**
	* Main method initialising the Marketplace and its Users.
	* Then executing the Login method to allow access to the Marketplace for an User or an Admin
	*
	* @author Nico Grösche
	*/
    public static void main(String[] args) {
        Marketplace market = new Marketplace();

        User first = new User("Max", "1234");
        User second = new User("Maxine", "4321");
		Admin admin = new Admin("Horst", "1111");

        Item one = new Item(
            "Sandalen",
            10,
            first,
            "Neue Sandalen schwarz. Festpreis!",
            Category.CLOTHES
        );

        Item two = new Item(
            "Hut",
            100,
            second,
            "Toller Hut. Keine Anfragen, wie 'was letzter Preis'.",
            Category.CLOTHES
        );

        first.addItem(one);
        second.addItem(two);
        
        market.addUser(first);
        market.addUser(second);
		market.addUser(admin);
		
		market.login();
    }
}



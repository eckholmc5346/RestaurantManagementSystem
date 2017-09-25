package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import objects.Ingredient;
import objects.Order;
import objects.Recipe;
import objects.Vendor;

/**
 * @author Ryan Moeller <moellerr2442@my.uwstout.edu>
 * @version 1.0
 * @since 1.0
 */

public class Inventory extends JFrame {

	private boolean manager;
	private static ArrayList<Vendor> vendorList;
	private static ArrayList<Order> orderList;
	private static ArrayList<Order> oldOrderList;
	private static ArrayList<Recipe> recipeList;
	private static ArrayList<Ingredient> ingredientsList;

	/**
	 * Create the application.
	 */
	public Inventory(boolean isManager) {
		manager = isManager;
		importData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Restaurant Management System");
		setBounds(100, 100, 456, 189);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Give the base user "Employee" certain access within the software
		employeeAccess(menuBar);

		// If the user is a manager, give them access to more permissions
		if (manager == true)
			managerAccess(menuBar);
	}

	/**
	 * Gives the "manager" user more permissions in the software
	 * <p>
	 * If the user entered in a correct username/password pair in the login
	 * screen, give them access to everything in the software
	 * 
	 * @param menuBar
	 *            Simply allows the UI elements to be added to the frame
	 */
	private void managerAccess(JMenuBar menuBar) {
		JMenu mnInventory = new JMenu("Inventory");
		menuBar.add(mnInventory);

		JMenu mnIngredients = new JMenu("Ingredients");
		mnInventory.add(mnIngredients);

		JMenuItem mntmNewOrUpdate = new JMenuItem("New/Update");
		// Opens the New Ingredient window
		mntmNewOrUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewIngredient addIngredient = new NewIngredient();
				addIngredient.NewIngredientWindow();
			}
		});
		mnIngredients.add(mntmNewOrUpdate);

		JMenuItem mntmDeleteIngredient = new JMenuItem("Delete Ingredient");
		// Opens the Delete Ingredient window
		mntmDeleteIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveIngredient deleteIngredient = new RemoveIngredient();
				deleteIngredient.RemoveIngredientWindow();
			}
		});
		mnIngredients.add(mntmDeleteIngredient);

		JMenu mnRecipe = new JMenu("Recipe");
		mnInventory.add(mnRecipe);

		JMenuItem mntmAddOrUpdateRecipe = new JMenuItem("Add/Update recipe");
		// Opens the New Recipe window
		mntmAddOrUpdateRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecipe addRecipe = new NewRecipe();
				addRecipe.NewRecipeWindow();
			}
		});
		mnRecipe.add(mntmAddOrUpdateRecipe);

		JMenuItem mntmDeleteRecipe = new JMenuItem("Delete recipe");
		// Opens the Remove Recipe window
		mntmDeleteRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveRecipe deleteRecipe = new RemoveRecipe();
				deleteRecipe.RemoveRecipeWindow();
			}
		});
		mnRecipe.add(mntmDeleteRecipe);

		JMenu mnVendors = new JMenu("Vendors");
		mnInventory.add(mnVendors);

		JMenuItem mntmAddOrUpdateVendor = new JMenuItem("Add/Update vendor");
		// Opens the Add / Update Vendor window
		mntmAddOrUpdateVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewVendor addVendor = new NewVendor();
				addVendor.NewVendorWindow();
			}
		});
		mnVendors.add(mntmAddOrUpdateVendor);

		JMenuItem mntmRemoveVendor = new JMenuItem("Remove vendor");
		// Opens the Remove Vendor window
		mntmRemoveVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveVendor deleteVendor = new RemoveVendor();
				deleteVendor.RemoveVendorWindow();
			}
		});
		mnVendors.add(mntmRemoveVendor);

		JMenu mnRestocking = new JMenu("Restocking");
		mnInventory.add(mnRestocking);

		JMenuItem mntmCheckSupply = new JMenuItem("Check or Update Supply");
		// Opens the Check Supply window
		mntmCheckSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InventorySupplyStatus status = new InventorySupplyStatus();
				InventorySupplyStatus.InventoryStatusWindow();
			}
		});
		mnRestocking.add(mntmCheckSupply);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmCompletedOrders = new JMenuItem("Finished Customer Orders");
		mntmCompletedOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CompletedCustomerOrders completedOrders = new CompletedCustomerOrders();
			}
		});
		mnReports.add(mntmCompletedOrders);

		JMenuItem mntmVendorOrdersMade = new JMenuItem("Vendor Orders Made");
		mnReports.add(mntmVendorOrdersMade);

	}

	/**
	 * Gives the base user, "employee", access to minimal features of the
	 * software
	 *
	 * @param menuBar
	 *            Allows the UI elements to be added to the frame
	 */
	private void employeeAccess(JMenuBar menuBar) {
		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);

		JMenuItem mntmAddOrder = new JMenuItem("Add customer order");
		// Opens the Add Order window
		mntmAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCustomerOrder addOrder = new NewCustomerOrder();
				addOrder.NewCustomerOrderWindow();
			}
		});
		mnCustomers.add(mntmAddOrder);

		JMenuItem mntmUpdateCustomerOrder = new JMenuItem("Update customer order");
		// Opens the Update Order window
		mntmUpdateCustomerOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orderList.size() != 0) {
					UpdateCustomerOrder updateOrder = new UpdateCustomerOrder();
					updateOrder.UpdateCustomerOrderWindow();
				}
			}
		});
		mnCustomers.add(mntmUpdateCustomerOrder);

		JMenuItem mntmCancelCustomerOrder = new JMenuItem("Cancel customer order");
		// Opens the Cancel Order window
		mntmCancelCustomerOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemovePendingOrder deleteOrder = new RemovePendingOrder();
				deleteOrder.RemovePendingOrderWindow();
			}
		});
		mnCustomers.add(mntmCancelCustomerOrder);

		JMenu mnKitchen = new JMenu("Kitchen");
		menuBar.add(mnKitchen);

		JMenuItem mntmProcessPlacedOrders = new JMenuItem("Process placed orders");
		mntmProcessPlacedOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Process Customer Order Here
				ProcessCustomerOrder processOrder = new ProcessCustomerOrder();
				processOrder.ProcessOrderWindow();
			}
		});
		mnKitchen.add(mntmProcessPlacedOrders);

		JMenuItem mntmListOfPending = new JMenuItem("List of pending orders");
		// Opens a window for pending orders
		mntmListOfPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PendingOrdersView viewOrder = new PendingOrdersView();
				viewOrder.PendingOrdersWindow();
			}
		});
		mnKitchen.add(mntmListOfPending);

		JMenuItem mntmShowRecipeInformation = new JMenuItem("Show recipe information");
		// Opens the Recipe window
		mntmShowRecipeInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeInformation viewRecipe = new RecipeInformation();
				viewRecipe.RecipeInformationWindow();
			}
		});
		mnKitchen.add(mntmShowRecipeInformation);
	}

	/**
	 * Reads in all stored data from previous uses of the program
	 * <p>
	 * Reads object data from all of the stored .dat files and reconstructs the
	 * objects to be used again when the user launches the program
	 */
	private void importData() {
		importOldOrders();
		if (oldOrderList == null)
			oldOrderList = new ArrayList<Order>();

		importOrders();
		if (orderList == null)
			orderList = new ArrayList<Order>();
		else
			Order.setStaticID(orderList.get(orderList.size() - 1).getID());

		importVendors();
		if (vendorList == null)
			vendorList = new ArrayList<Vendor>();
		else
			Vendor.setStaticID(vendorList.get(vendorList.size() - 1).getID() + 1);

		importRecipes();
		if (recipeList == null)
			recipeList = new ArrayList<Recipe>();
		else if (recipeList.size() > 1)
			Recipe.setStaticID(recipeList.get(recipeList.size() - 1).getRecipeID() + 1);

		importIngredients();
		if (ingredientsList == null)
			ingredientsList = new ArrayList<Ingredient>();
		else
			Ingredient.setStaticID(ingredientsList.get(ingredientsList.size() - 1).getIngredientID() + 1);

	}

	/**
	 * Reads in the old orders. If the file doesn't exist, it creates a new
	 * file.
	 */
	public static void importOldOrders() {
		try {
			File read = new File("oldOrders.dat");
			// Checks if the file exists. If not, it creates a new one.
			if (read.length() == 0)
				exportOldOrders();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(read));
			oldOrderList = (ArrayList<Order>) ois.readObject();
			ois.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves all old orders to a .dat file.
	 */
	public static void exportOldOrders() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oldOrders.dat", false));
			oos.writeObject(oldOrderList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports all currently existing orders.
	 */
	public static void importOrders() {
		try {
			File read = new File("orders.dat");
			// Checks if the file exists. If not, it creates a new one.
			if (read.length() == 0)
				exportOrders();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(read));
			orderList = (ArrayList<Order>) ois.readObject();
			ois.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves all orders to a .dat file.
	 */
	public static void exportOrders() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orders.dat", false));
			oos.writeObject(orderList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports all existing vendors.
	 */
	public void importVendors() {
		try {
			File read = new File("vendors.dat");
			// Checks if the file exists. If not, it creates a new one.
			if (read.length() == 0)
				exportVendors();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(read));
			vendorList = (ArrayList<Vendor>) ois.readObject();
			ois.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports all vendors to a .dat file.
	 */
	public static void exportVendors() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vendors.dat", false));
			oos.writeObject(vendorList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports all ingredients.
	 */
	public static void importIngredients() {
		try {
			File readFile = new File("ingredients.dat");

			// If the file is empty, create a new one.
			if (readFile.length() == 0)
				exportIngredients();

			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(readFile));
			ingredientsList = (ArrayList<Ingredient>) inStream.readObject();
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports all ingredients to a .dat file.
	 */
	public static void exportIngredients() {
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("ingredients.dat", false));
			outStream.writeObject(ingredientsList);
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports all recipes.
	 */
	public static void importRecipes() {
		try {
			File readFile = new File("recipes.dat");

			// If the file is empty, create a new one.
			if (readFile.length() == 0)
				exportRecipes();

			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(readFile));
			recipeList = (ArrayList<Recipe>) inStream.readObject();
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// writes to the recipes.dat file
	public static void exportRecipes() {
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("recipes.dat", false));
			if (recipeList.size() > 0)
				outStream.writeObject(recipeList);
			else
				recipeList.add(new Recipe());
				outStream.writeObject(recipeList);
			

			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// returns recipe List array
	public static ArrayList<Recipe> getRecipes() {
		return recipeList;
	} // end getIngredient

	// Returns the newest list of vendors
	public static ArrayList<Vendor> getVendors() {
		return vendorList;
	}

	// Returns the newest list of vendors
	public static ArrayList<Order> getOrders() {
		return orderList;
	}

	public static void addOrder(Order order) {
		orderList.add(order);
	}

	public static ArrayList<Ingredient> getIngredient() {
		return ingredientsList;
	}

	public static ArrayList<Order> getOldOrders() {
		return oldOrderList;
	}

}

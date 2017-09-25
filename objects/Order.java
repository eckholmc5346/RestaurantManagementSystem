package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

	private ArrayList<Recipe> itemList;
	private int orderID;
	private static int orderIDCounter = 0;

	public Order() {
		itemList = new ArrayList<Recipe>();
		orderID = -1;
	}

	public Order(ArrayList<Recipe> itemList) {
		this.itemList = itemList;
		orderIDCounter++;
		orderID = orderIDCounter;

	}

	/*
	 * public boolean checkIngredientCount(Recipe food) { Ingredient[] needed =
	 * new Ingredient[food.getIngredientList().size()];
	 * 
	 * int neededCount = 0; boolean makeable = true; for (int count = 0; count <
	 * food.getIngredientList().size(); count++) { if
	 * (food.getIngredientList().get(count).getQuantity() == 0) {
	 * System.out.println("We do not have enough " +
	 * food.getIngredientList().get(count).getName() + " to create " +
	 * food.getName() + "!"); needed[neededCount] =
	 * food.getIngredientList().get(count); neededCount++; makeable = false; } }
	 * 
	 * if (!makeable) { System.out.println("Creating new vendor order...");
	 * VendorOrder order = new VendorOrder(needed); return makeable; }
	 * 
	 * return makeable;
	 * 
	 * }
	 */

	public ArrayList<Recipe> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Recipe> itemList) {
		this.itemList = itemList;
	}

	public String toString() {
		return "Order " + orderID;
	}

	public int getID() {
		return orderID;
	}

	public void setID(int x) {
		orderID = x;
	}

	public static int getStaticID() {
		return orderIDCounter;
	}

	public static void setStaticID(int x) {
		orderIDCounter = x;
	}

	//public boolean isEmpty() {
		//if(orderID == -1)
	//}

}

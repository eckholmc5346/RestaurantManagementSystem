package objects;
import java.io.Serializable;

public class Ingredient implements Serializable, Comparable{

	private static int ingredientIDCounter = 1;
	private int ingredientID;
	private double quantity;
	private String name;
	private String info;
	private String vendor;
	private int vendorID;
	private double totalSupply = 200;


	//blank constructor
	public Ingredient()
	{
		this.name = "";
		this.info = "Enter product info here";
		this.vendor = "Select a vendor";
		this.quantity = 0;

	}
	public Ingredient(String name, String info, String vendor, int vendorID) 
	{
		ingredientID = ingredientIDCounter;
		ingredientIDCounter++;

		this.quantity = 0;
		this.name = name;
		this.info = info;
		this.vendor = vendor;
		this.vendorID = vendorID;

	}

	public int getIngredientID() {
		return ingredientID;
	}

	public void setQuantity(double amount) {
		quantity = amount;
	}

	public void addSupply(double amount){
		totalSupply += amount;
	}	//end set	

	public void subtractSupply(double amount)
	{
		totalSupply -= amount;
	}

	/**
	 * NEW CODE TELL RYAN
	 * @return
	 */
	public double getSupplyLevels()
	{
		return totalSupply;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendorID(int ID)
	{
		this.vendorID = ID;
	}

	public int getVendorID()
	{
		return vendorID;
	}

	public String toString() {
		if (name.equals(""))
			return "";
		return ingredientID + " " + name;
	}

	public static int getStaticID() {
		return ingredientIDCounter;
	}

	public static void setStaticID(int x) {
		ingredientIDCounter = x;
	}

	@Override
	public int compareTo(Object o)
	{
		int compareTest = ((Ingredient) o).getIngredientID();
		return this.ingredientID - compareTest;
	}

}

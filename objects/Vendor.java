package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendor implements Serializable {

	private static int vendorIDCounter;
	private int vendorID;
	private String name, phone, phoneTwo, fax, email, street, city, state, country;
	private ArrayList<Ingredient> ingredientList;

	// Creates a blank Vendor, used for creating a new vendor
	public Vendor() {
		vendorIDCounter++;
		this.name = "";
		this.phone = "";
		this.phoneTwo = "";
		this.fax = "";
		this.email = "";
		this.street = "";
		this.city = "";
		this.state = "";
		this.country = "";
	}

	public Vendor(String name, String phone, String phoneTwo, String fax, String email, String street, String city,
			String state, String country) {
		ingredientList = new ArrayList<Ingredient>();
		vendorID = vendorIDCounter;
		vendorIDCounter++;
		this.name = name;
		this.phone = phone;
		this.phoneTwo = phoneTwo;
		this.fax = fax;
		this.email = email;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public void addIngredient(Ingredient newIngredient) {
		ingredientList.add(newIngredient);
	}

	public void removeIngredient(Ingredient oldIngredient) {
		for (int count = 0; count < ingredientList.size(); count++) {
			if (ingredientList.get(count).getIngredientID() == oldIngredient.getIngredientID()) {
				ingredientList.remove(count);
				return;
			}
		}

		System.out.println("Ingredient not found!");
		return;
	}

	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public int getID() {
		return vendorID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	public String getPhoneTwo() {
		return phoneTwo;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public String toString() {
		if (name.equals(""))
			return "";

		return vendorID + " " + name;
	}

	public static int getStaticID() {
		return vendorIDCounter;
	}

	public static void setStaticID(int x) {
		vendorIDCounter = x;
	}

}

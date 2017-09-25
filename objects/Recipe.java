package objects;
import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

	private static int recipeIDCounter = 1;
	private int recipeID;
	private String name;
	private String info;
	private ArrayList<Ingredient> ingredientList;

	
	public Recipe()
	{
		this.name = "";
		this.info = "";
		ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();	
	}
	
	public Recipe(String name, String info, ArrayList<Ingredient> ingredientListIn) {
		recipeID = recipeIDCounter;
		recipeIDCounter++;
		this.name = name;
		this.info = info;
		this.ingredientList = ingredientListIn;
	}

	public int getRecipeID() {
		return recipeID;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	public String toString() {
		if (name.equals(""))
			return "";
		return recipeID + " " + name;
	}

	public void setIngredientList(ArrayList<Ingredient> list) {
		ingredientList = list;
	}

	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}
	
	
	public static int getStaticID() {
		return recipeIDCounter;
	}

	public static void setStaticID(int x) {
		recipeIDCounter = x;
	}

}

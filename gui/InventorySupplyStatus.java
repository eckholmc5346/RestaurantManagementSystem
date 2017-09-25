package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Ingredient;
import objects.Recipe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventorySupplyStatus extends JFrame
{
	private JPanel contentPane;
	private JTextField textField_IngredientName;
	private JTextField textField_QuantityLevel;
	private JTextField textField_SupplyLevel;

	/**
	 * Launch the application.
	 */
	public static void InventoryStatusWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InventorySupplyStatus frame = new InventorySupplyStatus();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InventorySupplyStatus()
	{
		//hold ingredients low on supply
		
		Vector<Ingredient> below = new Vector<Ingredient>();


		///////////////////////////////////////////////////////////code for testing
		for(int i = 0; i < Inventory.getIngredient().size(); i++)
		{
			i++; //counts up again, so only every other is hit.
			Inventory.getIngredient().get(i).subtractSupply(175);
		}
		///////////////////////////////////////////////////////////SUBTRACTING SUPPLY


		for(int i = 0; i < Inventory.getIngredient().size(); i++)
		{
			if(Inventory.getIngredient().get(i).getSupplyLevels() < 50)	//determines if this ingredient will show up on the "Low" list
			{
				below.add(Inventory.getIngredient().get(i));
			}	//end if
		}	//end for

		setTitle("Supply Levels");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_IngredientName = new JTextField();
		textField_IngredientName.setEditable(false);
		textField_IngredientName.setBounds(71, 76, 119, 20);
		contentPane.add(textField_IngredientName);
		textField_IngredientName.setColumns(10);

		textField_QuantityLevel = new JTextField();
		textField_QuantityLevel.setEditable(false);
		textField_QuantityLevel.setBounds(71, 122, 119, 20);
		contentPane.add(textField_QuantityLevel);
		textField_QuantityLevel.setColumns(10);

		textField_SupplyLevel = new JTextField();
		textField_SupplyLevel.setBounds(71, 168, 119, 20);
		contentPane.add(textField_SupplyLevel);
		textField_SupplyLevel.setColumns(10);



		JComboBox comboBox_IngredientsDropDown = new JComboBox(Inventory.getIngredient().toArray());
		comboBox_IngredientsDropDown.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//temp object to easily call needed data
				Ingredient tempObject = (Ingredient) comboBox_IngredientsDropDown.getSelectedItem();
				textField_IngredientName.setText(tempObject.getName());	//gets ingredient name
				textField_QuantityLevel.setText("" + tempObject.getSupplyLevels());	//gets ingredient supply level
			}
		});
		comboBox_IngredientsDropDown.setBounds(71, 28, 119, 20);
		contentPane.add(comboBox_IngredientsDropDown);

		JLabel lblIngredients = new JLabel("Ingredients List");
		lblIngredients.setBounds(71, 11, 119, 14);
		contentPane.add(lblIngredients);

		JLabel lblIngredientName = new JLabel("Ingredient Name");
		lblIngredientName.setBounds(71, 59, 119, 14);
		contentPane.add(lblIngredientName);

		JLabel lblCurrentSupply = new JLabel("Current Supply In Pounds");
		lblCurrentSupply.setBounds(71, 107, 174, 14);
		contentPane.add(lblCurrentSupply);

		JLabel lblOrderSupply = new JLabel("Add Supply: In Pounds");
		lblOrderSupply.setBounds(71, 153, 144, 14);
		contentPane.add(lblOrderSupply);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 28, 172, 266);
		contentPane.add(scrollPane);

		JList list_Below = new JList(below);
		list_Below.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				Ingredient tempObject = (Ingredient) list_Below.getSelectedValue();
				comboBox_IngredientsDropDown.setSelectedItem(tempObject);
				textField_IngredientName.setText(tempObject.getName());	//gets ingredient name
				textField_QuantityLevel.setText("" + tempObject.getSupplyLevels());	//gets ingredient supply level
			}
		});
		scrollPane.setViewportView(list_Below);

		JButton btnOrderSupply = new JButton("Order Supply");
		btnOrderSupply.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				double addSupply = 0;
				String supplyAmount = "";
				try 
				{
					supplyAmount = textField_SupplyLevel.getText();
					addSupply = Double.parseDouble(supplyAmount);

				} catch (Exception error) {
					textField_SupplyLevel.setText("");
					supplyAmount = textField_SupplyLevel.getText();
				} // end try/catch
				Inventory.getIngredient().get(comboBox_IngredientsDropDown.getSelectedIndex()).addSupply(addSupply);

				Ingredient tempObject = Inventory.getIngredient().get(comboBox_IngredientsDropDown.getSelectedIndex());
				//add supply, grab id from that object, search for that object (via ID) in "below", check if its above 50.

				//////////////////////////////////
				int temp = tempObject.getIngredientID();	//this stores the ID of the item that supply was ordered for
				//use this to search the list for the item with the matching id, then check if that single item
				//has a supply level over 50.
				
				//Collections.sort(below);	//had to edit Ingredient to get this to work
				
				
				//this should not be a for loop... this should be a sort method...
				
				for(int i = 0; i < below.size(); i ++)
				{
					if(below.elementAt(i).getSupplyLevels() > 50)
					{
						below.remove(i);
					}	//end if	
				}	//end for
				
				////////////////////////////////////////////
				
				
				textField_QuantityLevel.setText("" + Inventory.getIngredient().get(comboBox_IngredientsDropDown.getSelectedIndex()).getSupplyLevels());
				System.out.println(Inventory.getIngredient().get(comboBox_IngredientsDropDown.getSelectedIndex()).getSupplyLevels());


				list_Below.setListData(below); //this statement refreshes the listbox with ingredients below desired threshold.

				Inventory.exportIngredients();
			}
		});
		btnOrderSupply.setBounds(71, 199, 119, 39);
		contentPane.add(btnOrderSupply);

		JLabel lblIngredientsLowOn = new JLabel("Ingredients Low On Supply");
		lblIngredientsLowOn.setBounds(241, 11, 174, 14);
		contentPane.add(lblIngredientsLowOn);
	}
	
	
}

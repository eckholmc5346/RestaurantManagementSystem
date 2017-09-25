package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.Ingredient;
import objects.Recipe;

public class NewRecipe extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_AboutText;

	/**
	 * Launch the application.
	 */
	public void NewRecipeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewRecipe() {

		ArrayList<Ingredient> temp = new ArrayList<Ingredient>();

		Vector<Double> quantity = new Vector<Double>();

		if (Inventory.getRecipes().size() == 0) {
			Inventory.getRecipes().add(new Recipe());
		}

		setTitle("New/Update Recipe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID#");
		lblId.setBounds(58, 60, 46, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(58, 85, 46, 14);
		contentPane.add(lblName);

		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setBounds(90, 60, 334, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(90, 85, 334, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);

		JLabel lblAvailableIngredients = new JLabel("Available Ingredients");
		lblAvailableIngredients.setBounds(10, 125, 175, 14);
		contentPane.add(lblAvailableIngredients);

		JLabel lblRecipeIngredients = new JLabel("Recipe Ingredients");
		lblRecipeIngredients.setBounds(304, 125, 192, 14);
		contentPane.add(lblRecipeIngredients);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 142, 175, 154);
		contentPane.add(scrollPane_1);

		JList list_Available = new JList(Inventory.getIngredient().toArray());
		scrollPane_1.setViewportView(list_Available);

		// holds items for displaying in jlist boxes
		DefaultListModel<Ingredient> orders = new DefaultListModel<Ingredient>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 142, 175, 154);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(490, 142, 74, 154);
		contentPane.add(scrollPane_2);

		JList list_Quantity = new JList(quantity);
		scrollPane_2.setViewportView(list_Quantity);

		JList list_RecipeIngredients = new JList(orders);
		scrollPane.setViewportView(list_RecipeIngredients);

		JButton btn_moveLeft = new JButton("<");
		btn_moveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_RecipeIngredients.getSelectedIndex() > -1) {
					// need to store index here so I can call it below after its been deleted
					int tempIndex = list_RecipeIngredients.getSelectedIndex();
					// Deleted the ingredient at that index
					orders.removeElementAt(list_RecipeIngredients.getSelectedIndex());
					// Removes the quantity from the gui window
					quantity.removeElementAt(tempIndex);
					list_Quantity.setListData(quantity);
				}
			}
		});
		btn_moveLeft.setBounds(219, 177, 56, 23);
		contentPane.add(btn_moveLeft);

		JComboBox comboBox_RecipeSelection = new JComboBox(Inventory.getRecipes().toArray());
		comboBox_RecipeSelection.setBounds(90, 29, 334, 20);
		contentPane.add(comboBox_RecipeSelection);

		JButton btn_moveRight = new JButton(">");
		btn_moveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_Available.getSelectedIndex() > -1) {
					temp.add(Inventory.getIngredient().get(list_Available.getSelectedIndex()));
					orders.addElement(Inventory.getIngredient().get(list_Available.getSelectedIndex()));

					double ingredientAmount = 0;
					String ingredientQuantity = JOptionPane.showInputDialog("Please Enter Ingredient Quantity");
					boolean numberError = false;

					do {
						try {
							ingredientAmount = Double.parseDouble(ingredientQuantity);
							numberError = false;
							orders.getElementAt(comboBox_RecipeSelection.getSelectedIndex())
									.setQuantity(ingredientAmount);

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Please enter a number in the format of: 1.3 or 1");
							numberError = true;
							ingredientQuantity = JOptionPane.showInputDialog("Please Enter Ingredient Quantity");
						} // end try/catch
					} while (numberError == true);

					System.out.println("The entered number: " + ingredientAmount);

					quantity.add(ingredientAmount);
					list_Quantity.setListData(quantity);

					System.out.println(orders.elementAt(comboBox_RecipeSelection.getSelectedIndex()).getQuantity()
							+ " This is a call to the object itself, with the get quantity method");
					// when do we want to subtract quantity amount from the
					// ingredient supply?

				} // end if
			}
		});
		btn_moveRight.setBounds(219, 211, 56, 23);
		contentPane.add(btn_moveRight);

		JLabel lblAbout = new JLabel("About");
		lblAbout.setBounds(222, 319, 38, 14);
		contentPane.add(lblAbout);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(58, 344, 366, 100);
		contentPane.add(scrollPane_3);

		textField_AboutText = new JTextField();
		scrollPane_3.setViewportView(textField_AboutText);
		textField_AboutText.setColumns(10);

		comboBox_RecipeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_RecipeSelection.getSelectedIndex() != 0) {
					orders.clear();
					quantity.clear();

					Recipe tempObject = (Recipe) comboBox_RecipeSelection.getSelectedItem();
					textField_ID.setText("" + tempObject.getRecipeID());
					textField_Name.setText(tempObject.getName());
					textField_AboutText.setText(tempObject.getInfo());

					for (int i = 0; i < tempObject.getIngredientList().size(); i++) {
						orders.addElement(tempObject.getIngredientList().get(i));
						quantity.addElement(tempObject.getIngredientList().get(i).getQuantity());
					}
					list_Quantity.setListData(quantity);

				} // end if
				else {
					// If new is selected then the boxes are cleared.
					textField_ID.setText("");
					textField_Name.setText("");
					textField_AboutText.setText("");
				} // end else
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// clears array to display quantities
				quantity.clear();

				ArrayList<Ingredient> tempIngredients = new ArrayList<Ingredient>();
				for (int i = 0; i < orders.size(); i++) {
					tempIngredients.add(orders.getElementAt(i));
				}
				
				Inventory.getRecipes().set(comboBox_RecipeSelection.getSelectedIndex(),
						new Recipe(textField_Name.getText(), textField_AboutText.getText(), tempIngredients));

				if ((comboBox_RecipeSelection.getSelectedIndex() == 0))
					Inventory.getRecipes().add(0, new Recipe());

				// Updates file with new / edited ingredient
				Inventory.exportRecipes();

				// Closes window
				dispose();
			} 
		});

		btnSave.setBounds(10, 482, 94, 38);
		contentPane.add(btnSave);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Name.setText("");
				textField_AboutText.setText("");
			}
		});
		btnClear.setBounds(201, 482, 94, 38);
		contentPane.add(btnClear);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(385, 482, 94, 38);
		contentPane.add(btnCancel);

		JLabel lblSelectAnOption = new JLabel("Select an option below");
		lblSelectAnOption.setBounds(195, 11, 148, 14);
		contentPane.add(lblSelectAnOption);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(490, 125, 74, 14);
		contentPane.add(lblQuantity);
	}
}

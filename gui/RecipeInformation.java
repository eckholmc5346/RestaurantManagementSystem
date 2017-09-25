package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.Recipe;

public class RecipeInformation extends JFrame {

	private JPanel contentPane;
	private JTextField textField_RecipeInfo;

	/**
	 * Launch the application.
	 */
	public static void RecipeInformationWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeInformation frame = new RecipeInformation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecipeInformation() {
		setTitle("Recipe Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRecipeList = new JLabel("Recipe List");
		lblRecipeList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRecipeList.setBounds(25, 22, 79, 26);
		contentPane.add(lblRecipeList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 111, 201);
		contentPane.add(scrollPane);

		JList<Recipe> list_Recipes = new JList(Inventory.getRecipes().toArray());
		scrollPane.setViewportView(list_Recipes);

		JButton btnView = new JButton("View");
		btnView.setBounds(10, 259, 111, 26);
		contentPane.add(btnView);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(131, 47, 111, 201);
		contentPane.add(scrollPane_1);

		JList list_Contents = new JList();
		scrollPane_1.setViewportView(list_Contents);

		JLabel lblContents = new JLabel("Contents");
		lblContents.setBounds(165, 30, 51, 14);
		contentPane.add(lblContents);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(252, 47, 287, 201);
		contentPane.add(scrollPane_2);

		textField_RecipeInfo = new JTextField();
		scrollPane_2.setViewportView(textField_RecipeInfo);
		textField_RecipeInfo.setEditable(false);
		textField_RecipeInfo.setColumns(10);

		JLabel lblInformationAndDirections = new JLabel("Information and Directions");
		lblInformationAndDirections.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformationAndDirections.setBounds(308, 22, 176, 19);
		contentPane.add(lblInformationAndDirections);

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sets the display to show all ingredients from the currently selected recipe
				list_Contents.setListData(Inventory.getRecipes().get(list_Recipes.getSelectedIndex()).getIngredientList().toArray());
				textField_RecipeInfo.setText(Inventory.getRecipes().get(list_Recipes.getSelectedIndex()).getInfo());
			}
		});
	}

}

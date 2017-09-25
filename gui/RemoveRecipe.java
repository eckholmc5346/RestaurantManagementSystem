package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;

public class RemoveRecipe extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void RemoveRecipeWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RemoveRecipe frame = new RemoveRecipe();
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
	public RemoveRecipe()
	{
		setTitle("Delete Recipe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecipes = new JLabel("Recipes");
		lblRecipes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRecipes.setBounds(183, 20, 92, 23);
		contentPane.add(lblRecipes);
		
		JList list_Recipes = new JList();
		list_Recipes.setBounds(55, 54, 327, 152);
		contentPane.add(list_Recipes);
		
		JButton btnDeleteRecipe = new JButton("Delete Selected Recipe");
		btnDeleteRecipe.setBounds(55, 217, 327, 33);
		contentPane.add(btnDeleteRecipe);
	}
}

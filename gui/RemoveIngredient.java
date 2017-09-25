package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class RemoveIngredient extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void RemoveIngredientWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RemoveIngredient frame = new RemoveIngredient();
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
	public RemoveIngredient()
	{
		setTitle("Delete Ingredient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 333, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 297, 173);
		contentPane.add(scrollPane);
		
		JList list_Ingredients = new JList(Inventory.getIngredient().toArray());
		scrollPane.setViewportView(list_Ingredients);
		
		JLabel lblIngredientList = new JLabel("Ingredient List");
		lblIngredientList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngredientList.setBounds(105, 11, 136, 24);
		contentPane.add(lblIngredientList);
		
		JButton btnDelete = new JButton("Delete Selected Ingredient");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int[] check = list_Ingredients.getSelectedIndices();
				for (int count = 0; count < check.length; count++) {
					if (check[count] < 1)
						dispose();
				}

				// Get list of ingredients that were selected
				List temp = list_Ingredients.getSelectedValuesList();

				// Remove selected ingredients. Checks if they deleted the 0th
				// value, which is a placeholder.
				for (int count = 0; count < temp.size(); count++)
					if (!(list_Ingredients.getSelectedIndex() == 0))
						Inventory.getIngredient().remove(temp.get(count));

				// Save the new list of ingredients
				Inventory.exportIngredients();

				dispose();
			}
		});
		btnDelete.setBounds(10, 230, 297, 23);
		contentPane.add(btnDelete);
	}
}

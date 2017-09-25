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

public class Restock extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void RestockWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Restock frame = new Restock();
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
	public Restock()
	{
		setTitle("Restock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list_BelowThreshold = new JList();
		list_BelowThreshold.setBounds(10, 41, 145, 209);
		contentPane.add(list_BelowThreshold);
		
		JLabel lblIngredientsBelowThreshold = new JLabel("Ingredients Below Threshold");
		lblIngredientsBelowThreshold.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIngredientsBelowThreshold.setBounds(10, 22, 145, 14);
		contentPane.add(lblIngredientsBelowThreshold);
		
		JLabel lblIngredientOrderRequests = new JLabel("Ingredient Order Requests");
		lblIngredientOrderRequests.setBounds(448, 22, 160, 14);
		contentPane.add(lblIngredientOrderRequests);
		
		JList list_RequestedOrders = new JList();
		list_RequestedOrders.setBounds(448, 41, 145, 209);
		contentPane.add(list_RequestedOrders);
		
		JList list_Ordering = new JList();
		list_Ordering.setBounds(229, 41, 145, 175);
		contentPane.add(list_Ordering);
		
		JLabel lblItemsBeingOrdered = new JLabel("Items Being Ordered");
		lblItemsBeingOrdered.setBounds(229, 22, 148, 14);
		contentPane.add(lblItemsBeingOrdered);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setBounds(229, 227, 145, 23);
		contentPane.add(btnSubmitOrder);
		
		JButton btnAddFromBelowList = new JButton(">");
		btnAddFromBelowList.setBounds(165, 115, 54, 37);
		contentPane.add(btnAddFromBelowList);
		
		JButton btnAddFromOrderedRequests = new JButton("<");
		btnAddFromOrderedRequests.setBounds(384, 115, 54, 37);
		contentPane.add(btnAddFromOrderedRequests);
	}
}

package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.Order;
import objects.Recipe;

public class NewCustomerOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField_OrderNumber;
	private ArrayList<Recipe> tempRecipeList;

	/**
	 * Launch the application.
	 */
	public static void NewCustomerOrderWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewCustomerOrder() {
		tempRecipeList = new ArrayList<Recipe>();
		this.setVisible(true);

		setTitle("New Customer Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 116, 176);
		contentPane.add(scrollPane);

		JList<Order> list_Recipes = new JList(Inventory.getRecipes().toArray());
		scrollPane.setViewportView(list_Recipes);

		JLabel lblMenuItems = new JLabel("Menu Items");
		lblMenuItems.setBounds(10, 53, 78, 14);
		contentPane.add(lblMenuItems);

		JButton btnAddToOrder = new JButton("Add To Order");
		btnAddToOrder.setBounds(136, 74, 116, 36);
		contentPane.add(btnAddToOrder);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(263, 74, 116, 176);
		contentPane.add(scrollPane_1);
		DefaultListModel<Recipe> orderedItems = new DefaultListModel<Recipe>();
		JList<Recipe> displayList = new JList<Recipe>(orderedItems);
		scrollPane_1.setViewportView(displayList);

		JButton btnRemoveFromOrder = new JButton("Remove");
		btnRemoveFromOrder.setBounds(136, 121, 116, 36);
		contentPane.add(btnRemoveFromOrder);

		JLabel lblBeingOrdered = new JLabel("Items Being Ordered");
		lblBeingOrdered.setBounds(262, 53, 117, 14);
		contentPane.add(lblBeingOrdered);

		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tempRecipeList.size() > 0) {
					Order order = new Order(tempRecipeList);
					tempRecipeList = null;
					Inventory.addOrder(order);
				}
				Inventory.exportOrders();
				dispose();
			}
		});
		btnSubmitOrder.setBounds(136, 168, 116, 36);
		contentPane.add(btnSubmitOrder);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(136, 215, 116, 36);
		contentPane.add(btnCancel);

		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Makes it so that people need to be selecting
				if (list_Recipes.getSelectedIndex() > -1) {
					tempRecipeList.add(Inventory.getRecipes().get(list_Recipes.getSelectedIndex()));
					orderedItems.addElement(Inventory.getRecipes().get(list_Recipes.getSelectedIndex()));
				}
			}
		});

		btnRemoveFromOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (displayList.getSelectedIndex() != -1)
					orderedItems.removeElementAt(displayList.getSelectedIndex());
			}
		});

		// CAUTION! When displaying the order number to this textbox
		// do NOT add to the counter variable for order numbers
		// to display a "Potential" order number. Take the counter
		// variable and display "Counter + 1"
		// We do not want to actually increase the counter until
		// the order is PLACED
		textField_OrderNumber = new JTextField();

		textField_OrderNumber.setEditable(false);
		textField_OrderNumber.setBounds(136, 30, 116, 20);
		contentPane.add(textField_OrderNumber);
		textField_OrderNumber.setColumns(10);

		JLabel lblCustomerOrderNumber = new JLabel("Order Number");
		lblCustomerOrderNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomerOrderNumber.setBounds(152, 11, 89, 14);
		contentPane.add(lblCustomerOrderNumber);
	}
}

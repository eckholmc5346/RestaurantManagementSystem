package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import objects.Order;
import objects.Recipe;

public class UpdateCustomerOrder extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void UpdateCustomerOrderWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomerOrder frame = new UpdateCustomerOrder();
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
	public UpdateCustomerOrder() {
		setTitle("Update Customer Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 333, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPendingOrders = new JLabel("Select a Pending Order");
		lblPendingOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendingOrders.setBounds(10, 11, 294, 14);
		contentPane.add(lblPendingOrders);

		JComboBox<Recipe> comboBox_PendingOrders = new JComboBox(Inventory.getOrders().toArray());
		comboBox_PendingOrders.setBounds(100, 29, 116, 20);
		contentPane.add(comboBox_PendingOrders);

		ArrayList<Recipe> tempItemList = ((Order) comboBox_PendingOrders.getSelectedItem()).getItemList();
		Order temp = new Order();

		for (int count = 0; count < tempItemList.size(); count++) {
			temp.getItemList().add(tempItemList.get(count));
		}

		DefaultListModel<Recipe> orderedItems = new DefaultListModel<Recipe>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 80, 177);
		contentPane.add(scrollPane);
		JList<Recipe> displayList = new JList<Recipe>(orderedItems);
		scrollPane.setViewportView(displayList);

		JLabel lblOrderedItems = new JLabel("Ordered Items");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(5, 58, 91, 14);
		contentPane.add(lblOrderedItems);

		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (displayList.getSelectedIndex() > -1
						&& displayList.getSelectedIndex() < displayList.getSelectedValue().getIngredientList().size()) {
					temp.getItemList().remove(displayList.getSelectedIndex());
					orderedItems.remove(displayList.getSelectedIndex());
				}
			}
		});
		btnRemoveItem.setBounds(100, 97, 116, 36);
		contentPane.add(btnRemoveItem);

		JButton btnAddNewItem = new JButton("Add Item");
		btnAddNewItem.setBounds(100, 144, 116, 36);
		contentPane.add(btnAddNewItem);

		JButton btnResubmitOrder = new JButton("Edit Order");
		btnResubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				Inventory.getOrders().get(comboBox_PendingOrders.getSelectedIndex()).setItemList(temp.getItemList());
				Inventory.exportOrders();
				dispose();
			}
		});
		btnResubmitOrder.setBounds(100, 191, 116, 36);
		contentPane.add(btnResubmitOrder);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(224, 73, 80, 177);
		contentPane.add(scrollPane_1);

		JList list_MenuItems = new JList(Inventory.getRecipes().toArray());
		scrollPane_1.setViewportView(list_MenuItems);

		JLabel lblMenuItems = new JLabel("Menu Items");
		lblMenuItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuItems.setBounds(224, 58, 80, 14);
		contentPane.add(lblMenuItems);

		comboBox_PendingOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clears the list so that a newly selected order is the only
				// order in the list
				orderedItems.clear();
				// Gets the order from the order list
				ArrayList<Recipe> tempItemList = ((Order) comboBox_PendingOrders.getSelectedItem()).getItemList();
				for (int count = 0; count < tempItemList.size(); count++)
					orderedItems.addElement(tempItemList.get(count));
			}
		});

		btnAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.getItemList().add((Recipe) list_MenuItems.getSelectedValue());
				orderedItems.addElement((Recipe) list_MenuItems.getSelectedValue());
			}
		});
	}
}

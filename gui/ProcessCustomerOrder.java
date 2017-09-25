package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import objects.Order;
import objects.Recipe;

public class ProcessCustomerOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField_OrderNumber;

	/**
	 * Launch the application.
	 */
	public static void ProcessOrderWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProcessCustomerOrder() {
		this.setVisible(true);

		DefaultListModel<Recipe> currentOrder = new DefaultListModel<Recipe>();
		Queue<Order> orderQueue = new LinkedList<Order>();

		for (int count = 0; count < Inventory.getOrders().size(); count++) {
			orderQueue.add(Inventory.getOrders().get(count));
		}

		setTitle("Process Customer Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOrdersList = new JLabel("View Order List");
		lblOrdersList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdersList.setBounds(0, 10, 108, 23);
		contentPane.add(lblOrdersList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 87, 241);
		contentPane.add(scrollPane);

		JList orderList = new JList(orderQueue.toArray());
		scrollPane.setViewportView(orderList);

		// Allows the user to only delete one order at a time
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		textField_OrderNumber = new JTextField();
		textField_OrderNumber.setEditable(false);
		textField_OrderNumber.setBounds(232, 28, 132, 20);
		contentPane.add(textField_OrderNumber);
		textField_OrderNumber.setColumns(10);

		JLabel lblOrderedItems = new JLabel("Ordered Items & Details");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(107, 62, 257, 23);
		contentPane.add(lblOrderedItems);

		JList list_OrderContents = new JList(currentOrder);
		list_OrderContents.setBounds(107, 90, 257, 147);
		contentPane.add(list_OrderContents);

		JButton btnReady = new JButton("Finish Most Recent Order");
		btnReady.setBounds(107, 248, 257, 23);
		contentPane.add(btnReady);

		JLabel lblOrderBeingProcessed = new JLabel("Order Being Processed");
		lblOrderBeingProcessed.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderBeingProcessed.setBounds(220, 14, 156, 14);
		contentPane.add(lblOrderBeingProcessed);

		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentOrder.clear();
				if (orderQueue.peek() != null && Inventory.getOrders().size() != 0) {
					for (int count = 0; count < orderQueue.peek().getItemList().size(); count++) {
						currentOrder.addElement(orderQueue.peek().getItemList().get(count));
					}
					orderQueue.remove();
					Inventory.getOldOrders().add(Inventory.getOrders().remove(0));
					orderList.setListData(orderQueue.toArray());

					if (orderQueue.peek() != null)
						textField_OrderNumber.setText("" + orderQueue.peek().getID());
					else
						textField_OrderNumber.setText("");

					if (orderQueue.size() == 0)
						currentOrder.clear();
					
					Inventory.exportOldOrders();
					Inventory.exportOrders();
				}

			}
		});

		orderList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (orderList.getSelectedIndex() > -1) {
					// Clears out the current items in the Order box so that the
					// next items
					// Can be inserted
					currentOrder.clear();
					ArrayList<Recipe> temp = ((Order) orderList.getSelectedValue()).getItemList();
					// Fills the box with the currently selected Order's items
					for (int count = 0; count < temp.size(); count++)
						currentOrder.addElement(temp.get(count));
					textField_OrderNumber.setText("" + ((Order) orderList.getSelectedValue()).getID());
				}
			}
		});
	}
}

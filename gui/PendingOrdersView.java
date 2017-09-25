package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Order;

public class PendingOrdersView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void PendingOrdersWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PendingOrdersView() {

		setVisible(true);
		setTitle("Pending Orders");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Vector<Order> orders = new Vector<Order>();

		for (int count = 0; count < Inventory.getOrders().size(); count++)
			orders.add(Inventory.getOrders().get(count));

		JList list_PendingOrders = new JList(orders);
		list_PendingOrders.setBounds(10, 39, 117, 167);
		contentPane.add(list_PendingOrders);

		JLabel lblPendingOrders = new JLabel("Pending Orders");
		lblPendingOrders.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPendingOrders.setBounds(10, 11, 117, 27);
		contentPane.add(lblPendingOrders);

		JButton btnViewOrder = new JButton("View Order");
		btnViewOrder.setBounds(10, 217, 117, 24);
		contentPane.add(btnViewOrder);

		JList list_OrderContents = new JList();
		list_OrderContents.setBounds(153, 39, 159, 202);
		contentPane.add(list_OrderContents);

		JLabel lblOrderContents = new JLabel("Order Contents");
		lblOrderContents.setBounds(190, 19, 104, 14);
		contentPane.add(lblOrderContents);

		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list_OrderContents.setListData(orders.get(list_PendingOrders.getSelectedIndex()).getItemList().toArray());
			}
		});
	}
}

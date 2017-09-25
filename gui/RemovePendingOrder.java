package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Order;
import objects.Recipe;

public class RemovePendingOrder extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void RemovePendingOrderWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemovePendingOrder frame = new RemovePendingOrder();
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
	public RemovePendingOrder() {
		setTitle("Delete Pending Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPendingOrders = new JLabel("Pending Orders");
		lblPendingOrders.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPendingOrders.setBounds(94, 11, 119, 24);
		contentPane.add(lblPendingOrders);

		JList list_PendingOrders = new JList(Inventory.getOrders().toArray());
		list_PendingOrders.setBounds(29, 38, 247, 176);
		contentPane.add(list_PendingOrders);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory.getOrders().remove(list_PendingOrders.getSelectedIndex());
				if (Inventory.getOrders().size() == 0)
					Inventory.getOrders().add(new Order(new ArrayList<Recipe>()));
				Inventory.exportOrders();
				dispose();
			}
		});
		btnDelete.setBounds(29, 225, 247, 25);
		contentPane.add(btnDelete);
	}

}

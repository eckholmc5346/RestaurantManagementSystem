package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CompletedCustomerOrders extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CompletedCustomerOrders() {
		setVisible(true);
		setTitle("Completed Customer Orders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 170, 175);
		contentPane.add(scrollPane);

		JList orderList = new JList(Inventory.getOldOrders().toArray());
		scrollPane.setViewportView(orderList);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(254, 25, 170, 175);
		contentPane.add(scrollPane_1);

		JList itemList = new JList(Inventory.getOldOrders().toArray());
		scrollPane_1.setViewportView(itemList);

		JButton btnNewButton = new JButton("Show Items");
		btnNewButton.setBounds(53, 211, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Completed Orders");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 8, 170, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Order Items");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(254, 8, 170, 14);
		contentPane.add(lblNewLabel_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	}
}

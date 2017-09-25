package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class RemoveVendor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void RemoveVendorWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveVendor frame = new RemoveVendor();
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
	public RemoveVendor() {
		setTitle("Delete Vendor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 296, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVendors = new JLabel("Vendors");
		lblVendors.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendors.setBounds(103, 9, 77, 27);
		contentPane.add(lblVendors);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 47, 214, 137);
		contentPane.add(scrollPane);

		JList list_Vendors = new JList(Inventory.getVendors().toArray());
		scrollPane.setViewportView(list_Vendors);

		JButton btnDeleteSelectedVendor = new JButton("Delete Selected Vendor");
		btnDeleteSelectedVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] check = list_Vendors.getSelectedIndices();
				for (int count = 0; count < check.length; count++) {
					if (check[count] < 1)
						dispose();
				}

				// Get list of vendors that were selected
				List temp = list_Vendors.getSelectedValuesList();

				// Remove selected vendors. Checks if they deleted the 0th
				// value, which is a placeholder.
				for (int count = 0; count < temp.size(); count++)
					if (!(list_Vendors.getSelectedIndex() == 0))
						Inventory.getVendors().remove(temp.get(count));

				// Save the new list of vendors
				Inventory.exportVendors();

				dispose();

			}
		});
		btnDeleteSelectedVendor.setBounds(31, 195, 214, 35);
		contentPane.add(btnDeleteSelectedVendor);
	}

}

package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import objects.Vendor;

public class NewVendor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Phone;
	private JTextField textField_PhoneII;
	private JTextField textField_Fax;
	private JTextField textField_Email;
	private JTextField textField_Street;
	private JTextField textField_City;
	private JTextField textField_State;
	private JTextField textField_Country;

	/**
	 * Launch the application.
	 */
	public void NewVendorWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewVendor() {
		if (Inventory.getVendors().size() == 0)
			Inventory.getVendors().add(new Vendor());

		setTitle("New/Update Vendor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID#");
		lblId.setBounds(10, 65, 46, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 90, 46, 14);
		contentPane.add(lblName);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 116, 46, 14);
		contentPane.add(lblPhone);

		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(10, 142, 46, 14);
		contentPane.add(lblFax);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 168, 46, 14);
		contentPane.add(lblEmail);

		JLabel lblPhoneIi = new JLabel("Phone II");
		lblPhoneIi.setBounds(219, 116, 46, 14);
		contentPane.add(lblPhoneIi);

		JLabel lblAddressInformation = new JLabel("Address Information");
		lblAddressInformation.setBounds(10, 208, 152, 14);
		contentPane.add(lblAddressInformation);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 246, 46, 14);
		contentPane.add(lblStreet);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 276, 46, 14);
		contentPane.add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setBounds(10, 306, 46, 14);
		contentPane.add(lblState);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(10, 336, 46, 14);
		contentPane.add(lblCountry);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 387, 115, 43);
		contentPane.add(btnSave);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(176, 387, 115, 43);
		contentPane.add(btnClear);

		JButton btnCancel = new JButton("Exit");
		btnCancel.setBounds(339, 387, 115, 43);
		contentPane.add(btnCancel);

		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setBounds(57, 65, 86, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(57, 90, 353, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);

		textField_Phone = new JTextField();
		textField_Phone.setBounds(57, 116, 142, 20);
		contentPane.add(textField_Phone);
		textField_Phone.setColumns(10);

		textField_PhoneII = new JTextField();
		textField_PhoneII.setColumns(10);
		textField_PhoneII.setBounds(268, 116, 142, 20);
		contentPane.add(textField_PhoneII);

		textField_Fax = new JTextField();
		textField_Fax.setBounds(57, 142, 142, 20);
		contentPane.add(textField_Fax);
		textField_Fax.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setBounds(57, 168, 353, 20);
		contentPane.add(textField_Email);
		textField_Email.setColumns(10);

		textField_Street = new JTextField();
		textField_Street.setBounds(57, 246, 353, 20);
		contentPane.add(textField_Street);
		textField_Street.setColumns(10);

		textField_City = new JTextField();
		textField_City.setBounds(57, 276, 353, 20);
		contentPane.add(textField_City);
		textField_City.setColumns(10);

		textField_State = new JTextField();
		textField_State.setBounds(57, 306, 353, 20);
		contentPane.add(textField_State);
		textField_State.setColumns(10);

		textField_Country = new JTextField();
		textField_Country.setBounds(57, 336, 353, 20);
		contentPane.add(textField_Country);
		textField_Country.setColumns(10);

		// Feeds the ArrayList of vendors to the Combobox for selection of
		// vendors
		JComboBox<Vendor> comboBox_ItemSelection = new JComboBox(Inventory.getVendors().toArray());
		comboBox_ItemSelection.setBounds(118, 32, 251, 20);
		contentPane.add(comboBox_ItemSelection);

		JLabel lblSelectAnOption = new JLabel("Select an existing vendor or leave blank for a new vendor");
		lblSelectAnOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAnOption.setBounds(57, 11, 353, 14);
		contentPane.add(lblSelectAnOption);

		// Start of Action Listeners

		// When the user hits the save button, the new / edited user is saved to
		// the storage file
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If the index is 0 then a new Vendor is added, as the new
				// option was selected
				if (comboBox_ItemSelection.getSelectedIndex() == 0)
					Inventory.getVendors()
							.add(new Vendor(textField_Name.getText(), textField_Phone.getText(),
									textField_PhoneII.getText(), textField_Fax.getText(), textField_Email.getText(),
									textField_Street.getText(), textField_City.getText(), textField_State.getText(),
									textField_Country.getText()));
				// If any other index is selected, meaning the user wants to
				// edit a vendor, then the values are changed for that vendor
				else {
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setName(textField_Name.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setPhone(textField_Phone.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setPhoneTwo(textField_PhoneII.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setFax(textField_Fax.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setEmail(textField_Email.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setStreet(textField_Street.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setCity(textField_City.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setState(textField_State.getText());
					Inventory.getVendors().get(comboBox_ItemSelection.getSelectedIndex())
							.setCountry(textField_Country.getText());
				}

				// Updates the file with the new / edited vendor
				Inventory.exportVendors();
				dispose();
			}
		});

		// If the user selects a Vendor from the Combobox, the appropriate text
		// fields are updated
		comboBox_ItemSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_ItemSelection.getSelectedIndex() != 0) {
					Vendor temp = (Vendor) comboBox_ItemSelection.getSelectedItem();
					textField_ID.setText("" + temp.getID());
					textField_Name.setText(temp.getName());
					textField_Fax.setText(temp.getFax());
					textField_Email.setText(temp.getEmail());
					textField_Phone.setText(temp.getPhone());
					textField_PhoneII.setText(temp.getPhoneTwo());
					textField_Street.setText(temp.getStreet());
					textField_City.setText(temp.getCity());
					textField_State.setText(temp.getState());
					textField_Country.setText(temp.getCountry());
				} else {
					// If the "new" catagory is selected then the boxes are
					// cleared.
					clearBoxes();
				}
			}
		});

		// Clears the boxes if the "Clear" button is pressed
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearBoxes();
			}
		});

		// Exits the window if the "Cancel" button is pressed
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make sure that all changes are saved
				Inventory.exportVendors();
				dispose();
			}
		});

		// End of Action Listeners

	}

	// Sets all selectable text boxes to be empty
	private void clearBoxes() {
		textField_ID.setText("");
		textField_Name.setText("");
		textField_Fax.setText("");
		textField_Email.setText("");
		textField_Phone.setText("");
		textField_PhoneII.setText("");
		textField_Street.setText("");
		textField_City.setText("");
		textField_State.setText("");
		textField_Country.setText("");
	}
}

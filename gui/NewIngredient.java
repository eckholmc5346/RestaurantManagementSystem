package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import objects.Ingredient;

public class NewIngredient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_ItemDestription;

	/**
	 * Launch the application.
	 */
	public void NewIngredientWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// NewIngredient frame = new NewIngredient();
					// frame.setVisible(true);
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
	public NewIngredient() {
		if (Inventory.getIngredient().size() == 0) {
			Inventory.getIngredient().add(new Ingredient());
		}

		setTitle("New/Update Ingredient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID#");
		lblId.setBounds(49, 86, 46, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(49, 111, 46, 14);
		contentPane.add(lblName);

		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setBounds(49, 136, 46, 14);
		contentPane.add(lblVendor);

		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setBounds(105, 83, 222, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(105, 108, 222, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(49, 286, 101, 39);
		contentPane.add(btnSave);
		btnSave.setEnabled(false);
		
		JComboBox comboBox_Vendor = new JComboBox(Inventory.getVendors().toArray());
		comboBox_Vendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//this if statement prevents the program from crashing if the user were to try and save an ingredient without selecting a vendor
				if(comboBox_Vendor.getSelectedIndex() > 0)
					btnSave.setEnabled(true);
				else
					btnSave.setEnabled(false);
			}
		});
		comboBox_Vendor.setBounds(105, 133, 222, 20);
		contentPane.add(comboBox_Vendor);

		// populates ingredients drop box with ingredients array contents
		JComboBox comboBox_AddOrUpdate = new JComboBox(Inventory.getIngredient().toArray());
		comboBox_AddOrUpdate.setBounds(105, 39, 222, 20);
		contentPane.add(comboBox_AddOrUpdate);

		comboBox_AddOrUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox_AddOrUpdate.getSelectedIndex() != 0) {
					Ingredient tempObject = (Ingredient) comboBox_AddOrUpdate.getSelectedItem();
					textField_ID.setText("" + tempObject.getIngredientID());
					textField_Name.setText(tempObject.getName());
					textField_ItemDestription.setText(tempObject.getInfo());
					comboBox_Vendor.setSelectedIndex(tempObject.getVendorID());

				} // end if
				else {
					// If new is selected then the boxes are cleared.
					textField_ID.setText("");
					textField_Name.setText("");
					textField_ItemDestription.setText("");

				} // end else
			}
		});

		

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Inventory.getVendors().size() > 0) {
					//if(comboBox_Vendor.getSelectedIndex() > 0)	//this doesn't work.. need a loop?
					//{
					if (comboBox_AddOrUpdate.getSelectedIndex() == 0) {
						int ID = 0;

						String vendorID = comboBox_Vendor.getSelectedItem().toString();

						String oneCharacter = vendorID.substring(0, 1);
						ID = Integer.parseInt(oneCharacter);




						Inventory.getIngredient().add(new Ingredient(textField_Name.getText(),
								textField_ItemDestription.getText(), comboBox_Vendor.getSelectedItem().toString(), ID));
					} 	//end if
					else 
					{
						Inventory.getIngredient().get(comboBox_AddOrUpdate.getSelectedIndex())
						.setName(textField_Name.getText());
						Inventory.getIngredient().get(comboBox_AddOrUpdate.getSelectedIndex())
						.setInfo(textField_ItemDestription.getText());
						Inventory.getIngredient().get(comboBox_AddOrUpdate.getSelectedIndex())
						.setVendor(comboBox_Vendor.getSelectedItem().toString());
					}	//end else

					Inventory.exportIngredients();
					//} 	//end if

					// Closes window
					dispose();
				}	//end if comboBox_Vendor.selected Index > 0
				else
				{
					JOptionPane.showMessageDialog(null,
							"Vendor missing. Please input a vendor before attempting to add ingredients");
				}
			}	//end if vendor.size() > 0
		});

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Name.setText("");
				textField_ItemDestription.setText("");
			}
		});
		btnClear.setBounds(160, 286, 101, 39);
		contentPane.add(btnClear);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(271, 286, 101, 39);
		contentPane.add(btnCancel);

		JLabel lblAbout = new JLabel("About");
		lblAbout.setBounds(196, 164, 46, 14);
		contentPane.add(lblAbout);

		textField_ItemDestription = new JTextField();
		textField_ItemDestription.setBounds(105, 189, 222, 86);
		contentPane.add(textField_ItemDestription);
		textField_ItemDestription.setColumns(10);

		JLabel lblSelectionAnOption = new JLabel("Select an existing ingredient or leave blank for a new one");
		lblSelectionAnOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectionAnOption.setBounds(49, 14, 323, 14);
		contentPane.add(lblSelectionAnOption);
	}
}

package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author      Ryan Moeller <moellerr2442@my.uwstout.edu>
 * @version     1.0
 * @since       1.0
 */

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblEmployeeLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the login frame.
	 */
	public Login() {
		
		// Sets up the frame
		setTitle("System Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Username text label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(115, 90, 65, 15);
		contentPane.add(lblUsername);
		
		// Password text label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(115, 120, 65, 15);
		contentPane.add(lblPassword);
		
		// Field to enter in the username
		textField = new JTextField();
		textField.setBounds(190, 90, 110, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Field to enter in the password
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 120, 110, 20);
		contentPane.add(passwordField);
		
		// Title of the program, shows the text in the middle of the screen
		lblEmployeeLogin = new JLabel("RMS Login");
		lblEmployeeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeeLogin.setBounds(120, 43, 179, 25);
		contentPane.add(lblEmployeeLogin);

		// Starts off blank, but if a wrong username/password combo is entered,
		// the text is set to an error message
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 165, 434, 14);
		contentPane.add(lblNewLabel);
		
		// Attempts to log the user in as a manager, username/password required
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String username = textField.getText();
				String password = new String(passwordField.getPassword());

				if (checkPasswordMatch(username, password)) {
					Inventory newWindow = new Inventory(true);
					setVisible(false);
					dispose();
				} else
					lblNewLabel.setText("Wrong Username / Password entered. Try again.");

			}
		});
		loginButton.setBounds(0, 225, 434, 36);
		contentPane.add(loginButton);
		
		// Logs the user in as an employee, giving them limited permissions to access functions
		JButton employeeButton = new JButton("Continue as Employee");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory newWindow = new Inventory(false);
				setVisible(false);
				dispose();
			}
		});
		employeeButton.setBounds(0, 190, 434, 36);
		contentPane.add(employeeButton);

	}
	/**
	 * Checks to see if the entered password matches any existing passwords
	 * <p>
	 * Reads in a list of usernames and passwords from login.txt, and searches
	 * them in order to see if the entered pair matches an existing pair.
	 * 
	 * @param username Username that the user entered into the login box
	 * @param password Password that the user entered into the login box
	 * @return Returns true if match, false if no match
	 */
	private boolean checkPasswordMatch(String username, String password) {
		try {
			File login = new File("login.txt");
			
			// Creates a new file if one doesn't already exist.
			login.createNewFile();

			// Formats our entered info into the same format in login.txt
			String userPass = username + " " + password;
			
			Scanner reader;
			reader = new Scanner(login);
			
			// Runs until either the file has no more lines, or a match is found
			while (reader.hasNextLine()) {
				if (reader.nextLine().equals(userPass)) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

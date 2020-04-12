package AppletPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddressApplet {

	private JFrame frmAddressBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressApplet window = new AddressApplet();
					window.frmAddressBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddressApplet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddressBook = new JFrame();
		frmAddressBook.setTitle("Address Book");
		frmAddressBook.setBounds(100, 100, 491, 342);
		frmAddressBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddressBook.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To Address Book");
		lblNewLabel.setFont(new Font("L M Roman Slant10", Font.BOLD, 20));
		lblNewLabel.setBounds(110, 22, 285, 25);
		frmAddressBook.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select what you want to do:");
		lblNewLabel_1.setBounds(135, 85, 225, 15);
		frmAddressBook.getContentPane().add(lblNewLabel_1);
		
		JButton Button1 = new JButton("Create New");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewPressed();
			}
		});
		Button1.setBounds(72, 157, 117, 25);
		frmAddressBook.getContentPane().add(Button1);
		
		JButton Button2 = new JButton("Edit Existing");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editExistingPressed();
			}
		});
		Button2.setBounds(277, 157, 131, 25);
		frmAddressBook.getContentPane().add(Button2);
	}
	
	private void createNewPressed() {
		createNew cn = new createNew();
		cn.newCreated();
	}
	
	private void editExistingPressed() {
		editExisting ee = new editExisting();
		ee.existingEdited();
	}
	
}

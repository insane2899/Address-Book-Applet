package AppletPackage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Required.AddressBook;

import javax.swing.JButton;
import javax.swing.JDialog;

public class NewEntry {

	private JFrame frame;
	private String frameName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private AddressBook book;

	/**
	 * Launch the application.
	 */
	public void newEntry(String s,AddressBook book) {
		frameName = s;
		this.book = book;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEntry window = new NewEntry(s,book);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewEntry(String s,AddressBook book) {
		frameName = s;
		this.book = book;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(31, 23, 106, 15);
		frame.getContentPane().add(lblFirstName);
		
		textField = new JTextField();
		textField.setBounds(188, 21, 231, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(31, 50, 90, 15);
		frame.getContentPane().add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 48, 231, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(31, 83, 106, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 81, 231, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(33, 123, 70, 15);
		frame.getContentPane().add(lblZip);
		
		textField_3 = new JTextField();
		textField_3.setBounds(188, 121, 231, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPhoneoptional = new JLabel("Phone(Optional):");
		lblPhoneoptional.setBounds(31, 160, 138, 15);
		frame.getContentPane().add(lblPhoneoptional);
		
		textField_4 = new JTextField();
		textField_4.setBounds(188, 158, 231, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEmailoptional = new JLabel("Email(Optional):");
		lblEmailoptional.setBounds(31, 187, 136, 15);
		frame.getContentPane().add(lblEmailoptional);
		
		textField_5 = new JTextField();
		textField_5.setBounds(188, 183, 231, 19);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(154, 214, 117, 25);
		frame.getContentPane().add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String f = textField.getText();
				String l = textField_1.getText();
				String a = textField_2.getText();
				String z = textField_3.getText();
				String p = textField_4.getText();
				String em = textField_5.getText();
				if(f.equals("")||l.equals("")||a.equals("")||z.equals("")) {
					JDialog d = new JDialog(frame,"Error");
					JLabel label = new JLabel("Some Essential Fields are not filled");
					d.add(label);
					d.setSize(400,800);
					d.setVisible(true);
				}
				if(p.equals("")) {
					p="--";
				}
				if(em.equals("")) {
					em="--";
				}
				String s = f+"|"+l+"|"+a+"|"+z+"|"+p+"|"+em;
				book.addEntries(s);
				NewFile r = new NewFile(book,frameName);
				r.newFile(book,frameName);
				frame.dispose();
			}
		});
	}
}

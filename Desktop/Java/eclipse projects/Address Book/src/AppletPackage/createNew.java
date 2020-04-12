package AppletPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class createNew {

	private JFrame frmUntitled;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void newCreated() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createNew window = new createNew();
					window.frmUntitled.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public createNew() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUntitled = new JFrame();
		frmUntitled.setAlwaysOnTop(true);
		frmUntitled.setTitle("New Address Book");
		frmUntitled.setBounds(100, 100, 444, 140);
		frmUntitled.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUntitled.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(283, 12, 145, 19);
		frmUntitled.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel Label1 = new JLabel("Enter the Name of Address Book:");
		Label1.setBounds(12, 12, 245, 19);
		frmUntitled.getContentPane().add(Label1);
		
		JLabel Label2 = new JLabel("");
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = new String(textField.getText());
				if(str==null||str.equals("")) {
					Label2.setText("No Name Entered");
				}
				else {
					try {
						File f = new File("AddressBooks/"+str);
						boolean tag = f.createNewFile();
						if(tag) {
							created(str);
						}
						else {
							JDialog dialog = new JDialog(frmUntitled,"Error!");
							JLabel label = new JLabel("File Already Exists");
							dialog.add(label);
							dialog.setSize(500,200);
							dialog.setVisible(true);
						}
					}catch(Exception z) {
						System.out.println(z);
					}
				}
			}
		});
		btnEnter.setBounds(160, 43, 117, 25);
		frmUntitled.getContentPane().add(btnEnter);
		
		Label2.setBounds(12, 83, 416, 15);
		frmUntitled.getContentPane().add(Label2);
	}
	
	private void created(String str) {
		frmUntitled.dispose();
		NewFile f = new NewFile(str);
		f.newFile(str);
	}
}

package AppletPackage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Required.AddressBook;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JDialog;

public class editExisting {

	private JFrame frmEditExisting;
	private AddressBook book;
	JFileChooser fc;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void existingEdited() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editExisting window = new editExisting();
					window.frmEditExisting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public editExisting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditExisting = new JFrame();
		frmEditExisting.setTitle("Edit Existing");
		frmEditExisting.setBounds(100, 100, 412, 190);
		frmEditExisting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditExisting.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter the FilePath");
		lblNewLabel.setBounds(129, 12, 151, 34);
		frmEditExisting.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(95, 61, 225, 19);
		frmEditExisting.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(152, 92, 117, 25);
		frmEditExisting.getContentPane().add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				if(s.equals("")) {
					JDialog dialog = new JDialog(frmEditExisting,"Error!");
					JLabel label = new JLabel("No File Selected");
					dialog.add(label);
					dialog.setSize(100,200);
					dialog.setVisible(true);
				}
				else {
					int l = 0;
					for(int i=0;i<s.length();i++) {
						if(s.charAt(i)=='/') {
							l=i;
						}
					}
					String fileName = s.substring(l+1,s.length());
					try {
						File f = new File(s);
						BufferedReader br = null;
						book = new AddressBook(f,br);
						NewFile nf = new NewFile(book,fileName);
						nf.newFile(book,fileName);
						frmEditExisting.dispose();
					}catch(Exception z) {
						System.out.println(z);
					}
					
				}
			}
		});
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(332, 61, 35, 19);
		frmEditExisting.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int r = fc.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION) {
					textField.setText(fc.getSelectedFile().getAbsolutePath());
				}
				else {
					textField.setText("");
				}
			}
		});
	}
}

package AppletPackage;


import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Required.AddressBook;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class NewFile {

	private JFrame frame;
	private String frameName;
	private JTable table;
	private JScrollPane scroll;
	private AddressBook book;

	/**
	 * Launch the application.
	 */
	public void newFile(String s) {
		frameName = s;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFile window = new NewFile(s);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void newFile(AddressBook book,String s) {
		frameName = s;
		this.book = book;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFile window = new NewFile(book,s);
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
	public NewFile(String s) {
		frameName = s;
		initialize();
	}
	
	public NewFile(AddressBook book,String s) {
		frameName = s;
		this.book = book;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(frameName);
		frame.setBounds(100, 100, 796, 300);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit without saving?","Confirmation",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
					try{
						File file = new File("AddressBooks/"+frameName);
						BufferedReader br = new BufferedReader(new FileReader("AddressBooks/"+frameName));
						if((br.readLine())==null) {
							file.delete();
							//System.out.println("deleted");
						}
						frame.dispose();
					}catch(Exception z) {
						System.out.println(z);
					}
				}
				else {
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmReload = new JMenuItem("Reload");
		mnFile.add(mntmReload);
		mntmReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewFile nf = new NewFile(book,frameName);
				nf.newFile(book,frameName);
				frame.dispose();
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] message = {"Saving the File will overwrite all previous saves. Are you sure you want to save?"};
				int option = JOptionPane.showConfirmDialog(frame,message,"Warning",JOptionPane.OK_CANCEL_OPTION);
				if(option==JOptionPane.OK_OPTION) {
					File f = null;
					try {
						f=new File("AddressBooks/"+frameName);
						f.createNewFile();
						BufferedWriter bw = new BufferedWriter(new FileWriter("AddressBooks/"+frameName));
						book.writeFile("AddressBooks/"+frameName, bw);
						bw.close();
					}catch(Exception w) {
						System.out.println("Exception: "+w);
					}
				}
			}
		});
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] message = {"Do you want to close without saving?"};
				int option = JOptionPane.showConfirmDialog(frame, message,"Warning",JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAddNewEntry = new JMenuItem("Add New Entry");
		mnEdit.add(mntmAddNewEntry);
		mntmAddNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEntry ne = new NewEntry(frameName,book);
				ne.newEntry(frameName,book);
				frame.dispose();
			}
		});
		
		JMenuItem mntmDeleteEntry = new JMenuItem("Delete Entry");
		mnEdit.add(mntmDeleteEntry);
		mntmDeleteEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField num = new JTextField();
				Object[] message = {
						"Enter the index of the entry to be deleted:",num
				};
				int option = JOptionPane.showConfirmDialog(frame,message,"Enter the index",JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					int n = new Integer(num.getText());
					book.deleteEntries(n-1);
				}
				NewFile nf = new NewFile(book,frameName);
				nf.newFile(book,frameName);
				frame.dispose();
			}
		});
		
		JMenuItem mntmSortByName = new JMenuItem("Sort By Name");
		mnEdit.add(mntmSortByName);
		mntmSortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book.getSortedByName();
				NewFile nf = new NewFile(book,frameName);
				nf.newFile(book,frameName);
				frame.dispose();
			}
		});
		
		JMenuItem mntmSortByZip = new JMenuItem("Sort By Zip");
		mnEdit.add(mntmSortByZip);
		mntmSortByZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book.getSortedByZip();
				NewFile nf = new NewFile(book,frameName);
				nf.newFile(book,frameName);
				frame.dispose();
			}
		});
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frame.getContentPane().setLayout(null);
		
		JMenuItem Help = new JMenuItem("Help");
		mnHelp.add(Help);
		Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "To Add New Entry Edit->Add New Entry\nTo Delete Entry Edit->Delete Entry\nTo Save File->Save";
				JOptionPane.showMessageDialog(frame,s,"Help",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		JLabel lblAddressBook = new JLabel("Address Book:");
		lblAddressBook.setBounds(22, 12, 122, 15);
		frame.getContentPane().add(lblAddressBook);
		
		String[] cols = {"Index","First Name","Last Name","Address","Zip","Phone","Email"};
		if(book==null) {
			book = new AddressBook();
		}
		String[][] rows = new String[book.getTop()][7];
		for(int i=0;i<book.getTop();i++) {
			rows[i][0]=Integer.toString(i+1);
			rows[i][1]=book.getIthEntries(i).getFirstName();
			rows[i][2]=book.getIthEntries(i).getLastName();
			rows[i][3]=book.getIthEntries(i).getAddress();
			rows[i][4]=book.getIthEntries(i).getZip();
			rows[i][5]=book.getIthEntries(i).getPhone();
			rows[i][6]=book.getIthEntries(i).getEmail();
		}
		table = new JTable(rows,cols);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 28, 774, 209);
		frame.getContentPane().add(scroll);
		
	}
}

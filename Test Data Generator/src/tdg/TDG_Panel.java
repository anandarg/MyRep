package tdg;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class TDG_Panel extends JPanel implements ActionListener {
	
	private JLabel lblAppTitle;
	private JLabel lblSrcFileLoc;
	private SpringLayout currentLayout;
	private JTextField txtSrcFileLoc;
	private JTextField txtGblVarFileLoc;
	private JTextField txtInVarFileLoc;
	private JTextField txtFuncFileLoc;
	private JTextField txtRepFileLoc;
	private JTextField textField;
	public TDG_Panel() {
		setForeground(Color.RED);
		setBackground(Color.LIGHT_GRAY);
		
		
		
		setupPanel();
	}
	
	private void setupPanel() {
		
		
		lblAppTitle= new JLabel("Test Data Generator");
		lblAppTitle.setForeground(new Color(0, 0, 255));
		lblAppTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSrcFileLoc = new JLabel("Source File Location:");
		lblSrcFileLoc.setForeground(Color.RED);
		lblSrcFileLoc.setToolTipText("Location of the source code C file ");
		lblSrcFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		currentLayout = new SpringLayout();
		currentLayout.putConstraint(SpringLayout.NORTH, lblAppTitle, 10, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAppTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		this.setLayout(currentLayout);
		currentLayout.putConstraint(SpringLayout.NORTH, lblSrcFileLoc, 89, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, lblSrcFileLoc, 10, SpringLayout.WEST, this);
		
		this.add(lblAppTitle);
		this.add(lblSrcFileLoc);
		
		JLabel lblGblVarFileLoc = new JLabel("Global Variable File Location:");
		lblGblVarFileLoc.setForeground(Color.RED);
		currentLayout.putConstraint(SpringLayout.WEST, lblGblVarFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblGblVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblGblVarFileLoc);
		
		JLabel lblInVarFileLoc = new JLabel("Input Variable File Location:");
		lblInVarFileLoc.setForeground(Color.RED);
		currentLayout.putConstraint(SpringLayout.SOUTH, lblGblVarFileLoc, -16, SpringLayout.NORTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.NORTH, lblInVarFileLoc, 49, SpringLayout.SOUTH, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblInVarFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInVarFileLoc);
		
		JLabel lblFuncFileLoc = new JLabel("Function File Location:");
		lblFuncFileLoc.setForeground(Color.RED);
		currentLayout.putConstraint(SpringLayout.NORTH, lblFuncFileLoc, 18, SpringLayout.SOUTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblFuncFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblFuncFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFuncFileLoc);
		
		JLabel lblRepFileLoc = new JLabel("Representation File Location:");
		lblRepFileLoc.setForeground(Color.RED);
		currentLayout.putConstraint(SpringLayout.NORTH, lblRepFileLoc, 18, SpringLayout.SOUTH, lblFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblRepFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblRepFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblRepFileLoc);
		
		JLabel lblThreads = new JLabel("Number of Threads:");
		lblThreads.setForeground(Color.RED);
		currentLayout.putConstraint(SpringLayout.NORTH, lblThreads, 18, SpringLayout.SOUTH, lblRepFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblThreads, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblThreads.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblThreads);
		
		txtSrcFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtSrcFileLoc, -2, SpringLayout.NORTH, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtSrcFileLoc, 95, SpringLayout.EAST, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtSrcFileLoc, -343, SpringLayout.EAST, this);
		add(txtSrcFileLoc);
		txtSrcFileLoc.setColumns(250);
		
		txtGblVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtGblVarFileLoc, -2, SpringLayout.NORTH, lblGblVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtGblVarFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtGblVarFileLoc, -343, SpringLayout.EAST, this);
		txtGblVarFileLoc.setColumns(250);
		add(txtGblVarFileLoc);
		
		txtInVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtInVarFileLoc, -2, SpringLayout.NORTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtInVarFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtInVarFileLoc, -343, SpringLayout.EAST, this);
		txtInVarFileLoc.setColumns(250);
		add(txtInVarFileLoc);
		
		txtFuncFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtFuncFileLoc, -2, SpringLayout.NORTH, lblFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtFuncFileLoc, 84, SpringLayout.EAST, lblFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtFuncFileLoc, -343, SpringLayout.EAST, this);
		txtFuncFileLoc.setColumns(250);
		add(txtFuncFileLoc);
		
		txtRepFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtRepFileLoc, 0, SpringLayout.NORTH, lblRepFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtRepFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtRepFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		txtRepFileLoc.setColumns(250);
		add(txtRepFileLoc);
		
		textField = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, textField, -2, SpringLayout.NORTH, lblThreads);
		currentLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, txtSrcFileLoc);
		textField.setColumns(5);
		add(textField);
		
		JButton btnGenInFiles = new JButton("Generate Input Files");
		btnGenInFiles.setToolTipText("Generate the input files from the C file.");
		btnGenInFiles.setToolTipText("Generate the global var, input var and function files from the source C file");
		btnGenInFiles.setActionCommand("GenInputFiles");
		btnGenInFiles.addActionListener(this);
		add(btnGenInFiles);
		
		JButton btnExit = new JButton("Exit");
		currentLayout.putConstraint(SpringLayout.SOUTH, btnExit, -10, SpringLayout.SOUTH, this);
		btnExit.setToolTipText("Exit the application");
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(this);
		add(btnExit);
		
		JButton btnGenRepFile = new JButton("Generate Representation File");
		currentLayout.putConstraint(SpringLayout.NORTH, btnGenInFiles, 0, SpringLayout.NORTH, btnGenRepFile);
		currentLayout.putConstraint(SpringLayout.EAST, btnGenInFiles, -6, SpringLayout.WEST, btnGenRepFile);
		currentLayout.putConstraint(SpringLayout.EAST, btnGenRepFile, 0, SpringLayout.EAST, btnExit);
		btnGenRepFile.setToolTipText("Generate the input files from the C file.");
		btnGenRepFile.setActionCommand("GenRepFile");
		btnGenRepFile.addActionListener(this);
		add(btnGenRepFile);
		
		JButton btnGenTestData = new JButton("Generate Test Data");
		currentLayout.putConstraint(SpringLayout.EAST, btnGenTestData, -105, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.WEST, btnExit, 6, SpringLayout.EAST, btnGenTestData);
		currentLayout.putConstraint(SpringLayout.NORTH, btnGenTestData, 0, SpringLayout.NORTH, btnExit);
		btnGenTestData.setToolTipText("Generate the input files from the C file.");
		btnGenTestData.setActionCommand("GenTestData");
		btnGenTestData.addActionListener(this);
		add(btnGenTestData);
		
		JSeparator separator = new JSeparator();
		currentLayout.putConstraint(SpringLayout.SOUTH, btnGenRepFile, -6, SpringLayout.NORTH, separator);
		currentLayout.putConstraint(SpringLayout.NORTH, separator, 21, SpringLayout.SOUTH, textField);
		currentLayout.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, this);
		currentLayout.putConstraint(SpringLayout.SOUTH, separator, 23, SpringLayout.SOUTH, textField);
		currentLayout.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, btnExit);
		add(separator);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    if("exit".equals(e.getActionCommand())) {
	      System.out.println("exit button selected");
	      SwingUtilities.getWindowAncestor(this).dispose();
	    } 
	    else if ("GenInputFiles".equals(e.getActionCommand())) {
	      System.out.println("Generate Input Files button selected");
	    }
	    else if ("GenRepFile".equals(e.getActionCommand())) {
		      System.out.println("Generate Representation File button selected");
		}
	    else if ("GenTestData".equals(e.getActionCommand())) {
		      System.out.println("Generate Test Data button selected");
		}
	  }
}

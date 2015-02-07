package tdg;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.Color;

public class TDG_Panel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114058600440465384L;
	private JLabel lblSrcFileLoc;
	private SpringLayout currentLayout;
	private JTextField txtSrcFileLoc;
	private JTextField txtGblVarFileLoc;
	private JTextField txtInVarFileLoc;
	private JTextField txtFuncFileLoc;
	private JTextField txtRepFileLoc;
	private JTextField txtNbThreads;
	private JTextField txtConfigFile;
	private ManageConfigWindow mCfgWin;
	
	public TDG_Panel() {
		setForeground(UIManager.getColor("Panel.foreground"));
		setBackground(UIManager.getColor("Panel.background"));
				
		setupPanel();
		mCfgWin = new ManageConfigWindow();
	}
	
	private void setupPanel() {
		lblSrcFileLoc = new JLabel("Source File Location:");
		lblSrcFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		lblSrcFileLoc.setToolTipText("Location of the source code C file ");
		lblSrcFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		currentLayout = new SpringLayout();
		this.setLayout(currentLayout);
		currentLayout.putConstraint(SpringLayout.NORTH, lblSrcFileLoc, 89, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, lblSrcFileLoc, 10, SpringLayout.WEST, this);
		this.add(lblSrcFileLoc);
		
		JLabel lblGblVarFileLoc = new JLabel("Global Variable File Location:");
		lblGblVarFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		currentLayout.putConstraint(SpringLayout.WEST, lblGblVarFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblGblVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblGblVarFileLoc);
		
		JLabel lblInVarFileLoc = new JLabel("Input Variable File Location:");
		lblInVarFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		currentLayout.putConstraint(SpringLayout.SOUTH, lblGblVarFileLoc, -16, SpringLayout.NORTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.NORTH, lblInVarFileLoc, 49, SpringLayout.SOUTH, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblInVarFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInVarFileLoc);
		
		JLabel lblFuncFileLoc = new JLabel("Function File Location:");
		lblFuncFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		currentLayout.putConstraint(SpringLayout.NORTH, lblFuncFileLoc, 18, SpringLayout.SOUTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblFuncFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblFuncFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFuncFileLoc);
		
		JLabel lblRepFileLoc = new JLabel("Representation File Location:");
		lblRepFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		currentLayout.putConstraint(SpringLayout.NORTH, lblRepFileLoc, 18, SpringLayout.SOUTH, lblFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblRepFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblRepFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblRepFileLoc);
		
		JLabel lblThreads = new JLabel("Number of Threads:");
		lblThreads.setForeground(UIManager.getColor("Label.foreground"));
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
		
		txtNbThreads = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtNbThreads, -2, SpringLayout.NORTH, lblThreads);
		currentLayout.putConstraint(SpringLayout.WEST, txtNbThreads, 0, SpringLayout.WEST, txtSrcFileLoc);
		txtNbThreads.setColumns(5);
		add(txtNbThreads);
		
		JButton btnGenInFiles = new JButton("Generate Input Files");
		currentLayout.putConstraint(SpringLayout.SOUTH, btnGenInFiles, -10, SpringLayout.SOUTH, this);
		btnGenInFiles.setToolTipText("Generate the input files from the C file.");
		btnGenInFiles.setToolTipText("Generate the global var, input var and function files from the source C file");
		btnGenInFiles.setActionCommand("GenInputFiles");
		btnGenInFiles.addActionListener(this);
		add(btnGenInFiles);
		
		JButton btnExit = new JButton("Exit");
		currentLayout.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, btnGenInFiles);
		btnExit.setToolTipText("Exit the application");
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(this);
		add(btnExit);
		
		JButton btnGenRepFile = new JButton("Generate Representation File");
		currentLayout.putConstraint(SpringLayout.NORTH, btnGenRepFile, 0, SpringLayout.NORTH, btnGenInFiles);
		currentLayout.putConstraint(SpringLayout.WEST, btnGenRepFile, 6, SpringLayout.EAST, btnGenInFiles);
		btnGenRepFile.setToolTipText("Generate the input files from the C file.");
		btnGenRepFile.setActionCommand("GenRepFile");
		btnGenRepFile.addActionListener(this);
		add(btnGenRepFile);
		
		JButton btnGenTestData = new JButton("Generate Test Data");
		currentLayout.putConstraint(SpringLayout.NORTH, btnGenTestData, 0, SpringLayout.NORTH, btnGenInFiles);
		currentLayout.putConstraint(SpringLayout.WEST, btnGenTestData, 6, SpringLayout.EAST, btnGenRepFile);
		btnGenTestData.setToolTipText("Generate the input files from the C file.");
		btnGenTestData.setActionCommand("GenTestData");
		btnGenTestData.addActionListener(this);
		add(btnGenTestData);
		
		JButton btnSave = new JButton("Save");
		currentLayout.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, btnGenInFiles, 6, SpringLayout.EAST, btnSave);
		currentLayout.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, lblSrcFileLoc);
		btnSave.addActionListener(this);
		btnSave.setToolTipText("Save the config settings to a properties file");
		add(btnSave);
		
		JButton button = new JButton("Open Config File");
		currentLayout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, btnExit);
		button.addActionListener(this);
		button.setToolTipText("Open config properties file");
		button.setActionCommand("OpnCfgFile");
		add(button);
		
		JLabel lblCfgFile = new JLabel("Selected Config File:");
		currentLayout.putConstraint(SpringLayout.NORTH, button, -3, SpringLayout.NORTH, lblCfgFile);
		currentLayout.putConstraint(SpringLayout.NORTH, lblCfgFile, 53, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.EAST, lblCfgFile, 0, SpringLayout.EAST, lblSrcFileLoc);
		lblCfgFile.setToolTipText("Location of the config file ");
		lblCfgFile.setForeground(UIManager.getColor("Label.foreground"));
		lblCfgFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCfgFile);
		
		txtConfigFile = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtConfigFile, 51, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, txtConfigFile, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtConfigFile, 0, SpringLayout.EAST, txtSrcFileLoc);
		txtConfigFile.setColumns(250);
		add(txtConfigFile);
		
		JButton btnTransData = new JButton("Transform Test Data");
		currentLayout.putConstraint(SpringLayout.WEST, btnExit, 6, SpringLayout.EAST, btnTransData);
		currentLayout.putConstraint(SpringLayout.NORTH, btnTransData, 0, SpringLayout.NORTH, btnGenInFiles);
		currentLayout.putConstraint(SpringLayout.WEST, btnTransData, 6, SpringLayout.EAST, btnGenTestData);
		btnTransData.setToolTipText("Transform the test data to create RiBett input file.");
		btnTransData.setActionCommand("GenTestData");
		add(btnTransData);
		
		JLabel lblTestDataGenerator = new JLabel("Test Data Generator");
		lblTestDataGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		currentLayout.putConstraint(SpringLayout.NORTH, lblTestDataGenerator, 10, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, lblTestDataGenerator, 291, SpringLayout.WEST, this);
		lblTestDataGenerator.setToolTipText("");
		lblTestDataGenerator.setForeground(Color.BLACK);
		lblTestDataGenerator.setFont(new Font("Chiller", Font.BOLD, 40));
		add(lblTestDataGenerator);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    Process proc;
		InputStream in;
		InputStream err;
		JFileChooser fileChooser;
		FileNameExtensionFilter filter;
  	  	int value;
		
		
		try {
	    
	    
			if("exit".equals(e.getActionCommand())) {
		      System.out.println("exit button selected");
		      SwingUtilities.getWindowAncestor(this).dispose();
		    } 
		    else if ("GenInputFiles".equals(e.getActionCommand())) {
		      System.out.println("Generate Input Files button selected");
		    }
		    else if ("GenRepFile".equals(e.getActionCommand())) {
			      System.out.println("Generate Representation File button selected" + txtConfigFile.getText());
			      proc = Runtime.getRuntime().exec("java -jar -Xmx8192m -Xms8192m .\\lib\\ComputeChunks.jar " + txtConfigFile.getText());
			      proc.waitFor();
			      // Then retrieve the process output
			      in = proc.getInputStream();
			      err = proc.getErrorStream();
	
			      byte b[]=new byte[in.available()];
			      in.read(b,0,b.length);
			      System.out.println(new String(b));
	
			      byte c[]=new byte[err.available()];
			      err.read(c,0,c.length);
			      System.out.println(new String(c));
			}
		    else if ("GenTestData".equals(e.getActionCommand())) {
			      System.out.println("Generate Test Data button selected");
			      proc = Runtime.getRuntime().exec("java -jar -Xmx8192m -Xms8192m .\\lib\\GenerateInputs.jar " + txtConfigFile.getText());
			      proc.waitFor();
			      // Then retrieve the process output
			      in = proc.getInputStream();
			      err = proc.getErrorStream();
	
			      byte b[]=new byte[in.available()];
			      in.read(b,0,b.length);
			      System.out.println(new String(b));
	
			      byte c[]=new byte[err.available()];
			      err.read(c,0,c.length);
			      System.out.println(new String(c)); 
			      
			      System.out.println("Test Data Generation Completed");
			}
		    else if ("OpnCfgFile".equals(e.getActionCommand())) {
			      System.out.println("Open Config File button selected");
			      try {
			    	  fileChooser = new JFileChooser(".");
			    	  fileChooser.setAcceptAllFileFilterUsed(false);
			    	  filter = new FileNameExtensionFilter("Properties","properties");
			    	  fileChooser.setFileFilter(filter);
			    	  value = fileChooser.showOpenDialog(this);
			    	  if(value == JFileChooser.APPROVE_OPTION){
			    		  System.out.println("Selected File " + fileChooser.getSelectedFile().getPath());
			    		  txtConfigFile.setText(fileChooser.getSelectedFile().getPath());
			    		  mCfgWin.loadProperties(fileChooser.getSelectedFile());
			    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
			    		  this.txtGblVarFileLoc.setText(mCfgWin.getGlobalVarFileLoc());
			    		  this.txtInVarFileLoc.setText(mCfgWin.getInputVarFileLoc());
			    		  this.txtFuncFileLoc.setText(mCfgWin.getFunctionFileLoc());
			    		  this.txtRepFileLoc.setText(mCfgWin.getRepFileLoc());
			    		  this.txtNbThreads.setText(mCfgWin.getNbThreads());
			    	  }
			      }catch (Exception ofe) {
			    	  System.out.println("Open File Exception Occured");
			      }
		    }    
		    else if ("Save".equals(e.getActionCommand())) {
			      System.out.println("Save As button selected");
			      try {
			    	  mCfgWin.setConfigFile(this.txtConfigFile.getText());
			    	  mCfgWin.setCFileLoc(this.txtSrcFileLoc.getText());
			    	  mCfgWin.setGlobalVarFile(this.txtGblVarFileLoc.getText());
			    	  mCfgWin.setInputvarFileLoc(this.txtInVarFileLoc.getText());
			    	  mCfgWin.setFunctionFileLoc(this.txtFuncFileLoc.getText());
			    	  mCfgWin.setRepFileLoc(this.txtRepFileLoc.getText());
			    	  mCfgWin.setNbThreads(this.txtNbThreads.getText());
			    	  mCfgWin.saveProperties();
			    	  mCfgWin.loadProperties(new File(this.txtConfigFile.getText()));
		    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
		    		  this.txtGblVarFileLoc.setText(mCfgWin.getGlobalVarFileLoc());
		    		  this.txtInVarFileLoc.setText(mCfgWin.getInputVarFileLoc());
		    		  this.txtFuncFileLoc.setText(mCfgWin.getFunctionFileLoc());
		    		  this.txtRepFileLoc.setText(mCfgWin.getRepFileLoc());
		    		  this.txtNbThreads.setText(mCfgWin.getNbThreads());
		    		  System.out.println("Saved Config File Refreshed");
			      }catch (Exception sfe) {
			    	  System.out.println("Save File Exception Occured");
			      }     
			}
	    }catch (Exception ie){
	    	System.out.println("IO Exception Occured");
	    }
	  }
}

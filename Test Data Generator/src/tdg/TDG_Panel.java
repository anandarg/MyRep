package tdg;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
	private GenerateInputFiles genInpFiles;
	private TransformTestData trnTestData;
	private JTextField txtMaxCycles;
	private JTextField txtFirstCycle;
	private JTextField txtZ3InpFileLoc;
	private JTextField txtTestDataFileLoc;
	private JTextField txtNewGblVarFileLoc;
	private JTextField txtZ3OutFileLoc;
	private JTextField txtTargetFileLoc;
	
	public TDG_Panel() {
		setForeground(UIManager.getColor("Panel.foreground"));
		setBackground(UIManager.getColor("Panel.background"));
				
		setupPanel();
		mCfgWin = new ManageConfigWindow();
		genInpFiles = new GenerateInputFiles();
		trnTestData = new TransformTestData();
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
		currentLayout.putConstraint(SpringLayout.NORTH, button, 50, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, btnExit);
		button.addActionListener(this);
		button.setToolTipText("Open config properties file");
		button.setActionCommand("OpnCfgFile");
		add(button);
		
		JLabel lblCfgFile = new JLabel("Selected Config File:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblCfgFile, 3, SpringLayout.NORTH, button);
		currentLayout.putConstraint(SpringLayout.WEST, lblCfgFile, 0, SpringLayout.WEST, lblSrcFileLoc);
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
		btnTransData.addActionListener(this);
		btnTransData.setToolTipText("Transform the test data to create RiBett input file.");
		btnTransData.setActionCommand("TransTestData");
		add(btnTransData);
		
		JLabel lblTestDataGenerator = new JLabel("Test Data Generator");
		lblTestDataGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		currentLayout.putConstraint(SpringLayout.NORTH, lblTestDataGenerator, 10, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, lblTestDataGenerator, 291, SpringLayout.WEST, this);
		lblTestDataGenerator.setToolTipText("");
		lblTestDataGenerator.setForeground(Color.BLACK);
		lblTestDataGenerator.setFont(new Font("Chiller", Font.BOLD, 40));
		add(lblTestDataGenerator);
		
		JLabel lblMaxCycles = new JLabel("Max Cycles:");
		lblMaxCycles.setToolTipText("Specify the maximum cycles for the solver");
		currentLayout.putConstraint(SpringLayout.NORTH, lblMaxCycles, 14, SpringLayout.SOUTH, lblThreads);
		currentLayout.putConstraint(SpringLayout.WEST, lblMaxCycles, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblMaxCycles.setForeground(Color.BLACK);
		lblMaxCycles.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblMaxCycles);
		
		JLabel lblFirstCycle = new JLabel("First Cycle:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblFirstCycle, 13, SpringLayout.SOUTH, lblMaxCycles);
		currentLayout.putConstraint(SpringLayout.WEST, lblFirstCycle, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblFirstCycle.setToolTipText("Specify the first cycle for the solver");
		lblFirstCycle.setForeground(Color.BLACK);
		lblFirstCycle.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFirstCycle);
		
		JLabel lblInpZ3File = new JLabel("Z3 Input File Location:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblInpZ3File, 47, SpringLayout.SOUTH, lblFirstCycle);
		currentLayout.putConstraint(SpringLayout.WEST, lblInpZ3File, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInpZ3File.setToolTipText("Specify the Z3 Input File Location");
		lblInpZ3File.setForeground(Color.BLACK);
		lblInpZ3File.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInpZ3File);
		
		JLabel lblZ3OutFile = new JLabel("Z3 Output File Location:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblZ3OutFile, 18, SpringLayout.SOUTH, lblInpZ3File);
		currentLayout.putConstraint(SpringLayout.WEST, lblZ3OutFile, 10, SpringLayout.WEST, this);
		lblZ3OutFile.setToolTipText("Specify the Z3 Output File Location");
		lblZ3OutFile.setForeground(Color.BLACK);
		lblZ3OutFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblZ3OutFile);
		
		JLabel lblInpTestDataFile = new JLabel("Test Data File Location:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblInpTestDataFile, 15, SpringLayout.SOUTH, lblZ3OutFile);
		currentLayout.putConstraint(SpringLayout.WEST, lblInpTestDataFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInpTestDataFile.setToolTipText("Specify the Test Data File Location");
		lblInpTestDataFile.setForeground(Color.BLACK);
		lblInpTestDataFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInpTestDataFile);
		
		JLabel lblNewGblVarFile = new JLabel("New Global Var File Location:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblNewGblVarFile, 20, SpringLayout.SOUTH, lblInpTestDataFile);
		currentLayout.putConstraint(SpringLayout.WEST, lblNewGblVarFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblNewGblVarFile.setToolTipText("Specify the File Location for New Global Variables");
		lblNewGblVarFile.setForeground(Color.BLACK);
		lblNewGblVarFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewGblVarFile);
		
		txtMaxCycles = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtMaxCycles, -2, SpringLayout.NORTH, lblMaxCycles);
		currentLayout.putConstraint(SpringLayout.WEST, txtMaxCycles, 0, SpringLayout.WEST, txtSrcFileLoc);
		txtMaxCycles.setColumns(5);
		add(txtMaxCycles);
		
		txtFirstCycle = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtFirstCycle, 0, SpringLayout.NORTH, lblFirstCycle);
		currentLayout.putConstraint(SpringLayout.WEST, txtFirstCycle, 0, SpringLayout.WEST, txtSrcFileLoc);
		txtFirstCycle.setColumns(5);
		add(txtFirstCycle);
		
		txtZ3InpFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtZ3InpFileLoc, -2, SpringLayout.NORTH, lblInpZ3File);
		currentLayout.putConstraint(SpringLayout.WEST, txtZ3InpFileLoc, 231, SpringLayout.WEST, this);
		currentLayout.putConstraint(SpringLayout.EAST, txtZ3InpFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		add(txtZ3InpFileLoc);
		txtZ3InpFileLoc.setColumns(250);
		
		txtTestDataFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtTestDataFileLoc, -2, SpringLayout.NORTH, lblInpTestDataFile);
		currentLayout.putConstraint(SpringLayout.WEST, txtTestDataFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtTestDataFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		add(txtTestDataFileLoc);
		txtTestDataFileLoc.setColumns(255);
		
		txtNewGblVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtNewGblVarFileLoc, -2, SpringLayout.NORTH, lblNewGblVarFile);
		currentLayout.putConstraint(SpringLayout.WEST, txtNewGblVarFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtNewGblVarFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		add(txtNewGblVarFileLoc);
		txtNewGblVarFileLoc.setColumns(255);
		
		txtZ3OutFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtZ3OutFileLoc, -2, SpringLayout.NORTH, lblZ3OutFile);
		currentLayout.putConstraint(SpringLayout.WEST, txtZ3OutFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtZ3OutFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		add(txtZ3OutFileLoc);
		txtZ3OutFileLoc.setColumns(250);
		
		JLabel lblTargetFileLoc = new JLabel("Target State File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblTargetFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblTargetFileLoc.setToolTipText("Specify the Target State File Location");
		lblTargetFileLoc.setForeground(Color.BLACK);
		lblTargetFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTargetFileLoc);
		
		txtTargetFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, lblTargetFileLoc, 2, SpringLayout.NORTH, txtTargetFileLoc);
		currentLayout.putConstraint(SpringLayout.NORTH, txtTargetFileLoc, 6, SpringLayout.SOUTH, txtFirstCycle);
		currentLayout.putConstraint(SpringLayout.WEST, txtTargetFileLoc, 0, SpringLayout.WEST, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtTargetFileLoc, 0, SpringLayout.EAST, txtSrcFileLoc);
		add(txtTargetFileLoc);
		txtTargetFileLoc.setColumns(250);
		
		
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
		      genInpFiles.generateGlobalVarFile(this.txtSrcFileLoc.getText(),this.txtGblVarFileLoc.getText());
		      genInpFiles.generateInputVarFile(this.txtSrcFileLoc.getText(),this.txtInVarFileLoc.getText());
		      genInpFiles.generateFunctionFile(this.txtSrcFileLoc.getText(),this.txtFuncFileLoc.getText());
		    }
		    else if ("TransTestData".equals(e.getActionCommand())) {
			      System.out.println("Transform Test Data button selected");
			      trnTestData.TransformData();
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
			      System.out.println("Generate Test Data button selected" + " " + txtConfigFile.getText());
			      if(!txtConfigFile.getText().equals("")) {
			    	  try {
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
			    	  catch (Exception tde)
			    	  {
			    	      JOptionPane.showMessageDialog(null, tde.toString(), "Error",
			    	                                      JOptionPane.ERROR_MESSAGE);
			    	  }
			      }
			      else {
			    	  JOptionPane.showMessageDialog(null, "Please Provide Config File Location");
			      }
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
			    		  this.txtMaxCycles.setText(mCfgWin.getMaxCycles());
			    		  this.txtFirstCycle.setText(mCfgWin.getFirstCycle());
			    		  this.txtTargetFileLoc.setText(mCfgWin.getTargetStateFile());
			    		  this.txtTestDataFileLoc.setText(mCfgWin.getTestDataFile());
			    		  this.txtZ3InpFileLoc.setText(mCfgWin.getZ3InputFile());
			    		  this.txtZ3OutFileLoc.setText(mCfgWin.getZ3Outputfile());
			    		  this.txtNewGblVarFileLoc.setText(mCfgWin.getNewGlobalVarFile());
			    		  
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
			    	  mCfgWin.setMaxCycles(this.txtMaxCycles.getText());
			    	  mCfgWin.setFirstCycle(this.txtFirstCycle.getText());
			    	  mCfgWin.setTargetStateFile(this.txtTargetFileLoc.getText());
			    	  mCfgWin.setTestDataFile(this.txtTestDataFileLoc.getText());
			    	  mCfgWin.setZ3InputFile(this.txtZ3InpFileLoc.getText());
			    	  mCfgWin.setZ3Outputfile(this.txtZ3OutFileLoc.getText());
			    	  mCfgWin.setNewGlobalVarFile(this.txtNewGblVarFileLoc.getText());
			    	  
			    	  mCfgWin.saveProperties();
			    	  mCfgWin.loadProperties(new File(this.txtConfigFile.getText()));
		    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
		    		  this.txtGblVarFileLoc.setText(mCfgWin.getGlobalVarFileLoc());
		    		  this.txtInVarFileLoc.setText(mCfgWin.getInputVarFileLoc());
		    		  this.txtFuncFileLoc.setText(mCfgWin.getFunctionFileLoc());
		    		  this.txtRepFileLoc.setText(mCfgWin.getRepFileLoc());
		    		  this.txtNbThreads.setText(mCfgWin.getNbThreads());
		    		  this.txtMaxCycles.setText(mCfgWin.getMaxCycles());
		    		  this.txtFirstCycle.setText(mCfgWin.getFirstCycle());
		    		  this.txtTargetFileLoc.setText(mCfgWin.getTargetStateFile());
		    		  this.txtTestDataFileLoc.setText(mCfgWin.getTestDataFile());
		    		  this.txtZ3InpFileLoc.setText(mCfgWin.getZ3InputFile());
		    		  this.txtZ3OutFileLoc.setText(mCfgWin.getZ3Outputfile());
		    		  this.txtNewGblVarFileLoc.setText(mCfgWin.getNewGlobalVarFile());
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

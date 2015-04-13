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
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.Color;

public class TDG_Panel extends JPanel implements ActionListener{
	
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
	private JTextField txtTransTestDataFileLoc;
	private JTextField txtModSrcFileLoc;
	private JTextField txtRingName;
	
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
		this.add(lblSrcFileLoc);
		
		JLabel lblGblVarFileLoc = new JLabel("Global Variable File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblSrcFileLoc, 0, SpringLayout.WEST, lblGblVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblGblVarFileLoc, 10, SpringLayout.WEST, this);
		lblGblVarFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		lblGblVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblGblVarFileLoc);
		
		JLabel lblInVarFileLoc = new JLabel("Input Variable File Location:");
		currentLayout.putConstraint(SpringLayout.SOUTH, lblGblVarFileLoc, -17, SpringLayout.NORTH, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, lblInVarFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInVarFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		lblInVarFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInVarFileLoc);
		
		JLabel lblFuncFileLoc = new JLabel("Function File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblFuncFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblFuncFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		lblFuncFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFuncFileLoc);
		
		JLabel lblRepFileLoc = new JLabel("Representation File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblRepFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblRepFileLoc.setForeground(UIManager.getColor("Label.foreground"));
		lblRepFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblRepFileLoc);
		
		JLabel lblThreads = new JLabel("Number of Threads:");
		currentLayout.putConstraint(SpringLayout.WEST, lblThreads, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblThreads.setForeground(UIManager.getColor("Label.foreground"));
		lblThreads.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblThreads);
		
		txtSrcFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, lblSrcFileLoc, 2, SpringLayout.NORTH, txtSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtSrcFileLoc, 231, SpringLayout.WEST, this);
		add(txtSrcFileLoc);
		txtSrcFileLoc.setColumns(250);
		
		txtGblVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.EAST, txtSrcFileLoc, 0, SpringLayout.EAST, txtGblVarFileLoc);
		currentLayout.putConstraint(SpringLayout.WEST, txtGblVarFileLoc, 48, SpringLayout.EAST, lblGblVarFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtGblVarFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, txtGblVarFileLoc, -2, SpringLayout.NORTH, lblGblVarFileLoc);
		txtGblVarFileLoc.setColumns(250);
		add(txtGblVarFileLoc);
		
		txtInVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtInVarFileLoc, 50, SpringLayout.EAST, lblInVarFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtInVarFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblInVarFileLoc, 2, SpringLayout.NORTH, txtInVarFileLoc);
		txtInVarFileLoc.setColumns(250);
		add(txtInVarFileLoc);
		
		txtFuncFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtFuncFileLoc, 84, SpringLayout.EAST, lblFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtFuncFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblFuncFileLoc, 2, SpringLayout.NORTH, txtFuncFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtInVarFileLoc, -12, SpringLayout.NORTH, txtFuncFileLoc);
		txtFuncFileLoc.setColumns(250);
		add(txtFuncFileLoc);
		
		txtRepFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtRepFileLoc, 272, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, txtRepFileLoc, 42, SpringLayout.EAST, lblRepFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtRepFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblRepFileLoc, 2, SpringLayout.NORTH, txtRepFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtFuncFileLoc, -9, SpringLayout.NORTH, txtRepFileLoc);
		txtRepFileLoc.setColumns(250);
		add(txtRepFileLoc);
		
		txtNbThreads = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, lblThreads, 2, SpringLayout.NORTH, txtNbThreads);
		txtNbThreads.setText("10");
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
		currentLayout.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, this);
		currentLayout.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, btnGenInFiles, 6, SpringLayout.EAST, btnSave);
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
		currentLayout.putConstraint(SpringLayout.WEST, lblCfgFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, lblCfgFile, -22, SpringLayout.NORTH, lblSrcFileLoc);
		lblCfgFile.setToolTipText("Location of the config file ");
		lblCfgFile.setForeground(UIManager.getColor("Label.foreground"));
		lblCfgFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCfgFile);
		
		txtConfigFile = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtConfigFile, 98, SpringLayout.EAST, lblCfgFile);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtConfigFile, -17, SpringLayout.NORTH, txtSrcFileLoc);
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
		currentLayout.putConstraint(SpringLayout.NORTH, lblTestDataGenerator, 10, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, lblTestDataGenerator, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblTestDataGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestDataGenerator.setToolTipText("");
		lblTestDataGenerator.setForeground(Color.BLACK);
		lblTestDataGenerator.setFont(new Font("Castellar", Font.BOLD, 40));
		add(lblTestDataGenerator);
		
		JLabel lblMaxCycles = new JLabel("Max Cycles:");
		currentLayout.putConstraint(SpringLayout.WEST, lblMaxCycles, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblMaxCycles.setToolTipText("Specify the maximum cycles for the solver");
		lblMaxCycles.setForeground(Color.BLACK);
		lblMaxCycles.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblMaxCycles);
		
		JLabel lblFirstCycle = new JLabel("First Cycle:");
		currentLayout.putConstraint(SpringLayout.WEST, lblFirstCycle, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblFirstCycle.setToolTipText("Specify the first cycle for the solver");
		lblFirstCycle.setForeground(Color.BLACK);
		lblFirstCycle.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFirstCycle);
		
		JLabel lblInpZ3File = new JLabel("Z3 Input File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblInpZ3File, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInpZ3File.setToolTipText("Specify the Z3 Input File Location");
		lblInpZ3File.setForeground(Color.BLACK);
		lblInpZ3File.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInpZ3File);
		
		JLabel lblZ3OutFile = new JLabel("Z3 Output File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblZ3OutFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblZ3OutFile.setToolTipText("Specify the Z3 Output File Location");
		lblZ3OutFile.setForeground(Color.BLACK);
		lblZ3OutFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblZ3OutFile);
		
		JLabel lblInpTestDataFile = new JLabel("Test Data File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblInpTestDataFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblInpTestDataFile.setToolTipText("Specify the Test Data File Location");
		lblInpTestDataFile.setForeground(Color.BLACK);
		lblInpTestDataFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblInpTestDataFile);
		
		JLabel lblNewGblVarFile = new JLabel("New Global Var File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblNewGblVarFile, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblNewGblVarFile.setToolTipText("Specify the File Location for New Global Variables");
		lblNewGblVarFile.setForeground(Color.BLACK);
		lblNewGblVarFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewGblVarFile);
		
		txtMaxCycles = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtMaxCycles, 151, SpringLayout.EAST, lblMaxCycles);
		currentLayout.putConstraint(SpringLayout.NORTH, lblMaxCycles, 2, SpringLayout.NORTH, txtMaxCycles);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtNbThreads, -10, SpringLayout.NORTH, txtMaxCycles);
		currentLayout.putConstraint(SpringLayout.EAST, txtNbThreads, 0, SpringLayout.EAST, txtMaxCycles);
		txtMaxCycles.setText("15");
		txtMaxCycles.setColumns(5);
		add(txtMaxCycles);
		
		txtFirstCycle = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtFirstCycle, 363, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, txtFirstCycle, 156, SpringLayout.EAST, lblFirstCycle);
		currentLayout.putConstraint(SpringLayout.NORTH, lblFirstCycle, 2, SpringLayout.NORTH, txtFirstCycle);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtMaxCycles, -10, SpringLayout.NORTH, txtFirstCycle);
		txtFirstCycle.setText("0");
		txtFirstCycle.setColumns(5);
		add(txtFirstCycle);
		
		txtZ3InpFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtZ3InpFileLoc, 83, SpringLayout.EAST, lblInpZ3File);
		currentLayout.putConstraint(SpringLayout.EAST, txtZ3InpFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblInpZ3File, 2, SpringLayout.NORTH, txtZ3InpFileLoc);
		add(txtZ3InpFileLoc);
		txtZ3InpFileLoc.setColumns(250);
		
		txtTestDataFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtTestDataFileLoc, 78, SpringLayout.EAST, lblInpTestDataFile);
		currentLayout.putConstraint(SpringLayout.EAST, txtTestDataFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblInpTestDataFile, 2, SpringLayout.NORTH, txtTestDataFileLoc);
		add(txtTestDataFileLoc);
		txtTestDataFileLoc.setColumns(250);
		
		txtNewGblVarFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.WEST, txtNewGblVarFileLoc, 46, SpringLayout.EAST, lblNewGblVarFile);
		currentLayout.putConstraint(SpringLayout.EAST, txtNewGblVarFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblNewGblVarFile, 2, SpringLayout.NORTH, txtNewGblVarFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtTestDataFileLoc, -9, SpringLayout.NORTH, txtNewGblVarFileLoc);
		add(txtNewGblVarFileLoc);
		txtNewGblVarFileLoc.setColumns(250);
		
		txtZ3OutFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtZ3OutFileLoc, 460, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, txtZ3OutFileLoc, 73, SpringLayout.EAST, lblZ3OutFile);
		currentLayout.putConstraint(SpringLayout.EAST, txtZ3OutFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblZ3OutFile, 2, SpringLayout.NORTH, txtZ3OutFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtZ3InpFileLoc, -12, SpringLayout.NORTH, txtZ3OutFileLoc);
		add(txtZ3OutFileLoc);
		txtZ3OutFileLoc.setColumns(250);
		
		JLabel lblTargetFileLoc = new JLabel("Target State File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblTargetFileLoc, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblTargetFileLoc.setToolTipText("Specify the Target State File Location");
		lblTargetFileLoc.setForeground(Color.BLACK);
		lblTargetFileLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTargetFileLoc);
		
		txtTargetFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtTargetFileLoc, 12, SpringLayout.SOUTH, txtFirstCycle);
		currentLayout.putConstraint(SpringLayout.WEST, txtTargetFileLoc, 59, SpringLayout.EAST, lblTargetFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, txtTargetFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblTargetFileLoc, 2, SpringLayout.NORTH, txtTargetFileLoc);
		add(txtTargetFileLoc);
		txtTargetFileLoc.setColumns(250);
		
		JLabel lblTransTestData = new JLabel("Transformed Test Data File:");
		currentLayout.putConstraint(SpringLayout.WEST, lblTransTestData, 0, SpringLayout.WEST, lblSrcFileLoc);
		lblTransTestData.setToolTipText("Specify the Test Data File Location");
		lblTransTestData.setForeground(Color.BLACK);
		lblTransTestData.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTransTestData);
		
		txtTransTestDataFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.NORTH, txtTransTestDataFileLoc, 558, SpringLayout.NORTH, this);
		currentLayout.putConstraint(SpringLayout.WEST, txtTransTestDataFileLoc, 54, SpringLayout.EAST, lblTransTestData);
		currentLayout.putConstraint(SpringLayout.EAST, txtTransTestDataFileLoc, -343, SpringLayout.EAST, this);
		currentLayout.putConstraint(SpringLayout.NORTH, lblTransTestData, 2, SpringLayout.NORTH, txtTransTestDataFileLoc);
		currentLayout.putConstraint(SpringLayout.SOUTH, txtNewGblVarFileLoc, -13, SpringLayout.NORTH, txtTransTestDataFileLoc);
		txtTransTestDataFileLoc.setToolTipText("Location for the transformed test data file.");
		txtTransTestDataFileLoc.setColumns(250);
		add(txtTransTestDataFileLoc);
		
		JButton btnOpenCFile = new JButton("Open Source C File");
		currentLayout.putConstraint(SpringLayout.NORTH, btnOpenCFile, -3, SpringLayout.NORTH, lblSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.EAST, btnOpenCFile, 0, SpringLayout.EAST, btnExit);
		btnOpenCFile.setToolTipText("Open Source C File");
		btnOpenCFile.setActionCommand("OpnCFile");
		btnOpenCFile.addActionListener(this);
		add(btnOpenCFile);
		
		JLabel lblModSrcFile = new JLabel("Modified Source File Location:");
		currentLayout.putConstraint(SpringLayout.WEST, lblModSrcFile, 10, SpringLayout.WEST, this);
		currentLayout.putConstraint(SpringLayout.SOUTH, lblModSrcFile, -17, SpringLayout.NORTH, lblGblVarFileLoc);
		lblModSrcFile.setToolTipText("Location of the modified source code C file ");
		lblModSrcFile.setForeground(Color.BLACK);
		lblModSrcFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblModSrcFile);
		
		txtModSrcFileLoc = new JTextField();
		currentLayout.putConstraint(SpringLayout.SOUTH, txtSrcFileLoc, -15, SpringLayout.NORTH, txtModSrcFileLoc);
		currentLayout.putConstraint(SpringLayout.NORTH, txtModSrcFileLoc, -2, SpringLayout.NORTH, lblModSrcFile);
		currentLayout.putConstraint(SpringLayout.WEST, txtModSrcFileLoc, 38, SpringLayout.EAST, lblModSrcFile);
		currentLayout.putConstraint(SpringLayout.EAST, txtModSrcFileLoc, -343, SpringLayout.EAST, this);
		txtModSrcFileLoc.setColumns(250);
		add(txtModSrcFileLoc);
		
		JLabel lblRingName = new JLabel("Ring Name:");
		currentLayout.putConstraint(SpringLayout.NORTH, lblRingName, 0, SpringLayout.NORTH, lblModSrcFile);
		currentLayout.putConstraint(SpringLayout.WEST, lblRingName, 0, SpringLayout.WEST, btnOpenCFile);
		lblRingName.setToolTipText("Three Character Acronym of the GM Ring Software");
		lblRingName.setForeground(Color.BLACK);
		lblRingName.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblRingName);
		
		txtRingName = new JTextField();
		txtRingName.setToolTipText("Three Character Acronym of the GM Ring Software");
		currentLayout.putConstraint(SpringLayout.NORTH, txtRingName, -2, SpringLayout.NORTH, lblModSrcFile);
		currentLayout.putConstraint(SpringLayout.EAST, txtRingName, 0, SpringLayout.EAST, btnExit);
		txtRingName.setColumns(3);
		add(txtRingName);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    Process process;
	    ProcessBuilder pb;
	    BufferedReader reader;
	    List<String> params;
	    String line;
		JFileChooser fileChooser;
		FileNameExtensionFilter filter;
  	  	int value;
		
		
		try {
	    
			if("exit".equals(e.getActionCommand())) {
				SwingUtilities.getWindowAncestor(this).dispose();
				System.exit(0);
		    } 
		    else if ("GenInputFiles".equals(e.getActionCommand())) {
		    	System.out.println("Generate Input Files Process...Started");
		    	String srcfp = this.txtSrcFileLoc.getText();
		    	genInpFiles.genFiles(srcfp.substring(0, srcfp.lastIndexOf("\\Software")),
		    			this.txtGblVarFileLoc.getText(), this.txtInVarFileLoc.getText(),
		    			this.txtFuncFileLoc.getText(), this.txtRingName.getText(), srcfp);
		    	System.out.println("Generate Input Files Process...Completed");
		    }
		    else if ("TransTestData".equals(e.getActionCommand())) {
		    	System.out.println("Transform Test Data Process...Started");
			    trnTestData.TransformData(this.txtTestDataFileLoc.getText(), this.txtTransTestDataFileLoc.getText(), 
			    		this.txtFirstCycle.getText());
			    System.out.println("Transform Test Data Process...Completed");
			}
		    else if ("GenRepFile".equals(e.getActionCommand())) {
		    	
		    	if (!this.txtRepFileLoc.getText().isEmpty() && !this.txtConfigFile.getText().isEmpty()){
		    		System.out.println("Generate Representation File Process...Started");
			    	params = java.util.Arrays.asList("java", "-jar", "-Xmx8192m", ".\\TDG_lib\\ComputeChunks.jar ", txtConfigFile.getText());
			    	pb = new ProcessBuilder(params);
			    	pb.redirectErrorStream(true);
			    	process = pb.start();
			    	reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			    	while ((line = reader.readLine()) != null)
			    		System.out.println("tasklist: " + line);
			    	process.waitFor();
			    	System.out.println("Generate Representation File Process...Completed");
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(null, "Please Provide Config File and Represenation File Location");
		    	}
			}
		    else if ("GenTestData".equals(e.getActionCommand())) {
			      if(!txtConfigFile.getText().equals("")) {
			    	  try {
			    		  System.out.println("Generate Test Data Process...Started");
					      params = java.util.Arrays.asList("java", "-jar", "-Xmx8192m", ".\\TDG_lib\\GenerateInputs.jar ", txtConfigFile.getText());
					      pb = new ProcessBuilder(params);
					      pb.redirectErrorStream(true);
					      process = pb.start();
					      reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					      while ((line = reader.readLine()) != null)
					          System.out.println("tasklist: " + line);
					      process.waitFor();
					      System.out.println("Generate Test Data Process...Completed");
			    	  }
			    	  catch (Exception tde)
			    	  {
			    	      JOptionPane.showMessageDialog(null, tde.toString(), "Error",
			    	                                      JOptionPane.ERROR_MESSAGE);
			    	  }
			      }
			      else {
			    	  JOptionPane.showMessageDialog(null, "Please Provide Location of Config File that has all the parameter values");
			      }
			}
		    else if ("OpnCfgFile".equals(e.getActionCommand())) {
			      try {
			    	  fileChooser = new JFileChooser(".");
			    	  fileChooser.setAcceptAllFileFilterUsed(false);
			    	  filter = new FileNameExtensionFilter("Properties","properties");
			    	  fileChooser.setFileFilter(filter);
			    	  value = fileChooser.showOpenDialog(this);
			    	  if(value == JFileChooser.APPROVE_OPTION){

			    		  txtConfigFile.setText(fileChooser.getSelectedFile().getPath());
			    		  mCfgWin.loadProperties(fileChooser.getSelectedFile());
			    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
			    		  this.txtModSrcFileLoc.setText(mCfgWin.getModCFileLoc());
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
			    		  this.txtTransTestDataFileLoc.setText(mCfgWin.getTransTestDataFile());
			    		  this.txtRingName.setText(mCfgWin.getRingName());
			    		  
			    	  }
			      }catch (Exception ofe) {
			    	  System.out.println("Open File Exception Occured");
			      }
		    }
		    else if ("OpnCFile".equals(e.getActionCommand())) {
			      try {
			    	  fileChooser = new JFileChooser(".");
			    	  fileChooser.setAcceptAllFileFilterUsed(false);
			    	  filter = new FileNameExtensionFilter("C","c");
			    	  fileChooser.setFileFilter(filter);
			    	  value = fileChooser.showOpenDialog(this);
			    	  if(value == JFileChooser.APPROVE_OPTION){
			    		  
			    		  String srcFile = fileChooser.getSelectedFile().getPath();
			    		  txtSrcFileLoc.setText(srcFile);
			    		  //String path = srcFile.substring(0, srcFile.lastIndexOf(File.separator));
			    		  String path = srcFile.substring(0, srcFile.lastIndexOf("\\Software"));
			    		  mCfgWin.setConfigFile(path+ "\\" + "config.properties");
				    	  mCfgWin.setCFileLoc(this.txtSrcFileLoc.getText());
				    	  mCfgWin.setModCFileLoc(path+ "\\" + "ModifiedSource.c");
				    	  mCfgWin.setGlobalVarFile(path+ "\\" + "GlobalVar.csv");
				    	  mCfgWin.setInputvarFileLoc(path+ "\\" + "InputVar.csv");
				    	  mCfgWin.setFunctionFileLoc(path+ "\\" + "Functions.csv");
				    	  mCfgWin.setRepFileLoc(path+ "\\" + "RefFile_Chunks.ser");
				    	  mCfgWin.setNbThreads(this.txtNbThreads.getText());
				    	  mCfgWin.setMaxCycles(this.txtMaxCycles.getText());
				    	  mCfgWin.setFirstCycle(this.txtFirstCycle.getText());
				    	  mCfgWin.setRingName(this.txtRingName.getText());
				    	  mCfgWin.setTargetStateFile(path+ "\\" + "Target.csv");
				    	  mCfgWin.setTestDataFile(path+ "\\" + "InputTestData.txt");
				    	  mCfgWin.setZ3InputFile(path+ "\\" + "Z3Input.txt");
				    	  mCfgWin.setZ3Outputfile(path+ "\\" + "Z3Output.txt");
				    	  mCfgWin.setNewGlobalVarFile(path+ "\\" + "PrevGlobalVar.csv");
				    	  mCfgWin.setTransTestDataFile(path+ "\\" + "TransformedInputFile.csv");
				    	  
				    	  mCfgWin.saveProperties();
				    	  mCfgWin.loadProperties(new File(mCfgWin.getConfigFileLoc()));
				    	  this.txtConfigFile.setText(mCfgWin.getConfigFileLoc());
			    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
			    		  this.txtModSrcFileLoc.setText(mCfgWin.getModCFileLoc());
			    		  this.txtGblVarFileLoc.setText(mCfgWin.getGlobalVarFileLoc());
			    		  this.txtInVarFileLoc.setText(mCfgWin.getInputVarFileLoc());
			    		  this.txtFuncFileLoc.setText(mCfgWin.getFunctionFileLoc());
			    		  this.txtRepFileLoc.setText(mCfgWin.getRepFileLoc());
			    		  this.txtNbThreads.setText(mCfgWin.getNbThreads());
			    		  this.txtMaxCycles.setText(mCfgWin.getMaxCycles());
			    		  this.txtFirstCycle.setText(mCfgWin.getFirstCycle());
			    		  this.txtRingName.setText(mCfgWin.getRingName());
			    		  this.txtTargetFileLoc.setText(mCfgWin.getTargetStateFile());
			    		  this.txtTestDataFileLoc.setText(mCfgWin.getTestDataFile());
			    		  this.txtZ3InpFileLoc.setText(mCfgWin.getZ3InputFile());
			    		  this.txtZ3OutFileLoc.setText(mCfgWin.getZ3Outputfile());
			    		  this.txtNewGblVarFileLoc.setText(mCfgWin.getNewGlobalVarFile());
			    		  this.txtTransTestDataFileLoc.setText(mCfgWin.getTransTestDataFile());
			    	  }
			      }catch (Exception ofe) {
			    	  System.out.println("Open File Exception Occured");
			      }
		    }		
		    else if ("Save".equals(e.getActionCommand())) {
			      try {
			    	  mCfgWin.setConfigFile(this.txtConfigFile.getText());
			    	  mCfgWin.setCFileLoc(this.txtSrcFileLoc.getText());
			    	  mCfgWin.setModCFileLoc(this.txtModSrcFileLoc.getText());
			    	  mCfgWin.setGlobalVarFile(this.txtGblVarFileLoc.getText());
			    	  mCfgWin.setInputvarFileLoc(this.txtInVarFileLoc.getText());
			    	  mCfgWin.setFunctionFileLoc(this.txtFuncFileLoc.getText());
			    	  mCfgWin.setRepFileLoc(this.txtRepFileLoc.getText());
			    	  mCfgWin.setNbThreads(this.txtNbThreads.getText());
			    	  mCfgWin.setMaxCycles(this.txtMaxCycles.getText());
			    	  mCfgWin.setFirstCycle(this.txtFirstCycle.getText());
			    	  mCfgWin.setRingName(this.txtRingName.getText());
			    	  mCfgWin.setTargetStateFile(this.txtTargetFileLoc.getText());
			    	  mCfgWin.setTestDataFile(this.txtTestDataFileLoc.getText());
			    	  mCfgWin.setZ3InputFile(this.txtZ3InpFileLoc.getText());
			    	  mCfgWin.setZ3Outputfile(this.txtZ3OutFileLoc.getText());
			    	  mCfgWin.setNewGlobalVarFile(this.txtNewGblVarFileLoc.getText());
			    	  mCfgWin.setTransTestDataFile(this.txtTransTestDataFileLoc.getText());
			    	  
			    	  mCfgWin.saveProperties();
			    	  mCfgWin.loadProperties(new File(this.txtConfigFile.getText()));
		    		  this.txtSrcFileLoc.setText(mCfgWin.getCFileLoc());
		    		  this.txtModSrcFileLoc.setText(mCfgWin.getModCFileLoc());
		    		  this.txtGblVarFileLoc.setText(mCfgWin.getGlobalVarFileLoc());
		    		  this.txtInVarFileLoc.setText(mCfgWin.getInputVarFileLoc());
		    		  this.txtFuncFileLoc.setText(mCfgWin.getFunctionFileLoc());
		    		  this.txtRepFileLoc.setText(mCfgWin.getRepFileLoc());
		    		  this.txtNbThreads.setText(mCfgWin.getNbThreads());
		    		  this.txtMaxCycles.setText(mCfgWin.getMaxCycles());
		    		  this.txtFirstCycle.setText(mCfgWin.getFirstCycle());
		    		  this.txtRingName.setText(mCfgWin.getRingName());
		    		  this.txtTargetFileLoc.setText(mCfgWin.getTargetStateFile());
		    		  this.txtTestDataFileLoc.setText(mCfgWin.getTestDataFile());
		    		  this.txtZ3InpFileLoc.setText(mCfgWin.getZ3InputFile());
		    		  this.txtZ3OutFileLoc.setText(mCfgWin.getZ3Outputfile());
		    		  this.txtNewGblVarFileLoc.setText(mCfgWin.getNewGlobalVarFile());
		    		  this.txtTransTestDataFileLoc.setText(mCfgWin.getTransTestDataFile());
		    		 
			      }catch (Exception sfe) {
			    	  System.out.println("Save File Exception Occured");
			      }     
			}
	    }catch (Exception ie){
	    	System.out.println("IO Exception Occured");
	    }
	  }
}

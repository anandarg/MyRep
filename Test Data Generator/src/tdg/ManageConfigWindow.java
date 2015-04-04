package tdg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;


public class ManageConfigWindow {

	String filename = new String("");
	File cfgFile;
	StringBuffer buffer = new StringBuffer("");
	String confFile = new String("");
	Properties ConfigProperties;
	String cFile = new  String("");
	String modCFile = new String("");
	String functionFile = new String("");
	String globalVarFile = new String("");
	String inputVarFile = new String("");
	String repFile = new String("");
	String nbThreads = new String("");
	String maxCycles = new  String("");
	String firstCycle = new String("");
	String targetStateFile = new String("");
	String z3InputFile = new String("");
	String z3Outputfile = new String("");
	String testDataFile = new String("");
	String newGlobalVarFile = new String("");
	String transTestDataFile = new String("");
	String ringName = new String("");
	
	public ManageConfigWindow(){
		
	}
	
	
	public void loadProperties(File file)
	{
		confFile=file.getAbsolutePath();
		System.out.println("Configuration file set to " + confFile);
		ConfigProperties = new Properties();
		try {
			FileInputStream in = new FileInputStream(confFile);
			ConfigProperties.load(in);
			cFile = ConfigProperties.getProperty("OrigCfile");
			modCFile = ConfigProperties.getProperty("Cfile");
			if (modCFile == null){
				modCFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "ModifiedSource.c");
			}
			else if (modCFile.isEmpty()) {
				modCFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "ModifiedSource.c";
			}			
			globalVarFile = ConfigProperties.getProperty("GlobalVarFile");
			if (globalVarFile == null){
				globalVarFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "GlobalVar.csv");
			}
			else if (globalVarFile.isEmpty()) {
				globalVarFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "GlobalVar.csv";
			}
			
			inputVarFile = ConfigProperties.getProperty("InputVarFile");
			if (inputVarFile == null) {
				inputVarFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "InputVar.csv");
			}
			else if (inputVarFile.isEmpty()) {
				inputVarFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "InputVar.csv";
			}
			
			functionFile = ConfigProperties.getProperty("FunctionFile");
			if (functionFile == null){
				functionFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Functions.csv");
			}
			if (functionFile.isEmpty()) {
				functionFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Functions.csv";
			}
			
			transTestDataFile = ConfigProperties.getProperty("TransformedInputFile");
			if (transTestDataFile == null){
				transTestDataFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "TransformedInputFile.csv");
			}
			else if (transTestDataFile.isEmpty()) {
				transTestDataFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "TransformedInputFile.csv";
			}
			
			repFile = ConfigProperties.getProperty("SerializationFileNoDNF");
			if (repFile == null){
				repFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "RepFile_Chunks.ser");
			}
			else if (repFile.isEmpty()) {
				repFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "RepFile_Chunks.ser";
			}
			
			nbThreads = ConfigProperties.getProperty("nbThreads");
			maxCycles = ConfigProperties.getProperty("MaxCycle");
			firstCycle = ConfigProperties.getProperty("FirstCycle");
			ringName = ConfigProperties.getProperty("RingName");
			
			targetStateFile = ConfigProperties.getProperty("ConditionClauseFile");
			if (targetStateFile == null){
				targetStateFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Target.csv");
			}
			else if (targetStateFile.isEmpty()) {
				targetStateFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Target.csv";
			}
			
			z3InputFile = ConfigProperties.getProperty("InputForYicesFile");
			if (z3InputFile == null){
				z3InputFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Z3Input.txt");
			}
			else if (z3InputFile.isEmpty()) {
				z3InputFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Z3Input.txt";
			}
			
			z3Outputfile = ConfigProperties.getProperty("Z3OutputFile");
			if (z3Outputfile == null){
				z3Outputfile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Z3Output.txt");
			}
			else if (z3Outputfile.isEmpty()) {
				z3Outputfile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "Z3Output.txt";
			}
			
			testDataFile = ConfigProperties.getProperty("GeneratedInputFile");
			if (testDataFile == null){
				testDataFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "InputTestData.txt");
			}
			else if (testDataFile.isEmpty()) {
				testDataFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "InputTestData.txt";
			}
			
			newGlobalVarFile = ConfigProperties.getProperty("NewGlobalVarFile");
			if (newGlobalVarFile == null){
				newGlobalVarFile = new String(confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "PrevGlobalVar.csv");
			}
			else if (newGlobalVarFile.isEmpty()) {
				newGlobalVarFile = confFile.substring(0, confFile.lastIndexOf(File.separator)) + "\\" + "PrevGlobalVar.csv";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please provide path to a valid Source C file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveProperties(){
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(this.confFile);
	 
			// set the properties value
			if(this.modCFile.isEmpty()) {
				this.modCFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "ModifiedSource.c";
			}
			prop.setProperty("Cfile", this.modCFile);
			prop.setProperty("OrigCfile", this.cFile);
			if(this.globalVarFile.isEmpty()) {
				this.globalVarFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "GlobalVar.csv";
			}
			prop.setProperty("GlobalVarFile", this.globalVarFile);
			if(this.inputVarFile.isEmpty()) {
				this.inputVarFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "InputVar.csv";
			}
			prop.setProperty("InputVarFile", this.inputVarFile);
			
			if(this.functionFile.isEmpty()) {
				this.functionFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "Functions.csv";
			}
			prop.setProperty("FunctionFile", this.functionFile);
			
			if(this.repFile.isEmpty()) {
				this.repFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "RepFile_Chunks.ser";
			}
			prop.setProperty("SerializationFileNoDNF", this.repFile);
			
			prop.setProperty("nbThreads", this.nbThreads);
			prop.setProperty("MaxCycle", this.maxCycles);
			prop.setProperty("FirstCycle", this.firstCycle);
			prop.setProperty("RingName", this.ringName);
			
			if(this.targetStateFile.isEmpty()) {
				this.targetStateFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "Target.csv";
			}
			prop.setProperty("ConditionClauseFile", this.targetStateFile);
			
			if(this.z3InputFile.isEmpty()) {
				this.z3InputFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "Z3Input.txt";
			}
			prop.setProperty("InputForYicesFile", this.z3InputFile);
			
			if(this.z3Outputfile.isEmpty()) {
				this.z3Outputfile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "Z3Output.txt";
			}
			prop.setProperty("Z3OutputFile", this.z3Outputfile);
			
			if(this.testDataFile.isEmpty()) {
				this.testDataFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "InputTestData.txt";
			}
			prop.setProperty("GeneratedInputFile", this.testDataFile);
			
			if(this.newGlobalVarFile.isEmpty()) {
				this.newGlobalVarFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "PrevGlobalVar.csv";
			}
			prop.setProperty("NewGlobalVarFile", this.newGlobalVarFile);
			
			if(this.transTestDataFile.isEmpty()) {
				this.transTestDataFile = cFile.substring(0, cFile.lastIndexOf("\\Software")) + "\\" + "TransformedInputFile.csv";
			}
			prop.setProperty("TransformedInputFile", this.transTestDataFile);

			prop.store(output, null);
			
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	public String getConfigFileLoc(){
		return this.confFile;
	}
	
	public String getCFileLoc(){
		return this.cFile;
	}
	
	public String getModCFileLoc(){
		return this.modCFile;
	}
	
	public String getGlobalVarFileLoc(){
		return this.globalVarFile;
	}
	
	public String getInputVarFileLoc(){
		return this.inputVarFile;
	}
	
	public String getFunctionFileLoc(){
		return this.functionFile;
	}
	
	public String getRepFileLoc(){
		return this.repFile;
	}
	
	public String getNbThreads(){
		return this.nbThreads;
	}
	
	public String getMaxCycles() {
		return this.maxCycles;
	}
	
	public String getFirstCycle() {
		return this.firstCycle;
	}
	
	public String getTargetStateFile() {
		return this.targetStateFile;
	}
	
	public String getZ3InputFile() {
		return this.z3InputFile;
	}
	
	public String getZ3Outputfile() {
		return this.z3Outputfile;
	}
	
	public String getTestDataFile() {
		return this.testDataFile;
	}
	
	public String getNewGlobalVarFile() {
		return this.newGlobalVarFile;
	}
	
	public String getTransTestDataFile(){
		return this.transTestDataFile;
	}
	
	public String getRingName(){
		return this.ringName;
	}
		
	public void setConfigFile(String loc){
		this.confFile = loc;
	}
	
	public void setCFileLoc(String loc){
		this.cFile = loc;
	}
	
	public void setModCFileLoc(String loc){
		this.modCFile = loc;
	}
	
	public void setGlobalVarFile(String loc){
		this.globalVarFile = loc;
	}
	
	public void setInputvarFileLoc(String loc){
		this.inputVarFile = loc;
	}
	
	public void setFunctionFileLoc(String loc){
		this.functionFile = loc;
	}
	
	public void setRepFileLoc(String loc){
		this.repFile = loc;
	}
	
	public void setNbThreads(String threads){
		this.nbThreads = threads;
	}
	
	public void setMaxCycles(String cycles) {
		this.maxCycles=cycles;
	}
	
	public void setFirstCycle(String cycles) {
		this.firstCycle = cycles;
	}
	
	public void setTargetStateFile(String loc) {
		this.targetStateFile = loc;
	}
	
	public void setZ3InputFile(String loc) {
		this.z3InputFile = loc;
	}
	
	public void setZ3Outputfile(String loc) {
		this.z3Outputfile = loc;
	}
	
	public void setTestDataFile(String loc) {
		this.testDataFile = loc;
	}
	
	public void setNewGlobalVarFile(String loc) {
		this.newGlobalVarFile = loc;
	}
	
	public void setTransTestDataFile(String loc){
		this.transTestDataFile = loc;
	}
	
	public void setRingName(String name){
		this.ringName = name;
	}
}

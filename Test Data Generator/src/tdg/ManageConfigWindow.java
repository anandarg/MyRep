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
			cFile = ConfigProperties.getProperty("Cfile");
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
			nbThreads = ConfigProperties.getProperty("nbThreads");
			maxCycles = ConfigProperties.getProperty("MaxCycle");
			firstCycle = ConfigProperties.getProperty("FirstCycle");
			targetStateFile = ConfigProperties.getProperty("ConditionClauseFile");
			z3InputFile = ConfigProperties.getProperty("InputForYicesFile");
			z3Outputfile = ConfigProperties.getProperty("Z3OutputFile");
			testDataFile = ConfigProperties.getProperty("GeneratedInputFile");
			newGlobalVarFile = ConfigProperties.getProperty("NewGlobalVarFile");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please select a valid file");
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
			prop.setProperty("Cfile", cFile);
			prop.setProperty("GlobalVarFile", this.globalVarFile);
			prop.setProperty("InputVarFile", this.inputVarFile);
			prop.setProperty("FunctionFile", this.functionFile);
			prop.setProperty("SerializationFileNoDNF", this.repFile);
			prop.setProperty("nbThreads", this.nbThreads);
			prop.setProperty("ConditionClauseFile", this.targetStateFile);
			prop.setProperty("MaxCycle", this.maxCycles);
			prop.setProperty("FirstCycle", this.firstCycle);
			prop.setProperty("InputForYicesFile", this.z3InputFile);
			prop.setProperty("Z3OutputFile", this.z3Outputfile);
			prop.setProperty("GeneratedInputFile", this.testDataFile);
			prop.setProperty("NewGlobalVarFile", this.newGlobalVarFile);
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
	
	public void setConfigFile(String loc){
		this.confFile = loc;
	}
	
	public void setCFileLoc(String loc){
		this.cFile = loc;
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
}

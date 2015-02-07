package tdg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


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
			inputVarFile = ConfigProperties.getProperty("InputVarFile");
			functionFile = ConfigProperties.getProperty("FunctionFile");
			repFile = ConfigProperties.getProperty("SerializationFileNoDNF");
			nbThreads = ConfigProperties.getProperty("nbThreads");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveProperties(){
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(confFile);
	 
			// set the properties value
			prop.setProperty("Cfile", cFile);
			prop.setProperty("GlobalVarFile", globalVarFile);
			prop.setProperty("InputVarFile", inputVarFile);
			prop.setProperty("FunctionFile", functionFile);
			prop.setProperty("SerializationFileNoDNF", repFile);
			prop.setProperty("nbThreads", nbThreads);
			
			// save properties to project root folder
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
}

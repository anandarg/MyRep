package tdg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ManageConfigWindow {

	String filename = new String("");
	File cfgFile;
	StringBuffer buffer = new StringBuffer("");
	String confFile = new String("");
	Properties ConfigProperties;
	String cFile = new  String("");
	
	public ManageConfigWindow(File fname){
		try {
			cfgFile = fname;
			loadProperties(cfgFile);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	private void loadProperties(File file)
	{
		confFile=file.getAbsolutePath();
		System.out.println("Configuration file set to " + confFile);
		ConfigProperties = new Properties();
		try {
			FileInputStream in = new FileInputStream(confFile);
			ConfigProperties.load(in);
			//functionFile = ConfigProperties.getProperty("FunctionFile");
			//globalVarFile = ConfigProperties.getProperty("GlobalVarFile");
			//calibrationFile = ConfigProperties.getProperty("CalibrationFile");
			//inputVarFile = ConfigProperties.getProperty("InputVarFile");
			//conditionFile = ConfigProperties.getProperty("ConditionClauseFile");
			cFile = ConfigProperties.getProperty("Cfile");
			//currentStateFile = globalVarFile;
			//sourceCodeContent.setText(readFile(cFile));
			// Read global var file
			//globalVarFileContent=crim.analyzer.util.file.State.parseGlobalVarFile(globalVarFile);
			//File globVarFile = new File(globalVarFile);
			//loadCurrentState(globVarFile);
			// Read chunks
			//originalProgram=crim.analyzer.util.file.Summary.loadAllSummaries(ConfigProperties);
			// Reconstruct target from file
			//currentTarget=crim.analyzer.util.file.Target.readTargetFromFile(conditionFile, globalVarFileContent);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getCFileLoc(){
		return this.cFile;
	}
	
}

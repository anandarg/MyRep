package tdg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateInputFiles {

	public GenerateInputFiles(){
		
	}
	
	public void generateGlobalVarFile(String srcfilepath, String filepath){
		System.out.println("Global Var file location is " + filepath);
		File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
     
            writer.append("VarName");
    	    writer.append(';');
    	    writer.append("type");
    	    writer.append(';');
    	    writer.append("array size");
    	    writer.append(';');
    	    writer.append("const");
    	    writer.append(';');
    	    writer.append("value");
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append(';');
    	    writer.append('\n');
     
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public void generateInputVarFile(String srcfilepath, String filepath){
		System.out.println("Input Var file location is " + filepath);
		File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
           
            writer.append("VarName");
    	    writer.append(';');
    	    writer.append("type");
    	    writer.append(';');
    	    writer.append("array size");
    	    writer.append(';');
    	    writer.append("min");
    	    writer.append(';');
    	    writer.append("max");
    	    writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void generateFunctionFile(String srcfilepath, String filepath){
		System.out.println("Function file location is " + filepath);
		File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
           
            writer.append("FunctionName");
    	    writer.append(';');
    	    writer.append("time");
    	    writer.append(';');
    	    writer.append("isLeaf");
    	    writer.append(';');
    	    writer.append("isSchedulable");
    	    
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

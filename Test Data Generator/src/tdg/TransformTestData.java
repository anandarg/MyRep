package tdg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformTestData {

	public TransformTestData() {
		
	}
	
	public void TransformData(String srcTestDataFile, String tgtTestDataFile) {
		System.out.println("Source Test Data file location is " + srcTestDataFile);
		System.out.println("Target Test Data file location is " + tgtTestDataFile);
		CharSequence cs = "##";
		int cycleStart = 0;
		File file = new File(tgtTestDataFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
     
            writer.append("group,");
    	    writer.append("case,case,case,case,,");
    	    writer.append("calibration,calibration,calibration,");
    	    writer.append("step,step,step,step,step,step");
    	    
    	    BufferedReader br = new BufferedReader(new FileReader(srcTestDataFile));
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	      
    	    	if (line.trim().length() == 0) {
    	    		continue;
    	    	}
    	    	else if (line.contains(cs)){
    	    		System.out.println("Cycle Start value is " + cycleStart);
    	    		if (cycleStart == 0){
    	    			cycleStart = 1;
    	    		}
    	    		else{
    	    			break;
    	    		}
    	    	}
    	    	else if (!line.contains(cs) && cycleStart == 1){
    	    		writer.append(",Input");
    	    	}
    	    }
    	    br.close();
    	    writer.append('\n');	
    	    writer.append("name,");
    	    writer.append("case,name,mode,description,Calibration set,");
    	    writer.append("KaAIRD_p_APPD_BaroDiffHi,KeAIRC_t_MaxPumpOffDly,KeAIRD_Cnt_NumOfPresSnsr,");
    	    writer.append("sequence,description,name,time input,time function,time output");
    	    br = new BufferedReader(new FileReader(srcTestDataFile));
    	    cycleStart = 0;
    	    while ((line = br.readLine()) != null) {
    	      
    	    	System.out.println(line);
    	    	if (line.trim().length() == 0) {
    	    		continue;
    	    	}
    	    	else if (line.contains(cs)){
    	    		if (cycleStart == 0){
    	    			cycleStart = 1;
    	    		}
    	    		else{
    	    			break;
    	    		}		
    	    	}
    	    	else if (!line.contains(cs) && cycleStart == 1){
    	    		writer.append(","+ line.substring(0, line.lastIndexOf('=')));
    	    	}
    	    }
    	    br.close();
    	    
    	    br = new BufferedReader(new FileReader(srcTestDataFile));
    	    
    	    while ((line = br.readLine()) != null) {
    	      
    	    	System.out.println(line);
    	    	if (line.trim().length() == 0) {
    	    		continue;
    	    	}
    	    	else if (line.contains(cs)){
    	    		writer.append('\n');	
    	        	writer.append(",,,,,,,,,,,");
    	        	writer.append(line);
    	        	writer.append(",,,");
    	    	}
    	    	else if (!line.contains(cs)){
    	    		writer.append(","+ line.substring(line.lastIndexOf('=')+1, line.length()));
    	    	}
    	    }
    	    br.close();
    	    
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	
	}
}

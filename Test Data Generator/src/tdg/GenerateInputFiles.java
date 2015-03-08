package tdg;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GenerateInputFiles {
		
	//Specify arraylist/hashmap where data will be temporarily stored
	private static ArrayList<DataType> dataFrmCalFile = new ArrayList<DataType>(); 
	private static ArrayList<DataType> datFrmCalValFile = new ArrayList<DataType>();
	private static HashMap<String,ArrayList<Float>> hm = new HashMap<String,ArrayList<Float>>();
	//End specification of arraylist/hashmap
	private static ArrayList<DataType> SplitVarFile = new ArrayList<DataType>();
	private static HashMap<String, Integer> vhm = new HashMap<String, Integer>();
	
	private static ArrayList<DataType> EnumFile = new ArrayList<DataType>();
	
	private static ArrayList<FunctionType> FunctionFile = new ArrayList<FunctionType>();
	
	//setup file path for the input files
	private String filepath = "";
	//setup file path for the Global Variable output file
	private String gblvarfilepath = "";
	//setup file path for the Input Variable output file
	private String inputvarfilepath = "";	
	//setup file path for the Function List output file
	private String funclistfilepath = "";
	//setup file path for the Aspen files
	private String Aspenfilepath = "";
	//ring name
	private static String ringname = "CTO";
	
	//End specification of arraylist/hashmap
	
	public void genFiles(String fp, String gfp, String ifp, String ffp) throws IOException	{
		
		this.filepath = fp;
		this.Aspenfilepath = fp;
		this.gblvarfilepath = gfp;
		this.inputvarfilepath = ifp;
		this.funclistfilepath = ffp;
		System.out.println(filepath);
				
		//Read cal.dat File and parse into dataFrmCalFile
		this.readCalFile(this.filepath +"\\cal.dat");
		System.out.println("Done reading from file "+ this.filepath +"\\cal.dat");
		//Read calVal.dat File and parse into dataFrmCalValFile
		this.readCalValFile(this.filepath +"\\calval.dat");
		//Read splitvar.dat File and parse into datFrmsplitvarFile
		this.readSplitVarFile(this.filepath +"\\splitvar.dat");
		//Read enum.dat File and parse into EnumFile 
		this.readEnumFile(this.filepath +"\\enum.dat");
		//Read Rte_Runnables.h file to extract functions called from master scheduler
		this.readFunctionFile(this.Aspenfilepath + "\\Aspen\\Rte_Runnables.h");
		
		//loop thru the calibration list
		System.out.println("***In varArrayvalue merge data extracted from cal.dat file and calval.dat***");
		for (DataType x: dataFrmCalFile) {
			//grab varName		
			//append data from hm. hashmap into dataFrmCalFile arraylist				
			if (hm.containsKey(x.getVarName())) {
				x.setVarArray(hm.get(x.getVarName()));
			}
		}
		//loop thru the variables  list
		//build new lists. 1st list will contain global variables and second will contain inputs. 
//				System.out.println("***In varArrayvalue merge data extracted from splitvar.dat file andhasmap***");
		for (DataType x: SplitVarFile) {
			//grab varName		
			//append data from hm. hashmap into GlobalVarList and InputVarList	
			
			if (vhm.containsKey(x.getVarName())) {
				x.setVarSize(vhm.get(x.getVarName()));
			}
		}	
		//==============================================================================================
		//==Write to a file=============================================================================

		//Write to excel
		writeToGlobalVarCSV();
		writeToInputVarCSV();
		writeToFuncFileCSV();

		
	}
	
	//===THis function writes Input data into CSV File==========================================================
	public void writeToInputVarCSV() throws IOException{
			
		try{	
			// Output data into a text file
			String header = "varname,type,array size,min,max"; 
			File file = new File(this.inputvarfilepath);
			
			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(header);
			bw.newLine();
			
			for (DataType x:SplitVarFile) {
				
				if (!(ringname.contains(x.getVarName().substring(2,5))) && !("HWIO".contains(x.getVarName().substring(2,6)))){
				String temp = x.getVarType();
				String newType = checkType(temp);
				bw.write(x.getVarName() + "," + newType + "," + x.getVarSize() +",null,null");
				bw.newLine();
				}
			}
			bw.close(); 
			System.out.println("Done");
			// End outputting into a text file
			
		}
	       catch (IOException e) {
			e.printStackTrace();
		}
			
		}
	//============================================================================================================
	//===THis function writes Input data into CSV File==========================================================
	public void writeToFuncFileCSV() throws IOException{
			try{	
				// Output data into a text file
				String header = "FunctrionName,time,isLeaf,isSchedulable"; 
				File file = new File(this.funclistfilepath);
				
				// if file doesn't exist, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(header);
				bw.newLine();
				
				for (FunctionType x:FunctionFile) {
					
					bw.write(x.getFuncName() + ",0,true,true");
					bw.newLine();

				}
				bw.close(); 
				System.out.println("Done");
				// End outputting into a text file
				
			}
		       catch (IOException e) {
				e.printStackTrace();
			}
		}
		//======================================================================================================
		//===THis function writes Global Variables into CSV File=============================================
		public void writeToGlobalVarCSV() throws IOException{
			try {
				
				// Output data into a text file
				String header = "varname,type,array size,const,value"; 
				File file = new File(this.gblvarfilepath);
	 
				// if file doesn't exist, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(header);
				bw.newLine();
				
				for (DataType x:dataFrmCalFile) {
					String temp = x.getVarType();
					String newType = checkType(temp);
					bw.write(x.getVarName() + "," + newType + "," + x.getVarSize() + ",true,");
					String tmpString = null;
					for (float f:x.getVarArray()) {
						tmpString += String.valueOf(f) + ",";
					}
					//remove the last char
					bw.write(tmpString.substring(4, tmpString.length()-1));
					bw.newLine();
				}

			//populate enumerated constants
			for (DataType x:EnumFile) {
				bw.write(x.getVarName() + ",int,1,true," + x.getVarValue());
				bw.newLine();
			}		
			
			//populate variables
			for (DataType x:SplitVarFile) {
				if (ringname.contains(x.getVarName().substring(2,5))) {
					String temp = x.getVarType();
					String newType = checkType(temp);
					bw.write(x.getVarName() + "," + newType + "," + x.getVarSize() + ",false,");
					String tmpString = null;
					for (int i = 1; i<= x.getVarSize(); i++) {
						tmpString += String.valueOf(0) + ",";
					    }
					bw.write(tmpString.substring(4, tmpString.length()-1));
					bw.newLine();
				}
			}	
			
			bw.close(); 
			System.out.println("Done");	
			// End outputting into a text file
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}	
	    //========================================================================================================
		//==THis method is takes GM type as an input and returns int or float
		public static String checkType(String GMType){
			
			if ((GMType.equals(GMType.toUpperCase()) && !"FLOAT".contains(GMType))|| 
				"TbBOOLEAN".contains(GMType) || ("Te".contains(GMType.substring(0,2)))){
				if("_".contains(GMType.substring(GMType.length()-3))){
					if ("00".contains(GMType.substring(GMType.length()-2, GMType.length()-1))) {
						return "int";
					}
					else {
						return "float";
				    }
				}
				else{
					return "int";
				}		
			}
			else {
				return "float";
			}
			
		}
		//=========================================================================================================
		//=========================================================================================================	
		//This function is designed to read Rte_Runnables.h
		private void readFunctionFile(String filename) throws IOException{
			//Read the file line by line
			String sLineReader = null;
			BufferedReader br = null;
			try {
				//Setup the reader/scanner with file want to read from
				br = new BufferedReader(new FileReader(filename));
				while ((sLineReader=br.readLine()) != null) {
					//Do not read lines that are empty
					if (!sLineReader.isEmpty()) {
						
						FunctionType dt = new FunctionType();
						String splitUpThisString [] = null;
						String FuncNameTemp = null;
						String splitUpFuncNameString [] = null;
						
					  //parses splitvar file one line from file by white spaces includes tab,space,nextline....	
						splitUpThisString = sLineReader.split("\\s");  // \\s means white space
						if (splitUpThisString.length >= 2){
						         FuncNameTemp = splitUpThisString[1].trim();
						         if (FuncNameTemp.length() >= 7){
						        	 String temp = "Mng" + ringname + "R_";
								 if (temp.contains(FuncNameTemp.substring(0, 7))){
										//strip function name from "(void)"
											splitUpFuncNameString = FuncNameTemp.split("\\(");							
											dt.setFuncName(splitUpFuncNameString[0].trim());

										FunctionFile.add(dt);
									}
						         }
						}
						
				   }
					
			}
				
			} catch (FileNotFoundException e) { //Should do multi-catch
				//usually log this or do some system output to console
			} catch (IOException e) {
				//usually log this or do some system output to console
			}  finally {
				//Close the file
				br.close();
			}		
			
			for(FunctionType x:FunctionFile){
				System.out.println(x.getFuncName());
			}
		}
		
		//=========================================================================================================
		//=========================================================================================================
		//This function is designed to read enum.dat file
		private void readEnumFile(String filename) throws IOException{
			//Read the file line by line
			String sLineReader = null;
			BufferedReader br = null;
			try {
				//Setup the reader/scanner with file want to read from
				br = new BufferedReader(new FileReader(filename));
				while ((sLineReader=br.readLine()) != null) {
					//Do not read lines that are empty
					if (!sLineReader.isEmpty()) {
						DataType dt = new DataType();
						
					//parses splitvar file one line from file by white spaces includes tab,space,nextline....
						String splitUpThisString [] = null;
						splitUpThisString = sLineReader.split("\\s");  // \\s means white space
						
						if (splitUpThisString != null) {

							 dt.setVarName(splitUpThisString[2].trim());
							 dt.setVarValue(Integer.valueOf(splitUpThisString[3]));

						}
						EnumFile.add(dt);

				}
			}
				
			} catch (FileNotFoundException e) { //Should do multi-catch
				//usually log this or do some system output to console
			} catch (IOException e) {
				//usually log this or do some system output to console
			}  finally {
				//Close the file
				br.close();
			}	

		}
		//=========================================================================================================
		//=========================================================================================================
		//This function is designed to read splitvar.dat file
		private void readSplitVarFile(String filename) throws IOException {
				//Read the file line by line
				String sLineReader = null;
				BufferedReader br = null;
				try {
					//Setup the reader/scanner with file want to read from
					br = new BufferedReader(new FileReader(filename));
					while ((sLineReader=br.readLine()) != null) {
						//Do not read lines that are empty
						if (!sLineReader.isEmpty()) {
							DataType dt = new DataType();
							
						//parses splitvar file one line from file by white spaces includes tab,space,nextline....
							String splitUpThisString [] = null;
							splitUpThisString = sLineReader.split("\\s");  // \\s means white space
							
							if (splitUpThisString != null) {

								 dt.setVarName(splitUpThisString[2].trim());
								 dt.setVarType(splitUpThisString[3].trim());

							}
								SplitVarFile.add(dt);
		
					}
				}
					
				} catch (FileNotFoundException e) { //Should do multi-catch
					//usually log this or do some system output to console
				} catch (IOException e) {
					//usually log this or do some system output to console
				}  finally {
					//Close the file
					br.close();
				}
				
				//sort through splitVar file to identify arrays as well as whether it's a global var on an input var
				Sortsplitvarfile();
			
		}
		

		//==========================================================================================================
		//============================================================================================================
		//sort through splitVar file to identify arrays as well as whether it's a global var on an input varaible
		public void Sortsplitvarfile(){
			
			int  count = 0; 

			String VarNameNew = null;
			
			//Perform 1st filtering to remove any variable duplicates if present
			Map<String, DataType> map = new LinkedHashMap<String, DataType>();
			for(DataType i : SplitVarFile){
				map.put(i.getVarName(), i);
			}
			SplitVarFile.clear();
			SplitVarFile.addAll(map.values());
					
			for (DataType x:SplitVarFile) {
				//split GetVar name if it contains []
				String splitUpThisString [] = null;
				splitUpThisString = x.getVarName().split("\\[");
				VarNameNew = splitUpThisString[0].trim();
				
				x.setVarName(VarNameNew);
				
	    		if (splitUpThisString != null) {

							if (vhm.containsKey(VarNameNew)){
								count++;
								vhm.put(VarNameNew,count);
							} else {
								//Create new array list
								count = 1;
								vhm.put(VarNameNew,count);
							}
						x.setMaxValue("null");
						x.setMinValue("null");
					}
			}	
	      //perform one last filtering to remove duplicates after removing the size of the variables []
	    		//Perform 1st filtering to remove any variable duplicates if present
	    		Map<String, DataType> map2 = new LinkedHashMap<String, DataType>();
	    		for(DataType i : SplitVarFile){
	    			map2.put(i.getVarName(), i);
	    		}
	    		SplitVarFile.clear();
	    		SplitVarFile.addAll(map2.values());		
		}
		
		//==========================================================================================================
		//==========================================================================================================
		///This function is designed to read calibration file#1 (cal.dat)
		public void readCalFile(String filename) throws IOException {
			//Read the file line by line
			String sLineReader = null;
			BufferedReader br = null;
			try {
				//Setup the reader/scanner with file want to read from
				br = new BufferedReader(new FileReader(filename));
				while ((sLineReader=br.readLine()) != null) {
					//Have a method to parse each line
					//Then store this information into some array/collection
					dataFrmCalFile.add(parser4Cal(sLineReader));
				}
				
			} catch (FileNotFoundException e) { //Should do multi-catch
				//usually log this or do some system output to console
			} catch (IOException e) {
				//usually log this or do some system output to console
			}  finally {
				//Close the file
				br.close();
			}
		}

		//==============================================================================================
		//==============================================================================================
		//This only parses one line from file by white spaces includes tab,space,nextline....
		public DataType parser4Cal (String currentLine) { 
			//Need a counter to pull only columns 3,4,9
			int column = 0;
			//Need a hold these value from each of columns
			String holdValue = null;
			//Reading in value from each token
			String tmp = null;
			StringTokenizer st = new StringTokenizer(currentLine);
			//Create only one DateType instance for each line of read file....
			DataType dt = new DataType();
			while (st.hasMoreTokens()) {
				column ++;
				tmp = st.nextToken();
				if (column == 3) {
					holdValue = tmp;
					dt.setVarName(tmp.trim());
				} else if (column == 4) {
					holdValue += (" " + tmp);
					dt.setVarType(tmp);
				} else if (column == 9) {
					holdValue += (" " + tmp);
					int VarSize = Integer.parseInt(tmp);
					if (VarSize == 0){
						dt.setVarSize(1);
					}
					else {
						dt.setVarSize(VarSize);
					}
					break;	//get out of the loop, dont need to parse this line anymore
				}
			}
			if (holdValue == null | dt == null) {
				System.out.println("something is wrong....");
				dt.setVarName("error");
				dt.setVarType("error");
				dt.setVarSize(0);
			}		
			return dt;		
		}
		
		//==============================================================================================
		//==============================================================================================	
		//Read cal file #2 (calval.dat)
		public void readCalValFile(String filename) throws IOException  {
			//Read the file line by line
			String sLineReader = null;
			BufferedReader br = null;
			try {
				//Setup the reader/scanner with file want to read from
				br = new BufferedReader(new FileReader(filename));
				while ((sLineReader=br.readLine()) != null) {
					//Do not read lines that are empty
					if (!sLineReader.isEmpty()) {
//						String valuedData = split4EachCalValLine(sLineReader);
						DataType dt = split4EachCalValLine(sLineReader);
						if (dt != null) {
							datFrmCalValFile.add(dt);
						}	
					}
					
				}
				
			} catch (FileNotFoundException e) { //Should do multi-catch
				//usually log this or do some system output to console
			} catch (IOException e) {
				//usually log this or do some system output to console
			}  finally {
				//Close the file
				br.close();
			}
			putIntoKeyValuePair();
			
		}
		//==============================================================================================
		//==============================================================================================	
		//Get value from the current line that is being read 
		public DataType split4EachCalValLine (String currentLine) {

			DataType dt = new DataType();
			
//			System.out.println("read line: " + currentLine);
			String splitUpThisString [] = null;
			if (!currentLine.isEmpty()) {
				splitUpThisString = currentLine.split("\\s");
			}
			
			if (splitUpThisString != null) {
//				System.out.println("length of this string is: " + splitUpThisString.length);
				if (splitUpThisString.length>2) {
					dt.setVarName(splitUpThisString[1].trim());
					//Should be the last column
					dt.setVarValue(Float.parseFloat(splitUpThisString[splitUpThisString.length-1]));
				}
				
			}		
//			return varName + " " + varVal;	
			return dt;

		}
		//==============================================================================================
		//==============================================================================================
		//Create array of values listed horizontally in one row vs originally it's listed vertically in a column
		public void putIntoKeyValuePair () {
			//For each item from datFrmCalValFile
			//Create new key if not found
			//Then store into key,value pair like a hash map

			for (DataType x:datFrmCalValFile) {
				if (hm.containsKey(x.getVarName())) {
					//Get the keys arraylist value
					hm.get(x.getVarName()).add(x.getVarValue());
				} else {
					//Create new arraylist
					ArrayList<Float> lstFloat = new ArrayList<Float>();
					lstFloat.add(x.getVarValue());
					hm.put(x.getVarName(),lstFloat);
				}
			}

		}

	}


	class DataType {
		private String varType;
		private String varName;
		private int varSize;
		private ArrayList<Float> varArray;
		private float varValue;
		private int varState; // 1 if it's input and 0 if it's output 
		private String minValue;
		private String maxValue;
		
		
		public float getVarValue() {
			return varValue;
		}
		public void setvarSize(int i) {
			// TODO Auto-generated method stub
			
		}
		public void setVarValue(float varValue) {
			this.varValue = varValue;
		}
		public String getVarType() {
			return varType;
		}
		public void setVarType(String varType) {
			this.varType = varType;
		}
		public String getVarName() {
			return varName;
		}
		public void setVarName(String varName) {
			this.varName = varName.trim();
		}
		public int getVarSize() {
			return varSize;
		}
		public void setVarSize(int varSize) {
			this.varSize = varSize;
		}
		public ArrayList<Float> getVarArray() {
			return varArray;
		}
		public void setVarArray(ArrayList<Float> varArray) {
			this.varArray = varArray;
		}
		
		public int getvarState() {
			return varState;
		}
		public void setvarState(int varState) {
			this.varState = varState;
		}
		public String getMinValue() {
			return minValue;
		}
		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}
		public String getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}
		
	}

	class FunctionType {
		
		public String getFuncName() {
			return FuncName;
		}
		public void setFuncName(String funcName) {
			FuncName = funcName;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public String getIsLeaf() {
			return isLeaf;
		}
		public void setIsLeaf(String isLeaf) {
			this.isLeaf = isLeaf;
		}
		public String getIsScheduable() {
			return isScheduable;
		}
		public void setIsScheduable(String isScheduable) {
			this.isScheduable = isScheduable;
		}
		private String FuncName;
		private int time;
		private String isLeaf;
		private String isScheduable;
	}
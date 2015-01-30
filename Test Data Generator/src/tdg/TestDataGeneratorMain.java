package tdg;
import javax.swing.JFrame;

public class TestDataGeneratorMain {

	private TDG_ConfiguratorUI tdgConfig;
	
	public static void main(String[] args) {
		
		TestDataGeneratorMain tdgMain = new TestDataGeneratorMain();
		tdgMain.setupTDGWindow();
		
	}
	
	public void setupTDGWindow(){
		tdgConfig = new TDG_ConfiguratorUI();
		tdgConfig.setTitle("Test Data Generator");
		tdgConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tdgConfig.pack();
		tdgConfig.setSize(800, 700);
		tdgConfig.setVisible(true);
	    
	}

}

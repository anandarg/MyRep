package tdg;
import javax.swing.JFrame;

public class TestDataGeneratorMain extends JFrame{

	private static final long serialVersionUID = 1L;
	private TDG_Panel currentPanel;
	
	public static void main(String[] args) {
		
		TestDataGeneratorMain tdgMain = new TestDataGeneratorMain();
		tdgMain.setupTDGWindow();		
	}
	
	public void setupTDGWindow() {		
		
		currentPanel = new TDG_Panel();
		setupFrame();
		this.setTitle("Test Data Generator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(825, 650);
		this.setVisible(true);
	   	
	}
	
	private void setupFrame() {
		this.setContentPane(currentPanel);
	}
}

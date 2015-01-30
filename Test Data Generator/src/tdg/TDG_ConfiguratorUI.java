package tdg;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class TDG_ConfiguratorUI extends JFrame {
	private TDG_Panel currentPanel;
	
	public TDG_ConfiguratorUI() {
		currentPanel = new TDG_Panel();
			
		setupFrame();
	}
	
	private void setupFrame() {
		this.setContentPane(currentPanel);
	}

}

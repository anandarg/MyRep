package tdg;

import javax.swing.JFrame;



public class TDG_ConfiguratorUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6214228146388280093L;
	private TDG_Panel currentPanel;
	
	public TDG_ConfiguratorUI() {
		currentPanel = new TDG_Panel();
			
		setupFrame();
	}
	
	private void setupFrame() {
		this.setContentPane(currentPanel);
	}

}

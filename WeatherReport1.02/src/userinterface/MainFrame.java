package userinterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame{
	public Container cont;
	private EastPanel EP;
	private CenterPanel CP;
	
	public MainFrame(){
		
		this.setMaximumSize(new Dimension(800,800));
		
		this.setTitle("Weather Report");
		this.setBounds(100, 100, 900, 600);
		this.setVisible(true);		
		
		cont = this.getContentPane();
		cont.setLayout(new BorderLayout());
		
		//instantiate components
		EP = new EastPanel(this);
		CP = new CenterPanel(this);
		
		//add components to cont
		cont.add(EP, BorderLayout.EAST);
		cont.add(CP, BorderLayout.CENTER);
		
	}
	
	protected void processWindowEvent(WindowEvent e){ 
		if (e.getID() == WindowEvent.WINDOW_CLOSING){ 
			int exit = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", 
					"Confirm Exit!", JOptionPane.YES_NO_OPTION);
			if(exit == JOptionPane.YES_OPTION){
				System.exit(0);}
		} 
		else{ 
			super.processWindowEvent(e); 
		}
	}

	public EastPanel getEastPanel() {
		return EP;
	}

	public CenterPanel getCenterPanel() {
		return CP;
	}

}

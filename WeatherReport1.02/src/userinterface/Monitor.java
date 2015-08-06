package userinterface;

import java.awt.Color;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Location;

public abstract class Monitor extends JPanel implements Observer{
	
	protected Location location;
	protected boolean showRain;
	protected boolean showTemp;
	protected boolean showTime;
	
	public Monitor(){}

	public Monitor(Location location, boolean showRain, boolean showTemp, boolean showTime) {
		
		this.location = location;
		this.showRain = showRain;
		this.showTemp = showTemp;
		this.showTime = showTime;
	}
	
	public boolean isShowRain() {
		return showRain;
	}

	public void setShowRain(boolean showRain) {
		this.showRain = showRain;
	}

	public boolean isShowTemp() {
		return showTemp;
	}

	public void setShowTemp(boolean showTemp) {
		this.showTemp = showTemp;
	}

	public void refresh(){
		
		this.revalidate();
		this.repaint();
	}
	
	public abstract Monitor create(Location location, boolean showRain, boolean showTemp, boolean showTime);
}

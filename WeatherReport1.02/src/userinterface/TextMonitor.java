package userinterface;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Location;

public class TextMonitor extends Monitor{
	
	JLabel rain,temp,time;
	
	//Dummy constructor to make the monitor factory registration work
	public TextMonitor(){}

	public TextMonitor(Location location, boolean showRain, boolean showTemp, boolean showTime) {
				
		super(location, showRain, showTemp, showTime);
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.setBackground(new Color(255,255,255));
		newPanel.setName(location.getName());
		
		JLabel loc = new JLabel(location.getName());
		this.rain = new JLabel(location.getRainfall());
		this.temp = new JLabel(location.getTemperature());
		this.time = new JLabel(location.getTime());
		
		newPanel.add(loc);
		if(showRain){
			newPanel.add(this.rain);
		}
		if(showTemp){
			newPanel.add(this.temp);
		}
		newPanel.add(this.time);
		
		this.add(newPanel);
	}

	@Override
	public Monitor create(Location location, boolean showRain, boolean showTemp, boolean showTime) {
		System.out.println(showRain);
		System.out.println("ujytredfghj");
		System.out.println(showTemp);
		return new TextMonitor(location,showRain,showTemp,showTime);
	}

	public void update(Observable o, Object arg) {

		this.rain.setText(String.valueOf(this.location.getRainfall()));
		this.temp.setText(String.valueOf(this.location.getTemperature()));
		this.time.setText(String.valueOf(this.location.getTime()));
		
		this.refresh();
	}
}

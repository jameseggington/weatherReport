package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Location;

public class GraphicalMonitor extends Monitor{
	private JPanel newPanel;
	private GraphCreator graph;
	
	static{
		MonitorFactory.registerMonitorType("Graphical", new GraphicalMonitor());
	}
	
	public GraphicalMonitor(){}

	public GraphicalMonitor(Location location, boolean showRain, boolean showTemp, boolean showTime) {
		super(location, showRain, showTemp, showTime);
		
		newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.setBackground(new Color(255,255,255));
		newPanel.setName(location.getName());
		newPanel.setMaximumSize(new Dimension(210,160));
		
		graph = new GraphCreator(location, showRain,showTemp);
		
		newPanel.add(graph);
		
		this.add(newPanel);
		
	}

	@Override
	public Monitor create(Location location, boolean showRain, boolean showTemp, boolean showTime) {
		return new GraphicalMonitor(location, showRain, showTemp, showTime);
	}


	public void update(Observable o, Object arg1) {
		removePanel();
		newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.setBackground(new Color(255,255,255));
		newPanel.setName(location.getName());
		newPanel.setMaximumSize(new Dimension(210,160));
		
//		this.add(newPanel);
		this.refresh();
		
		graph = new GraphCreator(location, showRain, showTemp);
		newPanel.add(graph);
		
		this.add(newPanel);
		this.refresh();
	}
	
	private void removePanel(){
		this.removeAll();
	}
}


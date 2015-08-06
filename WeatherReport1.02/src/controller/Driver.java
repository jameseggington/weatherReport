package controller;

import model.*;
import userinterface.*;

public class Driver {
	
	public Driver(){}
	
	public static void main(String[] args) throws Exception{
		
		//register the two monitor types
		MonitorFactory.registerMonitorType("Text", new TextMonitor());
		MonitorFactory.registerMonitorType("Graphical", new GraphicalMonitor());
		
		WeatherServiceCollection wsc = new WeatherServiceCollection();
		wsc.addService(new DummyWeatherService());		
		wsc.addService(new Melbourne2WebService());
		wsc.addService(new MelbourneTimeLapseService());
		
		Controller control = new Controller(wsc);
	}
}

package userinterface;

import java.util.Collection;
import java.util.HashMap;

import model.Location;

public class MonitorFactory {
	
	public static HashMap<String,Monitor> monitorList = new HashMap<String,Monitor>();
	
	public static MonitorFactory instance;
	
	public MonitorFactory(){}
	
	//if no instance has so far been created, returns a new
	//instance, otherwise returns existing instance
	public static MonitorFactory getInstance(){
		if(instance == null){
			instance = new MonitorFactory();
		}
			
		return instance;
	}
	
	//Registers a monitor which can be created by the factory
	public static void registerMonitorType(String name, Monitor monitor){
		monitorList.put(name, monitor);
	}
	
	//Creates a new monitor of the requested type, returns null if no monitor is
	//associated with it
	public Monitor create(String type, Location location, boolean showRain, boolean showTemp, boolean showTime){
		
		Monitor monitorType = monitorList.get(type);
		Monitor object =  null;
		
		//The type exists
		if(monitorType != null){
			object = monitorType.create(location, showRain, showTemp, showTime);
		}
		
		return object;
	}
	
	/*
	 * Returns a list of the names of available monitors
	 */
	public Collection getMonitorNameList(){
		System.out.println(this.monitorList);
		return this.monitorList.keySet();
	}
}

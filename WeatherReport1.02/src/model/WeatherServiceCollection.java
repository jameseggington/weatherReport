package model;

import java.util.Collection;
import java.util.HashMap;

/*
 * A class to manage a group of weather services, basically
 * just provides a storage space
 */

public class WeatherServiceCollection {

	private HashMap<String,WeatherService> services = new HashMap<String,WeatherService>();
	
	//add a new service
	public void addService(WeatherService service){
		
		this.services.put(service.getName(),service);
	}
	
	public WeatherService getWeatherServiceByName(String name){
		System.out.println(this.services.get(name));
		return this.services.get(name);
	}
	
	//return a list of available services
	public Collection getServices(){
		
		return this.services.values();
	}
	
	//return a list of the names of available services
	public Collection getServiceNames(){
		
		return this.services.keySet();
	}
	
	public static void main(String args[]) throws Exception{
		
		WeatherServiceCollection wsc = new WeatherServiceCollection();
		
		WeatherService weatherservice = new MelbourneTimeLapseService();		
		wsc.addService(weatherservice);
		weatherservice = new Melbourne2WebService();
		wsc.addService(weatherservice);
		
		System.out.println(wsc.getServiceNames().toString());
	}
}

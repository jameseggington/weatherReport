package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Timer;

import org.apache.axis2.AxisFault;

import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.GetRainfall;
import melbourneweather2.MelbourneWeather2Stub.GetTemperature;


public class Melbourne2WebService extends WeatherService{
	
	private static final String name = "Melbourne 2"; 
	private static int timerDelay = 1000*60*10; //delay in milliseconds
	private MelbourneWeather2Stub weatherStub;
	
	//TODO: add a variable for the soap endpoint class
    
	//Takes a soap object on construction and uses this object to query the 
	// endpoint for location information
    public Melbourne2WebService() throws Exception{
    	super(name, timerDelay);
    }

	@Override
	public void updateLocationList(){
		try {
			this.weatherStub = new MelbourneWeather2Stub();
			
			GetRainfall getRainfallRequest = new GetRainfall();
	        GetTemperature getTemperatureRequest = new GetTemperature();
			
			Location location;
			String[] locations = weatherStub.getLocations().get_return();
        for(int i = 0; i < locations.length; i++){
        	getRainfallRequest.setLocation(locations[i]);
            getTemperatureRequest.setLocation(locations[i]);
            
            String[] temperatureData = weatherStub.getTemperature(getTemperatureRequest).get_return();
            String[] rainfallData = weatherStub.getRainfall(getRainfallRequest).get_return();
            
            location = new Location(locations[i], temperatureData[1], rainfallData[1], temperatureData[0]);
    		this.locations.addLocation(location);
        }
		} catch (RemoteException | ExceptionException e) {
			// TODO Auto-generated catch block
			System.err.println("Fuck this shit!");
		}
	}

	@Override
	public void updateSingleLocationData(Location location) {
		try {
		GetRainfall getRainfallRequest = new GetRainfall();
        GetTemperature getTemperatureRequest = new GetTemperature();
		
        getRainfallRequest.setLocation(location.getName());
        getTemperatureRequest.setLocation(location.getName());
        
        String[] temperatureData = weatherStub.getTemperature(getTemperatureRequest).get_return();
        String[] rainfallData = weatherStub.getRainfall(getRainfallRequest).get_return();
        
		System.out.println(location.getName());
		System.out.println("Old rainfall: " + location.getRainfall());
		location.setRainfall(rainfallData[1]);
		System.out.println("New rainfall: " + location.getRainfall());
		location.setTemperature(temperatureData[1]);
		location.setTime(temperatureData[0]);
		} catch (RemoteException | ExceptionException e) {
			// TODO Auto-generated catch block
			System.err.println("Fuck this shit!");
		}
	}
}

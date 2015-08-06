package model;

import java.rmi.RemoteException;
import melbourneweather2.MelbourneWeatherTimeLapseStub;
import melbourneweather2.MelbourneWeatherTimeLapseStub.GetWeather;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.GetRainfall;
import melbourneweather2.MelbourneWeather2Stub.GetTemperature;

public class MelbourneTimeLapseService extends WeatherService{
	
	private static final String name = "Melbourne Time Lapse"; 
	private static final int timerDelay = 20000; //10 minutes
	private MelbourneWeatherTimeLapseStub weatherStub;
	
	//TODO: add a variable for the soap endpoint
	
	public MelbourneTimeLapseService(){		
		super(name,timerDelay);
	}
	
	@Override
	public void updateLocationList() {
		try {
			this.weatherStub = new MelbourneWeatherTimeLapseStub();
			
			GetWeather getWeatherRequest = new GetWeather();
			
			Location location;
			String[] locations = weatherStub.getLocations().get_return();
        for(int i = 0; i < locations.length; i++){
        	getWeatherRequest.setLocation(locations[i]);
            
        	String[] weatherData = weatherStub.getWeather(getWeatherRequest).get_return();
            
            location = new Location(locations[i], weatherData[1], weatherData[2], weatherData[0]);
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
			GetWeather getWeatherRequest = new GetWeather();
			
			getWeatherRequest.setLocation(location.getName());
	        
			String[] weatherData = weatherStub.getWeather(getWeatherRequest).get_return();
	        
			System.out.println(location.getName());
			System.out.println("Old rainfall: " + location.getRainfall());
			location.setRainfall(weatherData[2]);
			System.out.println("New rainfall: " + location.getRainfall());
			location.setTemperature(weatherData[1]);
			location.setTime(weatherData[0]);
			} catch (RemoteException | ExceptionException e) {
				// TODO Auto-generated catch block
				System.err.println("Fuck this shit!");
			}
	}

}

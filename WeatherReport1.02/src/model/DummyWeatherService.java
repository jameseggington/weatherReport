package model;

import java.util.Timer;


public class DummyWeatherService extends WeatherService{

	private static final int timerDelay = 3000;
	private static final String serviceName = "Dummy Weather Service";
	
	
	public DummyWeatherService() {
		super(serviceName, timerDelay);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateLocationList() {
		Location location = new Location("Glen Waverley", "1", "1", "10:00");
		this.locations.addLocation(location);
		
		location = new Location("Blackburn", "2", "2", "20:56");
		this.locations.addLocation(location);
		
		location = new Location("Wheelers Hill", "0", "0", "2:12");
		this.locations.addLocation(location);		
	}

	@Override
	public void updateSingleLocationData(Location location) {
		System.out.println(location.getName());
		System.out.println("Old rainfall: " + location.getRainfall());
		location.setRainfall("100000");
		System.out.println("New rainfall: " + location.getRainfall());
		location.setTemperature("0.5");
	}
}

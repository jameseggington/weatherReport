package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Timer;

//An interface for a weather service
public abstract class WeatherService extends Observable{
	
	protected LocationCollection locations = new LocationCollection();
	protected String name;
	private int updateTimerDelay;
	
	public WeatherService(String name, int timerDelay){
		this.name = name;
		this.updateTimerDelay = timerDelay;
		
		this.updateLocationList();
		this.updateAllLocationData();		
		this.scheduleUpdateTimer();
	}
	
	public String getName(){
		return this.name;
	}
		
	//Updates the internal list of locations from the soap endpoint
	public abstract void updateLocationList();
	
	public abstract void updateSingleLocationData(Location location);
	
	//Updates weather data for each location in the locations array
	public void updateAllLocationData(){
		
		//Get a list of locations
		Collection locationList = this.locations.getLocationList();
		Iterator it = locationList.iterator();
		
		//Update each location
		while(it.hasNext()){
			this.updateSingleLocationData( (Location) it.next());
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	//Returns a location by its name
	public Location getLocationByName(String name){
		
		return this.locations.getLocationByName(name);
	}
	
	//Returns each location in a collection
	public Collection getLocationNames(){
		
		return this.locations.getLocationNameList();
	}
    
    //Schedules a timer to update weather data, the timer task
    // then calls this method again to make a loop
	public void scheduleUpdateTimer(){
		(new Timer()).schedule(new WeatherUpdateTimerTask(this),updateTimerDelay);
	}
}

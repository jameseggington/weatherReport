package model;

import java.util.Collection;
import java.util.HashMap;

public class LocationCollection {

	private HashMap<String,Location> locations = new HashMap<String,Location>();
	
	public void addLocation(Location location){
		this.locations.put(location.getName(),location);
	}
	
	public Location getLocationByName(String name){
		return this.locations.get(name);
	}
	
	public Collection<String> getLocationNameList(){
		return this.locations.keySet();
	}
	
	public Collection<Location> getLocationList(){
		return this.locations.values();
	}
}

package model;

import java.util.ArrayList;

public class Location {

	private String name;
	private String temperature;
	private String rainfall;
	private String time;
	private ArrayList<String> rainfallList;
	private ArrayList<String> tempList;
	private ArrayList<String> timeList;
	
	public Location(String name, String temperature, String rainfall, String time){
		
		this.name = name;
		this.temperature = temperature;
		this.rainfall = rainfall;
		this.time = time;
		this.rainfallList = new ArrayList<String>();
		this.tempList = new ArrayList<String>();
		this.timeList = new ArrayList<String>();
	}
	
	public ArrayList<String> getRainfallList() {
		return rainfallList;
	}

	public ArrayList<String> getTempList() {
		return tempList;
	}

	public ArrayList<String> getTimeList() {
		return timeList;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		timeList.add(time);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
		tempList.add(temperature);
	}

	public String getRainfall() {
		return rainfall;
	}

	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
		rainfallList.add(rainfall);
	}
	
	
}

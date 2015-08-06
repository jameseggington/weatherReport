package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import antlr.collections.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.Location;
import model.WeatherService;
import model.WeatherServiceCollection;
import userinterface.EastPanel;
import userinterface.MainFrame;
import userinterface.Monitor;
import userinterface.MonitorFactory;


public class Controller{
	
	private MainFrame mainFrame;
	private WeatherServiceCollection weatherServiceCollection;
	private Listener locationComboBoxListener;
	
	public Controller(WeatherServiceCollection weatherServiceCollection){
		this.mainFrame = new MainFrame();
		this.weatherServiceCollection = weatherServiceCollection;
		
		this.mainFrame.getEastPanel().addListener(new Listener(this));
		this.mainFrame.getEastPanel().addWeatherServiceComboBoxListener(new WeatherServiceComboBoxListener(this));
		
		this.refreshWeatherServiceComboBox(this.weatherServiceCollection.getServiceNames());
		this.refreshLocationComboBox();
		this.refreshMonitorTypesComboBox(MonitorFactory.getInstance().getMonitorNameList());
		ArrayList<String> optionList = new ArrayList<String>();
		optionList.add("Both");
		optionList.add("RainFall");
		optionList.add("Temperature");
		this.refreshOptionsComboBox(optionList);
	}
	
	public MainFrame getMF(){
		return mainFrame;
	}
	
	public WeatherServiceCollection getWeatherServiceCollection(){
		return weatherServiceCollection;
	}
	
	public Monitor createMonitor(String type, Location location, boolean showRain, boolean showTemp, boolean showTime){
		return MonitorFactory.getInstance().create(type, location, showRain, showTemp, showTime);
	}
	
	public void addWeatherService(WeatherService weatherService){
		System.out.println(weatherService.getName());
		
		this.weatherServiceCollection.addService(weatherService);
	}
	
	public String getSelectedWeatherServiceName(){
		return this.getMF().getEastPanel().getBoxSelectedItem(EastPanel.WEATHER_SERVICE_BOX);
	}
	
	public void refreshWeatherServiceComboBox(Collection<String> weatherServiceNameList){
		this.getMF().getEastPanel().fillComboBox(EastPanel.WEATHER_SERVICE_BOX, weatherServiceNameList);
	}
	
	public String getSelectedLocationName(){
		return this.getMF().getEastPanel().getBoxSelectedItem(EastPanel.LOCATION_NAME_BOX);
	}
	
	public void refreshLocationComboBox(){
		
		WeatherService weatherService = this.weatherServiceCollection.
				getWeatherServiceByName(this.getSelectedWeatherServiceName());
				
		//TODO: fix this, it needs to update based on the currently selected weather service
		Collection<String> locationList = weatherService.getLocationNames();
		this.getMF().getEastPanel().fillComboBox(EastPanel.LOCATION_NAME_BOX, locationList);
	}
	
	public String getSelectedMonitorType(){
		
		return this.mainFrame.getEastPanel().getBoxSelectedItem(EastPanel.MONITOR_TYPE_BOX);
	}
	
	public void refreshMonitorTypesComboBox(Collection<String> monitorTypesList){
		this.getMF().getEastPanel().fillComboBox(EastPanel.MONITOR_TYPE_BOX, monitorTypesList);
	}
	
	public int getSelectedOption(){
		return 0;
	}
	
	//TODO: method to fill the temp/ rain dropdown box
	public void refreshOptionsComboBox(Collection<String> optionsList){
		this.getMF().getEastPanel().fillComboBox(EastPanel.OPTIONS_BOX, optionsList);
	}
}
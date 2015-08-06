package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Location;
import model.WeatherService;
import userinterface.EastPanel;
import userinterface.Monitor;
import userinterface.MonitorFactory;

public class Listener implements ActionListener{
	
	private ArrayList<JButton> bList;
	private ArrayList<JMenuItem> mList;
	private Controller control;
	private ArrayList<String> removeList;
	
	public Listener(Controller control){
		this.control = control;
		bList = this.control.getMF().getEastPanel().getList();
	}
	
	public void actionPerformed(ActionEvent event) {
//		determines which button is clicked using the placement of buttons from the array
		switch(bList.indexOf(event.getSource())){
		
		//Create a new monitor using the options currently selected in the eastpanel 
		// and add it to the centre panel
		case 0:{
			
			
			// Get the selected weather service name
			String weatherServiceName = control.getMF().getEastPanel().
					getBoxSelectedItem(EastPanel.WEATHER_SERVICE_BOX);
			//Get the selected location name
			String locationName = control.getMF().getEastPanel().
					getBoxSelectedItem(EastPanel.LOCATION_NAME_BOX);
			// Get selected monitor type
			String monitorType = control.getMF().getEastPanel().
					getBoxSelectedItem(EastPanel.MONITOR_TYPE_BOX);
			// Get the temp/rain display option
			String rainTempOption = control.getMF().getEastPanel().
					getBoxSelectedItem(EastPanel.OPTIONS_BOX);
			
			//Retrieve the objects associated with those names
			WeatherService weatherService = control.getWeatherServiceCollection().
					getWeatherServiceByName(weatherServiceName);
			Location location = weatherService.getLocationByName(locationName);
			
			//Create a new monitor, make it observe the selected weather service
			// and add it to the centre panel
			boolean showRain,showTemp;
			
			if(rainTempOption == "Both"){
				System.out.println("X");
				showRain = true;
				showTemp = true;
			}else if(rainTempOption == "Temperature"){
				System.out.println("Y");
				showRain = false;
				showTemp = true;
			}else{
				System.out.println("Z");
				showRain = true;
				showTemp = false;
			}
			
			System.out.println(rainTempOption);
			System.out.println(showRain);
			System.out.println("adeuygiuy");
			System.out.println(showTemp);
			Monitor newMonitor = this.control.createMonitor(monitorType,location,showRain,showTemp,true);
			weatherService.addObserver(newMonitor);
			System.out.println(weatherService.countObservers());
			
			System.out.println(newMonitor.isShowRain());
			System.out.println(newMonitor.isShowTemp());
			
			control.getMF().getCenterPanel().addMonitor(newMonitor);
			bList.get(2).setEnabled(true);
			
			break;
		}
		
		//Ask the user if they want to exit
		case 1:{
			int exit = JOptionPane.showConfirmDialog(control.getMF(), "Are you sure you want to exit?", 
					"Confirm Exit!", JOptionPane.YES_NO_OPTION);
			if(exit == JOptionPane.YES_OPTION){
				System.exit(0);}
			break;
		}
		
		//Remove selected monitors from the centre panel
		case 2:{
			ArrayList<Monitor> list = control.getMF().getCenterPanel().getMonitorList();
			ArrayList<MListener> mouseList = control.getMF().getCenterPanel().getMouseList();
			for(int i=0;i<mouseList.size();i++){
				if(mouseList.get(i).getCounter()){
					for(int a=0;a<list.size();a++){
						Monitor monitor = mouseList.get(i).getMonitor();
						if(monitor == list.get(a)){
							list.remove(a);
							control.getMF().getCenterPanel().remove(monitor);
							a--;
						}
					}
					mouseList.remove(i);
					i--;
				}
			}
			control.getMF().getCenterPanel().refresh();
			break;
		}
		
		}
		
	}
}

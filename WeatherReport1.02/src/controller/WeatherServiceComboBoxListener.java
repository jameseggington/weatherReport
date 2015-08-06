package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WeatherServiceComboBoxListener implements ItemListener{

	private Controller controller;
	
	public WeatherServiceComboBoxListener(Controller controller){
		this.controller = controller; 
	}
	
	public void itemStateChanged(ItemEvent event) {
		if(event.getStateChange() == event.SELECTED){
			this.controller.refreshLocationComboBox();			
		}		
	}

}

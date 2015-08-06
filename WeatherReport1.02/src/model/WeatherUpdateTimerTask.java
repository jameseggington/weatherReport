package model;

import java.util.TimerTask;


public class WeatherUpdateTimerTask extends TimerTask implements Runnable{

	private WeatherService weatherService;
	
	public WeatherUpdateTimerTask(WeatherService weatherService){		
		this.weatherService = weatherService;
	}
	@Override
	public void run() {

		try {
			System.out.println(weatherService.name + " updating");
			this.weatherService.updateAllLocationData();
			this.weatherService.scheduleUpdateTimer();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

}

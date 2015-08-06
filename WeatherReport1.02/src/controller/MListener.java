package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import userinterface.Monitor;

public class MListener implements MouseListener{
	
//	private int code;
//	private Point P1;
	private Monitor monitor;
	private boolean counter;
	
	public MListener(Monitor monitor){
		this.monitor = monitor;
		counter = false;
	}

	public void mouseClicked(MouseEvent event) {
		
//		P1 = event.getComponent().getMousePosition();
//		code = event.getComponent().getComponentAt(P1).hashCode();
//		
		if(!counter){
			monitor.setBackground(new Color(200,200,200,255));
			counter = true;
		}
		else{
			monitor.setBackground(null);
			counter = false;
		}
	}
	
	public Monitor getMonitor(){
		return monitor;
	}
	
	public boolean getCounter(){
		return counter;
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

}

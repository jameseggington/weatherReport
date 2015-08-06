package userinterface;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import controller.MListener;

public class CenterPanel extends JPanel{
	
	private MainFrame MF;
	private ArrayList<Monitor> monitorList;
	private ArrayList<MListener> mouseList;

	public CenterPanel(MainFrame MF) {
			
		this.MF = MF;
		
		this.setLayout(new FlowLayout());
		monitorList = new ArrayList<Monitor>();
		mouseList = new ArrayList<MListener>();

		//add panel to frame
		this.MF.add(this);					
	}
	
	public void addMonitor(Monitor monitor){
		
		System.out.println("Here");
		
		monitorList.add(monitor);
		MListener mouselistener = new MListener(monitor);
		monitor.addMouseListener(mouselistener);
		mouseList.add(mouselistener);
		this.add(monitor);
		refresh();
	}
	
	public ArrayList<MListener> getMouseList(){
		return mouseList;
	}
	
	public void refresh(){
		this.revalidate();
		this.repaint();
	}
	
	public MainFrame getFrame(){
		return MF;
	}
	
	public ArrayList<Monitor> getMonitorList(){
		return monitorList;
	}
	
}

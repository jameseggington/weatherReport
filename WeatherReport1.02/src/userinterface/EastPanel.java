package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controller.WeatherServiceComboBoxListener;


public class EastPanel extends JPanel{
	
	public static final int WEATHER_SERVICE_BOX = 0;
	public static final int LOCATION_NAME_BOX = 1;
	public static final int MONITOR_TYPE_BOX = 2;
	public static final int OPTIONS_BOX = 3; 
	
	private JPanel east;
	private JLabel lblOption;
	private JButton btnAdd, btnRemove, btnExit;
	private ArrayList<JComboBox<String>> comboBoxes = new ArrayList<JComboBox<String>>();
	private MainFrame MF;
	private ArrayList<JButton> button;
	private GridBagConstraints GBC = new GridBagConstraints();

	public EastPanel(MainFrame MF) {
			
			this.MF = MF;
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(51, 153, 255), null, 
				null, null));
			
			east = new JPanel(new GridBagLayout());
			button = new ArrayList<JButton>();
			
			//create labels and set properties
			lblOption = new JLabel("Options");
			lblOption.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblOption.setAlignmentX(CENTER_ALIGNMENT);
			this.add(lblOption);
			
			//add buttons and set properties
			btnAdd = new JButton("Add");
			btnAdd.setName("WAdd");
			btnAdd.setEnabled(true);
			
			btnRemove = new JButton("Remove");
			btnRemove.setName("WRemove");
			btnRemove.setEnabled(false);

			btnExit = new JButton("Exit");
			btnExit.setName("WExit");
			btnExit.setEnabled(true);
			
			button.add(btnAdd);
			button.add(btnExit);
			button.add(btnRemove);	

			this.comboBoxes.add(EastPanel.WEATHER_SERVICE_BOX,new JComboBox<String>());
			this.GBC.gridx = 0;
			this.GBC.gridy = 1;
			this.GBC.insets = new Insets(20,20,0,20);

			this.comboBoxes.add(EastPanel.LOCATION_NAME_BOX,new JComboBox<String>());
			this.GBC.gridx = 0;
			this.GBC.gridy = 2;
			this.GBC.insets = new Insets(20,20,0,20);

			this.comboBoxes.add(EastPanel.MONITOR_TYPE_BOX,new JComboBox<String>());
			this.GBC.gridx = 0;
			this.GBC.gridy = 3;
			this.GBC.insets = new Insets(20,20,0,20);

			this.comboBoxes.add(EastPanel.OPTIONS_BOX,new JComboBox<String>());
			this.GBC.gridx = 0;
			this.GBC.gridy = 4;
			this.GBC.insets = new Insets(20,20,0,20);

			Iterator<JComboBox<String>> it = this.comboBoxes.iterator();
			while(it.hasNext()){
				this.add((JComboBox<String>) it.next());
			}
			JLabel legend = new JLabel();
			legend.setText("Red = Temperature");
			legend.setAlignmentX(CENTER_ALIGNMENT);
			
			JLabel legend2 = new JLabel();
			legend2.setText("Blue = Rainfall");
			legend2.setAlignmentX(CENTER_ALIGNMENT);
			
			GBC.gridx = 0;
			GBC.gridy = 2;
			GBC.insets = new Insets(20,20,20,20);
			east.add(legend,GBC);
			GBC.gridx = 0;
			GBC.gridy = 3;
			east.add(legend2,GBC);
			GBC.gridx = 0;
			GBC.gridy = 5;
			east.add(btnAdd,GBC);
			GBC.gridx = 0;
			GBC.gridy = 6;
			east.add(btnRemove,GBC);
			GBC.gridx = 0;
			GBC.gridy = 7;
			east.add(btnExit,GBC);

			//add panel to frame
			this.add(east);
			this.MF.add(this);
	}
	
	public void addListener(ActionListener AListen){
		btnAdd.addActionListener(AListen);
		btnRemove.addActionListener(AListen);
		btnExit.addActionListener(AListen);
	}
	
	public ArrayList<JButton> getList(){
		return button;
	}
	
	/*
	 * Fills a specific combo box with a list of strings
	 */
	public void fillComboBox(int box, Collection<String> locationList){
		
		if(locationList != null){
			this.comboBoxes.get(box).removeAllItems();
			Iterator<String> setIt = locationList.iterator();
			
			while(setIt.hasNext()){
				this.comboBoxes.get(box).addItem(setIt.next());
			}
		}
		
		this.refresh();
	}
	
	/*
	 * Returns the item currently selected for a box
	 */
	public String getBoxSelectedItem(int box){
		return (String) this.comboBoxes.get(box).getSelectedItem();
	}
	
	public void refresh(){
		this.revalidate();
		this.repaint();
	}
	
	/*
	 * Adds a listener so that the list of locations can update dynamically
	 * when the weather service changes
	 */
	public void addWeatherServiceComboBoxListener(WeatherServiceComboBoxListener weatherServiceComboBoxListener){
		this.comboBoxes.get(EastPanel.WEATHER_SERVICE_BOX).addItemListener(weatherServiceComboBoxListener);
	}
}


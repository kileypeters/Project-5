import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HammingFrame extends JFrame {

	public HammingFrame() throws IOException
	{
		this.setLayout(new GridLayout(1,2));
		HammingDist ham = new HammingDist();
		
		JPanel panel = new JPanel(new GridLayout(7,1));
		
		JPanel enterHammingPanel = new JPanel();
		JLabel enterHamming = new JLabel("Enter Hamming Dist:");
		JTextField enterHammingInfo = new JTextField(15);
		enterHammingInfo.setText("2");
		enterHammingInfo.setEditable(false);
		
		enterHammingPanel.add(enterHamming);
		enterHammingPanel.add(enterHammingInfo);
		panel.add(enterHammingPanel);
		
		//JSlider
		JSlider hammingSlider = new JSlider(0, 1, 4, 1); 
		hammingSlider.setMajorTickSpacing(1);
		hammingSlider.setPaintTicks(true);
		hammingSlider.setPaintLabels(true);
		hammingSlider.setValue(2);
		
		hammingSlider.addChangeListener(new ChangeListener() {
			@Override
	        public void stateChanged(ChangeEvent ce) {
	            
	            enterHammingInfo.setText(""+hammingSlider.getValue());
	        }
		});
		panel.add(hammingSlider);
		
		//Station Box
		JPanel stationsPanel = new JPanel(new GridLayout(2,1));
		JButton showStation = new JButton("Show Station"); 
		
		JTextArea stationBox = new JTextArea(10,20);
		  
		 JScrollPane sp = new JScrollPane(stationBox);
		 sp.setVerticalScrollBarPolicy(
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	sp.setPreferredSize(new Dimension(250, 250));
		stationBox.setEditable(false);
		
		stationsPanel.add(showStation);
		stationsPanel.add(sp);
		panel.add(stationsPanel);
		
		//Compare With:
		JPanel compare = new JPanel();
		JLabel compareWith = new JLabel("Compare with:");
		String[] comboBoxModel = ham.getStations().toArray(new String[ham.getStations().size()]);
		JComboBox<String> stationsSelect = new JComboBox<String>(comboBoxModel);
		
		compare.add(compareWith);
		compare.add(stationsSelect);
		panel.add(compare);
		
		//Calculate HD
		JButton calculateHD = new JButton("Calculate HD"); 
		panel.add(calculateHD);
		
		//Distance:
		JPanel distances = new JPanel(new GridLayout(5,2));
		JLabel distance0 = new JLabel("Distance 0");
		JTextField d0 = new JTextField();
		d0.setEditable(false);
		distances.add(distance0);
		distances.add(d0);
		
		JLabel distance1 = new JLabel("Distance 1");
		JTextField d1 = new JTextField();
		d1.setEditable(false);
		distances.add(distance1);
		distances.add(d1);
		
		JLabel distance2 = new JLabel("Distance 2");
		JTextField d2 = new JTextField();
		d2.setEditable(false);
		distances.add(distance2);
		distances.add(d2);
		
		JLabel distance3 = new JLabel("Distance 3");
		JTextField d3 = new JTextField();
		d3.setEditable(false);
		distances.add(distance3);
		distances.add(d3);
		
		JLabel distance4 = new JLabel("Distance 4");
		JTextField d4 = new JTextField();
		d4.setEditable(false);
		distances.add(distance4);
		distances.add(d4);
		panel.add(distances);
		
		JPanel addPanel = new JPanel();
		JButton addStation = new JButton("Add Station");
		JTextField newStation = new JTextField("ZERO");
		addPanel.add(addStation);
		addPanel.add(newStation);
		
		addStation.addActionListener((e) -> {
					String stationString = newStation.getText();
					
					if(!ham.getStations().contains(stationString))
					{
					ham.addStation(stationString);

					stationsSelect.addItem(stationString);
					}
					else
					{
						
					}
		});
		calculateHD.addActionListener((e) -> {
			
			String stID = (String) stationsSelect.getSelectedItem();
			ham.findHammingDist(stID);
			
			int dist0 = (int) ham.getDist().get(0).size();
			int dist1 = (int) ham.getDist().get(1).size();
			int dist2 = (int) ham.getDist().get(2).size();
			int dist3 = (int) ham.getDist().get(3).size();
			int dist4 = (int) ham.getDist().get(4).size();
			
			d0.setText(String.valueOf(dist0));
			d1.setText(String.valueOf(dist1));
			d2.setText(String.valueOf(dist2));
			d3.setText(String.valueOf(dist3));
			d4.setText(String.valueOf(dist4));		
			
		});
		showStation.addActionListener((e) -> {
			String stID = (String) stationsSelect.getSelectedItem();
			ham.findHammingDist(stID);
			
			stationBox.setText("");
			int select = hammingSlider.getValue();
			
			
			ArrayList<String> sel = (ArrayList<String>) ham.getDist().get(select);
			
			for (Object o : sel){
				stationBox.append(o + "\n");
			}
		});
		
		panel.add(addPanel);
		
		// Creative
		
		JPanel freeZone = new JPanel(new GridLayout(3,1));
		JLabel theme = new JLabel("Select a theme:");
		String[] comboBoxTheme = {"Pink", "Custom"};
		JComboBox<String> themeSelect = new JComboBox<String>(comboBoxTheme);
		JButton confirmTheme = new JButton("OK");
		
		confirmTheme.addActionListener((e) -> {
			String option = (String) themeSelect.getSelectedItem();
			
			if (option.equals("Pink"))
			{
				freeZone.setBackground(Color.PINK);
				confirmTheme.setBackground(Color.PINK);
				enterHamming.setBackground(Color.PINK);
				panel.setBackground(Color.PINK);
				calculateHD.setBackground(Color.PINK);
				showStation.setBackground(Color.PINK);
				addStation.setBackground(Color.PINK);
				
			}
			else if (option == "Custom")
			{
				Color color = JColorChooser.showDialog(HammingFrame.this, "Choose: Background Color", confirmTheme.getBackground());
				
				freeZone.setBackground(color);
				confirmTheme.setBackground(color);
				enterHamming.setBackground(color);
				panel.setBackground(color);
				calculateHD.setBackground(color);
				showStation.setBackground(color);
				addStation.setBackground(color);
			}

		});
		this.add(panel);
		
		freeZone.add(theme);
		freeZone.add(themeSelect);
		freeZone.add(confirmTheme);
		this.add(freeZone);
	
		this.setTitle("Hamming Distance");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(625, 775);
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		new HammingFrame();
	} 

}

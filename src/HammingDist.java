import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HammingDist {

	private ArrayList<String> stations = new ArrayList<String>();
	
	private HashMap<Integer, ArrayList<String>> dist = new HashMap<>();

	public HammingDist() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"));
		String string;
		string = br.readLine();
     	while (string != null)
     	{
         stations.add(string);
         string = br.readLine();
     	}
     	br.close();
     	
	}
	
	public void addStation(String station)
	{	
			stations.add(station);		
	}
	
	public void findHammingDist(String stID)
	{
		int count = 0;
		ArrayList<String> dist0 = new ArrayList<String>();
		ArrayList<String> dist1 = new ArrayList<String>();
		ArrayList<String> dist2 = new ArrayList<String>();
		ArrayList<String> dist3 = new ArrayList<String>();
		ArrayList<String> dist4 = new ArrayList<String>();
		
		for (int index = 0; index < stations.size(); index++)
		{
			count = 0;
			for (int k = 0; k < stID.length(); ++k) //stations.get(k).length()
			{
				if (stID.charAt(k) != stations.get(index).charAt(k))
				{
					count++;
				}
			}
			if(count == 1)
			{
				dist1.add(stations.get(index));
			}
			else if(count == 2)
			{
				dist2.add(stations.get(index));
			}
			else if(count == 3)
			{
				dist3.add(stations.get(index));
			}
			else if(count == 4)
			{
				dist4.add(stations.get(index));
			}
			else if(count == 0)
			{
				dist0.add(stations.get(index));
			}
		}
		dist.put(0, dist0);
		dist.put(1, dist1);
		dist.put(2, dist2);
		dist.put(3, dist3);
		dist.put(4, dist4);
		
	}

	public ArrayList<String> getStations() {
		return stations;
	}

	public HashMap<Integer, ArrayList<String>> getDist() {
		return dist;
	}
 
 
}

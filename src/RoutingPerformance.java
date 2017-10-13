import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class RoutingPerformance {
	String networkScheme;
	String routingScheme;
	int packetRate;
	
	public static void main(String[] args) throws FileNotFoundException {
		RoutingPerformance rp = new RoutingPerformance();
		rp.networkScheme = args[0];
		rp.routingScheme = args[1];
		
		String topologyFile = args[2];
		String workloadFile = args[3];
		
		
		String[] workloadFileArray = workloadFile.split("\n");
		int packetRate = Integer.parseInt(args[4]);
		TreeMap<Float,String> connections = addConnections(workloadFile);
		
		
		
		//int packetRate = Integer.parseInt(args[4]);
		
		Graph g = new Graph(topologyFile);

		/* 3 algorithms to implement (Dijkstra's):
		*
		*1. least number of hops (take cost of edge to be = 1)
		*2. least delay (cost of edge = propagation delay)
		*
		*3. least loaded (pick route where the 'narrowest' link that can accommodate highest capacity)
		*/
		
	}	
	
	public static TreeMap<Float, String> addConnections(String workloadFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(workloadFile));
		int connectionNumber = 0;
		String currentPacket;
		float currentPacketTime;
		String currentPacketSource;
		String currentPacketDestination;
		float currentPacketLength;
		float currentPacketEndTime;
		String pushLine;
		String pullLine;
		TreeMap<Float,String> connections = new TreeMap<Float,String>();  
		
		while (connectionNumber < 10) {
			currentPacket = sc.nextLine();
			currentPacketTime = Float.parseFloat((currentPacket.split(" "))[0]);
			currentPacketSource = (currentPacket.split(" "))[1];
			currentPacketDestination = (currentPacket.split(" "))[2];
			currentPacketLength = Float.parseFloat((currentPacket.split(" "))[3]);
			currentPacketEndTime = currentPacketLength + currentPacketTime;
			pushLine = "S " + currentPacketSource + " " + currentPacketDestination + " " + connectionNumber;
			pullLine = "E " + currentPacketSource + " " + currentPacketDestination + " " + connectionNumber;
			connections.put(currentPacketTime, pushLine);
			connections.put(currentPacketEndTime, pullLine);
			connectionNumber++;
			System.out.println(connections.firstEntry());
			connections.remove(connections.firstEntry().getKey());
		}
		return connections;
		
	}
}

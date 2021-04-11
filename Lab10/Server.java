package nqpham_CSCI201L_Lab10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
	// to do --> data structure to hold server threads 
	
	private ArrayList<ServerThread> serverThreads;
	
	public Server(int port) {
		// to do --> implement your constructor
		ServerSocket ss;
		
		try {
			System.out.println("Binding to the port "+ port);
			ss = new ServerSocket(port);
			System.out.println("Bound to port "+ port);
			
			
			while(true) {
				//blocking
				Socket s = ss.accept(); 
				System.out.println("Recieved Connection");
				
				ServerThread st = new ServerThread(s);
				//serverThreads.add(st);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
		
		
	
	public static void main(String [] args) {
		// to do --> implement your main()
		Server sr = new Server(6789);
		
	}
}

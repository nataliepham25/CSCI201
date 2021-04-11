package nqpham_CSCI201L_Lab10;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;


public class ServerThread extends Thread{
	// to do --> private variables for the server thread 
	
	Socket s;
	private PrintWriter pw;
	private BufferedReader br;
	private Server sr; 
	
	
	public ServerThread(Socket s) {
		try {
			// to do --> store them somewhere, you will need them later 
			this.s =s;
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.pw = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
			this.start();
			// to do --> complete the implementation for the constructor
			
			
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	// to do --> what method are we missing? Implement the missing method 
	
	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
		pw.print("HTTP/1.1 200 Found");
		pw.print("\r\n");
		
		//BufferedImage bi = ImageIO.read(new File(filename));
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
	}
	
	public void run() {
		try {
			String request = br.readLine();
			System.out.println(request);
			String[] arrOfStr1= request.split("/", 3);
			String[] arrOfStr2 = arrOfStr1[1].split(" ", 2);
			
			String fileName = arrOfStr2[0];
			System.out.println(fileName);
			
			FileReader fr= new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			pw.println("HTTP/1.1 200 OK\\R\nContent-Type: text/html; charset= UTF-8\n");
			
			String line = br.readLine();
			while(line!= null) {
				pw.println(line);
				pw.flush();
				line= br.readLine();
			}
			pw.flush();
			
			pw.close();
			br.close();
			
		}catch (FileNotFoundException fnfe) {
			//file not found
			System.out.println("File not found.");
			
			pw.println("HTTP/1.1 404 ok\\r\\nContent-Type: text/html; charset=UTF-8\\n");
			
			pw.println("<html>\n"
					+"      <head>\n "
					+"      </head>\n"
					+"      <body>\n"
					+"          <h1> 404 Error </h1>\n"
					+"      </body>\n"
					+"      </html>");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	//line = br.readline

}

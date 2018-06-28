package motor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import view.Gui;

public class Connection {
	
	private InetAddress myIp;
	private InetAddress servIp;
	private final int port = 4243;

	private DataOutputStream dos;
	private DataInputStream dis;
	
	private Socket socket;
	
	private Gui gui;
	
	public Connection(Gui gui) throws IOException 
	{
		this.gui = gui;
		myIp = InetAddress.getLocalHost();
		servIp = InetAddress.getByName("localhost");
    	System.out.println("Ip : " + myIp);
		System.out.println("Port  : " + port);	

		
//		socket = new Socket(servIp.getHostAddress(), 4243);
//		
//		dos = new DataOutputStream(socket.getOutputStream());
//		dis = new DataInputStream(socket.getInputStream());
//		
	}
		
	public String getData() 
	{
		String str = "";
		try {
			System.out.println("1");
			dos.writeUTF("Demande de Donnee");
			System.out.println("2");
			str = dis.readUTF();
			System.out.println("3");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	

}

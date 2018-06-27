package motor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Connection {
	
	private InetAddress myIp;
	private InetAddress servIp;
	private final int port = 4243;

	private DataOutputStream dos;
	private DataInputStream dis;
	
	private Socket socket;
	
	public Connection() throws UnknownHostException 
	{
		myIp = InetAddress.getLocalHost();
		servIp = InetAddress.getByName("pc-omen");
    	System.out.println("Ip : " + myIp);
		System.out.println("Port  : " + port);	
		initCon(servIp, port);
	}
		
	
	public void initCon(InetAddress ip, int port)
	{
		try 
		{
			socket = new Socket(ip.getHostAddress(), port);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		initStream();
	}


	public void initStream() 
	{
		try 
		{
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

		} 
		catch (IOException e)
		{

			e.printStackTrace();
		}
	}
	
	public void sendData(String str)
	{
		try {
			dos.write(str.getBytes(Charset.forName("UTF-8")));
		} catch (IOException e) {
			// TODO Blink Circle #Arduino
			//System.exit(-4);
		}
	}
	
	
	public String getData() 
	{
		byte[] message = new byte[1024];
		try {
			dis.read(message);
		} catch (IOException e1) {
			//System.exit(-3);
		}
		try {
			return new String(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	

}

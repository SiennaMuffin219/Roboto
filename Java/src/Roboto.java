
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Roboto {

	static InetAddress ip;
	static int port = 10000;

	public static void main(String args[]) {
		int i = 0;
	   while (true)
		   
	   {
		   
		   
		   try {
		    	//TimeUnit.MILLISECONDS.sleep(1);

		        ip = InetAddress.getByName("192.168.43.194");
		        DatagramSocket socket = new DatagramSocket();
		        byte[] sendData = new byte[1024];
		        sendData = "Hola".getBytes();
		        DatagramPacket sendPacket = null;//new DatagramPacket(sendData, sendData.length, ip, port);
		        socket.connect(ip, port);
		        socket.receive(sendPacket);
		        System.out.println("42");
		        System.out.println(sendPacket.getPort());

		        //		        socket.send(sendPacket);
//		        socket.close();
//		    	System.out.println("data Send");
//		    	System.out.println(i++);

		        
		    } catch (Exception e) {
		    } 
	   }
		
	}

}

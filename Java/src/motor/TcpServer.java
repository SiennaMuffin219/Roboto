package motor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import view.Gui;

public class TcpServer extends Thread {

	private ServerSocket serverSocket;
	private final int PORT = 4243;
	private Gui gui;

	public TcpServer(Gui gui) 
	{
		this.gui = gui;
		try {this.serverSocket = new ServerSocket(PORT);} 
		catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void run() 
	{
//		try {Thread.sleep(1000);} 
//		catch (InterruptedException e) {}
		System.out.println("X");
		Socket socket = null;
		System.out.println(serverSocket);
		try {socket = serverSocket.accept();} 
		catch (IOException e1) {e1.printStackTrace();}
		System.out.println("W");
		try 
		{
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			while (true)
			{
				System.out.println("Reading ... ");
				String str = input.readUTF();
				System.out.println("A");

				String[] tab = str.split(" "); 
				gui.getBat().setText(tab[0]);
				gui.getSpeedG().setText(tab[1]);
				gui.getSpeedD().setText(tab[2]);
				gui.getIsForward().setText(tab[3]);
				System.out.println("B");
			
				System.out.printf("read : %s", str);
				output.writeUTF("GOT");
			}
		
		}
		
		catch (IOException e) {}
		finally
		{
			try {serverSocket.close();} 
			catch (IOException e) {e.printStackTrace();}
		}
		
		super.run();
	}

}

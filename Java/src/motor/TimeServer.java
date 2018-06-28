package motor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Platform;
import view.Gui;

public class TimeServer {

   //On initialise des valeurs par défaut
   private int port = 4243;
   private String host = "127.0.0.1";
   private ServerSocket server = null;
   private boolean isRunning = true;
   private Gui gui;
   public TimeServer(Gui gui) 
   {
      try {
    	  System.out.println("Gui1 : " + gui);
    	 this.gui = gui;
         server = new ServerSocket(port, 1);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public TimeServer(String pHost, int pPort, Gui gui)
   {
      host = pHost;
      port = pPort;
      this.gui = gui;
      try {
         server = new ServerSocket(port, 100);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   //On lance notre serveur
   public void open(){
      
      //Toujours dans un thread à part vu qu'il est dans une boucle infinie
      Platform.runLater(new Runnable(){
         public void run(){
            while(isRunning == true){
               
               try {
                  //On attend une connexion d'un client
                  Socket client = server.accept();
                  
                  //Une fois reçue, on la traite dans un thread séparé
                  System.out.println("Connexion cliente reçue.");                  
                  Thread t = new Thread(new ClientProcessor(client, gui));
                  t.start();
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
            
            try {
               server.close();
            } catch (IOException e) {
               e.printStackTrace();
               server = null;
            }
         }
      });
      
   
   }
   
   public void close(){
      isRunning = false;
   }   
}
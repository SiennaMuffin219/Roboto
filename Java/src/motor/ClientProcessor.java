package motor;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import view.Gui;

public class ClientProcessor implements Runnable
{

   private Socket sock;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   private Gui gui;
   
   public ClientProcessor(Socket pSock, Gui gui){
      sock = pSock;
      System.out.println("Gui : " + gui);
      this.gui = gui;
   }
   
   //Le traitement lancé dans un thread séparé
   public void run(){
      System.err.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      //tant que la connexion est active, on traite les demandes
      while(!sock.isClosed()){
         
         try {
            
            //Ici, nous n'utilisons pas les mêmes objets que précédemment
            //Je vous expliquerai pourquoi ensuite
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedInputStream(sock.getInputStream());
            
            //On attend la demande du client            
            String data = read();
            System.out.println("Data : " + data);
            String[] tab = data.split(" ");
            gui.getBat().setText(tab[0]);
			gui.getSpeedG().setText(tab[1]);
			gui.getSpeedD().setText(tab[2]);
			gui.getIsForward().setText(tab[3]);   
			System.out.println("3 : "  + tab[3]);
            //On traite la demande du client en fonction de la commande envoyée
            String toSend = "Donnée reçu";
            
           
            
            //On envoie la réponse au client
            ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(toSend);

            writer.write(byteBuffer.getInt());
            //Il FAUT IMPERATIVEMENT UTILISER flush()
            //Sinon les données ne seront pas transmises au client
            //et il attendra indéfiniment
            writer.flush();
            
            if(closeConnexion){
               System.err.println("COMMANDE CLOSE DETECTEE ! ");
               writer = null;
               reader = null;
               sock.close();
               break;
            }
         }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            break;
         } catch (IOException e) {
            e.printStackTrace();
         }         
      }
   }
   
   //La méthode que nous utilisons pour lire les réponses
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[1024];
      stream = reader.read(b);
      response = new String(b, 0, stream);
      return response;
   }
   
}
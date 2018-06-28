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
   
   //Le traitement lanc� dans un thread s�par�
   public void run(){
      System.err.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      //tant que la connexion est active, on traite les demandes
      while(!sock.isClosed()){
         
         try {
            
            //Ici, nous n'utilisons pas les m�mes objets que pr�c�demment
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
            //On traite la demande du client en fonction de la commande envoy�e
            String toSend = "Donn�e re�u";
            
           
            
            //On envoie la r�ponse au client
            ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(toSend);

            writer.write(byteBuffer.getInt());
            //Il FAUT IMPERATIVEMENT UTILISER flush()
            //Sinon les donn�es ne seront pas transmises au client
            //et il attendra ind�finiment
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
   
   //La m�thode que nous utilisons pour lire les r�ponses
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[1024];
      stream = reader.read(b);
      response = new String(b, 0, stream);
      return response;
   }
   
}
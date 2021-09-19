package rs.ac.bg.fon.ai.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerNit extends Thread{
    
    
    ServerSocket ss;

  @Override
  public void run() {
  
      try {
          ss= new ServerSocket(9000);
          System.out.println("server je pokrenut");
          
          while (true) {
              Socket s= ss.accept();
              System.out.println("Klijent se povezao");
              
              ObradaKlijentskogZahteva okz= new ObradaKlijentskogZahteva(s);
              okz.start();
          }
          
      } catch (IOException ex) {
          Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
      }
  
  
  
  }
  

}

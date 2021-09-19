package rs.ac.bg.fon.ai.niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.domen.Polaznik;
import rs.ac.bg.fon.ai.domen.Trening;
import rs.ac.bg.fon.ai.kontroler.Kontroler;
import rs.ac.bg.fon.ai.transkript.KlijentskiZahtev;
import rs.ac.bg.fon.ai.transkript.Operacije;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;

public class ObradaKlijentskogZahteva extends Thread {
    
    Socket klijenskiSoket;
    boolean kraj = false;
    
    public ObradaKlijentskogZahteva(Socket klijenskiSoket) {
        this.klijenskiSoket = klijenskiSoket;
    }
    
    @Override
    public void run() {
        while (!kraj) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch (kz.getOperacija()) {
                case Operacije.VRATI_TRENERE:
                    so = Kontroler.getInstance().login();
                    break;
//                       List<Trener> listT = Kontroler.getInstance().login();
//                        if (listT != null) {
//                            so.setUspesno(true);
//                        } else {
//                            so.setUspesno(false);
//                        }
//                        so.setOdgovor(listT);
//                        break;
                
                case Operacije.SACUVAJ_POLAZNIKA:
                    
                    Polaznik p = (Polaznik) kz.getParametar();
                    so = Kontroler.getInstance().sacuvajPolaznika(p);
                    break;
//                            Polaznik p=(Polaznik) kz.getParametar();
//                        boolean sacuvano=Kontroler.getInstance().sacuvajPolaznika(p);
//                        
//                        if(sacuvano){
//                            so.setUspesno(true);
//                        } else{
//                            so.setUspesno(false);
//                        }
//                        so.setOdgovor(sacuvano);
//                        break;
                
                case Operacije.VRATI_POLAZNIKE:
                    so = Kontroler.getInstance().vratiPolaznike();
                    break;
//                       List<Polaznik> listP = Kontroler.getInstance().vratiPolaznike();
//                        if (listP != null) {
//                            so.setUspesno(true);
//                        } else {
//                            so.setUspesno(false);
//                        }
//                        so.setOdgovor(listP);
//                        break;
                
                case Operacije.VRATI_CLANARINE:
                    so = Kontroler.getInstance().vratiClanarine();
                    break;
//                       List<Clanarina> listC = Kontroler.getInstance().vratiClanarine();
//                        if (listC != null) {
//                            so.setUspesno(true);
//                        } else {
//                            so.setUspesno(false);
//                        }
//                        so.setOdgovor(listC);
//                        break;
                
                case Operacije.SACUVAJ_TRENINGE:
                    List<Trening> listTr = (List<Trening>) kz.getParametar();
                    so = Kontroler.getInstance().sacuvajListuTreninga(listTr);
                    break;
//                             List<Trening> listTr=  (List<Trening>) kz.getParametar();
//              boolean uspesno= Kontroler.getInstance().sacuvajListuTreninga(listTr);
//                so.setOdgovor(uspesno);
//                if(uspesno){
//                    so.setUspesno(uspesno);
//                }
//                else so.setUspesno(false);
//                        break;
//                
                
            }
            posaljiOdgovor(so);
            
        }
        
    }
    
    private KlijentskiZahtev primiZahtev() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(klijenskiSoket.getInputStream());
            return (KlijentskiZahtev) in.readObject();
        } catch (IOException ex) {
            System.out.println("Greska na serveru prilikom in primanja zahteva");
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Greska na serveru prilikom in citanja zahteva");
            
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        
        try {
            ObjectOutputStream out = new ObjectOutputStream(klijenskiSoket.getOutputStream());
            out.writeObject(so);
        } catch (IOException ex) {
            System.out.println("Greska na serveru prilikom out slanja odgovora");
            
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

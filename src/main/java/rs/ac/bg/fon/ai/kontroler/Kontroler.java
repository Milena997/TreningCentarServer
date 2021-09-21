package rs.ac.bg.fon.ai.kontroler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.JsonParser;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.domen.Clanarina;
import rs.ac.bg.fon.ai.domen.Polaznik;
import rs.ac.bg.fon.ai.domen.TipClanarine;
import rs.ac.bg.fon.ai.domen.Trener;
import rs.ac.bg.fon.ai.domen.Trening;
import rs.ac.bg.fon.ai.sopracije.OpstaSO;
import rs.ac.bg.fon.ai.sotrening.Login;
import rs.ac.bg.fon.ai.sotrening.SacuvajListuTreninga;
import rs.ac.bg.fon.ai.sotrening.SacuvajPolaznika;
import rs.ac.bg.fon.ai.sotrening.VratiClanarine;
import rs.ac.bg.fon.ai.sotrening.VratiPolaznike;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;


import org.json.JSONObject;

import com.google.gson.JsonParser;

public class Kontroler {
	private static Kontroler instance;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ServerskiOdgovor login() {
        
        OpstaSO oso = new Login();
        return oso.izvrsiOperaciju(null);

//    List<Trener> lista = new ArrayList<>();
//        try {
//            db.ucitajDriver();
//            db.otvoriKonekciju();
//
//            lista = db.vratiSveTrenere();
//            if (lista == null) {
//                throw new Exception("Lista je prazna");
//            }
//
//            db.zatvoriKonekciju();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//              Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//          }
//        return lista;

    }

    public ServerskiOdgovor sacuvajPolaznika(Polaznik p) {
          OpstaSO oso = new SacuvajPolaznika();
      	ServerskiOdgovor so=oso.izvrsiOperaciju(p);
        if(so.isUspesno()) {
        	sacuvajUJSON(p);
        }
        return so;
//      boolean sacuvano = false;
//        System.out.println(p);
//        try {
//            db.ucitajDriver();
//            db.otvoriKonekciju();
//           
//               sacuvano=db.sacuvajPolaznika(p);
//               if(sacuvano==false){
//                   System.out.println("nije sacuvan kontroler javio " + p);
//               }
//                System.out.println("\n polaznk je sacuvan " + p);
//            
//           
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sacuvano; 

      
    }

    public ServerskiOdgovor vratiPolaznike() {
           OpstaSO oso = new VratiPolaznike();
        return oso.izvrsiOperaciju(null);
//   List<Polaznik> lista = new ArrayList<>();
//        try {
//            db.ucitajDriver();
//            db.otvoriKonekciju();
//
//            lista = db.vratiSvePolaznike();
//            if (lista == null) {
//                throw new Exception("Lista je prazna");
//            }
//
//            db.zatvoriKonekciju();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//              Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//          }
//        return lista;

     
    }

    public ServerskiOdgovor vratiClanarine() {
         OpstaSO oso = new VratiClanarine();
        return oso.izvrsiOperaciju(null);
//   List<Clanarina> lista = new ArrayList<>();
//        try {
//            db.ucitajDriver();
//            db.otvoriKonekciju();
//
//            lista = db.vratiSveClanarine();
//            if (lista == null) {
//                throw new Exception("Lista je prazna");
//            }
//
//            db.zatvoriKonekciju();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//              Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//          }
//        return lista;
       
    }

    public ServerskiOdgovor sacuvajListuTreninga(List<Trening> listTr) {
        OpstaSO oso = new SacuvajListuTreninga();
        return oso.izvrsiOperaciju(listTr);
//    boolean sacuvano = false;
//        try {
//            db.ucitajDriver();
//            db.otvoriKonekciju();
//            for (Trening z : listTr) {
//               sacuvano=db.sacuvajTrening(z);
//               if(sacuvano==false){
//                   System.out.println("nije sacuvan kontroler javio " + z);
//               }
//                System.out.println("\n Angazman je sacuvan " + z);
//            }
//           
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sacuvano;

        
    }
    
    
    private void sacuvajUJSON(Polaznik p) {
		JSONObject polaznikSacuvan = new JSONObject();
		 polaznikSacuvan.put("ID:", p.getPid());
      polaznikSacuvan.put("Polaznik:", p.getIme());
         polaznikSacuvan.put("zenski:",p.getPol());
        polaznikSacuvan.put("Datum rodjenja: ", p.getDatumRodj());
        polaznikSacuvan.put("Telefon:",p.getTel());
        polaznikSacuvan.put("Adresa:", p.getAdresa());
       
        
        JsonParser jsonParser = new JsonParser();

        try {
            Object obj = jsonParser.parse(new FileReader("C:\\Users\\Milena\\eclipse-workspace\\TreningCentarServer\\sacuvani.json"));
            

            FileWriter file = new FileWriter("C:\\Users\\Milena\\eclipse-workspace\\TreningCentarServer\\sacuvani.json",true);
            file.write(polaznikSacuvan.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

		
	}


}

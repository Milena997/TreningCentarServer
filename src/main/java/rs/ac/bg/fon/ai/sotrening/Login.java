package rs.ac.bg.fon.ai.sotrening;

import java.util.List;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.domen.Trener;
import rs.ac.bg.fon.ai.sopracije.OpstaSO;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;

public class Login extends OpstaSO{

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
  ServerskiOdgovor so=new ServerskiOdgovor();
		List<Trener> listT;
		listT = DBBroker.getInstance().vratiSveTrenere();
		if(listT.isEmpty()) {
			so.setUspesno(false);
		}else {
			so.setOdgovor(listT);
			so.setUspesno(true);
		}
		return so;
    
    
    
    }

}

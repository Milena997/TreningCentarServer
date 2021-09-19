package rs.ac.bg.fon.ai.sotrening;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.domen.Polaznik;
import rs.ac.bg.fon.ai.sopracije.OpstaSO;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;

public class SacuvajPolaznika extends OpstaSO{

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
   	ServerskiOdgovor so=new ServerskiOdgovor();
		      Polaznik p=(Polaznik) object;
		
		boolean sacuvan = DBBroker.getInstance().sacuvajPolaznika(p);
		if(sacuvan) {
			so.setUspesno(true);
			so.setOdgovor(sacuvan);
		} else {
			so.setUspesno(false);
			so.setOdgovor(sacuvan);
		}
		return so;
    }


}

package rs.ac.bg.fon.ai.sotrening;

import java.util.List;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.sopracije.OpstaSO;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;
import rs.ac.bg.fon.ai.domen.Clanarina;

public class VratiClanarine extends OpstaSO{

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
    ServerskiOdgovor so=new ServerskiOdgovor();
		      List<Clanarina> listP;
		listP = DBBroker.getInstance().vratiSveClanarine();
		if(listP.isEmpty()) {
			so.setUspesno(false);
		}else {
			so.setOdgovor(listP);
			so.setUspesno(true);
		}
		return so; }

}

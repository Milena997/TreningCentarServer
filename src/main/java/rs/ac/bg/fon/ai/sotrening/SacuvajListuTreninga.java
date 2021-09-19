package rs.ac.bg.fon.ai.sotrening;

import java.util.List;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.domen.Trening;
import rs.ac.bg.fon.ai.sopracije.OpstaSO;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;

public class SacuvajListuTreninga extends OpstaSO {

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<Trening> list = (List<Trening>) object;

        	if(list==null || list.isEmpty()) {
			so.setUspesno(false);
			so.setOdgovor(null);
                         return so;
                        }
        for (Trening z : list) {
            boolean sacuvan = DBBroker.getInstance().sacuvajTrening(z);
            if (sacuvan) {
                so.setUspesno(true);
                so.setOdgovor(sacuvan);
            } else {
                so.setUspesno(false);
                so.setOdgovor(sacuvan);
            }
        }

        return so;
    }


}

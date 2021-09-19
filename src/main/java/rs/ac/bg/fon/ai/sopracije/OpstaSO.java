package rs.ac.bg.fon.ai.sopracije;

import java.sql.SQLException;
import java.util.logging.Level;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.transkript.ServerskiOdgovor;

public abstract class OpstaSO {
	
	 public ServerskiOdgovor izvrsiOperaciju(Object object) {
	        ServerskiOdgovor so = new ServerskiOdgovor();
	        try {
	            DBBroker.getInstance().ucitajDriver();
	            DBBroker.getInstance().otvoriKonekciju();
	            so = izvrsiKonkretnuOperaciju(object);

	        } catch (Exception ex) {
	          java.util.logging.Logger.getLogger(OpstaSO.class.getName()).log(Level.SEVERE, null, ex);
	            so.setUspesno(false);
	            so.setPoruka(ex.getMessage());

	        } finally {
	            try {
	                DBBroker.getInstance().zatvoriKonekciju();
	            } catch (SQLException ex) {
	                java.util.logging.Logger.getLogger(OpstaSO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }

	        return so;
	    }

	   
	    protected abstract ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception;


}

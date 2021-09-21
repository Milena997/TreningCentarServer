package rs.ac.bg.fon.ai.sotrening;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.domen.Clanarina;
import rs.ac.bg.fon.ai.domen.Grad;
import rs.ac.bg.fon.ai.domen.Polaznik;
import rs.ac.bg.fon.ai.domen.Sala;
import rs.ac.bg.fon.ai.domen.TipClanarine;
import rs.ac.bg.fon.ai.domen.Trener;
import rs.ac.bg.fon.ai.domen.Trening;
import rs.ac.bg.fon.ai.domen.VrstaVezbe;




class SacuvajListuTreningaTest {

	private SacuvajListuTreninga operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija = new SacuvajListuTreninga();
		rs.ac.bg.fon.ai.dbb.DBBroker.getInstance().ucitajDriver();
		rs.ac.bg.fon.ai.dbb.DBBroker.getInstance().otvoriKonekciju();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija = null;
		rs.ac.bg.fon.ai.dbb.DBBroker.getInstance().zatvoriKonekciju();
	}

	@Test
	void testIzvrsiKonkretnuOperaciju() throws Exception {
	
		Trener tr= new Trener(1,"Mara","m");
		Date d= new Date();
		Polaznik p= new Polaznik(2, "Ana Antic", "zenski", d,"066/123456","R 33" );
		Clanarina c= new Clanarina(3, 2500, TipClanarine.MESECNA);
		Trening t1= new Trening(1, tr, p, VrstaVezbe.trcanje,Grad.BEOGRAD,c, Sala.SALA1);
		List<Trening> listT= new ArrayList<>();
		listT.add(t1);
		
		rs.ac.bg.fon.ai.transkript.ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(listT);
        boolean sacuvan=(boolean) so.getOdgovor();
		
        assertNotNull(so);
		assertTrue(so.isUspesno());
		assertEquals(so.getOdgovor(),sacuvan);
	}

}

package rs.ac.bg.fon.ai.sotrening;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.dbb.DBBroker;
import rs.ac.bg.fon.ai.domen.Polaznik;


class SacuvajPolaznikaTest {
private SacuvajPolaznika operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija= new SacuvajPolaznika();
		DBBroker.getInstance().ucitajDriver();
		DBBroker.getInstance().otvoriKonekciju();
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
		DBBroker.getInstance().zatvoriKonekciju();
	}

	@Test
	void testIzvrsiKonkretnuOperaciju() throws Exception {
		Date d= new Date();
		Polaznik p = new Polaznik(1, "Jovan Jovanovic", "zenski", d, "06655541", "M 2");
		
		rs.ac.bg.fon.ai.transkript.ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(p);
		   boolean sacuvan=(boolean) so.getOdgovor();
		
		assertNotNull(so);
		assertTrue(so.isUspesno());
		assertEquals(so.getOdgovor(),sacuvan);

	}

}

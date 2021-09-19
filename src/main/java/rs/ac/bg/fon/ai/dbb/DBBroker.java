package rs.ac.bg.fon.ai.dbb;

import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import rs.ac.bg.fon.ai.domen.Clanarina;
import rs.ac.bg.fon.ai.domen.Polaznik;
import rs.ac.bg.fon.ai.domen.TipClanarine;
import rs.ac.bg.fon.ai.domen.Trener;
import rs.ac.bg.fon.ai.domen.Trening;


public class DBBroker {
	
	  Connection conn;
	    String driver = "com.mysql.jdbc.Driver";
	    String user = "root";
	    String pass = "";
	    String url = "jdbc:mysql://localhost:3306/treningcentar";
		private static DBBroker instance;
	  public static DBBroker getInstance() {
			if (instance == null)
				instance = new DBBroker();
			return instance;
		}

	    public void ucitajDriver() throws ClassNotFoundException {
	        Class.forName(driver);
	    }

	    public void otvoriKonekciju() throws SQLException {
	        conn = (Connection) DriverManager.getConnection(url, user, pass);
	        //conn.setAutoCommit(true);
	    }

	    public void zatvoriKonekciju() throws SQLException {
	        conn.close();
	    }

	    public List<Trener> vratiSveTrenere() {
	        List<Trener> lista = new ArrayList<>();
	        try {

	            String sql = "SELECT * FROM trener ";
	            Statement stat = (Statement) conn.createStatement();
	            ResultSet rs = stat.executeQuery(sql);
	            while (rs.next()) {
	                Trener t = new Trener();
	                t.setTid(rs.getInt("TrenerID"));
//	            Date datum = rs.getTimestamp("z.DatumVremeTestiranja");
//	            z.setDatumIVremeTestiranja(datum);
	                t.setKorisnickoIme(rs.getString("KorisnickoIme"));
	                t.setSifra(rs.getString("Sifra"));
	                lista.add(t);

	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }

	        return lista;
	    }

	    public int vratiId(String tabela, String id) {
	        int maxId = 0;

	        String upit = " SELECT MAX(" + id + ") as maxId FROM " + tabela;

	        try {
	            Statement stat = (Statement) conn.createStatement();
	            ResultSet rs = stat.executeQuery(upit);
	            while (rs.next()) {

	                maxId = rs.getInt("maxId");
	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            System.out.println("NIje nasao max");
	        }

	        return ++maxId;

	    }

	    public boolean sacuvajPolaznika(Polaznik p) {
	        boolean uspesno = false;
	        try {

	            String upit = "Insert into Polaznik Values (?,?,?,?,?,?)";
	            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
	            int polaznikID = vratiId("Polaznik", "polaznikID");
	            ps.setInt(1, polaznikID);
	            System.out.println("pid ok");
	            ps.setString(2, p.getIme());
	            System.out.println("ime ok");

	            ps.setString(3, p.getPol());
	            System.out.println("pol ok");

	            java.sql.Date rodj = new java.sql.Date(p.getDatumRodj().getTime());

	//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//Date myDate = formatter.parse(date);
	//java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
	            ps.setDate(4, rodj);
	            System.out.println("date ok");

	            ps.setString(5, p.getTel());
	            System.out.println("tel ok");

	            ps.setString(6, p.getAdresa());
	            System.out.println("adr ok");

	            try {
	                ps.executeUpdate();
	                uspesno = true;
	                System.out.println("upi ok");
	            } catch (SQLException ex) {
	                Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	                System.out.println("Greska prilikom ps pravljenja predmeta");
	                return false;
	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            return false;
	        }
	        return uspesno;
	    }

	    public List<Polaznik> vratiSvePolaznike() {
	        List<Polaznik> lista = new ArrayList<>();
	        try {

	            String sql = "SELECT * FROM polaznik ";
	            Statement stat = (Statement) conn.createStatement();
	            ResultSet rs = stat.executeQuery(sql);
	            while (rs.next()) {
	                Date datum = new Date(rs.getDate("datumRodj").getTime());

	                Polaznik p = new Polaznik(rs.getInt("polaznikID"), rs.getString("ImePrezime"), rs.getString("pol"), datum, rs.getString("telefon"), rs.getString("adresa"));
	                lista.add(p);

	            }

	        } catch (SQLException ex) {
	        	Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }

	        return lista;
	    }

	    public List<Clanarina> vratiSveClanarine() {
	        List<Clanarina> lista = new ArrayList<>();
	        try {

	            String sql = "SELECT * FROM clanarina ";
	            java.sql.Statement stat = conn.createStatement();
	            ResultSet rs = stat.executeQuery(sql);
	            while (rs.next()) {
	                String tc = rs.getString("TipClanarine");
	                TipClanarine tip = TipClanarine.valueOf(tc);
	                Clanarina p = new Clanarina(rs.getInt("clanarinaID"), rs.getDouble("iznos"), tip);

	                lista.add(p);

	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }

	        return lista;
	    }

	    public boolean sacuvajTrening(Trening z) {
	        boolean uspesno = false;
	        try {

	            String upit = "Insert into Trening Values (?,?,?,?,?,?,?)";
	            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
	            int treningID = vratiId("trening", "TreningID");
	            ps.setInt(1, treningID);

	            ps.setInt(2, z.getTrenenr().getTid());
	            ps.setInt(3, z.getPolaznik().getPid());
	            ps.setString(4, z.getVv().toString());
	            ps.setString(5, z.getGrad().toString());
	            ps.setInt(6, z.getClanarina().getClanarinaID());
	            ps.setString(7, z.getSala().toString());

	            try {
	                ps.executeUpdate();
	                uspesno = true;
	                System.out.println("upi ok");
	            } catch (SQLException ex) {
	                Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	                System.out.println("Greska prilikom ps pravljenja predmeta");
	                return false;
	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(DBBroker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            return false;
	        }
	        return uspesno;
	    }

}

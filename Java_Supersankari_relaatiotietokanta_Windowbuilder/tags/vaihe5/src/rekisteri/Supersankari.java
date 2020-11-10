package rekisteri;

import java.io.*;

/**
 * Supersankari-luokka. Supersankari tietää omat tietonsa ja etenkin
 * id-numeronsa
 *
 * @author majosalo
 * @version 22.2.2013
 * @version 7.3.2013
 */
public class Supersankari {
	private int id;
	private String stageName = "";
	private String nimi = "";
	private String puoliso = "";
	private String kotikaupunki = "";
	private String jluNumero;
	private int voimanumero;
	private static int seuraavaNro = 1; // jotta seuraava supersankari osataan
										// sijoittaa oikeaan kohtaan taulukossa


	/**
	 * Testipääohjelma
	 *
	 * @param args
	 *            ei käytössä
	 */
	public static void main(String[] args) {
		Supersankari suppe = new Supersankari("Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ");
		suppe.tulosta(System.out);
		Supersankari supis = new Supersankari();
		supis.alustaToimivaksi();
		supis.tulosta(System.out);

	}


	/**
	 * Parametriton muodostaja
	 */
	public Supersankari() {
	}


	/**
	 * Muodostetaan Supersankari merkkijonon tietojen perusteella
	 * @param tiedot
	 * @example
	 * <pre name="test">
	 * Supersankari battis = new Supersankari("Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ");
	 * Supersankari captis = new Supersankari("Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ");
	 * battis.getStageName().equals("Batman") === true;
	 * captis.getStageName().equals("Captain America") === true;
	 *   int n1 = battis.getId();
	 *   int n2 = captis.getId();
	 *   n1 === n2-1;
	 * </pre>
	 */
	public Supersankari(String supiksenTiedot) {
		supiksenTiedot.trim();
		String[] tiedot = supiksenTiedot.split("\\|");
		id = this.rekisteroi();
		stageName = tiedot[0].trim();
		nimi = tiedot[1].trim();
		puoliso = tiedot[2].trim();
		kotikaupunki = tiedot[3].trim();
		jluNumero = tiedot[4].trim();

	}


	/**
	 * Antaa supersankarille seuraavan id-numeron.
	 *
	 * @return supersankarin uusi id
	 * @example <pre name="test">
	 *   Supersankari supis = new Supersankari();
	 *   supis.getId() === 0;
	 *   supis.rekisteroi();
	 *   Supersankari supis2 = new Supersankari();
	 *   supis2.rekisteroi();
	 *   int n1 = supis.getId();
	 *   int n2 = supis2.getId();
	 *   n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		id = seuraavaNro;
		seuraavaNro++;
		return id;
	}


	/**
	 * Alustetaan supersankari toimivaksi jotta sitä voi käyttää ja pystytään
	 * testailemaan
	 */
	public void alustaToimivaksi() {
		id = this.rekisteroi();
		stageName = "Captain America";
		nimi = "Rogers Steve";
		puoliso = "Stark Tony";
		kotikaupunki = "Brooklyn";
		jluNumero = "A2";
		voimanumero = 800;
	}


	/**
	 * @return supersankarin id-numeron.
	 * @example <pre name="test">
	 * 	Supersankari supis = new Supersankari();
	 *  supis.getId() === 0;
	 *  supis.rekisteroi();
	 *  Supersankari supis2 = new Supersankari();
	 *  supis2.rekisteroi();
	 *  int n1 = supis.getId();
	 *  int n2 = supis2.getId();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public int getId() {
		return id;
	}


	/**
	 * Tulostetaan henkilön tiedot
	 *
	 * @param out
	 *            tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("%03d", id, 3) + " Stage name: " + stageName
				+ " Nimi: " + nimi);
		out.println("Puoliso:  " + puoliso + " Kotikaupunki: " + kotikaupunki);
		out.println("JLU Yhteysnumero: " + jluNumero + " voimanumero: "
				+ voimanumero);
	}


	/**
	 * @return supersankarin stage namen
	 * @example <pre name="test">
	 * Supersankari supis = new Supersankari();
	 * supis.alustaToimivaksi();
	 * supis.getStageName() === "Captain America";
	 * </pre>
	 */
	public String getStageName() {
		return stageName;
	}


	/**
	 * Tulostetaan henkilön tiedot
	 *
	 * @param os
	 *            tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
		tulosta(new PrintStream(os));
	}

}

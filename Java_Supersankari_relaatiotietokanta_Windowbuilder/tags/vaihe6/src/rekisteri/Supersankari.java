package rekisteri;

import java.io.*;

/**
 * Supersankari-luokka. Supersankari tiet‰‰ omat tietonsa ja etenkin
 * id-numeronsa
 *
 * @author majosalo
 * @version 22.2.2013
 * @version 12.4.2013
 */
public class Supersankari {

	private Kentta kentat[] = { // valitettavasti ei voi olla final
			// vaikka pit‰isi, clone est‰‰ t‰m‰n :-(
			new IntKentta("id"), new JonoKentta("Stage name"),
			new JonoKentta("Nimi"), new JonoKentta("Puoliso"),
			new JonoKentta("Kotikaupunki"), new JonoKentta("JLU-yhteysnumero"),
			new IntKentta("Voimanumero"), };

	private static int seuraavaNro = 1; // jotta seuraava supersankari osataan
										// sijoittaa oikeaan kohtaan taulukossa


	/**
	 * Testip‰‰ohjelma
	 *
	 * @param args
	 *            ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Supersankari suppe = new Supersankari(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ");
		suppe.tulosta(System.out);
		System.out.println("Testi");
		Supersankari supis = new Supersankari();
		supis.alustaToimivaksi();
		supis.tulosta(System.out);

	}





	/**
	 * @return palauttaa ekan k‰ytt‰j‰‰ kiinnostavan kent‰n indeksin. K‰ytt‰j‰‰
	 *         ei kiinnosta esim. id.
	 */
	public int ekaKentta() {
		return 1;
	}


	/**
	 * Palauttaa k:n kent‰n
	 *
	 * @param k
	 *            kent‰n indeksi
	 * @return kent‰n
	 */
	public Kentta anna(int k) {
		return kentat[k];
	}


	/**
	 * Parametriton muodostaja
	 */
	public Supersankari() {
	}


	/**
	 * Muodostetaan Supersankari merkkijonon tietojen perusteella
	 *
	 * @param tiedot
	 * @example <pre name="test">
	 * Supersankari battis = new Supersankari("Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1          | 800     ");
	 * Supersankari captis = new Supersankari("Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2          | 2100     ");
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
		aseta(0, rekisteroi() + "");
		for (int k = 1; k <= tiedot.length; k++) {
			aseta(k, tiedot[k - 1]);
		}

	}





	/**
	 * Asettaa k:n kent‰n arvoksi parametrina tuodun merkkijonon arvon
	 *
	 * @param k
	 *            kuinka monennen kent‰n arvo asetetaan
	 * @param jono
	 *            jonoa joka asetetaan kent‰n arvoksi
	 * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
	 * @example <pre name="test">
	 *   Supersankari jasen = new Supersankari();
	 *   jasen.aseta(1,"Ankka Aku") === null;
	 *   jasen.aseta(2,"030201-111C") === null;
	 * </pre>
	 */
	public String aseta(int k, String jono) {
		try {
			String virhe = kentat[k].aseta(jono.trim());
			if (virhe == null && k == 0)
				setTunnusNro(getId());
			if (virhe == null)
				return virhe;
			return getKysymys(k) + ": " + virhe;
		} catch (Exception ex) {
			return "Virhe: " + ex.getMessage();
		}
	}


	/**
	 * Palauttaa k:tta j‰senen kentt‰‰ vastaavan kysymyksen
	 *
	 * @param k
	 *            kuinka monennen kent‰n kysymys palautetaan (0-alkuinen)
	 * @return k:netta kentt‰‰ vastaava kysymys
	 */
	public String getKysymys(int k) {
		try {
			return kentat[k].getKysymys();
		} catch (Exception ex) {
			return "ƒ‰liˆ";
		}
	}


	/**
	 * Asettaa tunnusnumeron ja samalla varmistaa ett‰ seuraava numero on aina
	 * suurempi kuin t‰h‰n menness‰ suurin.
	 *
	 * @param nr
	 *            asetettava tunnusnumero
	 * @return asetettu tunnusnumero
	 */
	protected int setTunnusNro(int nr) {
		IntKentta k = ((IntKentta) (kentat[0]));
		k.setValue(nr);
		if (nr >= seuraavaNro)
			seuraavaNro = nr + 1;
		return k.getValue();
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
		IntKentta k = ((IntKentta) (kentat[0]));
		int id = seuraavaNro;
		k.setValue(id);
		seuraavaNro = id + 1;
		return k.getValue();

	}


	/**
	 * Alustetaan supersankari toimivaksi jotta sit‰ voi k‰ytt‰‰ ja pystyt‰‰n
	 * testailemaan
	 */
	public void alustaToimivaksi() {
		int id = this.rekisteroi();
		aseta(0, id + "");
		aseta(1, "Captain America" + id);
		aseta(2, "Rogers Steve");
		aseta(3, "Stark Tony");
		aseta(4, "Brooklyn");
		aseta(5, "A2");
		aseta(6, 800 + "");
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
		return ((IntKentta) (kentat[0])).getValue();
	}


	/**
	 * Aliohjelma joka muuttaa olion voimanumeroa.
	 *
	 * @param voimakkuus
	 *            voimakkuus jolla muutetaan
	 */
	public void muutaVoimanumeroa(int voimakkuus) {
		IntKentta k;
		for (int i = 1; i < kentat.length; i++) {
			if (kentat[i].getKysymys().equals("Voimanumero")) {
				k = ((IntKentta) (kentat[i]));
				k.setValue(((IntKentta) (kentat[i])).getValue() + voimakkuus);
				break;
			}
		}
	}


	/**
	 * @return supersankarin merkkijonona. T‰m‰ on se mik‰ pistet‰‰n tiedostoon
	 * @example
	 * <pre name="test">
	 * Supersankari supis = new Supersankari();
	 * supis.alustaToimivaksi();
	 * ; // ei toimi koska id-numero poukkoilee, mutta muuten onnistuu supis.merkkijonoksi() === "5|Captain America|Rogers Steve|Stark Tony|Brooklyn|A2|800";
	 * </pre>
	 */
	public String merkkijonoksi() {
		StringBuilder jono = new StringBuilder();
		for (int i = 0; i < kentat.length; i++) {
			jono.append(kentat[i].toString()+"|");
		}
		return jono.toString();
	}


	/**
	 * Tulostetaan henkilˆn tiedot
	 *
	 * @param out
	 *            tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("%03d", ((IntKentta) (kentat[0])).getValue(),
				3));
		for (int i = 1; i < kentat.length; i++) {
			out.println(kentat[i].toString());
		}
	}


	/**
	 * @return montako kentt‰‰ supiksella on
	 */
	public int getKenttia() {
		return kentat.length;
	}


	/**
	 * @return supersankarin stage namen
	 * @example <pre name="test">
	 * Supersankari supis = new Supersankari();
	 * supis.alustaToimivaksi();
	 * supis.getStageName().startsWith("Captain America") === true;
	 * </pre>
	 */
	public String getStageName() {
		for (int i = 1; i < kentat.length; i++) {
			if (kentat[i].getKysymys().equals("Stage name")) {
				return kentat[i].toString();
			}
		}
		return "";
	}


	/**
	 * @return supiksen voimanumeron
	 * @example <pre name="test">
	 * Supersankari supe = new Supersankari("Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ");
	 * supe.getVoimanumero() === 0;
	 * supe.muutaVoimanumeroa(500);
	 * supe.getVoimanumero() === 500;
	 * </pre>
	 */
	public int getVoimanumero() {
		IntKentta k;
		for (int i = 1; i < kentat.length; i++) {
			if (kentat[i].getKysymys().equals("Voimanumero")) {
				k = ((IntKentta) (kentat[i]));
				return k.getValue();
			}
		}
		return 0;
	}


	/**
	 * Tulostetaan henkilˆn tiedot
	 *
	 * @param os
	 *            tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
		tulosta(new PrintStream(os));
	}

}

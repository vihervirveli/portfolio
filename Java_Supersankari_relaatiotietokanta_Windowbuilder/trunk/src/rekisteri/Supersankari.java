package rekisteri;

import java.io.*;
import java.util.Comparator;

/**
 * Supersankari-luokka. Supersankari tiet‰‰ omat tietonsa ja etenkin
 * id-numeronsa
 *
 * @author majosalo
 * @version 22.2.2013
 * @version 24.4.2013
 */
public class Supersankari implements Cloneable {

	private Kentta kentat[] = { // valitettavasti ei voi olla final
			// vaikka pit‰isi, clone est‰‰ t‰m‰n :-(
			new IntKentta("id"),
			new JonoKentta("Stage name"),
			new JonoKentta("Nimi"),
			new JonoKentta("Puoliso"),
			new JonoKentta("Kotikaupunki"),
			new JonoKentta("JLU-yhteysnumero", new RegExpTarkistaja(
					"[a-zA-Z][0-9]")),
			new IntKentta("Voimanumero", new RegExpTarkistaja("[0-9]")) };

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
		try {
			Supersankari klooni = supis.clone();
			klooni.setTunnusNro(100);
			if (supis.getId() != klooni.getId())
				System.out.println("Kloonaus onnistui");
		} catch (CloneNotSupportedException e) {
			System.out.println("Kloonaus on viel‰ liian kaukana tulevaisuudessa");
		}

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
	 * Tehd‰‰n identtinen klooni supersankarista
	 *
	 * @return Object kloonattu supis
	 * @example <pre name="test">
	 * #THROWS CloneNotSupportedException
	 *   Supersankari supe = new Supersankari();
	 *   Supersankari kopio = supe.clone();
	 *   kopio.merkkijonoksi() === supe.merkkijonoksi();
	 *   supe.setTunnusNro(100);
	 *   supe.getId() == kopio.getId() === false;
	 * </pre>
	 */
	@Override
	public Supersankari clone() throws CloneNotSupportedException {
		Supersankari uusi;
		uusi = (Supersankari) super.clone();
		uusi.kentat = kentat.clone();

		for (int k = 0; k < getKenttia(); k++)
			uusi.kentat[k] = kentat[k].clone();
		return uusi;
	}

	/**
	 * Luokka joka vertaa kahta j‰sent‰ kesken‰‰n
	 */
	public static class Vertailija implements Comparator<Supersankari> {

		private final int kenttanro;


		/**
		 * Alustetaan vertailija vertailemaan tietyn kent‰n perusteella
		 *
		 * @param k
		 *            vertailtavan kent‰n indeksi.
		 */
		public Vertailija(int k) {
			this.kenttanro = k;
		}


		/**
		 * Verrataana kahta supersankaria kesken‰‰n.
		 *
		 * @param supe1
		 *            1. verrattava supersankari
		 * @param supe2
		 *            2. verrattava supersankari
		 * @return <0 jos supe1 < supe2, == 0 jos supe1 == supe2 ja muuten >0
		 */
		@Override
		public int compare(Supersankari supe1, Supersankari supe2) {
			String s1 = supe1.anna(kenttanro).getAvain();
			String s2 = supe2.anna(kenttanro).getAvain();

			return s1.compareTo(s2);

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
	public int setTunnusNro(int nr) {
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
	 * @example <pre name="test">
	 * Supersankari supis = new Supersankari();
	 * supis.alustaToimivaksi();
	 * ; // ei toimi koska id-numero poukkoilee, mutta muuten onnistuu
	 * // supis.merkkijonoksi() ===
	 * // &quot;5|Captain America|Rogers Steve|Stark Tony|Brooklyn|A2|800&quot;;
	 * </pre>
	 */
	public String merkkijonoksi() {
		StringBuilder jono = new StringBuilder();
		for (int i = 0; i < kentat.length; i++) {
			jono.append(kentat[i].toString() + "|");
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

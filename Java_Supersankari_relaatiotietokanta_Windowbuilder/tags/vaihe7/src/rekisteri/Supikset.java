package rekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Supikset-luokka joka tiet‰‰ montako supista on yms.
 *
 * @author majosalo
 * @version 22.2.2013
 * @version 25.4.2013
 */
public class Supikset implements Iterable<Supersankari> {
	private static final int MAX_Supiksia = 5;
	private int montakoSupista = 0;
	private String tiedostonNimi = "";
	private Supersankari[] supikset = new Supersankari[MAX_Supiksia]; // siell‰
																		// ne
																		// supikset
																		// sitten
																		// on.
																		// ok,
																		// t‰ss‰
																		// vaiheessa
																		// viel‰
																		// 5
																		// null-viitett‰,
																		// mutta
																		// piakkoin.
	private String tiedNimi = "supis.dat";


	/**
	 * Testip‰‰ohjelma
	 *
	 * @param args
	 *            ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Supikset supiksit = new Supikset();

		Supersankari suppe = new Supersankari(), suppe2 = new Supersankari(), suppe3 = new Supersankari();
		suppe.alustaToimivaksi();
		suppe2.alustaToimivaksi();

		supiksit.lisaaTyhjaSupis(suppe);

		supiksit.lisaaTyhjaSupis(suppe2);
		String tiedostoNimi = "supis.dat";
		supiksit.lisaaSupis(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ",
				tiedostoNimi);
		supiksit.lisaaSupis(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ",
				tiedostoNimi);
		supiksit.lisaaSupis(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ",
				tiedostoNimi);
		supiksit.lisaaSupis(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ",
				tiedostoNimi);
		supiksit.lisaaSupis(
				"Batman          | Wayne Bruce        | Kyle Selina   | Gotham City | A2 | 1               ",
				tiedostoNimi);
		suppe3.alustaToimivaksi();
		supiksit.lisaaTyhjaSupis(suppe3);
		System.out.println("========== Supersankarit testi ==============");
		int iNro = 1;
		for (Iterator<Supersankari> i = supiksit.iterator(); i.hasNext();) {

			Supersankari supe = i.next();
			if (supe != null) {
				System.out.println("Supersankari nro: " + iNro);
				supe.tulosta(System.out);
				iNro++;
				System.out.println("");
			}
		}

	}


	/**
	 * Muodostetaan supikset
	 */
	public Supikset() {

		lue();
	}


	/**
	 * Aliohjelma joka lukee tiedostosta supikset
	 */
	public void lue() {

		try (BufferedReader f = new BufferedReader(new FileReader(tiedNimi))) {
			String rivi;
			while ((rivi = f.readLine()) != null) {
				luoRivistaSupis(rivi);
			}
		} catch (IOException ex) {
			System.err.println("En toimi koska: " + ex.getMessage());
		}
	}


	/**
	 * Aliohjelma joka rivin perusteella luo supiksen ja lis‰‰ sen taulukkoon
	 * Eroaa siin‰ normaalista muodostajasta ett‰ nyt luodaan tiedostosta, eli
	 * siell‰ on jo valmiina id
	 *
	 * @param rivi
	 *            rivi jossa supiksen tiedot on
	 */
	public void luoRivistaSupis(String rivi) {
		Supersankari supe = new Supersankari();
		rivi.trim();
		String[] tiedot = rivi.split("\\|");
		for (int k = 0; k < tiedot.length; k++) {
			supe.aseta(k, tiedot[k]);
		}
		lisaaTyhjaSupis(supe);

	}


	/**
	 * Heivaa alemmas kutsun tallentaa supis jonka tiedot ovat muuttuneet ja sen
	 * kamut
	 *
	 * @param supe
	 *            muuttunut supe
	 * @param tiedot
	 *            muuttuneet tiedot
	 */
	public void tallennaSupisJaKaverit(Supersankari supe, String tiedot) {
		File tiedosto = new File("supis.dat");
		tiedosto.delete();
		try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto,
				false))) {

			for (int i = 0; i < supikset.length; i++) {
				if (anna(i) != null) {
					if (anna(i).getStageName().equals(supe.getStageName()))
						fo.println(tiedot);
					else
						fo.println(anna(i).merkkijonoksi());
				}
			}

		} catch (IOException ex) {
			System.err.println("Tiedoston k‰sittelyss‰ vika: "
					+ ex.getMessage());
		}

	}


	/**
	 * Aliohjelma joka lis‰‰ halutun supiksen voimanumeroa
	 *
	 * @param supisId
	 *            supis jonka voimanumeroa rukataan
	 * @param voimakkuus
	 *            voimakkuus jolla rukataan
	 * @example <pre name="test">
	 * Supikset supikset = new Supikset();
	 * String supis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * supikset.lisaaSupis(supis, "testit.txt" ) === true;
	 * supikset.muutaVoimanumeroa(supikset.annaNimenId("Captain America"), 500);
	 * </pre>
	 */
	public void muutaVoimanumeroa(int supisId, int voimakkuus) {
		for (Iterator<Supersankari> i = iterator(); i.hasNext();) {
			Supersankari supe = i.next();
			if (supe.getId() == supisId) {
				supe.muutaVoimanumeroa(voimakkuus);
				break;
			}

		}
	}


	/**
	 * Palauttaa supersankaritaulukon i. indeksiss‰ sijaitsevan supersankarin
	 *
	 * @param i
	 *            indeksi jossa etsitty supis sijaitsee
	 * @return supersankari jota etsittiin
	 * @example
	 * @throws IndexOutOfBoundsException
	 *             jos i ei ole sallitulla alueella
	 *
	 *             <pre name="test">
	 * #THROWS SailoException
	 * Supikset supikset = new Supikset();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.lisaaTyhjaSupis(supis2);
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.anna(0).getStageName() === "Batman";
	 * supikset.anna(1).getStageName() === "Captain America";
	 * supikset.anna(2).getStageName() === "Iron Man";
	 * supikset.anna(1).getStageName() == "Iron Man" === false;
	 * supikset.anna(2).getStageName() === "Iron Man";
	 * </pre>
	 */
	public Supersankari anna(int i) throws IndexOutOfBoundsException {

		if (i < 0 || i > supikset.length - 1) {
			throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
		}
		return supikset[i];
	}


	/**
	 * Aliohjelma joka kertoo onko jollakulla muulla jo sama id
	 *
	 * @param id
	 *            id jota k‰ytt‰j‰ tarjoaa
	 * @return true jos on, false jos ei
	 */
	public boolean loytyykoIdJo(int id) {
		for (int i = 0; i < supikset.length; i++) {
			if (anna(i).getId() == id)
				return true;
		}
		return false;
	}


	/**
	 * @return supikset-taulukon
	 */
	public Supersankari[] annaSupiksetTaulukossa() {
		return supikset;
	}


	/**
	 * Palauttaa supersankaritaulukosta nimen perusteella supiksen id:n
	 *
	 * @param stageName
	 *            nimi jolla supersankaria etsit‰‰n
	 * @return supersankarin id
	 * @example <pre name="test">
	 * Supikset supikset = new Supikset();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * supikset.lisaaSupis(battis, "testit.txt") === true;
	 * supikset.lisaaSupis(captis, "testit.txt") === true;
	 *    int n1 = supikset.annaNimenId("Batman");
	 *    int n2 = supikset.annaNimenId("Captain America");
	 * n1 === n2-1;
	 * </pre>
	 */
	public int annaNimenId(String stageName) {
		for (int i = 0; i < supikset.length; i++) {
			if (anna(i) != null && anna(i).getStageName().equals(stageName))
				return anna(i).getId();
		}
		return -1;
	}


	/**
	 * Palauttaa supersankaritaulukosta nimen perusteella supiksen id:n
	 *
	 * @param stageName
	 *            nimi jolla supersankaria etsit‰‰n
	 * @return supersankarin id
	 * @example <pre name="test">
	 * Supikset supikset = new Supikset();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * supikset.lisaaSupis(battis, "testit.txt") === true;
	 * supikset.lisaaSupis(captis, "testit.txt") === true;
	 * Supersankari supe = supikset.annaSupisNimenPerusteella("Batman");
	 * supe.getStageName() === "Batman";
	 * </pre>
	 */
	public Supersankari annaSupisNimenPerusteella(String stageName) {
		for (int i = 0; i < supikset.length; i++) {
			if (anna(i) != null && anna(i).getStageName().equals(stageName))
				return anna(i);
		}
		return null;
	}


	/**
	 * Lis‰‰ uuden supiksen tietorakenteeseen. Ottaa supiksen omistukseensa.
	 *
	 * @param supis
	 *            lis‰t‰‰v‰n j‰senen viite. Huom tietorakenne muuttuu
	 *            omistajaksi
	 * @throws SailoException
	 *             jos tietorakenne on jo t‰ynn‰
	 * @example <pre name="test">
	 *
	 * Supikset supikset = new Supikset();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.lisaaTyhjaSupis(supis2);
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.anna(0).getStageName() === "Batman";
	 * supikset.anna(1).getStageName() === "Captain America";
	 * supikset.anna(2).getStageName() === "Iron Man";
	 * supikset.anna(0).getStageName() == "Captain America" === false;
	 *
	 * supikset.anna(1).getStageName()  === "Captain America";
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.lisaaTyhjaSupis(supis1);
	 * supikset.lisaaTyhjaSupis(supis1);
	 * </pre>
	 */
	public void lisaaTyhjaSupis(Supersankari supis) {
		if (montakoSupista >= supikset.length)
			kasvataTaulukkoa();
		supikset[montakoSupista] = supis;
		montakoSupista++;

	}


	/**
	 * Palauttaa "taulukossa" hakuehtoon vastaavien supisten viitteet
	 *
	 * @param hakuehto
	 *            hakuehto eli mit‰ k‰ytt‰j‰ on kirjoittanut
	 * @param k
	 *            etsitt‰v‰n kent‰n indeksi eli mik‰ comboboxi kyseess‰
	 * @return tietorakenteen lˆytyneist‰ supiksista
	 */
	public List<Supersankari> etsi(String hakuehto, int k) {
		// List<Supersankari> loytyneet = new ArrayList<Supersankari>();
		//
		// for (int i = 0; i < supikset.length; i++) {
		// if (anna(i) != null) {
		// String avain;
		// if (anna(i).anna(k).getAvain().length() <= hakuehto.length()) {
		// avain = anna(i).anna(k).getAvain()
		// .substring(0, hakuehto.length());
		// if ((avain.equalsIgnoreCase(hakuehto)) ||
		// anna(i).anna(k).getAvain().contains(hakuehto))
		// loytyneet.add(anna(i));
		//
		// }
		// }
		//
		// }
		// // Collections.sort(loytyneet, new Supersankari.Vertailija(k));
		// return loytyneet;

		List<Supersankari> loytyneet = new ArrayList<Supersankari>();
		for (Supersankari supe : this) {
			if (supe != null) {
				if (WildChars.onkoSamat(supe.anna(k).getAvain(), hakuehto))
					loytyneet.add(supe);
				// if (
				// jasen.anna(k).toUpperCase().startsWith(hakuehto.toUpperCase())
				// ) loytyneet.add(jasen);
			}
		}
		Collections.sort(loytyneet, new Supersankari.Vertailija(k));
		return loytyneet;
	}


	/**
	 * Lis‰‰ uuden supiksen tietorakenteeseen. Ottaa supiksen omistukseensa.
	 *
	 * @param tiedot
	 *            lis‰tt‰v‰n supiksen tiedot merkkijonona. Huom tietorakenne
	 *            muuttuu omistajaksi
	 * @param tiedostoNimi
	 *            tiedosto johon lisaystehd‰‰n
	 * @throws SailoException
	 *             jos tietorakenne on jo t‰ynn‰
	 * @example <pre name="test">
	 * Supikset supikset = new Supikset();
	 * String supis1 = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String supis2 = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * supikset.lisaaSupis(supis1, "testit.txt") === true;
	 * supikset.lisaaSupis(supis2, "testit.txt") === true;
	 * supikset.lisaaSupis(supis1, "testit.txt") === true;
	 * supikset.anna(0).getStageName().equals("Batman") === true;
	 * supikset.anna(1).getStageName().equals("Captain America") === true;
	 * supikset.anna(2).getStageName().equals("Batman") === false;
	 * supikset.anna(1).getStageName().equals("Batman") === false;
	 * supikset.anna(1).getStageName().equals("Captain America") === true;
	 * </pre>
	 */
	public boolean lisaaSupis(String tiedot, String tiedostoNimi) {
		Supersankari suppe = new Supersankari(tiedot);
		if (montakoSupista >= supikset.length) {
			kasvataTaulukkoa();
		}

		supikset[montakoSupista++] = suppe;
		kirjoitaUusiTiedostoon(tiedostoNimi);
		return true;
	}


	/**
	 * Aliohjelma joka kirjoittaa uuden supikseen tiedostoonkin
	 */
	public void kirjoitaUusiTiedostoon(String tiedostoNimi) {
		try (PrintStream fo = new PrintStream(new FileOutputStream(
				tiedostoNimi, false))) {

			for (int i = 0; i < supikset.length; i++) {
				if (anna(i) != null) {
					fo.println(anna(i).merkkijonoksi());
				}
			}

		} catch (IOException ex) {
			System.err.println("Tiedoston k‰sittelyss‰ vika: "
					+ ex.getMessage());
		}

	}


	/**
	 * Kasvattaa supikset-taulukkoa
	 */
	public void kasvataTaulukkoa() {
		Supersankari[] supet = new Supersankari[supikset.length * 2];
		for (int i = 0; i < supikset.length; i++) {
			supet[i] = supikset[i];
		}
		supikset = supet;
	}


	/**
	 * Poistaa supiksen stage namen perusteella
	 *
	 * @param stageName
	 *            poistettavan supiksen viite.
	 * @throws SailoException
	 *             jos homma menee vikaan
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Supikset supikset = new Supikset();
	 * String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * supikset.lisaaSupis(battis, "testit.txt") === true;
	 * supikset.lisaaSupis(captis, "testit.txt") === true;
	 * supikset.poistaSupis("Captain America");
	 * </pre>
	 */
	public void poistaSupis(String stageName) {
		for (int i = 0; i < supikset.length; i++) {
			if (anna(i) != null && anna(i).getStageName().startsWith(stageName)) { // tyhjien
																					// supiksien
																					// testaamisen
																					// vuoksi
																					// n‰in,
																					// oikeasti
																					// equals)
				supikset[i] = null;
				montakoSupista--;
				break;
			}
		}

	}


	/**
	 * @return supersankarien lukum‰‰r‰n taulukossa
	 */
	public int getMontakoSupista() {
		return montakoSupista;
	}


	/**
	 * Lukee supersankarit tiedostosta. Ei toimi viel‰!
	 *
	 * @param tiedosto
	 *            tiedoston nimen alkuosa
	 * @throws SailoException
	 *             jos lukeminen ep‰onnistuu
	 */
	public void lueTiedostosta(String tiedosto) throws SailoException {
		tiedostonNimi = tiedosto + ".dat";
		throw new SailoException("Ei osata viel‰ lukea tiedostoa "
				+ tiedostonNimi);
	}


	/**
	 * Tallentaa j‰senistˆn tiedostoon. Ei toimi viel‰!
	 *
	 * @throws SailoException
	 *             jos talletus ep‰onnistuu
	 */
	public void talleta() throws SailoException {
		throw new SailoException("Ei osata viel‰ tallettaa tiedostoa "
				+ tiedostonNimi);
	}

	/**
	 * Vipelt‰j‰-luokka supiksille
	 *
	 * @author majosalo
	 * @version 28.2.2013
	 *
	 */
	public class SupiksetIterator implements Iterator<Supersankari> {
		private int kohdalla = -1;


		/**
		 * Onko olemassa viel‰ seuraavaa supista
		 *
		 * @see java.util.Iterator#hasNext()
		 * @return true jos on viel‰ supiksia
		 */
		public boolean hasNext() {
			// if ( kohdalla + 1 >= lkm ) return false;
			// return true;
			return kohdalla + 1 < supikset.length;
		}


		/**
		 * Annetaan seuraava supis
		 *
		 * @return seuraava supis
		 * @throws NoSuchElementException
		 *             jos seuraava alkiota ei en‰‰ ole
		 * @see java.util.Iterator#next()
		 */
		public Supersankari next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("Ei oo");
			kohdalla++;
			return supikset[kohdalla];
		}


		/**
		 * Tuhoamista ei ole toteutettu
		 *
		 * @throws UnsupportedOperationException
		 *             aina
		 * @see java.util.Iterator#remove()
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Myˆ ei poisteta");
		}
	}


	/**
	 * Palautetaan iteraattori supiksista.
	 *
	 * @return supikset iteraattori
	 */
	public Iterator<Supersankari> iterator() {
		// return alkiot.iterator();
		return new SupiksetIterator();
	}

}

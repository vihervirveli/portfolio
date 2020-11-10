package rekisteri;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Kyvyt-luokka jossa asuu Kyky[]
 *
 * @author majosalo
 * @version 27.2.2013
 * @version 22.4.2013
 */
public class Kyvyt {
	private static final int MAX_KYKYJA = 10;
	private Kyky[] kyvyt = new Kyky[MAX_KYKYJA];
	private int montakoKykya = 0;


	/**
	 * Muodostetaan kyvyt-luokka
	 */
	public Kyvyt() {
		lue("kyvyt.dat");
	}


	/**
	 * Lukee tiedostosta kyvyt taulukkoon
	 *
	 * @param tiedNimi
	 *            luettavan tiedoston nimi
	 */
	public void lue(String tiedNimi) {
		try (BufferedReader f = new BufferedReader(new FileReader(tiedNimi))) {
			String rivi;
			while ((rivi = f.readLine()) != null) {
				luoRivistaKyky(rivi);
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
	 * @throws SailoException
	 * @example <pre name="test">
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * </pre>
	 */
	public void luoRivistaKyky(String rivi) {
		rivi.trim();
		Kyky kyky = new Kyky();
		String[] tiedot = rivi.split("\\|");
		for (int k = 0; k < tiedot.length; k++) {
			kyky.aseta(k, tiedot[k]);
		}

		lisaaKykyOlio(kyky);

	}


	/**
	 * @return Kyky[]-taulukon
	 */
	public Kyky[] annaKyvyt() {
		return kyvyt;
	}


	/**
	 * @return montako kyky‰ t‰ll‰ hetkell‰ on
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * </pre>
	 */
	public int getMontakoKykya() {
		return montakoKykya;

	}


	/**
	 * Kasvattaa kyvyt-taulukkoa
	 */
	public void kasvataTaulukkoa() {
		Kyky[] kyvytUusi = new Kyky[kyvyt.length * 2];
		for (int i = 0; i < kyvyt.length; i++) {
			kyvytUusi[i] = kyvyt[i];
		}
		kyvyt = kyvytUusi;
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
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(1).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public Kyky anna(int i) throws IndexOutOfBoundsException {

		if (i < 0 || i > kyvyt.length - 1) {
			throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
		}
		return kyvyt[i];
	}


	/**
	 * Lis‰‰ uuden kyvyn tietorakenteeseen.
	 *
	 * @param uudenKyvynNimi
	 *            lis‰tt‰v‰n kyvyn nimi
	 * @throws SailoException
	 *             jos tietorakenne on jo t‰ynn‰
	 * @example <pre name="test">
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("Projektiilit", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(1).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public void lisaaKyky(String uudenKyvynNimi, String tiedostoNimi) {

		if (montakoKykya >= kyvyt.length)
			kasvataTaulukkoa();
		Kyky kyky = new Kyky(uudenKyvynNimi);
		kyvyt[montakoKykya] = kyky;
		montakoKykya++;
		lueUusiTiedostoon(tiedostoNimi);
	}


	/**
	 * Lukee uuden kyvyn tiedostoon
	 * @param tiedostoNimi tiedosto johon kirjoitetaan
	 */
	public void lueUusiTiedostoon(String tiedostoNimi) {
		try (PrintStream fo = new PrintStream(new FileOutputStream(
				tiedostoNimi, false))) {

			for (int i = 0; i < kyvyt.length; i++) {
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
	 * Lis‰‰ uuden kyvyn tietorakenteeseen.
	 *
	 * @param uudenKyvynNimi
	 *            lis‰tt‰v‰n kyvyn nimi
	 * @throws SailoException
	 *             jos tietorakenne on jo t‰ynn‰
	 * @example <pre name="test">
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("Projektiilit", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(1).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public void lisaaKykyOlio(Kyky uusiKyky) {

		if (montakoKykya >= kyvyt.length)
			kasvataTaulukkoa();
		kyvyt[montakoKykya] = uusiKyky;
		montakoKykya++;
	}


	/**
	 * Lis‰‰ uuden kyvyn tietorakenteeseen.
	 *
	 * @param uudenKyvynNimi
	 *            lis‰tt‰v‰n kyvyn nimi
	 * @throws SailoException
	 *             jos tietorakenne on jo t‰ynn‰
	 * @example <pre name="test">
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("Projektiilit", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(1).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public void lisaaKyky(Kyky uusKyky) {
		if (montakoKykya >= kyvyt.length)
			kasvataTaulukkoa();
		kyvyt[montakoKykya] = uusKyky;
		montakoKykya++;
	}


	/**
	 * Palauttaa kykytaulukosta nimen perusteella kyvyn id:n
	 *
	 * @param kyvynNimi
	 *            nimi jolla kyky‰ etsit‰‰n
	 * @return kyvyn id
	 * @example <pre name="test">
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt");
	 * kyvyt.lisaaKyky("Tuli", "testit.txt");
	 *    int n1 = kyvyt.annaKyvynId("Tuli");
	 *    int n2 = kyvyt.annaKyvynId("J‰‰");
	 * n1 === n2-1;
	 * </pre>
	 */
	public int annaKyvynId(String kyvynNimi) {
		for (int i = 0; i < montakoKykya; i++) {
			if (anna(i) != null && anna(i).getKyvynNimi().equals(kyvynNimi))
				return anna(i).getId();
		}
		return -1;
	}


	/**
	 * Aliohjelma joka palauttaa kyvyn nimen sen id:n perusteella
	 *
	 * @param id
	 *            id jolla etsit‰‰n
	 * @return kyvyn nimen
	 */
	public String annaKyvynNimi(int id) {
		for (int i = 0; i < montakoKykya; i++) {
			if (anna(i) != null && anna(i).getId() == id)
				return anna(i).getKyvynNimi();
		}
		return null;
	}


	/**
	 * Poistaa kyvyn sen nimen perusteella
	 *
	 * @param kyvynNimi
	 *            poistettavan kyvyn nimi.
	 * @throws SailoException
	 *             jos homma menee vikaan
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 8;
	 * kyvyt.lisaaKyky("J‰‰", "testit.txt"); kyvyt.getMontakoKykya() === 9;
	 * kyvyt.lisaaKyky("Tuli", "testit.txt"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.lisaaKyky("Projektiilit", "testit.txt"); kyvyt.getMontakoKykya() === 11;
	 * kyvyt.anna(0).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(1).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * kyvyt.poistaKyky("J‰‰"); kyvyt.getMontakoKykya() === 10;
	 * kyvyt.poistaKyky("Tuli"); kyvyt.getMontakoKykya() === 9;
	 * </pre>
	 */
	public void poistaKyky(String kyvynNimi) {
		for (int i = 0; i < kyvyt.length; i++) {
			if (anna(i) != null && anna(i).getKyvynNimi() == kyvynNimi) {
				kyvyt[i] = null;
				montakoKykya--;
				break;
			}
		}

	}


	/**
	 * Testip‰‰ohjelma
	 *
	 * @param args
	 *            ei k‰ytˆss‰
	 * @throws SailoException
	 */
	public static void main(String[] args) throws SailoException {
		Kyvyt kyvyt = new Kyvyt();
		System.out.println();
		String testi = "testit.txt";
		kyvyt.lisaaKyky("J‰‰", testi);
		kyvyt.lisaaKyky("Tuli", testi);
		kyvyt.lisaaKyky("Projektiilit", testi);

		System.out.println("============= Kyvyt testi =================");

		for (int i = 0; i < kyvyt.kyvyt.length; i++) {
			if (kyvyt.anna(i) != null) {
				Kyky kyky = kyvyt.anna(i);
				System.out.println("Kyky nro: " + (i + 1)); // ei siis id,
															// vaan se
															// mist‰ ne
															// lˆytyy
															// taulukosta
				kyky.tulosta(System.out);
				System.out.println();
			}
		}

	}

	/**
	 * Vipelt‰j‰-luokka kyvyille
	 *
	 * @author majosalo
	 * @version 28.2.2013
	 *
	 */
	public class KyvytIterator implements Iterator<Kyky> {
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
			return kohdalla + 1 < kyvyt.length;
		}


		/**
		 * Annetaan seuraava kyky
		 *
		 * @return seuraava kyky
		 * @throws NoSuchElementException
		 *             jos seuraava alkiota ei en‰‰ ole
		 * @see java.util.Iterator#next()
		 */
		public Kyky next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("Ei oo");
			kohdalla++;
			return kyvyt[kohdalla];
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
	public Iterator<Kyky> iterator() {
		// return alkiot.iterator();
		return new KyvytIterator();
	}
}

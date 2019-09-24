package rekisteri;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Kyvyt-luokka jossa asuu Kyky[]
 *
 * @author majosalo
 * @version 27.2.2013
 * @version 28.2.2013
 */
public class Kyvyt {
	private static final int MAX_KYKYJA = 10;
	private Kyky[] kyvyt = new Kyky[MAX_KYKYJA];
	private int montakoKykya = 0;

	/**
	 * @return montako kyky‰ t‰ll‰ hetkell‰ on
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 0;
	 * kyvyt.lisaaKyky("J‰‰"); kyvyt.getMontakoKykya() === 1;
	 * kyvyt.lisaaKyky("Tuli"); kyvyt.getMontakoKykya() === 2;
	 * kyvyt.lisaaKyky("Tuli"); kyvyt.getMontakoKykya() === 3;
	 * </pre>
	 */
	public int getMontakoKykya() {
		return montakoKykya;

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
	 * kyvyt.getMontakoKykya() === 0;
	 * kyvyt.lisaaKyky("J‰‰"); kyvyt.getMontakoKykya() === 1;
	 * kyvyt.lisaaKyky("Tuli"); kyvyt.getMontakoKykya() === 2;
	 * kyvyt.lisaaKyky("J‰‰"); kyvyt.getMontakoKykya() === 3;
	 * kyvyt.anna(0).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(1).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(2).getKyvynNimi() === "J‰‰";
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
	 * #THROWS SailoException
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.getMontakoKykya() === 0;
	 * kyvyt.lisaaKyky("J‰‰"); kyvyt.getMontakoKykya() === 1;
	 * kyvyt.lisaaKyky("Tuli"); kyvyt.getMontakoKykya() === 2;
	 * kyvyt.lisaaKyky("Projektiilit"); kyvyt.getMontakoKykya() === 3;
	 * kyvyt.anna(0).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(1).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public void lisaaKyky(String uudenKyvynNimi) throws SailoException {
		// TODO varmista ettei kahta samannimist‰ kyky‰ ole
		if (montakoKykya >= kyvyt.length)
			throw new SailoException("Liikaa alkioita");
		Kyky kyky = new Kyky(uudenKyvynNimi);
		kyvyt[montakoKykya] = kyky;
		montakoKykya++;
	}

	/**
	 * Palauttaa kykytaulukosta nimen perusteella kyvyn id:n
	 *
	 * @param kyvynNimi
	 *            nimi jolla kyky‰ etsit‰‰n
	 * @return kyvyn id
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * Kyvyt kyvyt = new Kyvyt();
	 * kyvyt.lisaaKyky("J‰‰");
	 * kyvyt.lisaaKyky("Tuli");
	 *    int n1 = kyvyt.annaKyvynId("J‰‰");
	 *    int n2 = kyvyt.annaKyvynId("Tuli");
	 * n1 === n2-1;
	 * </pre>
	 */
	public int annaKyvynId(String kyvynNimi)  {
		for (int i = 0; i < montakoKykya; i++){
			if (anna(i) != null && anna(i).getKyvynNimi().equals(kyvynNimi))
				return anna(i).getId();
		}
		return -1;
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
	 * kyvyt.getMontakoKykya() === 0;
	 * kyvyt.lisaaKyky("J‰‰"); kyvyt.getMontakoKykya() === 1;
	 * kyvyt.lisaaKyky("Tuli"); kyvyt.getMontakoKykya() === 2;
	 * kyvyt.lisaaKyky("Projektiilit"); kyvyt.getMontakoKykya() === 3;
	 * kyvyt.anna(0).getKyvynNimi() === "J‰‰";
	 * kyvyt.anna(1).getKyvynNimi() === "Tuli";
	 * kyvyt.anna(2).getKyvynNimi() === "Projektiilit";
	 * kyvyt.poistaKyky("J‰‰"); kyvyt.getMontakoKykya() === 2;
	 * kyvyt.poistaKyky("Tuli"); kyvyt.getMontakoKykya() === 1;
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
		try {
			kyvyt.lisaaKyky("J‰‰");
			kyvyt.lisaaKyky("Tuli");
			kyvyt.lisaaKyky("Projektiilit");

			System.out.println("============= Kyvyt testi =================");

			for (int i = 0; i < kyvyt.kyvyt.length; i++) {
				if (kyvyt.anna(i) != null){
				Kyky kyky = kyvyt.anna(i);
				System.out.println("Kyky nro: " + (i+1)); // ei siis id, vaan se
														// mist‰ ne lˆytyy
														// taulukosta
				kyky.tulosta(System.out);
				System.out.println();
				}
			}

		} catch (SailoException ex) {
			System.out.println(ex.getMessage());
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




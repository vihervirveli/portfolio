package rekisteri;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * KykySupisParit-luokka, jossa asuu kokoelma KykySupisPari-olioita
 *
 * @author majosalo
 * @version 5.3.2013
 * @version 25.4.2013
 */
public class KykySupisParit {
	private List<KykySupisPari> parit = new ArrayList<KykySupisPari>();
	private String tiedNimi = "kykysupisparit.dat";


	/**
	 * Muodostetaan kykysupisparit-olio
	 */
	public KykySupisParit() {
		lue();
	}


	/**
	 * Aliohjelma joka lukee tiedostosta kykysupisparit
	 */
	public void lue() {

		try (BufferedReader f = new BufferedReader(new FileReader(tiedNimi))) {
			String rivi;
			while ((rivi = f.readLine()) != null) {
				luoRivistaPari(rivi);
			}
		} catch (IOException ex) {
			System.err.println("En toimi koska: " + ex.getMessage());
		}
	}


	/**
	 * Aliohjelma joka rivin perusteella luo parin ja lisää sen taulukkoon
	 *
	 * @param rivi
	 *            rivi jossa parin tiedot on
	 */
	public void luoRivistaPari(String rivi) {
		rivi.trim();
		String[] tiedot = rivi.split("\\|");
		lisaaPari(Integer.parseInt(tiedot[0].trim()),
				Integer.parseInt(tiedot[1].trim()),
				Integer.parseInt(tiedot[2].trim()), tiedNimi);

	}


	/**
	 * Aliohjelma joka lisää uuden kykysupisparin. Jos voimakkuus on 0, ei
	 * lisätä.
	 *
	 * @param kenelle
	 *            mille supersankarille, eli idnumeroa kaivataan
	 * @param mikaKyky
	 *            mikä kyky lisätään, eli idnumeroa kaivataan
	 * @param voimakkuus
	 *            mikä voimakkuus supiksella on kyvyssä
	 * @return onnistuiko lisäys
	 * @example <pre name="test">
	 * KykySupisParit parit = new KykySupisParit();
	 * parit.lisaaPari(2, 3, 300, "testiparit.dat") === true;
	 * parit.poistaPariSupiksenPerusteella(2) === true;
	 * </pre>
	 */
	public boolean lisaaPari(int kenelle, int mikaKyky, int voimakkuus,
			String tiedostoNimi) {
		if (voimakkuus <= 0)
			return false;
		if (tiedostoNimi == null) tiedostoNimi = tiedNimi;
		KykySupisPari pari = new KykySupisPari(kenelle, mikaKyky, voimakkuus);
		parit.add(pari);
		kirjoitaUusiTiedostoon(tiedostoNimi);
		return true;
	}


	/**
	 * Aliohjelma joka kirjoittaa uuden supikseen tiedostoonkin
	 */
	public void kirjoitaUusiTiedostoon(String tiedostoNimi) {
		try (PrintStream fo = new PrintStream(new FileOutputStream(
				tiedostoNimi, false))) {

			for (int i = 0; i < parit.size(); i++) {
				if (anna(i) != null) {
					fo.println(anna(i).merkkijonoksi());
				}
			}

		} catch (IOException ex) {
			System.err.println("Tiedoston käsittelyssä vika: "
					+ ex.getMessage());
		}

	}


	/**
	 * Aliohjelma joka poistaa tiettyä supista koskevat parit
	 *
	 * @param supiksenId
	 *            supis jonka id:tä etsitään
	 * @return totuustiedon siitä poistettiinko yhtään paria
	 * @example <pre name="test">
	 * KykySupisParit parit = new KykySupisParit();
	 * parit.lisaaPari(2, 3, 300, "testiparit.dat") === true;
	 * parit.poistaPariSupiksenPerusteella(2) === true;
	 * </pre>
	 */
	public boolean poistaPariSupiksenPerusteella(int supiksenId) {
		int poistettuja = 0;
		for (int i = 0; i < parit.size(); i++) {
			if (anna(i).getSupisId() == supiksenId) {
				parit.remove(i);
				poistettuja++;
			}
		}
		if (poistettuja > 0)
			return true;
		return false;
	}


	/**
	 * Palauttaa supen id:n perusteella listan sen kykypareista
	 *
	 * @param id
	 *            supen id jonka kyvyista lista halutaan
	 * @return listan supen kyvyista
	 * @example <pre name="test">
	 * #import java.util.*;
	 * KykySupisParit parit = new KykySupisParit();
	 * List<KykySupisPari> pareet = parit.supiksenKyvyt(1);
	 * pareet.size() === 6;
	 * </pre>
	 */
	public List<KykySupisPari> supiksenKyvyt(int supiksenId) {
		List<KykySupisPari> supenparit = new ArrayList<KykySupisPari>();
		for (int i = 0; i < parit.size(); i++) {
			if (anna(i).getSupisId() == supiksenId) {
				supenparit.add(anna(i));
			}
		}
		return supenparit;
	}


	/**
	 * Palauttaa supen id:n perusteella listan niistä kyvyistä mitä sillä ei ole
	 * Eli käytännössä kykyId-listan
	 *
	 * @param kyvyt
	 *            kyvyt joita ylipäätään on
	 * @param id
	 *            supen id jonka kyvyista lista halutaan
	 * @return listan supen kyvyista
	 */
	public List<Integer> eiSupiksenKyvyt(Kyky[] kyvyt, int supiksenId) {
		List<Integer> eiSupenparit = new ArrayList<Integer>();
		List<KykySupisPari> parit = supiksenKyvyt(supiksenId);

		for (int i = 0; i < kyvyt.length; i++) {
			if (kyvyt[i] != null) {
				eiSupenparit.add(kyvyt[i].getId());
			}
		}
		// Collections.sort(eiSupenparit);

		for (int i = 0; i < eiSupenparit.size(); i++) {
			for (int k = 0; k < parit.size(); k++) {
				if (eiSupenparit.get(i) == parit.get(k).getKykyId()) {
					eiSupenparit.set(i, -1);
				}
			}
		}
		int k = 0;
		while (k < eiSupenparit.size()) {
			if (eiSupenparit.get(k) == -1)
				eiSupenparit.remove(k);
			else
				k++;
		}
		// kertaluokaltaan aivan hirveää koodia, mutta ainakin nyt se toimii.
		return eiSupenparit;
	}


	// if ((j == parit.size() - 1) && loytyiko == false)
	// eiSupenparit.add(kyvyt[i].getId());

	/**
	 * Aliohjelma joka poistaa tietyltä supikseltä kyvyn
	 *
	 * @param supiksenId
	 *            supis jolta poistetaan
	 * @param kykyId
	 *            kyky joka siltä poistetaan
	 * @return totuustieto siitä poistettiinko kyky (eli löytyikö sitä)
	 * @example <pre name="test">
	 * KykySupisParit parit = new KykySupisParit();
	 * parit.lisaaPari(2, 3, 300, "testiparit.dat") === true;
	 * parit.lisaaPari(2, 1, 300, "testiparit.dat") === true;
	 * parit.poistaKykySupikselta(2,3) === true;
	 * </pre>
	 */
	public boolean poistaKykySupikselta(int supiksenId, int kykyId) {
		int poistettuja = 0;
		for (int i = 0; i < parit.size(); i++) {
			if (anna(i).getSupisId() == supiksenId
					&& anna(i).getKykyId() == kykyId) {
				parit.remove(i);
				poistettuja++;
				break;
			}
		}
		if (poistettuja > 0)
			return true;
		return false;
	}


///**
// * @return montako paria on
// * @example <pre name="test">
// * KykySupisParit parit = new KykySupisParit();
// * parit.lisaaPari(2, 3, 300) === true;
// * parit.getParienLkm() === 17;
// * </pre>
// */
//public int getParienLkm() {
//	return parit.size();
//}


	/**
	 * Palauttaa parin tietystä indeksistä
	 *
	 * @param i
	 *            indeksi joka kohdalta löytyvä olio halutaan
	 * @return olio halutusta paikasta
	 * @example <pre name="test">
	 * KykySupisParit parit = new KykySupisParit();
	 * parit.anna(0).getVoimakkuus() === 30;
	 * </pre>
	 */
	public KykySupisPari anna(int i) {
		return parit.get(i);
	}

	/**
	 * Vipeltäjä-luokka pareille
	 *
	 * @author majosalo
	 * @version 28.2.2013
	 *
	 */
	public class KykySupisParitIterator implements Iterator<KykySupisPari> {
		private int kohdalla = -1;


		/**
		 * Onko olemassa vielä seuraavaa supista
		 *
		 * @see java.util.Iterator#hasNext()
		 * @return true jos on vielä supiksia
		 */
		public boolean hasNext() {
			// if ( kohdalla + 1 >= lkm ) return false;
			// return true;
			return kohdalla + 1 < parit.size();
		}


		/**
		 * Annetaan seuraava supis
		 *
		 * @return seuraava supis
		 * @throws NoSuchElementException
		 *             jos seuraava alkiota ei enää ole
		 * @see java.util.Iterator#next()
		 */
		public KykySupisPari next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("Ei oo");
			kohdalla++;
			return parit.get(kohdalla);
		}


		/**
		 * Tuhoamista ei ole toteutettu
		 *
		 * @throws UnsupportedOperationException
		 *             aina
		 * @see java.util.Iterator#remove()
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Myö ei poisteta");
		}
	}


	/**
	 * Palautetaan iteraattori pareista
	 *
	 * @return parit iteraattori
	 */
	public Iterator<KykySupisPari> iteratorPareille() {
		// return alkiot.iterator();
		return new KykySupisParitIterator();
	}


	/**
	 * Testipääohjelma
	 *
	 * @param args
	 *            ei käytössä
	 */
	public static void main(String[] args) {
		KykySupisParit parit = new KykySupisParit();
		parit.lisaaPari(3, 6, 335, "testiparit.dat");
		parit.lisaaPari(4, 3, 45,"testiparit.dat");
		// for (KykySupisPari pari : parit.parit) {
		// pari.tulosta(System.out);
		// }

	}

}

package rekisteri;

import java.util.Iterator;



/**
 * Rekisteri-luokka joka on yhteydess� oikeastaan kaikkialle muualle harkassa
 *
 * @author majosalo
 * @version 22.2.2013
 * @version 14.3.2013
 */
public class Rekisteri {
	private final Supikset supikset = new Supikset();
	private final Kyvyt kyvyt = new Kyvyt();
	private final KykySupisParit parit = new KykySupisParit();
	/**
	 * Testip��ohjelma
	 *
	 * @param args
	 *            ei k�yt�ss�
	 */
	public static void main(String[] args) {
		Rekisteri rekkari = new Rekisteri();
		try {

			Supersankari supis1 = new Supersankari();
			supis1.alustaToimivaksi();
			rekkari.lisaaTyhjaSupis(supis1);
			Supersankari supis2 = new Supersankari();
			supis2.alustaToimivaksi();
			rekkari.lisaaTyhjaSupis(supis2);
			Supersankari supis3 = new Supersankari();
			supis3.alustaToimivaksi();
			rekkari.lisaaTyhjaSupis(supis3);
			rekkari.lisaaAivanUusiKyky("J��");
			rekkari.lisaaAivanUusiKyky("Tuli");

			System.out.println("========== Supersankarit testi ==============");
			int iNro = 1;
			for (Iterator<Supersankari> i = rekkari.iteratorSupiksille(); i.hasNext();) {

				Supersankari supe = i.next();
				if (supe != null) {
					System.out.println("Supersankari nro: " + iNro);
					supe.tulosta(System.out);
					iNro++;
				}
			}

		} catch (SailoException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			 String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
			 String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
			 String iris = "Iron Man        | Stark Tony         | Rogers Steve  | New York    |  3";
			 rekkari.lisaaSupis(battis);
			 rekkari.lisaaSupis(captis);
			 rekkari.lisaaSupis(iris);
			rekkari.lisaaAivanUusiKyky("J��");
			rekkari.lisaaAivanUusiKyky("Tuli");
			rekkari.lisaaAivanUusiKyky("Projektiilit");
			rekkari.lisaaAivanUusiKyky("Taikuus");

			System.out.println("========== Kyvyt testi ==============");
			int iNro = 1;
			for (Iterator<Kyky> i = rekkari.iteratorKyvyille(); i.hasNext();) {

				Kyky kyky = i.next();
				if (kyky != null) {
					System.out.println("Kyky nro: " + iNro);
					kyky.tulosta(System.out);
					iNro++;
				}
			}

		} catch (SailoException ex) {
			System.out.println(ex.getMessage());
		}
		{
		String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
		 String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
		 String iris = "Iron Man        | Stark Tony         | Rogers Steve  | New York    |  3";
		 rekkari.lisaaSupis(battis);
		 rekkari.lisaaSupis(captis);
		 rekkari.lisaaSupis(iris);
			rekkari.lisaaKykySupikselle("Captain America","J��", 300);
			rekkari.lisaaKykySupikselle("Iron Man","Tuli", 300);
			rekkari.lisaaKykySupikselle("Captain America","Projektiilit", 300);
			rekkari.lisaaKykySupikselle("Batman","J��", 300);
			rekkari.lisaaKykySupikselle("Captain America","Tuli", 300);
			rekkari.lisaaKykySupikselle("Iron Man","Projektiilit", 300);

			System.out.println("========== Parit testi ==============");
			int iNro = 1;
			for (Iterator<KykySupisPari> i = rekkari.iteratorPareille(); i.hasNext();) {

				KykySupisPari kykySupisPari = i.next();
				if (kykySupisPari != null) {
					System.out.println("Pari nro: " + iNro);
					kykySupisPari.tulosta(System.out);
					iNro++;
				}
			}
		}	}


	/**
	 * Palautaa rekisterin supism��r�n
	 *
	 * @return supism��r�
	 */
	public int getSupiksia() {
		return supikset.getMontakoSupista();
	}


	/**
	 * V�litt�� uuden kyvyn lis�yspyynn�n Kyvyt-luokalle
	 *
	 * @param uudenKyvynNimi
	 *            uuden kyvyn nimi
	 * @throws SailoException
	 *             jos homma menee puihin
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Rekisteri rekkari = new Rekisteri();
	 * rekkari.getMontakoKykya() === 0;
	 * rekkari.lisaaAivanUusiKyky("J��"); rekkari.getMontakoKykya() === 1;
	 * rekkari.lisaaAivanUusiKyky("Tuli"); rekkari.getMontakoKykya() === 2;
	 * rekkari.lisaaAivanUusiKyky("Projektiilit"); rekkari.getMontakoKykya() === 3;
	 * rekkari.annaKyky(0).getKyvynNimi() === "J��";
	 * rekkari.annaKyky(1).getKyvynNimi() === "Tuli";
	 * rekkari.annaKyky(2).getKyvynNimi() === "Projektiilit";
	 * </pre>
	 */
	public void lisaaAivanUusiKyky(String uudenKyvynNimi) throws SailoException {
		kyvyt.lisaaKyky(uudenKyvynNimi);
	}


	/**
	 * @return montako kyky� rekisterist� l�ytyy
	 */
	public int getMontakoKykya() {
		return kyvyt.getMontakoKykya();
	}


	/**
	 * Palauttaa i. kyvyn
	 *
	 * @param i
	 *            paikka josta kyky� etsit��n
	 * @return kyky jos l�ytyi
	 * @throws IndexOutOfBoundsException
	 *             jos i v��rin
	 */
	public Kyky annaKyky(int i) throws IndexOutOfBoundsException {
		return kyvyt.anna(i);
	}


	/**
	 * Poistaa supiksen tietorakenteestaan.
	 *
	 * @param stageName
	 *            poistettavan supiksen stage name jonka perusteella poistetaan
	 * @throws SailoException
	 *             jos tietorakenne on jo t�ynn�
	 * @example <pre name="test">
	  * #THROWS SailoException
	 * Rekisteri rekkari = new Rekisteri();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * supis1.alustaToimivaksi(); supis2.alustaToimivaksi();
	 * rekkari.getMontakoSupista() === 0;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 1;
	 * rekkari.lisaaTyhjaSupis(supis2); rekkari.getMontakoSupista() === 2;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 3;
	 * rekkari.annaSupis(0) === supis1;
	 * rekkari.annaSupis(1) === supis2;
	 * rekkari.annaSupis(2) === supis1;
	 * rekkari.annaSupis(1) == supis1 === false;
	 * rekkari.annaSupis(1) == supis2 === true;
	 * rekkari.poistaSupis("Captain America"); rekkari.getMontakoSupista() === 2;
	 * rekkari.poistaSupis("Captain America"); rekkari.getMontakoSupista() === 1;
	 * rekkari.poistaSupis("Captain America"); rekkari.getMontakoSupista() === 0;
	 * </pre>
	 */
	public void poistaSupis(String stageName) {
		supikset.poistaSupis(stageName);
		parit.poistaPariSupiksenPerusteella(annaNimenId(stageName));

	}


	/**
	 * @return supersankarien lukum��r�n rekisteriss�
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Rekisteri rekkari = new Rekisteri();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * rekkari.getMontakoSupista() === 0;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 1;
	 * rekkari.lisaaTyhjaSupis(supis2); rekkari.getMontakoSupista() === 2;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 3;
	 * </pre>
	 */
	public int getMontakoSupista() {
		return supikset.getMontakoSupista();
	}


	/**
	 * Lis�� uuden supiksen tietorakenteeseen. Ottaa j�senen omistukseensa.
	 *
	 * @param jasen
	 *            lis�t��v�n j�senen viite. Huom tietorakenne muuttuu
	 *            omistajaksi
	 * @throws SailoException
	 *             jos tietorakenne on jo t�ynn�
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Rekisteri rekkari = new Rekisteri();
	 * Supersankari supis1 = new Supersankari(), supis2 = new Supersankari();
	 * rekkari.getMontakoSupista() === 0;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 1;
	 * rekkari.lisaaTyhjaSupis(supis2); rekkari.getMontakoSupista() === 2;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 3;
	 * rekkari.annaSupis(0) === supis1;
	 * rekkari.annaSupis(1) === supis2;
	 * rekkari.annaSupis(2) === supis1;
	 * rekkari.annaSupis(1) == supis1 === false;
	 * rekkari.annaSupis(1) == supis2 === true;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 4;
	 * rekkari.lisaaTyhjaSupis(supis1); rekkari.getMontakoSupista() === 5;
	 * rekkari.lisaaTyhjaSupis(supis1);
	 * </pre>
	 */
	public void lisaaTyhjaSupis(Supersankari supis) throws SailoException {
		supikset.lisaaTyhjaSupis(supis);
	}


	/**
	 * Lis�� uuden supiksen tietorakenteeseen. Ottaa j�senen omistukseensa.
	 *
	 * @param tiedot
	 *            lis�tt�v�n supiksen tiedot merkkijonona. Huom tietorakenne
	 *            muuttuu omistajaksi
	 * @throws SailoException
	 *             jos tietorakenne on jo t�ynn�
	 * @example <pre name="test">
	 * Rekisteri rekkari = new Rekisteri();
	 * String supis1 = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String supis2 = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * rekkari.getMontakoSupista() === 0;
	 * rekkari.lisaaSupis(supis1) === true;
	 * rekkari.getMontakoSupista() === 1;
	 * rekkari.lisaaSupis(supis2) === true;
	 * rekkari.getMontakoSupista() === 2;
	 * rekkari.lisaaSupis(supis1) === true;
	 * rekkari.getMontakoSupista() === 3;
	 * rekkari.annaSupis(0).getStageName().equals("Batman") === true;
	 * rekkari.annaSupis(1).getStageName().equals("Captain America") === true;
	 * rekkari.annaSupis(2).getStageName().equals("Batman") === true;
	 * rekkari.annaSupis(1).getStageName().equals("Batman") === false;
	 * rekkari.annaSupis(1).getStageName().equals("Captain America") === true;
	 * rekkari.lisaaSupis(supis1); rekkari.getMontakoSupista() === 4;
	 * rekkari.lisaaSupis(supis1); rekkari.getMontakoSupista() === 5;
	 * </pre>
	 */
	public boolean lisaaSupis(String tiedot) {
		if (tiedot.length() == 0)
			return false;
		return supikset.lisaaSupis(tiedot);
	}


	/**
	 * Palauttaa i. supersankarin
	 *
	 * @param i
	 *            paikka josta supista etsit��n
	 * @return supis jos l�ytyi
	 * @throws IndexOutOfBoundsException
	 *             jos i v��rin
	 */
	public Supersankari annaSupis(int i) throws IndexOutOfBoundsException {
		return supikset.anna(i);
	}


	/**
	 * Aliohjelma joka lis�� uuden kykysupisparin. Jos voimakkuus on 0, ei
	 * lis�t�.
	 *
	 * @param kenelle
	 *            mille supersankarille, eli idnumeroa kaivataan
	 * @param mikaKyky
	 *            mik� kyky lis�t��n, eli idnumeroa kaivataan
	 * @param voimakkuus
	 *            mik� voimakkuus supiksella on kyvyss�
	 * @return onnistuiko lis�ys
	 * @example <pre name="test">
	 * Rekisteri rekkari = new Rekisteri();
	 * rekkari.lisaaKykySupikselle("Captain America", "J��", 300) === true;
	 * rekkari.getMontakoParia() === 1;
	 * </pre>
	 */
	public boolean lisaaKykySupikselle(String kenelle, String mikaKyky,
			int voimakkuus) {
		int supisId = annaNimenId(kenelle);
		int kykyId = annaKyvynId(mikaKyky);
		return parit.lisaaPari(supisId, kykyId, voimakkuus);
	}


	/**
	 * Aliohjelma joka poistaa tietyn kyvyn supikselta
	 *
	 * @param supis
	 *            supis jolta poistetaan
	 * @param kyky
	 *            kyky joka silt� poistetaan
	 * @return poistettiinko (eli l�ytyik�)
	 * @example <pre name="test">
	 * Rekisteri rekkari = new Rekisteri();
	 * String supis1 = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String supis2 = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * rekkari.getMontakoSupista() === 0;
	 * rekkari.lisaaSupis(supis1) === true;
	 * rekkari.getMontakoSupista() === 1;
	 * rekkari.lisaaSupis(supis2) === true;
	 * rekkari.getMontakoSupista() === 2;
	 * rekkari.lisaaKykySupikselle("Captain America","Tuli", 30) === true;
	 * rekkari.lisaaKykySupikselle("Captain America","J��", 300) === true;
	 * rekkari.getMontakoParia() === 2;
	 * rekkari.poistaKykySupikselta("Captain America","J��") === true;
	 * rekkari.getMontakoParia() === 1;
	 * </pre>
	 */
	public boolean poistaKykySupikselta(String stageName, String kyky) {
		int supisId = annaNimenId(stageName);
		int kykyId = kyvyt.annaKyvynId(kyky);
		return parit.poistaKykySupikselta(supisId, kykyId);
	}


	/**
	 * Palauttaa supersankaritaulukosta nimen perusteella supiksen id:n
	 *
	 * @param stageName
	 *            nimi jolla supersankaria etsit��n
	 * @return supersankarin id
	 * @example <pre name="test">
	 * Rekisteri rekkari = new Rekisteri();
	 * rekkari.getMontakoSupista() === 0;
	 * String battis = "Batman          | Wayne Bruce        | Kyle Selina   | Gotham City |  1               ";
	 * String captis = "Captain America | Rogers Steve       | Stark Tony    | Brooklyn    |  2               ";
	 * rekkari.lisaaSupis(battis) === true;
	 * rekkari.lisaaSupis(captis) === true;
	 *    int n1 = rekkari.annaNimenId("Batman");
	 *    int n2 = rekkari.annaNimenId("Captain America");
	 * n1 === n2-1;
	 * </pre>
	 */
	public int annaNimenId(String stageName) {
		return supikset.annaNimenId(stageName);
	}


	/**
	 * Palauttaa kykytaulukosta nimen perusteella kyvyn id:n
	 *
	 * @param kyvynNimi
	 *            nimi jolla kyky� etsit��n
	 * @return kyvyn id
	 * @example <pre name="test">
	 * #THROWS SailoException
	 * Rekisteri rekkari = new Rekisteri();
	 * rekkari.lisaaAivanUusiKyky("J��");
	 * rekkari.lisaaAivanUusiKyky("Tuli");
	 *    int n1 = rekkari.annaKyvynId("J��");
	 *    int n2 = rekkari.annaKyvynId("Tuli");
	 * n1 === n2-1;
	 * </pre>
	 */
	public int annaKyvynId(String kyvynNimi) {
		return kyvyt.annaKyvynId(kyvynNimi);
	}


	/**
	 * @return kyky-supis-parien lkm
	 * @example <pre name="test">
	 * Rekisteri rekkari = new Rekisteri();
	 * rekkari.lisaaKykySupikselle("Captain America","J��", 300) === true;
	 * rekkari.getMontakoParia() === 1;
	 * </pre>
	 */
	public int getMontakoParia() {
		return parit.getParienLkm();
	}


	/**
	 * Tallettaa rekisterin tiedot tiedostoon
	 *
	 * @throws SailoException
	 *             jos tallettamisessa ongelmia
	 */
	public void talleta() throws SailoException {
		supikset.talleta();
	}


	/**
	 * Lukee rekisterin tiedot tiedostosta
	 *
	 * @param nimi
	 *            jota k�yte��n lukemisessa
	 * @throws SailoException
	 *             jos lukeminen ep�onnistuu
	 */
	public void lueTiedostosta(String nimi) throws SailoException {
		supikset.lueTiedostosta(nimi);
	}


	/**
	 * Palautetaan iteraattori supiksista.
	 *
	 * @return supikset iteraattori
	 */
	public Iterator<Supersankari> iteratorSupiksille() {
		// return alkiot.iterator();
		return supikset.iteratorSupiksille();
	}

	/**
	 * Palautetaan iteraattori kyvyist�.
	 *
	 * @return kyvyt iteraattori
	 */
	public Iterator<Kyky> iteratorKyvyille() {
		// return alkiot.iterator();
		return kyvyt.iterator();
	}

	/**
	 * Palautetaan iteraattori pareista.
	 *
	 * @return parit iteraattori
	 */
	public Iterator<KykySupisPari> iteratorPareille() {
		// return alkiot.iterator();
		return parit.iteratorPareille();
	}


}

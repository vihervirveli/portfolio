package rekisteri;

import java.io.PrintStream;

/**
 * Kyky-luokka tiet‰‰ mm. id-numeronsa ja nimens‰ ja osaa muutaman tempun
 *
 * @author majosalo
 * @version 12.4.2013
 */
public class Kyky {
	private Kentta kentat[] = { // valitettavasti ei voi olla final
	// vaikka pit‰isi, clone est‰‰ t‰m‰n :-(
			new IntKentta("id"), new JonoKentta("Kyvyn nimi"), };

	// private int idNro = 0;
	// private String kyvynNimi = "";
	private static int seuraavaNro = 1;


	/**
	 * Muodostetaan Kyky.
	 *
	 * @param kykyNimi
	 *            mink‰niminen uusi kyky on
	 * @example <pre name="test">
	 *  Kyky kyky = new Kyky("J‰‰");
	 *  Kyky kyky2 = new Kyky("Tuli");
	 *  int n1 = kyky.getId();
	 *  int n2 = kyky2.getId();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public Kyky(String kykyNimi) {
		if (kykyNimi.length() == 0)
			return;
		aseta(0, "" + rekisteroi());
		aseta(1, kykyNimi);
	}

	/**
	 * Muodostetaan Kyky.
	 *
	 * @param kykyNimi
	 *            mink‰niminen uusi kyky on
	 * @example <pre name="test">
	 *  Kyky kyky = new Kyky("J‰‰");
	 *  Kyky kyky2 = new Kyky("Tuli");
	 *  int n1 = kyky.getId();
	 *  int n2 = kyky2.getId();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public Kyky() {

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
	 *   Kyky jaa = new Kyky();
	 *   jaa.aseta(1,"Ankka Aku") === null;
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
	 *   Kyky kyky = new Kyky("J‰‰");
	 *  Kyky kyky2 = new Kyky("Tuli");
	 *  int n1 = kyky.getId();
	 *  int n2 = kyky2.getId();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		int id = seuraavaNro;
		seuraavaNro++;
		return id;
	}


	/**
	 * @return palauttaa kyvyn nimen
	 * @example <pre name="test">
	 * Kyky kyky = new Kyky("J‰‰");
	 *  Kyky kyky2 = new Kyky("Tuli");
	 *  kyky.getKyvynNimi() === "J‰‰";
	 *  kyky2.getKyvynNimi() === "Tuli";
	 * </pre>
	 */
	public String getKyvynNimi() {
		for (int i = 1; i < kentat.length; i++) {
			if (kentat[i].getKysymys().equals("Kyvyn nimi")) {
				return kentat[i].toString();
			}
		}
		return "";

	}


	/**
	 * @return kyvyn merkkijonona (t‰m‰ laitetaan tiedostoon)
	 * @example <pre name="test">
	 * Kyky jaa = new Kyky("J‰‰");
	 *
	 * jaa.merkkijonoksi() === "11|J‰‰|";
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
	 * @return kyvyn id-numeron.
	 * @example <pre name="test">
	 *   Kyky kyky = new Kyky("J‰‰");
	 *  Kyky kyky2 = new Kyky("Tuli");
	 *  int n1 = kyky.getId();
	 *  int n2 = kyky2.getId();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public int getId() {
		return ((IntKentta) (kentat[0])).getValue();
	}


	/**
	 * Tulostetaan Kyvyn tiedot
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
	 * Testip‰‰ohjelma
	 *
	 * @param args
	 *            ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Kyky kyky = new Kyky("J‰‰");
		Kyky kyky2 = new Kyky("Tuli");
		kyky.tulosta(System.out);
		kyky2.tulosta(System.out);

	}

}

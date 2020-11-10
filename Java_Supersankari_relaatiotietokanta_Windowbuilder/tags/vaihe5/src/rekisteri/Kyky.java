package rekisteri;

import java.io.PrintStream;

/**
 * Kyky-luokka tiet‰‰ mm. id-numeronsa ja nimens‰ ja osaa muutaman tempun
 *
 * @author majosalo
 * @version 7.3.2013
 */
public class Kyky {
	private int idNro = 0;
	private String kyvynNimi = "";
	private static int seuraavaNro = 1;


	/**
	 * Muodostetaan Kyky. EI tehd‰ oletusmuodostajaa, geneerisest‰ kyvyst‰ ei
	 * ole mit‰‰n hyˆty‰.
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
		this.idNro = rekisteroi();
		this.kyvynNimi = kykyNimi;
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
		return kyvynNimi;
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
		return idNro;
	}


	/**
	 * Tulostetaan Kyvyn tiedot
	 *
	 * @param out
	 *            tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("%03d", idNro, 3) + " Kyvyn nimi: "
				+ kyvynNimi);
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

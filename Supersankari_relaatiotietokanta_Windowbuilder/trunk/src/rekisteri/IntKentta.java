package rekisteri;

import javax.swing.SwingConstants;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Kentt‰ kokonaislukujen tallentamiseksi
 *
 * @author vesal
 * @version 31.3.2008
 * @version 4.4.2013
 *
 */
public class IntKentta extends PerusKentta {
	private int arvo;


	/**
	 * Alustetaan kentt‰ kysymyksell‰.
	 *
	 * @param kysymys
	 *            joka n‰ytet‰‰n kentt‰‰ kysytt‰ess‰.
	 */
	public IntKentta(String kysymys) {
		super(kysymys);
	}


	/**
	 * Alustetaan kentt‰ kysymyksell‰.
	 *
	 * @param kysymys
	 *            joka n‰ytet‰‰n kentt‰‰ kysytt‰ess‰.
	 */
	public IntKentta(String kysymys, Tarkistaja tarkistaja) {
		super(kysymys);
	}


	/**
	 * @return kent‰n arvo kokonaislukuna
	 */
	public int getValue() {
		return arvo;
	}


	/**
	 * Asetetaan kent‰n arvo kokonaislukuna
	 *
	 * @param value
	 *            asetettava kokonaislukuarvo
	 */
	public void setValue(int value) {
		arvo = value;
	}


	/**
	 * @return Kent‰n arvo merkkijonona
	 * @see kanta.PerusKentta#toString()
	 */
	@Override
	public String toString() {
		return "" + arvo;
	}


	/**
	 * Asetetaan kent‰n arvo merkkijonosta. Jos jono kunnollinen, palautetaan
	 * null. Jos jono ei kunnollinen int-syˆte, palautetaan virheilmoitus ja
	 * kent‰n alkuper‰inen arvo j‰‰ voimaan.
	 *
	 * @param jono
	 *            kent‰‰n asetettava arvo mekrkijonona
	 * @return null jos hyv‰ arvo, muuten virhe.
	 * @see kanta.PerusKentta#aseta(java.lang.String)
	 *
	 * @example <pre name="test">
	 * IntKentta kentta = new IntKentta("m‰‰r‰");
	 * kentta.aseta("12") === null; kentta.getValue() === 12;
	 * kentta.aseta("k") === "Ei kokonaisluku (k)"; kentta.getValue() === 12;
	 * </pre>
	 */
	@Override
	public String aseta(String jono) {
		StringBuffer sb = new StringBuffer(jono);
		try {
			this.arvo = Mjonot.erotaEx(sb, ' ', 0);
			return null;
		} catch (NumberFormatException ex) {
			return "Ei kokonaisluku (" + jono + ")";
		}
	}


	/**
	 * Palauttaa kent‰n tiedot vertailtavana merkkijonona
	 *
	 * @return vertailtava merkkijono kent‰st‰
	 * @example <pre name="test">
	 * IntKentta kentta = new IntKentta("m‰‰r‰");
	 * kentta.aseta("12");  kentta.getAvain() === "        12";
	 * kentta.aseta("1");   kentta.getAvain() === "         1";
	 * kentta.aseta("999"); kentta.getAvain() === "       999";
	 *
	 * </pre>
	 */
	@Override
	public String getAvain() {
		return Mjonot.fmt(arvo, 10);
	}


	/**
	 * @return syv‰kopio oliosta
	 * @example <pre name="test">
	 *   #THROWS CloneNotSupportedException
	 *   IntKentta kentta = new IntKentta("m‰‰r‰");
	 *   kentta.aseta("12");
	 *   IntKentta klooni = (IntKentta)kentta.clone();
	 *   kentta.getValue() === klooni.getValue();
	 *   kentta.aseta("13");
	 *   kentta.getValue() === 13;
	 *   klooni.getValue() === 12;
	 * </pre>
	 */
	@Override
	public Kentta clone() throws CloneNotSupportedException {
		return super.clone();
	}


	/**
	 * @return vaakasuuntainen sijainti kent‰lle
	 */
	// @Override
	public int getSijainti() {
		return SwingConstants.RIGHT;
	}


	/**
	 * Vertailee kahta Intkenttaa toisiinsa.
	 * @param o se toinen kentta johon tata verrataan
	 * @return -1 jos this on pienempi, 0 jos saman kokoinen ja 1 jos toinen on isompi
	 */
	@Override
	public int compareTo(Kentta o) {
		IntKentta kentan = (IntKentta) o;
		if (this.getValue() < kentan.getValue()) return -1;
		if (this.getValue() == kentan.getValue()) return 0;
		return 1;

	}

}
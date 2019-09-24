package rekisteri;

/**
 * Rajapinta tietueen yhdelle kentälle.
 *
 * @author vesal
 * @author majosalo
 * @version 4.4.2013
 *
 */
public interface Kentta extends Cloneable, Comparable<Kentta> {

	/**
	 * kentän arvo merkkijonona.
	 *
	 * @return kenttä merkkkijonona
	 */
	@Override
	String toString();

	/**
	 * Palauttaa kentään liittyvän kysymyksen.
	 *
	 * @return kenttän liittyvä kysymys.
	 */
	String getKysymys();

	/**
	 * Asettaa kentän sisällön ottamalla tiedot merkkijonosta.
	 *
	 * @param jono
	 *            jono josta tiedot otetaan.
	 * @return null jos sisältö on hyvä, muuten merkkijonona virhetieto
	 */
	String aseta(String jono);

	/**
	 * Palauttaa kentän tiedot veratiltavana merkkijonona
	 *
	 * @return vertailtava merkkijono kentästä
	 */
	String getAvain();

	/**
	 * @return syväkopio kentästä, tehtävä jokaiseen luokkaa toimivaksi
	 * @throws CloneNotSupportedException
	 */
	Kentta clone() throws CloneNotSupportedException;

	/**
	 * @return vaakasuuntainen sijainti kentälle
	 */
	//int getSijainti();

}
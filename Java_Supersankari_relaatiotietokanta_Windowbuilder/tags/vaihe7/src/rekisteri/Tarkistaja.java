package rekisteri;

/**
 * Rajapinta yleiselle tarkistajalle.
 * Tarkistajan tehtävä on tutkia onko annettu
 * merkkijono kelvollinen kentän sisällöksi ja jos on.
 * palautetaan null.
 * Virhetapauksessa palautetaan virhettä mahdollisimman
 * hyvin kuvaava merkkijono.
 * @author vesal
 * @version 31.3.2008
 * @version 25.4.2013
 *
 */
public interface Tarkistaja {
    /**
     * Tutkitaan käykö annettu merkkijono kentän sisällöksi.
     * @param jono tutkittava merkkijono
     * @return null jos jono oikein, muuten virheilmoitusta vastaava merkkijono
     */
    String tarkista(String jono);
}
package rekisteri;

/**
 * Tarkistaja joka tarkistaa ett‰ jono vastaa regexpi‰ Hyv‰ksyy tyhj‰n jonon.
 *
 * @author vesal
 * @version 25.4.2013
 *
 */
public class RegExpTarkistaja implements Tarkistaja {

	private final String regexp;


	/**
	 * Luodaan tarkistaja joka hyv‰ksyy sallitut merkit
	 *
	 * @param regexp
	 *            hyv‰ksytt‰v‰t merkit
	 */
	public RegExpTarkistaja(String regexp) {
		this.regexp = regexp;
	}


	/**
	 * Tarkistaa ett‰ jono sis‰lt‰‰ vain valittuja numeroita
	 *
	 * @param jono
	 *            tutkittava jono
	 * @example <pre name="test">
	 *   RegExpTarkistaja tar = new RegExpTarkistaja("[1-4]*");
	 *   tar.tarkista("12") === null;
	 *   tar.tarkista("15") === "Ei vastaa maskia: [1-4]*";
	 *   tar.tarkista("")   === null;
	 * </pre>
	 */
	@Override
	public String tarkista(String jono) {
		if (jono.matches(regexp))
			return null;
		return "Ei vastaa maskia: " + regexp;
	}

}
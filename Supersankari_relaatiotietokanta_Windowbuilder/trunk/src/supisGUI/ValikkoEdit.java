package supisGUI;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//private static final long serialVersionUID = 1L;
/**
 * Luokka otsikon ja edit-kentän yhdistämiseksi
 *
 * @author majosalo
 * @version 17.1.2013
 */
public class ValikkoEdit extends JMenuBar {
	private static final long serialVersionUID = 1L;
	// private final JMenuBar menuBar = new JMenuBar();
	private final JMenu menuTiedosto = new JMenu("Tiedosto");
	private final JMenu menuMuokkaa = new JMenu("Muokkaa");
	private final JMenu menuOhje = new JMenu("Ohje");
	private final JMenuItem menuItem = new JMenuItem("Tallenna");
	private final JMenuItem menuItem_1 = new JMenuItem(
			"Valitse supersankari...");
	private final JMenuItem menuItem_2 = new JMenuItem("Valitse kyky...");
	private final JMenuItem menuItem_3 = new JMenuItem("You'll be back");
	private final JMenuItem menuItem_4 = new JMenuItem(
			"Muokkaa supersankarin tietoja...");
	private final JMenuItem menuItem_5 = new JMenuItem(
			"Muokkaa kyvyn tietoja...");
	private final JMenuItem menuItem_6 = new JMenuItem(
			"Lis\u00E4\u00E4 uusi supersankari");
	private final JMenuItem menuItem_7 = new JMenuItem(
			"Lis\u00E4\u00E4 uusi kyky");
	private final JMenuItem menuItem_8 = new JMenuItem("Poista supersankari...");
	private final JMenuItem menuItem_9 = new JMenuItem("Poista kyky...");
	private final JMenuItem menuItem_10 = new JMenuItem(
			"K\u00E4ytt\u00F6ohjeet");
	private final JMenuItem menuItem_11 = new JMenuItem(
			"About Batmanin supersankarirekisteri");

	/**
	 * Create the panel.
	 */
	public ValikkoEdit() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// add(menuBar);

		this.add(menuTiedosto);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Tallentaa tiedot tietokantaan. Ei vielä, tosin.",
						"Tallennus", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		menuTiedosto.add(menuItem);

		menuTiedosto.add(menuItem_1);

		menuTiedosto.add(menuItem_2);

		menuTiedosto.add(menuItem_3);

		this.add(menuMuokkaa);

		menuMuokkaa.add(menuItem_4);

		menuMuokkaa.add(menuItem_5);

		menuMuokkaa.add(menuItem_6);

		menuMuokkaa.add(menuItem_7);

		menuMuokkaa.add(menuItem_8);

		menuMuokkaa.add(menuItem_9);

		this.add(menuOhje);

		menuOhje.add(menuItem_10);

		menuOhje.add(menuItem_11);

	}

}

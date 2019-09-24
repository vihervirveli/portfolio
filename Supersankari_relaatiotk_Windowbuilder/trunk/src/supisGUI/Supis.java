package supisGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import rekisteri.Rekisteri;
import rekisteri.SailoException;
import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.IStringListChooser;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.SelectionChangeListener;

/**
 * Supersankarin‰kym‰, (k‰yt‰nnˆss‰ p‰‰n‰kym‰), eli se miss‰ on valikko ja
 * suurin osa p‰‰toiminnoista
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @version 25.4.2013
 *
 */
public class Supis extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelSisalto;
	private final JPanel panelNappulat = new JPanel();
	private final JSplitPane splitpanelListaSupisTiedot = new JSplitPane();
	private final JPanel panelLista = new JPanel();
	private final JSplitPane splitPanelTiedotJaKyvyt = new JSplitPane();
	private final JPanel panelHaku = new JPanel();
	private final JScrollPane scrollSupikset = new JScrollPane();

	private final JPanel panelKyvyt = new JPanel();
	private final JPanel panelSupiksenTiedot = new JPanel();

	private final JLabel labelKyvyt = new JLabel(" Valitun supersankarin kyvyt");
	private final JScrollPane scrollVoimaTable = new JScrollPane();
	private final JPanel supisNappulat = new JPanel();
	private final JPanel kykyNappulat = new JPanel();
	private AbstractChooser<String> cbKentat = new ComboBoxChooser();
	// private JComboBox<String> cbKentat = new JComboBox<String>();
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JTextField txtHaku = new JTextField();
	private final JButton btnTallennaSupis = new JButton(
			"Tallenna supersankarin tiedot");
	private final JButton tallennaKyKy = new JButton(
			"Lis\u00E4\u00E4 kyky supikselle");
	private final JLabel labelHakuehto = new JLabel("Hakuehto");
	private final JSplitPane splitPaneSupisTiedot = new JSplitPane();
	private final JPanel panelSupisTiedot = new JPanel();
	private final JPanel panelVahvemmat = new JPanel();
	private final JLabel label = new JLabel("Supersankarin tiedot");
	private final JScrollPane scrollPaneSupisTiedot = new JScrollPane();
	private final JScrollPane scrollPanelVahvemmat = new JScrollPane();
	private final JPanel panelTiedot = new JPanel();
	private final JPanel valitunSupiksenTiedot = new JPanel();
	private EditPanel editSupisKentta[]; // Supiksen kentti‰ vastaavat
											// edit-kent‰t
	private List<EditPanel> editKykyKentta = new ArrayList<EditPanel>(); // Supiksen
																			// kykyj‰
																			// vastaavat
	// edit-kent‰t
	private final EditPanel editPanel = new EditPanel();
	private final EditPanel editPanel_1 = new EditPanel();
	private final EditPanel editPanel_2 = new EditPanel();
	private final EditPanel editPanel_3 = new EditPanel();
	private final EditPanel editPanel_4 = new EditPanel();
	private final EditPanel editPanel_5 = new EditPanel();
	private final JPanel panelKyky = new JPanel();
	private final JPanel panelKykyeditit = new JPanel();
	private final EditPanel editPanel_6 = new EditPanel();
	private final EditPanel editPanel_7 = new EditPanel();
	private final EditPanel editPanel_8 = new EditPanel();
	private final EditPanel editPanel_9 = new EditPanel();
	private final EditPanel editPanel_10 = new EditPanel();
	private final JMenuBar menuValikko = new JMenuBar();
	private final JMenu menudropTiedosto = new JMenu("Tiedosto");
	private final JMenuItem menuItem = new JMenuItem("Tallenna");
	private final JMenuItem menuItem_1 = new JMenuItem(
			"Valitse supersankarin\u00E4kym\u00E4");
	private final JMenuItem menuItem_2 = new JMenuItem(
			"Valitse kykyn\u00E4kym\u00E4");
	private final JMenuItem menuItem_3 = new JMenuItem("You'll be back");
	private final JMenu menudropMuokkaa = new JMenu("Muokkaa");
	private final JMenuItem menuItem_4 = new JMenuItem(
			"Lis\u00E4\u00E4 uusi supersankari");
	private final JMenuItem mntmUusiKyky = new JMenuItem(
			"Lis\u00E4\u00E4 uusi kyky");
	private final JMenuItem menuItem_6 = new JMenuItem("Poista supersankari...");
	private final JMenuItem menuItem_7 = new JMenuItem("Poista kyky...");
	private final JMenu menudropOhje = new JMenu("Ohje");
	private final JMenuItem menuItem_8 = new JMenuItem("K\u00E4ytt\u00F6ohjeet");
	private final JMenuItem menuItem_9 = new JMenuItem(
			"About Batmanin supersankarirekisteri");
	private final Rekisteri rekkari = new Rekisteri();
	private ListChooser supiksetListassa = new ListChooser();

	private String valittuSupisJono;

	private final JTextArea vahvemmatSupikset = new JTextArea();
	private rekisteri.Supersankari[] supet = rekkari.annaSupiksetTaulukossa();
	private rekisteri.Supersankari apusupe = new rekisteri.Supersankari();
	private rekisteri.Supersankari valittuSupis = rekkari.annaSupis(0);
	private Color normaaliVari = new Color(255, 255, 255);
	private List<rekisteri.KykySupisPari> valitunKyvyt = rekkari
			.supiksenKyvyt(valittuSupis.getId());
	private int valitunIndeksi = 0;// -1
	private int cbIndeksi = 0;
	private Color virheVari = new Color(255, 0, 0);
	private final JLabel labelVirhe = new JLabel("");
	// private int stageNamenIndeksi = apusupe.ekaKentta();
	private boolean onkomuutettu = false;


	// private String[] apuKentat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supis frame = new Supis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Supis() {

		txtHaku.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				hae(0);
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Kun ohjelma toimii voit kirjoittaa t‰h‰n esim 'cap' \nja hakutuloksena pit‰isi ilmesty‰ Captain American tiedot. Et viel‰.",
				// "Tba", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		txtHaku.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 626);

		setJMenuBar(menuValikko);

		menuValikko.add(menudropTiedosto);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Kun ohjelma toimii, t‰st‰ tallennat tiedot. Et viel‰",
						"Tba", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		menudropTiedosto.add(menuItem);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Olet jo supisn‰kym‰ss‰!",
						"Tba", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		menudropTiedosto.add(menuItem_1);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kykyNakyma();
			}
		});

		menudropTiedosto.add(menuItem_2);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menudropTiedosto.add(menuItem_3);

		menuValikko.add(menudropMuokkaa);
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// rekisteri.Supersankari supe = new rekisteri.Supersankari();
				// supe.alustaToimivaksi();
				// rekkari.lisaaTyhjaSupis(supe);
				// supiksetListassa.clear();
				// supet = rekkari.annaSupiksetTaulukossa();
				// for (int i = 0; i < supet.length; i++) {
				// if (supet[i] != null)
				// supiksetListassa.add(supet[i].getStageName());
				// }
				uusiSupis();
			}
		});

		menudropMuokkaa.add(menuItem_4);
		mntmUusiKyky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ihanUusiKyky();
			}
		});

		menudropMuokkaa.add(mntmUusiKyky);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poistaSup();
			}
		});

		menudropMuokkaa.add(menuItem_6);
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valittuSupis != null)
					poistaKyk(valittuSupis);
			}
		});

		menudropMuokkaa.add(menuItem_7);
		menudropOhje
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		menuValikko.add(menudropOhje);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ohje();
			}
		});

		menudropOhje.add(menuItem_8);
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutti();
			}
		});

		menudropOhje.add(menuItem_9);
		panelSisalto = new JPanel();
		panelSisalto.setPreferredSize(new Dimension(300, 200));
		panelSisalto.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSisalto.setLayout(new BorderLayout(0, 0));
		setContentPane(panelSisalto);

		panelSisalto.add(panelNappulat, BorderLayout.SOUTH);

		panelNappulat.add(supisNappulat);
		supisNappulat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnTallennaSupis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (onkomuutettu == false) {
					JOptionPane.showMessageDialog(null,
							"Et ole muuttanut mit‰‰n!", "Ei muutoksia",
							JOptionPane.INFORMATION_MESSAGE);
				}
				if (onkomuutettu == true) {
					StringBuilder supentiedot = new StringBuilder();
					supentiedot.append(valittuSupis.getId()+"|");
					for (int i = 0; i < editSupisKentta.length; i++) {
						EditPanel edit = editSupisKentta[i];
						String s = edit.getText();
						supentiedot.append(s + "|");
					}
					rekkari.tallennaSupisJaKaverit(valittuSupis,
							supentiedot.toString());
					hae(0);
				}

				//
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Kun ohjelma toimii, t‰t‰ painamalla tallennat\nmahdollisesti muuttamasi supiksen tiedot. Et viel‰.",
				// "Tba", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		supisNappulat.add(btnTallennaSupis);
		FlowLayout fl_kykyNappulat = (FlowLayout) kykyNappulat.getLayout();
		fl_kykyNappulat.setAlignment(FlowLayout.RIGHT);

		panelNappulat.add(kykyNappulat);
		tallennaKyKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				uusiKykySupelle();

			}
		});

		kykyNappulat.add(tallennaKyKy);

		panelSisalto.add(splitpanelListaSupisTiedot, BorderLayout.CENTER);

		splitpanelListaSupisTiedot.setLeftComponent(panelLista);
		panelLista.setLayout(new BorderLayout(0, 0));
		panelLista.add(panelHaku, BorderLayout.NORTH);
		panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
		labelHakuehto.setAlignmentX(Component.RIGHT_ALIGNMENT);

		panelHaku.add(labelHakuehto);
		cbKentat.addSelectionChangeListener(new SelectionChangeListener<String>() {
			public void selectionChange(IStringListChooser<String> arg0) {
				cbIndeksi = cbKentat.getSelectedIndex();
			}
		});
		// cbKentat.setModel(new DefaultComboBoxModel<String>(new String[] {
		// "Stage Name", "Nimi", "Puoliso", "Kotikaupunki",
		// "JLU yhteysnumero" }));

		panelHaku.add(cbKentat);

		panelHaku.add(txtHaku);
		scrollSupikset.setToolTipText("");

		panelLista.add(scrollSupikset, BorderLayout.CENTER);
		supiksetListassa.getCaptionLabel().setText("Supersankarit");
		tietojenNayttoKuntoon();
		supiksetListassa
				.addSelectionChangeListener(new SelectionChangeListener<String>() {
					public void selectionChange(IStringListChooser<String> arg0) {

						valitunIndeksi = supiksetListassa.getSelectedIndex();
						valittuSupisJono = supiksetListassa
								.getKohde(valitunIndeksi);
						valittuSupis = rekkari
								.annaSupisNimenPerusteella(valittuSupisJono);
						if (valitunIndeksi > -1) {

							valitunKyvyt.clear();
							valitunKyvyt = rekkari.supiksenKyvyt(valittuSupis
									.getId());
						}

						naytaValitunTiedot();
						naytaSupetVahvuusJarjestyksessa();
						// supiksetListassa.clearSelection();
					}
				});
		supiksetListassa.setKohteet(new String[] { "Batman", "Captain America",
				"Iron Man" });

		scrollSupikset.setViewportView(supiksetListassa);
		splitPanelTiedotJaKyvyt.setResizeWeight(0.5);

		splitpanelListaSupisTiedot.setRightComponent(splitPanelTiedotJaKyvyt);
		panelKyvyt.setMaximumSize(new Dimension(200, 14));

		splitPanelTiedotJaKyvyt.setRightComponent(panelKyvyt);
		panelKyvyt.setLayout(new BorderLayout(0, 0));

		panelKyvyt.add(labelKyvyt, BorderLayout.NORTH);
		scrollVoimaTable.setMaximumSize(new Dimension(200, 14));

		panelKyvyt.add(scrollVoimaTable, BorderLayout.CENTER);

		scrollVoimaTable.setViewportView(panelKyky);
		panelKyky.setLayout(new BorderLayout(0, 0));

		panelKyky.add(panelKykyeditit, BorderLayout.NORTH);
		panelKykyeditit.setLayout(new BoxLayout(panelKykyeditit,
				BoxLayout.Y_AXIS));
		editPanel_6.getLabel().setText("Supervoimat");
		editPanel_6.getEdit().setText("750");

		panelKykyeditit.add(editPanel_6);
		editPanel_7.getLabel().setText("Gadgets");
		editPanel_7.getEdit().setText("680");

		panelKykyeditit.add(editPanel_7);
		editPanel_8.getLabel().setText("Long range");
		editPanel_8.getEdit().setText("550");

		panelKykyeditit.add(editPanel_8);
		editPanel_9.getLabel().setText("Melee");
		editPanel_9.getEdit().setText("350");

		panelKykyeditit.add(editPanel_9);
		editPanel_10.getLabel().setText("Projektiilit");
		editPanel_10.getEdit().setText("150");

		panelKykyeditit.add(editPanel_10);
		panelSupiksenTiedot.setPreferredSize(new Dimension(200, 10));
		splitPanelTiedotJaKyvyt.setLeftComponent(panelSupiksenTiedot);
		panelSupiksenTiedot.setLayout(new BorderLayout(0, 0));
		splitPaneSupisTiedot.setResizeWeight(0.5);
		splitPaneSupisTiedot.setOrientation(JSplitPane.VERTICAL_SPLIT);

		panelSupiksenTiedot.add(splitPaneSupisTiedot, BorderLayout.CENTER);

		splitPaneSupisTiedot.setLeftComponent(panelSupisTiedot);
		panelSupisTiedot.setLayout(new BorderLayout(0, 0));

		panelSupisTiedot.add(label, BorderLayout.NORTH);
		scrollPaneSupisTiedot.setToolTipText("");
		scrollPaneSupisTiedot.setPreferredSize(new Dimension(200, 14));

		panelSupisTiedot.add(scrollPaneSupisTiedot, BorderLayout.CENTER);

		scrollPaneSupisTiedot.setViewportView(panelTiedot);
		panelTiedot.setLayout(new BorderLayout(0, 0));

		panelTiedot.add(valitunSupiksenTiedot, BorderLayout.NORTH);
		valitunSupiksenTiedot.setLayout(new BoxLayout(valitunSupiksenTiedot,
				BoxLayout.Y_AXIS));
		editPanel.getEdit().setText("Captain America");
		editPanel.setCaption("Stage name");

		valitunSupiksenTiedot.add(editPanel);
		editPanel_1.getEdit().setText("Rogers Steve");
		editPanel_1.setCaption("Sukunimi Etunimi");

		valitunSupiksenTiedot.add(editPanel_1);
		editPanel_2.getLabel().setText("Puoliso");
		editPanel_2.getEdit().setText("Stark Tony");

		valitunSupiksenTiedot.add(editPanel_2);
		editPanel_3.getLabel().setText("Kotikaupunki");
		editPanel_3.getEdit().setText("Brooklyn");

		valitunSupiksenTiedot.add(editPanel_3);
		editPanel_4.getLabel().setText("JLU yhteysnumero");
		editPanel_4.getEdit().setText("2");

		valitunSupiksenTiedot.add(editPanel_4);
		editPanel_5.getLabel().setText("Voimanumero");
		editPanel_5.getEdit().setText("800");

		valitunSupiksenTiedot.add(editPanel_5);

		panelTiedot.add(labelVirhe, BorderLayout.SOUTH);

		splitPaneSupisTiedot.setRightComponent(panelVahvemmat);
		panelVahvemmat.setLayout(new BorderLayout(0, 0));

		scrollPanelVahvemmat
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelVahvemmat.add(scrollPanelVahvemmat, BorderLayout.CENTER);
		vahvemmatSupikset.setEditable(false);

		scrollPanelVahvemmat.setViewportView(vahvemmatSupikset);

		panel.setLayout(new BorderLayout(0, 0));

		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		alustaApulainen();
		tietojenNayttoKuntoon();
		alustaSupisListaKayttoon();
		kykyjenNayttoKuntoon();
		comboboxikokoon();
		supiksetListassa.setSelectedIndex(0);
	}


	/**
	 * @return hakuehdon
	 */
	public String getLabelHakuehtoText() {
		return labelHakuehto.getText();
	}


	/**
	 * @param text
	 *            asetettava hakuteksti
	 */
	public void setLabelHakuehtoText(String text) {
		labelHakuehto.setText(text);
	}


	// ///////////////////////////////////
	// //siirtyy muualle
	/**
	 * Luo uuden ja laittaa Kyvyt-dialogin n‰kyviin
	 */
	protected void kykyNakyma() {

		Kyvyt kykynakyma = new Kyvyt();
		kykynakyma.setVisible(true);
	}


	/**
	 * Luo uuden ja laittaa UusiSupis-dialogin n‰kyviin
	 */
	protected void uusiSupis() {
		UusiSupis uusSupis = new UusiSupis(this, apusupe);
		uusSupis.setVisible(true);

	}


	/**
	 * Luo uuden ja laittaa UusiSupis-dialogin n‰kyviin
	 */
	protected void uusiKykySupelle() {
		if (valittuSupis == null)
			return;

		UusiKyky uusKyky = new UusiKyky(this, valittuSupis);
		uusKyky.setVisible(true);

	}


	/**
	 * Luo uuden ja laittaa PoistaSupis-dialogin n‰kyviin
	 */
	protected void poistaSup() {
		PoistaSupis poisSup = new PoistaSupis(rekkari);
		poisSup.setVisible(true);

	}


	/**
	 * Luo uuden ja laittaa PoistaKyky-dialogin n‰kyviin
	 */
	protected void poistaKyk(rekisteri.Supersankari valkattuSupis) {
		PoistaKyky poisKyk = new PoistaKyky(rekkari, valkattuSupis);
		poisKyk.setVisible(true);

	}


	/**
	 * Luo uuden ja Luo uuden ja laittaa SupisOhje-dialogin n‰kyviin
	 */
	protected void ohje() {
		SupisOhje ohjeet = new SupisOhje();
		ohjeet.setVisible(true);

	}


	/**
	 * Luo uuden ja Luo uuden ja laittaa SupisAbout-dialogin n‰kyviin
	 */
	protected void aboutti() {
		SupisAbout aboutt = new SupisAbout();
		aboutt.setVisible(true);

	}


	/**
	 * Lis‰t‰‰n uusi supis
	 *
	 * @param tiedot
	 *            uuden supen tiedot merkkijonona
	 */
	public void lisaaSupis(String tiedot) {
		rekkari.lisaaSupis(tiedot, "supis.dat");
		alustaSupisListaKayttoon();

		// supet = rekkari.annaSupiksetTaulukossa();
		// valittuSupis = rekkari.annaSupis(0);
		// validate();
		// tehty: hae(0) jotta p‰ivittyy kivasti tiedot!
	}


	/**
	 * Antaa kyvyn nimen sen id:n perusteella
	 *
	 * @param id
	 *            kyvyn id
	 * @return kyvyn nimen
	 */
	public String annaKyvynNimi(int id) {
		return rekkari.annaKyvynNimi(id);
	}


	/**
	 * N‰ytt‰‰ valitun supiksen tiedot eli laittaa tiedot n‰kyviin
	 * editpaneeleihin
	 */
	public void naytaValitunTiedot() {
		if (valittuSupis == null)
			return;
		// valitunSupiksenTiedot.removeAll();

		for (int i = 0, k = valittuSupis.ekaKentta(); k < valittuSupis
				.getKenttia(); k++, i++) {
			editSupisKentta[i].setText(valittuSupis.anna(k).toString());
			editSupisKentta[i].getEdit().setBackground(normaaliVari);
			editSupisKentta[i].setToolTipText("");
			editSupisKentta[i].addKeyListener(new KeyAdapter() { // NOPMD
						@Override
						public void keyReleased(KeyEvent e) {
							kasitteleMuutosSupenTietoihin((JTextField) e
									.getComponent());
						}
					});
		}
		naytaValitunKyvyt();

	}


	/**
	 * Nayttaa valitun supen kyvyt sen perusteella montako kyky‰ supiksella on
	 */
	public void naytaValitunKyvyt() {
		panelKykyeditit.removeAll();
		editKykyKentta.clear();
		valitunKyvyt = rekkari.supiksenKyvyt(valittuSupis.getId());
		for (int j = 0, z = 0; z < valitunKyvyt.size(); z++, j++) {
			EditPanel edit = new EditPanel();
			edit.setCaption(rekkari.annaParinKyvynNimi(valitunKyvyt.get(j)));
			edit.setText(valitunKyvyt.get(j).getVoimakkuus() + "");
			edit.setName("ep" + j);
			edit.getEdit().setName("tj" + z);
			edit.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					kasitteleMuutosSupenTietoihin((JTextField) e.getComponent());
				}
			});
			editKykyKentta.add(edit);
			panelKykyeditit.add(edit);

		}
		this.validate();

	}


	/**
	 * Luo editpaneeleja ihan vaan nayttaaksemme kayttajalle ett‰ t‰h‰n tulee
	 * jotain kun klikkaa
	 */
	public void kykyjenNayttoKuntoon() {
		panelKykyeditit.removeAll();

		for (int j = 0, z = 0; z < valitunKyvyt.size(); z++, j++) {
			EditPanel edit = new EditPanel();
			edit.setCaption("Kyky" + (j + 1));
			editKykyKentta.add(edit);
			edit.setName("ej" + z);
			edit.getEdit().setName("tj" + z);
			panelKykyeditit.add(edit);
			edit.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					// kasitteleMuutosJaseneen((JTextField)e.getComponent());
				}
			});

		}
	}


	/**
	 * Alustaa apulaisen toimivaksi
	 */
	public void alustaApulainen() {
		apusupe.alustaToimivaksi();

		// int n = apusupe.getKenttia()-apusupe.ekaKentta();
		// apuKentat = new String[n];
		// for (int i = 0; i < apuKentat.length; i++)
		// apuKentat[i] = apusupe.getKysymys(i);

	}


	/**
	 * K‰sittelee edit-kentt‰‰n tulleen muutoksen j‰seneen. Mik‰li j‰sent‰ ei
	 * viel‰ ole editoitu, luodaan j‰senest‰ kopio editJasen-olioon ja muutokset
	 * kohdistetaan t‰h‰n j‰seneen. N‰in voidaan muutoksia verrata jasen-olioon
	 * ja tunnistetaan muutostarpeet. Jos muutosta ei voida sijoittaa j‰seneen,
	 * muutetaan taustav‰ri punaiseksi.
	 *
	 * @param edit
	 *            muuttunut kentt‰
	 */
	protected void kasitteleMuutosSupenTietoihin(JTextField edit) {
		// if (valittuSupis == null) luoUusiJasen();
		// if (editJasen == null)
		// try {
		// setEditJasen(jasenKohdalla.clone());
		// } catch (CloneNotSupportedException e) { // virhett‰ ei tule
		// }
		onkomuutettu = true;
		String s = edit.getText();
		int k = Integer.parseInt(edit.getName().substring(2));
		String virhe = valittuSupis.aseta(k, s);
		setVirhe(virhe);
		if (virhe == null) {
			edit.setToolTipText("");
			edit.setBackground(normaaliVari);
		} else {
			edit.setToolTipText(virhe);
			edit.setBackground(virheVari);
		}
	}


	/**
	 * comboboxiin kent‰t kuntoon. Vaikka se onkin oikeasti abstractChooser.
	 */
	public void comboboxikokoon() {
		// int n = apusupe.getKenttia()-apusupe.ekaKentta();
		// apuKentat = new String[n];
		for (int i = apusupe.ekaKentta(); i < apusupe.getKenttia(); i++)
			cbKentat.add(apusupe.anna(i).getKysymys());
		cbKentat.setSelectedIndex(0);
		// cbKentat.clear();
		// cbKentat = new JComboBox<String>(apuKentat);
	}


	/**
	 * Laitetaan virheilmoitus n‰kyville jos labelVirhe on alustettu. Mik‰li
	 * virhett‰ ei ole, niin piilotetaan virheilmoitus.
	 *
	 * @param virhe
	 *            virheteksti
	 */
	private void setVirhe(String virhe) {
		if (labelVirhe == null)
			return;
		if (virhe == null) {
			labelVirhe.setVisible(false);
			return;
		}
		labelVirhe.setVisible(true);
		labelVirhe.setText(virhe);
		labelVirhe.setBackground(virheVari);
	}


	/**
	 * Luo editpaneeli-taulukon sen perusteella montako kentt‰‰ supiksella on
	 */
	public void tietojenNayttoKuntoon() {
		valitunSupiksenTiedot.removeAll();
		int n = apusupe.getKenttia() - apusupe.ekaKentta();
		editSupisKentta = new EditPanel[n];

		for (int i = 0, k = apusupe.ekaKentta(); k < apusupe.getKenttia(); k++, i++) {
			EditPanel edit = new EditPanel();
			edit.setCaption(apusupe.getKysymys(k));
			editSupisKentta[i] = edit;
			edit.setName("ej" + k);
			edit.getEdit().setName("tj" + k);
			valitunSupiksenTiedot.add(edit);
			edit.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					// kasitteleMuutosJaseneen((JTextField)e.getComponent());
				}
			});

		}
	}


	/**
	 * Kysyy k‰ytt‰j‰lt‰ aivan uuden lis‰tt‰v‰n kyvyn nimen
	 */
	protected void ihanUusiKyky() {
		String s = (String) JOptionPane.showInputDialog(this,
				"Kirjoita uuden kyvyn nimi", "Aivan uuden kyvyn lis‰ys",
				JOptionPane.PLAIN_MESSAGE, null, null, "");

		if ((s != null) && (s.length() > 0)) {
			try {
				rekkari.lisaaAivanUusiKyky(s, "kyvyt.dat");
			} catch (SailoException e) {
				e.printStackTrace();
			}
		}
		return;

	}


	/**
	 * Aliohjelma joka heivaa rekkarille pyynnˆn lis‰t‰ supersankarille kyky
	 *
	 * @param kenelle
	 *            supen stagename
	 * @param mikaKyky
	 *            mik‰ kyky lis‰t‰‰n
	 * @param voimakkuus
	 *            supen voimakkuus kyvyss‰
	 */
	public void lisaaSupelleKyky(String kenelle, String mikaKyky, int voimakkuus) {
		rekkari.lisaaKykySupikselle(kenelle, mikaKyky, voimakkuus);
		alustaSupisListaKayttoon();
	}


	/**
	 * @return Kyvyt taulukossa
	 */
	public rekisteri.Kyky[] annaKyvyt() {
		return rekkari.annaKyvyt();
	}


	/**
	 * @param valitunSupenId
	 *            valitun supen id
	 * @return ei supiksen kyvyt listassa
	 */
	public List<Integer> eiSupenKyvyt(int valitunSupenId) {
		return rekkari.eiSupiksenKyvyt(valitunSupenId);
	}


	/*
	 * Hakee k‰ytt‰j‰n antamalla ehdolla ja comboboxin valinnalla
	 *
	 * @param indeksi supis joka aktivoidaan kun haku on valmis. jos -1 niin
	 * aktivoidaan valittusupis
	 */
	public void hae(int indeksi) {
		int jn = indeksi;
		if (jn < 0 && valittuSupis != null)
			jn = valittuSupis.getId();
		// int k = cbIndeksi + apusupe.ekaKentta();
		// if (ehto.length() == 0){
		// alustaSupisListaKayttoon();
		// return;
		// }
		// Collection<rekisteri.Supersankari> haunTulos = rekkari.etsi(ehto, k);
		// if (haunTulos.size() == 0) return;
		// supiksetListassa.clear();
		//
		// //int index = 0, i = 0;
		// for (rekisteri.Supersankari supe : haunTulos ) {
		// //if (supe.getId() == jn) index = i;
		// supiksetListassa.add(supe.getStageName());
		// //i++;
		// }

		supiksetListassa.setSelectedIndex(0);
		int k = cbIndeksi + apusupe.ekaKentta();
		String ehto = txtHaku.getText();
		if (ehto.indexOf('*') < 0) {
			ehto = "*" + ehto + "*";
		}
		List<rekisteri.Supersankari> haunTulos = rekkari.etsi(ehto, k);
		if (haunTulos.size() > 0) {
			supiksetListassa.clear();
			// Collections.sort(haunTulos, new
			// rekisteri.Supersankari.Vertailija(
			// stageNamenIndeksi));
			for (rekisteri.Supersankari supe : haunTulos) {
				supiksetListassa.add(supe.getStageName());
			}

			supiksetListassa.setSelectedIndex(jn);

		}

		// int index = 0, i = 0;
		// for (rekisteri.Supersankari supe : haunTulos) {
		// if (supe.getTunnusNro() == jn)
		// index = i;
		// listJasenet.add(supe.jasen.getNimi(), jasen);
		// i++;
		// }

	}


	/**
	 * Pist‰‰ alusta l‰htien listaan ne supikset mit‰ tiedostosta on taulukkoon
	 * luettu
	 */
	public void alustaSupisListaKayttoon() {
		supiksetListassa.clear();
		supet = rekkari.annaSupiksetTaulukossa();

		for (int i = 0; i < supet.length; i++) {
			if (supet[i] != null)
				supiksetListassa.add(supet[i].getStageName());
		}

	}


	/**
	 * J‰rjest‰‰ supet vahvuuden mukaan j‰rjestykseen ja kirjottaa niiden
	 * stagenamet textareaan mutta ei n‰yt‰ valittua supista koska se on jo
	 */
	public void naytaSupetVahvuusJarjestyksessa() {
		if (valittuSupis == null)
			return;
		supet = rekkari.annaSupiksetTaulukossa();
		List<rekisteri.Supersankari> supikset = new ArrayList<rekisteri.Supersankari>();
		for (int i = 0; i < supet.length; i++) {
			if ((supet[i] == null))
				continue;
			if (!valittuSupis.getStageName().equals(supet[i].getStageName()))
				supikset.add(supet[i]);
		}
		int voimanumeronIndeksi = -1;
		for (int i = valittuSupis.ekaKentta(); i < valittuSupis.getKenttia(); i++) {
			if (valittuSupis.anna(i).getKysymys().equals("Voimanumero"))
				voimanumeronIndeksi = i;
		}
		if (voimanumeronIndeksi >= 0) {
			Collections.sort(supikset, new rekisteri.Supersankari.Vertailija(
					voimanumeronIndeksi));
		}
		vahvemmatSupikset.setText("");
		for (int i = (supikset.size() - 1); i >= 0; i--)
			vahvemmatSupikset.append(supikset.get(i).getStageName() + "\n");

	}
}

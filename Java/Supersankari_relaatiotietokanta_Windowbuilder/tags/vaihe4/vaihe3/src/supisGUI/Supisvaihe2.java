package supisGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.ComponentOrientation;
import javax.swing.AbstractListModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 *
 */
public class Supisvaihe2 extends JFrame {

	/**
	 *
	 */
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
	private final JTable tableKyvyt = new JTable();
	private final JPanel supisNappulat = new JPanel();
	private final JPanel kykyNappulat = new JPanel();
	private final JComboBox cbKentat = new JComboBox();
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JTextField txtHaku = new JTextField();
	private final JButton btnTallennaSupis = new JButton("Tallenna supersankarin tiedot");
	private final JButton tallennaKyKy = new JButton("Tallenna supersankarin kykytiedot");
	private final JLabel lblNewLabel = new JLabel("Supersankarit");
	private final JLabel labelHakuehto = new JLabel("Hakuehto");
	private final JMenuBar menuValikko = new JMenuBar();
	private final JMenu menudropTiedosto = new JMenu("Tiedosto");
	private final JMenu menudropMuokkaa = new JMenu("Muokkaa");
	private final JMenu menudropOhje = new JMenu("Ohje");
	private final JMenuItem menuTSisaltoTallenna = new JMenuItem("Tallenna");
	private final JMenuItem menuTSisaltoValitseSupis = new JMenuItem("Valitse supersankari...");
	private final JMenuItem menuTSisaltoValitseKyky = new JMenuItem("Valitse kyky...");

	private final JMenuItem menuTSisaltoLopeta = new JMenuItem("You'll be back");
	private final JMenuItem menuMSisaltoLisaaUSupis = new JMenuItem("Lis\u00E4\u00E4 uusi supersankari");
	private final JMenuItem menuMSisaltoLisaaUSkyky = new JMenuItem("Lis\u00E4\u00E4 uusi kyky");
	private final JMenuItem menuMSisaltoPoisSupis = new JMenuItem("Poista supersankari...");
	private final JMenuItem menuMSisaltoPoisKyky = new JMenuItem("Poista kyky...");
	private final JMenuItem menuOSisaltoOhje = new JMenuItem("K\u00E4ytt\u00F6ohjeet");
	private final JMenuItem menuOSisaltoAbout = new JMenuItem("About Batmanin supersankarirekisteri");
	private final JMenuItem mennuMSisaltoMuok = new JMenuItem("Muokkaa supersankarin tietoja...");
	private final JMenuItem menuMSisaltoMuokKyk = new JMenuItem("Muokkaa kyvyn tietoja...");
	private final JList list = new JList();
	private final JSplitPane splitPaneSupisTiedot = new JSplitPane();
	private final JPanel panelSupisTiedot = new JPanel();
	private final JPanel panelVahvemmat = new JPanel();
	private final JLabel label = new JLabel("Supersankarin tiedot");
	private final JScrollPane scrollPaneSupisTiedot = new JScrollPane();
	private final JTable tableSupisTiedot = new JTable();
	private final JScrollPane scrollPanelVahvemmat = new JScrollPane();
	private final JList listVahvemmat = new JList();
	private final JLabel lblVahvemmat = new JLabel("Vahvemmat supersankarit");

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
					Supisvaihe2 frame = new Supisvaihe2();
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
	public Supisvaihe2() {
		txtHaku.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 626);
		panelSisalto = new JPanel();
		panelSisalto.setPreferredSize(new Dimension(300, 200));
		panelSisalto.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSisalto.setLayout(new BorderLayout(0, 0));
		setContentPane(panelSisalto);

		panelSisalto.add(panelNappulat, BorderLayout.SOUTH);

		panelNappulat.add(supisNappulat);
		supisNappulat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		supisNappulat.add(btnTallennaSupis);
		FlowLayout fl_kykyNappulat = (FlowLayout) kykyNappulat.getLayout();
		fl_kykyNappulat.setAlignment(FlowLayout.RIGHT);

		panelNappulat.add(kykyNappulat);

		kykyNappulat.add(tallennaKyKy);

		panelSisalto.add(splitpanelListaSupisTiedot, BorderLayout.CENTER);

		splitpanelListaSupisTiedot.setLeftComponent(panelLista);
		panelLista.setLayout(new BorderLayout(0, 0));
		panelLista.add(panelHaku, BorderLayout.NORTH);
		panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
		labelHakuehto.setAlignmentX(Component.RIGHT_ALIGNMENT);

		panelHaku.add(labelHakuehto);
		cbKentat.setModel(new DefaultComboBoxModel(new String[] {"Stage Name", "Nimi", "Puoliso", "Kotikaupunki", "JLU yhteysnumero"}));

		panelHaku.add(cbKentat);

		panelHaku.add(txtHaku);
		lblNewLabel.setAlignmentX(0.8f);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);

		panelHaku.add(lblNewLabel);

		panelLista.add(scrollSupikset, BorderLayout.CENTER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showConfirmDialog(null,
						"Kun ohjelma toimii voit klikata yht� nime� ja saada sen tiedot. Et viel�.", "N�e supersankarin tiedot", JOptionPane.OK_OPTION);
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Batman", "Captain America", "Iron Man"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

		scrollSupikset.setViewportView(list);
		splitPanelTiedotJaKyvyt.setResizeWeight(1.0);

		splitpanelListaSupisTiedot.setRightComponent(splitPanelTiedotJaKyvyt);
		panelKyvyt.setMaximumSize(new Dimension(200, 14));

		splitPanelTiedotJaKyvyt.setRightComponent(panelKyvyt);
		panelKyvyt.setLayout(new BorderLayout(0, 0));

		panelKyvyt.add(labelKyvyt, BorderLayout.NORTH);
		scrollVoimaTable.setMaximumSize(new Dimension(200, 14));

		panelKyvyt.add(scrollVoimaTable, BorderLayout.CENTER);
		tableKyvyt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null,
						"Kun ohjelma toimii voit muokata kykytietoja. Et viel�.", "Muokaa kyvyn tietoja", JOptionPane.OK_OPTION);

			}
		});

		tableKyvyt.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tuli", "0"},
				{"J\u00E4\u00E4", "0"},
				{"Projektiilit", "100"},
				{"Taikuus", "0"},
				{"Melee", null},
				{"Long range", "650"},
				{"Supervoimat", "750"},
				{"Gadgets", "850"},
			},
			new String[] {
				"Kyky", "Voimakkuus"
			}
		));

		scrollVoimaTable.setViewportView(tableKyvyt);
		panelSupiksenTiedot.setPreferredSize(new Dimension(200, 10));
		splitPanelTiedotJaKyvyt.setLeftComponent(panelSupiksenTiedot);
		panelSupiksenTiedot.setLayout(new BorderLayout(0, 0));
		splitPaneSupisTiedot.setResizeWeight(1.0);
		splitPaneSupisTiedot.setOrientation(JSplitPane.VERTICAL_SPLIT);

		panelSupiksenTiedot.add(splitPaneSupisTiedot, BorderLayout.CENTER);

		splitPaneSupisTiedot.setLeftComponent(panelSupisTiedot);
		panelSupisTiedot.setLayout(new BorderLayout(0, 0));

		panelSupisTiedot.add(label, BorderLayout.NORTH);
		scrollPaneSupisTiedot.setToolTipText("");
		scrollPaneSupisTiedot.setPreferredSize(new Dimension(200, 14));

		panelSupisTiedot.add(scrollPaneSupisTiedot, BorderLayout.CENTER);
		tableSupisTiedot.setModel(new DefaultTableModel(
			new Object[][] {
					{"Stage name", "Captain America"},
					{"Sukunimi Etunimi", "Rogers Steve"},
					{"Puoliso", "Stark Tony"},
					{"Kotikaupunki", "Brooklyn"},
					{"JLU yhteysnumero", "81"},
					{"Voimanumero", "800"},
					{"P\u00E4ihitt\u00E4v\u00E4 supersankari", "Batman"},
					{null, null},
				},
				new String[] {
					"Kohdat", "Tiedot"
				}
		));

		scrollPaneSupisTiedot.setViewportView(tableSupisTiedot);

		splitPaneSupisTiedot.setRightComponent(panelVahvemmat);
		panelVahvemmat.setLayout(new BorderLayout(0, 0));
		scrollPanelVahvemmat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelVahvemmat.add(scrollPanelVahvemmat, BorderLayout.CENTER);
		listVahvemmat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showConfirmDialog(null,
						"Kun ohjelma toimii voit klikata listasta supersankaria ja n�hd� sen tiedot. Et viel�.", "N�e vahvemman sankarin tiedot", JOptionPane.OK_OPTION);

			}
		});
		listVahvemmat.setModel(new AbstractListModel() {
			String[] values = new String[] {"Batman", "Rorscach", "Deadpool", "Spiderman", "Superman"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

		scrollPanelVahvemmat.setViewportView(listVahvemmat);

		panelVahvemmat.add(lblVahvemmat, BorderLayout.NORTH);

		panel.setLayout(new BorderLayout(0, 0));

		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		panelSisalto.add(menuValikko, BorderLayout.NORTH);

		menuValikko.add(menudropTiedosto);

		menudropTiedosto.add(menuTSisaltoTallenna);

		menudropTiedosto.add(menuTSisaltoValitseSupis);

		menudropTiedosto.add(menuTSisaltoValitseKyky);


		menudropTiedosto.add(menuTSisaltoLopeta);

		menuValikko.add(menudropMuokkaa);

		menudropMuokkaa.add(mennuMSisaltoMuok);

		menudropMuokkaa.add(menuMSisaltoMuokKyk);

		menudropMuokkaa.add(menuMSisaltoLisaaUSupis);

		menudropMuokkaa.add(menuMSisaltoLisaaUSkyky);
		menudropMuokkaa.add(menuMSisaltoPoisSupis);
		menudropMuokkaa.add(menuMSisaltoPoisKyky);
		menudropOhje.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		menuValikko.add(menudropOhje);

		menudropOhje.add(menuOSisaltoOhje);

		menudropOhje.add(menuOSisaltoAbout);
	}

	public String getLabelHakuehtoText() {
		return labelHakuehto.getText();
	}
	public void setLabelHakuehtoText(String text) {
		labelHakuehto.setText(text);
	}
}

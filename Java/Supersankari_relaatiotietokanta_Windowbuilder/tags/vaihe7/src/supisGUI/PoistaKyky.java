package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import rekisteri.Rekisteri;

import fi.jyu.mit.gui.IStringListChooser;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.SelectionChangeListener;

/**
 * Poistaa supersankarilta kyvyn
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @modified 9.4.2013
 *
 */
public class PoistaKyky extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private final ListChooser listaValitsin = new ListChooser();
	private int indeksi = -1;
	private final Rekisteri rekkari;
	private rekisteri.Supersankari kenelta;
	private String mika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * try { PoistaKyky dialog = new PoistaKyky();
		 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace();
		 * }
		 */}


	/**
	 * Muodostetaan PoistaKyky
	 *
     * @param rekisteri
	 *            rekisteri joka muutoksilla pelaa
	 * @param keneltaPoistetaan
	 *            kenelt‰ supikselta poistetaan
	 */
	public PoistaKyky(Rekisteri rekisteri, rekisteri.Supersankari keneltaPoistetaan) {
		this.rekkari = rekisteri;
		kenelta = keneltaPoistetaan;
		setBounds(100, 100, 352, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelPoisKyk = new JPanel();
			contentPanel.add(panelPoisKyk, BorderLayout.CENTER);
			panelPoisKyk.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelPoisKyk.add(scrollPane, BorderLayout.CENTER);
				{
					final ListChooser listChooser = new ListChooser();
					listChooser.addSelectionChangeListener(new SelectionChangeListener<String>() {
						public void selectionChange(IStringListChooser<String> arg0) {
							indeksi = listChooser.getSelectedIndex();
						}
					});
					listChooser
							.getCaptionLabel()
							.setText(
									"Supiksen kyvyn poisto: Valitse listasta ja paina \"poista...\"");
					listChooser.setKohteet(new String[] { "Projektiilit",
							"Melee", "Long range", "Supervoimat" });
					scrollPane.setViewportView(listChooser);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Poista...");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (indeksi >= 0) {
							mika = listaValitsin.getKohde(indeksi);
							Object[] options = { "Joo joo", "Ep‰ilytt‰‰ viel‰" };
							int n = JOptionPane
									.showOptionDialog(
											null,
											"Oletko nyt ihan varma ett‰ valitsemasi supis menetti tuon kyvyn?",
											"Supiksen kyvyn poiston varmistus",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											options, options[0]);
							if (n == 0) {
								rekkari.poistaKykySupikselta(kenelta, mika);
								dispose();
							} else
								dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

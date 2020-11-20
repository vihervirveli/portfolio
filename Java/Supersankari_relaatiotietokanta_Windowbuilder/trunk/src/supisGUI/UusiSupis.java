package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fi.jyu.mit.gui.EditPanel;

/**
 * Uuden supersankarin lis‰‰minen
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @version 22.4.2013
 *
 */
public class UusiSupis extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Supis suppe;
	private rekisteri.Supersankari apusupe;
	private EditPanel[] taytettavatTiedot;
	private EditPanel[] taytettavatKyvyt;
	private JPanel panelTiedot;
	private JPanel panelUusSupKykyEdit;
	private StringBuilder tiedot = new StringBuilder();
	private rekisteri.Kyky[] kyvyt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// try {
		// UusiSupis dialog = new UusiSupis();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}


	/**
	 * Create the dialog.
	 */
	public UusiSupis(Supis supe, rekisteri.Supersankari apusupis) {

		suppe = supe;
		apusupe = apusupis;
		setBounds(100, 100, 490, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelSupis = new JPanel();
			contentPanel.add(panelSupis, BorderLayout.CENTER);
			panelSupis.setLayout(new BorderLayout(0, 0));
			{
				JSplitPane splitPane = new JSplitPane();
				splitPane.setResizeWeight(0.3);
				panelSupis.add(splitPane, BorderLayout.CENTER);
				{
					JPanel panelSupisTiedot = new JPanel();
					splitPane.setLeftComponent(panelSupisTiedot);
					panelSupisTiedot.setLayout(new BorderLayout(0, 0));
					{
						panelTiedot = new JPanel();
						panelSupisTiedot.add(panelTiedot, BorderLayout.NORTH);
						panelTiedot.setLayout(new BoxLayout(panelTiedot,
								BoxLayout.Y_AXIS));
						{
							EditPanel editStage = new EditPanel();
							editStage.setCaption("Stage name");
							panelTiedot.add(editStage);
						}
						{
							EditPanel editNimi = new EditPanel();
							editNimi.setCaption("Sukunimi Etunimi");
							panelTiedot.add(editNimi);
						}
						{
							EditPanel editPuoliso = new EditPanel();
							editPuoliso.setCaption("Puoliso");
							panelTiedot.add(editPuoliso);
						}
						{
							EditPanel editKoti = new EditPanel();
							editKoti.setCaption("Kotikaupunki");
							panelTiedot.add(editKoti);
						}
						{
							EditPanel editJLU = new EditPanel();
							editJLU.setCaption("JLU yhteysnro");
							panelTiedot.add(editJLU);
						}
						{
							EditPanel editVoimaNro = new EditPanel();
							editVoimaNro.setCaption("Voimanumero");
							panelTiedot.add(editVoimaNro);
						}
					}
				}
				{
					JPanel panelKyvyt = new JPanel();
					splitPane.setRightComponent(panelKyvyt);
					panelKyvyt.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						scrollPane
								.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						panelKyvyt.add(scrollPane, BorderLayout.CENTER);
						{
							JPanel panelKUusiSupKyky = new JPanel();
							scrollPane.setViewportView(panelKUusiSupKyky);
							panelKUusiSupKyky.setLayout(new BorderLayout(0, 0));
							{
								panelUusSupKykyEdit = new JPanel();
								panelKUusiSupKyky.add(panelUusSupKykyEdit,
										BorderLayout.NORTH);
								panelUusSupKykyEdit.setLayout(new BoxLayout(
										panelUusSupKykyEdit, BoxLayout.Y_AXIS));
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText("Tuli");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText(
											"J\u00E4\u00E4");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel()
											.setText("Projektiilit");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText("Taikuus");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText("Melee");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText("Long range");
									panelUusSupKykyEdit.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.getLabel().setText("Supervoimat");
									panelUusSupKykyEdit.add(editPanel);
								}
							}
						}
					}
				}

				{
					JLabel lblUusiSupis = new JLabel(
							"Uuden supersankarin lis\u00E4ys: t\u00E4yt\u00E4 tiedot ja voimakkuus kyvyiss\u00E4");
					panelSupis.add(lblUusiSupis, BorderLayout.NORTH);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("Tallenna uusi supersankari");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							EditPanel stage = taytettavatTiedot[0];
								//stage.getName().equals("ej" + 0) &&
							if (stage == null)
								return;
							String supenNimi = stage.getText();
							for (int i = 0; i < taytettavatTiedot.length; i++) {
								EditPanel edit = taytettavatTiedot[i];
								String s = edit.getText();
								tiedot.append(s + "|");
							}
							suppe.lisaaSupis(tiedot.toString());

							for (int i = 0; i < taytettavatKyvyt.length; i++) {
								EditPanel edit = taytettavatKyvyt[i];
								if (edit != null){
								String s = edit.getText();
								if (s.equals(""))
									continue;
								try {
									int voimakkuus = Integer.parseInt(s);
									suppe.lisaaSupelleKyky(supenNimi,
											edit.getCaption(), voimakkuus);

								} catch (NumberFormatException ex) {
									continue;
								}
								}
							}

							// lisaaKykySupikselle(String kenelle, String
							// mikaKyky,
							// int voimakkuus) {

							dispose();
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Peru");
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
		naytaTaytettavatTiedot();
		naytaTaytettavatKyvyt();
	}


	/**
	 * Nayttaa ne Kyvyt joita uudella supella voisi olla
	 */
	public void naytaTaytettavatKyvyt() {
		panelUusSupKykyEdit.removeAll();
		kyvyt = suppe.annaKyvyt();
		taytettavatKyvyt = new EditPanel[kyvyt.length]; // onhan sekin tiedossa
														// paljon olioita on,
														// mutta se ei kerro
														// mit‰‰n siit‰ onko ne
														// siell‰ j‰rjestyksess‰

		// int n = apusupe.getKenttia() - apusupe.ekaKentta();
		// taytettavatKyvyt = new EditPanel[n];

		for (int i = 0; i < kyvyt.length; i++) {
			if (kyvyt[i] != null) {
				EditPanel edit = new EditPanel();
				edit.setCaption(kyvyt[i].getKyvynNimi());
				taytettavatKyvyt[i] = edit;
				edit.setName("xj" + i);
				edit.setText("");
				edit.getEdit().setName("xj" + i);
				panelUusSupKykyEdit.add(edit);
			}
		}
	}


	/**
	 * Nayttaa ne tiedot jotka kayttajan olisi kiva tayttaa jotta uusi supis
	 * voidaan luoda Ainoastaan Stage name on pakollinen.
	 */
	public void naytaTaytettavatTiedot() {
		// taytettavatTiedot = new EditPanel[apusupe.getKenttia() - 1];
		panelTiedot.removeAll();
		int n = apusupe.getKenttia() - apusupe.ekaKentta();
		taytettavatTiedot = new EditPanel[n];

		for (int i = 0, k = apusupe.ekaKentta(); k < apusupe.getKenttia(); k++, i++) {
			EditPanel edit = new EditPanel();
			edit.setCaption(apusupe.getKysymys(k));
			taytettavatTiedot[i] = edit;
			edit.setName("ej" + k);
			edit.setText("");
			edit.getEdit().setName("tj" + k);
			panelTiedot.add(edit);

		}
	}

}

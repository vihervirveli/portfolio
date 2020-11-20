package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import rekisteri.Supersankari;
import fi.jyu.mit.gui.EditPanel;

/**
 * Uuden kyvyn lis‰‰minen supersankarille
 *
 * @author majosalo
 * @version 31.1.2013
 * @version 9.4.2013
 *
 */
public class UusiKyky extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Supis supe;
	private Supersankari valittu;
	private JPanel panelUusKyvytEdit;
	// private rekisteri.Kyky[] kyvyt;
	private List<EditPanel> taytettavatKyvyt = new ArrayList<EditPanel>();
	private ActionListener toiminnonKasittelija = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			// rekkari.lisaaKykySupikselle(valittu, mikaKyky, voimakkuus);
		}
	};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * try { UusiKyky dialog = new UusiKyky();
		 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace();
		 * }
		 */}


	/**
	 * Create the dialog.
	 */
	public UusiKyky(Supis supeee, Supersankari valkattu) {
		valittu = valkattu;

		supe = supeee;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelKyky = new JPanel();
			contentPanel.add(panelKyky, BorderLayout.CENTER);
			panelKyky.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelKyky.add(scrollPane, BorderLayout.CENTER);
				{
					JPanel panelKyvytUudet = new JPanel();
					scrollPane.setViewportView(panelKyvytUudet);
					panelKyvytUudet.setLayout(new BorderLayout(0, 0));
					{
						panelUusKyvytEdit = new JPanel();
						panelKyvytUudet.add(panelUusKyvytEdit,
								BorderLayout.NORTH);
						panelUusKyvytEdit.setLayout(new BoxLayout(
								panelUusKyvytEdit, BoxLayout.Y_AXIS));
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getEdit().addActionListener(
									toiminnonKasittelija);
							editPanel.getLabel().setText("Tuli");
							panelUusKyvytEdit.add(editPanel);
						}
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getLabel().setText("J\u00E4\u00E4");
							panelUusKyvytEdit.add(editPanel);
						}
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getLabel().setText("Taikuus");
							panelUusKyvytEdit.add(editPanel);
						}
					}
				}
			}
			{
				JLabel lblUusiKyky = new JLabel(
						"Anna supersankarille uusi kyky: kerro supersankarien voimakkuudet kyvyss\u00E4");
				panelKyky.add(lblUusiKyky, BorderLayout.NORTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Tallenna");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// JOptionPane
						// .showMessageDialog(
						// null,
						// "Kun ohjelma toimii, ja jos olet antanut supersankarille \nkyvyss‰ nollasta eroavan arvon, tallennetaan supersankarille uusi kyky. Ei viel‰.",
						// "Tba", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < taytettavatKyvyt.size(); i++) {
							EditPanel edit = taytettavatKyvyt.get(i);
							// if (edit != null) {
							String s = edit.getText();
							if (s.equals(""))
								continue;
							try {
								int voimakkuus = Integer.parseInt(s);
								supe.lisaaSupelleKyky(valittu.getStageName(),
										edit.getCaption(), voimakkuus);

							} catch (NumberFormatException ex) {
								continue;
							}
							// }
						}
						dispose();
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
		naytaTaytettavatKyvyt();
	}


	/**
	 * Nayttaa ne Kyvyt joita supella voisi olla
	 */
	void naytaTaytettavatKyvyt() {

		panelUusKyvytEdit.removeAll();
		List<Integer> eisupenkyvyt = supe.eiSupenKyvyt(valittu.getId());
		// kyvyt = supe.annaKyvyt();
		// valitunKyvyt annaKyvynNimi(int id) {
		// taytettavatKyvyt
		for (int i = 0; i < eisupenkyvyt.size(); i++) {
			EditPanel edit = new EditPanel();
			edit.setCaption(supe.annaKyvynNimi(eisupenkyvyt.get(i)));
			taytettavatKyvyt.add(edit);
			edit.setName("xj" + i);
			edit.setText("");
			edit.getEdit().setName("xj" + i);
			panelUusKyvytEdit.add(edit);
		}

	}
}

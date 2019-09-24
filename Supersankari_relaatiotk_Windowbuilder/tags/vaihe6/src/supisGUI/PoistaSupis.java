package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import rekisteri.Rekisteri;
import fi.jyu.mit.gui.ListChooser;

/**
 * Poistaa supersankarin
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @version 14.3.2013
 *
 */
public class PoistaSupis extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String valittu;
	private final ListChooser listChooser = new ListChooser();
	private final Rekisteri rekkari;

	private int indeksi;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * try { PoistaSupis dialog = new PoistaSupis();
		 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace();
		 * }
		 */}


	/**
	 * Create the dialog.
	 */
	public PoistaSupis(Rekisteri rekisteri) {
		this.rekkari = rekisteri;
		setBounds(100, 100, 360, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelPoisSupis = new JPanel();
			contentPanel.add(panelPoisSupis, BorderLayout.CENTER);
			panelPoisSupis.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelPoisSupis.add(scrollPane, BorderLayout.CENTER);
				{

					listChooser.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							indeksi = listChooser.getSelectedIndex();
						}
					});
					listChooser
							.getCaptionLabel()
							.setText(
									"Supersankarin poisto: Valitse listasta ja paina \"poista...\"");
					listChooser.setKohteet(new String[] { "Batman",
							"Captain America", "Iron Man" });
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
					public void actionPerformed(ActionEvent arg0) {
						valittu = listChooser.getKohde(indeksi);
						if (valittu != null) {
							oletkoVarma(valittu);

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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	/**
	 * N‰ytt‰‰ PoistonVarmistus-dialogin
	 */
	public void oletkoVarma(String valittu) {

		PoistonVarmistus poisto = new PoistonVarmistus(rekkari, valittu);
		poisto.setVisible(true);
	}

}

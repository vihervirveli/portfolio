package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import rekisteri.Rekisteri;

/**
 *Varmistaa käyttäjältä poiston, se ei meinaan kannata...ikinä
 * @author majosalo
 * @version 31.1.2013
 * @version 15.3.2013
 *
 */
public class PoistonVarmistus extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Rekisteri rekkari;
	private String poistoon = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Muodostetaan PoistonVarmistus
	 * @param rekisteri rekisteri johon muutokset talletetaan
	 * @param poistettava poistettavan supiksen nimi
	 */
	public PoistonVarmistus(final Rekisteri rekisteri, String poistettava) {
		this.rekkari = rekisteri;
		poistoon = poistettava;
		setBounds(100, 100, 450, 161);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setModal(true);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelVarmistus = new JPanel();
			contentPanel.add(panelVarmistus, BorderLayout.CENTER);
			panelVarmistus.setLayout(new BorderLayout(0, 0));
			{
				JTextArea txtrOletkoAivanVarma = new JTextArea();
				txtrOletkoAivanVarma.setWrapStyleWord(true);
				txtrOletkoAivanVarma.setLineWrap(true);
				txtrOletkoAivanVarma.setText("Oletko aivan varma? Poistoa ei voi perua.\r\nEik\u00E4 kukaan oikeasti kuole sarjakuvissa! Paitsi Uncle Ben.");
				txtrOletkoAivanVarma.setEditable(false);
				panelVarmistus.add(txtrOletkoAivanVarma, BorderLayout.CENTER);
			}
			{
				JLabel lblVarmistus = new JLabel("Vamistus");
				panelVarmistus.add(lblVarmistus, BorderLayout.NORTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Olen varma");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rekkari.poistaSupis(poistoon);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Ei se kuitenkaan kuollut");
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

package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @version 7.2.2013
 *
 */
public class SupisOhje extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SupisOhje dialog = new SupisOhje();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SupisOhje() {
		setBounds(100, 100, 450, 223);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextArea txtrRekisterinAvatessaAukeaa = new JTextArea();
			txtrRekisterinAvatessaAukeaa.setWrapStyleWord(true);
			txtrRekisterinAvatessaAukeaa.setEditable(false);
			txtrRekisterinAvatessaAukeaa.setLineWrap(true);
			txtrRekisterinAvatessaAukeaa.setText("Rekisteri\u00E4 avatessa aukeaa ensin p\u00E4\u00E4ikkuna. Sielt\u00E4  voi valita joko supersankarilistan tai kykylistan.  Kentti\u00E4 klikkaamalla voi valita tietyn supersankarin tai kyvyn. Valikon kautta p\u00E4\u00E4see lis\u00E4\u00E4m\u00E4\u00E4n, muokkaamaan tai poistamaan eri tietoja. Jos sinulla on ongelmia rekisterin kanssa, ota yhteytt\u00E4 yll\u00E4pitoon osoitteessa maarit.salo@gmail.com");
			contentPanel.add(txtrRekisterinAvatessaAukeaa, BorderLayout.CENTER);
		}
		{
			JLabel lblOhje = new JLabel("Ohje");
			contentPanel.add(lblOhje, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sulje ikkuna");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

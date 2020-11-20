package guiAbout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class SupisAbout extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SupisAbout dialog = new SupisAbout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SupisAbout() {
		setBounds(100, 100, 450, 243);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextArea txtrHeiTervetuloaKyttmn = new JTextArea();
			txtrHeiTervetuloaKyttmn.setEditable(false);
			txtrHeiTervetuloaKyttmn.setLineWrap(true);
			txtrHeiTervetuloaKyttmn.setText("Hei, tervetuloa k\u00E4ytt\u00E4m\u00E4\u00E4n Batmanin supersankarirekisteri\u00E4. Rekisterin tarkoitus on helpottaa Batmanin ty\u00F6t\u00E4. Jos joku supersankari aivopest\u00E4\u00E4n pahan puolelle, rekisteri l\u00F6yt\u00E4\u00E4 ko. supersankarin voittavan supersankarin helposti ja vaivattomasti, ja Batmanin tarvitsee vain yhytt\u00E4\u00E4 voittaja ja pyyt\u00E4\u00E4 sit\u00E4 peittoamaan seonnut supersankari. \r\n");
			contentPanel.add(txtrHeiTervetuloaKyttmn, BorderLayout.CENTER);
		}
		{
			JLabel lblAbout = new JLabel("About");
			contentPanel.add(lblAbout, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sulje ikkuna");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

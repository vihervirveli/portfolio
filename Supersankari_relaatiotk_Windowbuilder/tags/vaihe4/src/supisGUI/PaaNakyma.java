package supisGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *Kun ohjelma aukeaa, tämä ikkuna näyttäytyy käyttäjälle ensimmäisenä
 * @author majosalo
 * @version 31.1.2013
 * @version 20.2.2013
 *
 */
public class PaaNakyma extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTervetuloaBtmninSupersankarirekisteriin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			PaaNakyma dialog = new PaaNakyma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PaaNakyma() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				txtTervetuloaBtmninSupersankarirekisteriin = new JTextField();
				txtTervetuloaBtmninSupersankarirekisteriin.setHorizontalAlignment(SwingConstants.CENTER);
				txtTervetuloaBtmninSupersankarirekisteriin.setForeground(Color.WHITE);
				txtTervetuloaBtmninSupersankarirekisteriin.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtTervetuloaBtmninSupersankarirekisteriin.setBackground(Color.DARK_GRAY);
				txtTervetuloaBtmninSupersankarirekisteriin.setCaretColor(Color.BLACK);
				txtTervetuloaBtmninSupersankarirekisteriin.setText("Tervetuloa Batmanin supersankarirekisteriin versio 1.0!");
				panel.add(txtTervetuloaBtmninSupersankarirekisteriin, BorderLayout.CENTER);
				txtTervetuloaBtmninSupersankarirekisteriin.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("T\u00E4st\u00E4 painamalla p\u00E4\u00E4set rekisteriin sis\u00E4lle");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//jos valitsi supikset, avautuu supisikkuna, jos kyvyt, kykyikkuna
						supikset();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}
	}
///////////////////////////////////////////
	///////siirtyy muualle
	/**
	 * Laittaa näkyviin Supis-(vai framen)dialogin
	 */
	protected void supikset(){
		Supis supikset = new Supis();
		supikset.setVisible(true);
		this.dispose();
	}
}

package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *Poistaa supersankarin
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 * @version 20.2.2013
 *
 */
public class PoistaSupis extends JDialog {



	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PoistaSupis dialog = new PoistaSupis();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PoistaSupis() {
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
				JLabel lblPoisSupis = new JLabel("Supersankarin poisto: Valitse listasta ja paina \"poista...\"");
				panelPoisSupis.add(lblPoisSupis, BorderLayout.NORTH);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panelPoisSupis.add(scrollPane, BorderLayout.CENTER);
				{
					JList<String> list = new JList<String>();
					list.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							JOptionPane
							.showMessageDialog(
									null,
									"Lopullisessa ohjelmassa valitset poistettavan supiksen klikkaamalla. Et viel‰.",
									"Tba",
									JOptionPane.INFORMATION_MESSAGE);
						}
					});
					list.setModel(new AbstractListModel<String>() {

						private static final long serialVersionUID = 1L;
						String[] values = new String[] {"Batman", "Captain America", "Iron Man"};
						public int getSize() {
							return values.length;
						}
						public String getElementAt(int index) {
							return values[index];
						}
					});
					scrollPane.setViewportView(list);
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
						oletkoVarma();
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
/////////////////////////////////////////
	//siirtyy muualle
/**
 * N‰ytet‰‰n PoistonVarmistus-dialogi
 */
protected void oletkoVarma(){
//if on valittu yksi supis
	PoistonVarmistus poisto = new PoistonVarmistus();
	poisto.setVisible(true);

}
}

package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.ComponentOrientation;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 *
 */
public class PoistaSupis extends JDialog {

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
					JList list = new JList();
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
					list.setModel(new AbstractListModel() {
						String[] values = new String[] {"Batman", "Captain America", "Iron Man"};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
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

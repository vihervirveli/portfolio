package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
 * @modified 7.2.2013
 *
 */
public class PoistaKyky extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PoistaKyky dialog = new PoistaKyky();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PoistaKyky() {
		setBounds(100, 100, 343, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelPoisKyk = new JPanel();
			contentPanel.add(panelPoisKyk, BorderLayout.CENTER);
			panelPoisKyk.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblPoisKyky = new JLabel(
						"Kyvyn poisto: Valitse listasta ja paina \"poista...\"");
				panelPoisKyk.add(lblPoisKyky, BorderLayout.NORTH);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panelPoisKyk.add(scrollPane, BorderLayout.CENTER);
				{
					JList listKyky = new JList();
					listKyky.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							JOptionPane
									.showMessageDialog(
											null,
											"Lopullisessa ohjelmassa klikkaamalla valitset poistettavan kyvyn. Et viel‰.",
											"Tba",
											JOptionPane.INFORMATION_MESSAGE);
						}
					});
					listKyky.setModel(new AbstractListModel() {
						String[] values = new String[] { "Tuli",
								"J\u00E4\u00E4", "Projektiilit", "Taikuus",
								"Melee", "Long range", "Supervoimat" };

						public int getSize() {
							return values.length;
						}

						public Object getElementAt(int index) {
							return values[index];
						}
					});
					scrollPane.setViewportView(listKyky);
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
					public void actionPerformed(ActionEvent e) {
						 dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	///////////////////////////////////////////////////
	//siirtyy muualle
	/**
	 *N‰ytt‰‰ PoistonVarmistus-dialogin
	 */
	protected void oletkoVarma(){

		PoistonVarmistus poisto = new PoistonVarmistus();
		poisto.setVisible(true);
	}

}

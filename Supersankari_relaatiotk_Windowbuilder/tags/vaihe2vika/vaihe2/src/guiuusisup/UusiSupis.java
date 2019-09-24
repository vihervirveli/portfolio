package guiuusisup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;

public class UusiSupis extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableUusiSupis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UusiSupis dialog = new UusiSupis();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UusiSupis() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelSupis = new JPanel();
			contentPanel.add(panelSupis, BorderLayout.CENTER);
			panelSupis.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblUusiSupis = new JLabel("T\u00E4yt\u00E4 t\u00E4m\u00E4 lomake uudesta supersankarista");
				panelSupis.add(lblUusiSupis, BorderLayout.NORTH);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panelSupis.add(scrollPane, BorderLayout.CENTER);
				{
					tableUusiSupis = new JTable();
					tableUusiSupis.setModel(new DefaultTableModel(
						new Object[][] {
							{"Stage name", null},
							{"Sukunimi Etunimi", null},
							{"Puoliso", null},
							{"Kotikaupunki", null},
							{"JLU yhteysnumero", null},
							{"Voimanumero", null},
						},
						new String[] {
							"Tarvittavat tiedot", "Puuttuva tieto on tyhjä rivi"
						}
					));
					tableUusiSupis.getColumnModel().getColumn(0).setPreferredWidth(101);
					scrollPane.setViewportView(tableUusiSupis);
				}
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			contentPanel.add(menuBar, BorderLayout.NORTH);
			{
				JMenu menu = new JMenu("Tiedosto");
				menuBar.add(menu);
				{
					JMenuItem menuItem = new JMenuItem("Tallenna");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Valitse supersankari...");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Valitse kyky...");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("You'll be back");
					menu.add(menuItem);
				}
			}
			{
				JMenu menu = new JMenu("Muokkaa");
				menuBar.add(menu);
				{
					JMenuItem menuItem = new JMenuItem("Muokkaa supersankarin tietoja...");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Muokkaa kyvyn tietoja...");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Lis\u00E4\u00E4 uusi supersankari");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Lis\u00E4\u00E4 uusi kyky");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Poista supersankari...");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("Poista kyky...");
					menu.add(menuItem);
				}
			}
			{
				JMenu menu = new JMenu("Ohje");
				menu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				menuBar.add(menu);
				{
					JMenuItem menuItem = new JMenuItem("K\u00E4ytt\u00F6ohjeet");
					menu.add(menuItem);
				}
				{
					JMenuItem menuItem = new JMenuItem("About Batmanin supersankarirekisteri");
					menu.add(menuItem);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Tallenna uusi supersankari");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Peru");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

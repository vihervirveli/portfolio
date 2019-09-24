package guiuuskyk;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UusiKyky extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableKyky;
	private JTextField textUusiKykyNimi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UusiKyky dialog = new UusiKyky();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UusiKyky() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
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
			JPanel panelKyky = new JPanel();
			contentPanel.add(panelKyky, BorderLayout.CENTER);
			panelKyky.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelKyky.add(scrollPane, BorderLayout.CENTER);
				{
					tableKyky = new JTable();
					tableKyky.setModel(new DefaultTableModel(
						new Object[][] {
							{"Batman", "-500", "500"},
							{"Captain America", "-200", "200"},
							{"Iron Man", "-10", "250"},
						},
						new String[] {
							"Supersankari", "Puolustus", "Hyökkäys"
						}
					));
					scrollPane.setViewportView(tableKyky);
				}
			}
			{
				JLabel lblUusiKyky = new JLabel("Uuden kyvyn luomiseksi kerro sen vaikutukset supersankareihin");
				panelKyky.add(lblUusiKyky, BorderLayout.NORTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblKerroKyvyNimi = new JLabel("Kerro viel\u00E4 kyvyn nimi! ");
				buttonPane.add(lblKerroKyvyNimi);
			}
			{
				textUusiKykyNimi = new JTextField();
				buttonPane.add(textUusiKykyNimi);
				textUusiKykyNimi.setColumns(10);
			}
			{
				JButton okButton = new JButton("Tallenna");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

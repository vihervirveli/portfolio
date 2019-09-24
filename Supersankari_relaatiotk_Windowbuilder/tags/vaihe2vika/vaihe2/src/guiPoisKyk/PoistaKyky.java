package guiPoisKyk;

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
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PoistaKyky extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableKyvyt;

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
			JPanel panelPoisKyk = new JPanel();
			contentPanel.add(panelPoisKyk, BorderLayout.CENTER);
			panelPoisKyk.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblPoisKyky = new JLabel("Poistaaksesi kyvyn, valitse se listasta ja paina \"poista...\"");
				panelPoisKyk.add(lblPoisKyky, BorderLayout.NORTH);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panelPoisKyk.add(scrollPane, BorderLayout.CENTER);
				{
					tableKyvyt = new JTable();
					tableKyvyt.setModel(new DefaultTableModel(
						new Object[][] {
							{"Tuli"},
							{"J\u00E4\u00E4"},
							{"Projektiilit"},
							{"Taikuus"},
							{"Melee"},
							{"Long range"},
							{"Supervoima"},
							{null},
						},
						new String[] {
							"Poistettava kyky"
						}
					));
					scrollPane.setViewportView(tableKyvyt);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Poista...");
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

package supisGUI;

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
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author majosalo : Maarit Salo
 * @version 31.1.2013
 *
 */
public class UusiSupis extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
				JSplitPane splitPane = new JSplitPane();
				splitPane.setResizeWeight(1.0);
				panelSupis.add(splitPane, BorderLayout.CENTER);
				{
					JPanel panelSupisTiedot = new JPanel();
					splitPane.setLeftComponent(panelSupisTiedot);
					panelSupisTiedot.setLayout(new BorderLayout(0, 0));
					{
						JPanel panelTiedot = new JPanel();
						panelSupisTiedot.add(panelTiedot, BorderLayout.NORTH);
						panelTiedot.setLayout(new BoxLayout(panelTiedot, BoxLayout.Y_AXIS));
						{
							EditPanel editStage = new EditPanel();
							panelTiedot.add(editStage);
						}
						{
							EditPanel editNimi = new EditPanel();
							editNimi.setCaption("Sukunimi Etunimi");
							panelTiedot.add(editNimi);
						}
						{
							EditPanel editPuoliso = new EditPanel();
							editPuoliso.setCaption("Puoliso");
							panelTiedot.add(editPuoliso);
						}
						{
							EditPanel editKoti = new EditPanel();
							editKoti.setCaption("Kotikaupunki");
							panelTiedot.add(editKoti);
						}
						{
							EditPanel editJLU = new EditPanel();
							editJLU.setCaption("JLU yhteysnro");
							panelTiedot.add(editJLU);
						}
						{
							EditPanel editVoimaNro = new EditPanel();
							editVoimaNro.setCaption("Voimanumero");
							panelTiedot.add(editVoimaNro);
						}
					}
				}
				{
					JPanel panelKyvyt = new JPanel();
					splitPane.setRightComponent(panelKyvyt);
					panelKyvyt.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						panelKyvyt.add(scrollPane, BorderLayout.CENTER);
						{
							JPanel panelKyvytCheck = new JPanel();
							scrollPane.setViewportView(panelKyvytCheck);
							panelKyvytCheck.setLayout(new BorderLayout(0, 0));
							{
								JPanel panelCheckit = new JPanel();
								panelKyvytCheck.add(panelCheckit, BorderLayout.NORTH);
								panelCheckit.setLayout(new BoxLayout(panelCheckit, BoxLayout.Y_AXIS));
								{
									JCheckBox checkTuli = new JCheckBox("Tuli");
									panelCheckit.add(checkTuli);
								}
								{
									JCheckBox checkJaa = new JCheckBox("J\u00E4\u00E4");
									panelCheckit.add(checkJaa);
								}
								{
									JCheckBox checkProjekt = new JCheckBox("Projektiilit");
									panelCheckit.add(checkProjekt);
								}
								{
									JCheckBox checkTaika = new JCheckBox("Taikuus");
									panelCheckit.add(checkTaika);
								}
								{
									JCheckBox checkMelee = new JCheckBox("Melee");
									panelCheckit.add(checkMelee);
								}
								{
									JCheckBox checkLongRange = new JCheckBox("Long range");
									panelCheckit.add(checkLongRange);
								}
								{
									JCheckBox checkSuper = new JCheckBox("Supervoimat");
									panelCheckit.add(checkSuper);
								}
							}
						}
					}
				}
				splitPane.setDividerLocation(-2);
				{
					{
						JPanel panelScrollinylle = new JPanel();
						panelSupis.add(panelScrollinylle, BorderLayout.SOUTH);
						panelScrollinylle.setLayout(new BorderLayout(0, 0));
				}
			}

			{
				JLabel lblUusiSupis = new JLabel("Uuden supersankarin lis\u00E4ys");
				panelSupis.add(lblUusiSupis, BorderLayout.NORTH);
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

}}

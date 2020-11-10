package guilibSupiksetList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import guilibmenu.ValikkoEdit;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import guilib.EditPanel;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class SupisListaNakyma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SupisListaNakyma dialog = new SupisListaNakyma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SupisListaNakyma() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JPanel panelLista = new JPanel();
				splitPane.setLeftComponent(panelLista);
				panelLista.setLayout(new BorderLayout(0, 0));
				{
					JPanel panelHaku = new JPanel();
					panelLista.add(panelHaku, BorderLayout.NORTH);
					panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
						{
							JLabel lblHakuehto = new JLabel("Hakuehto");
							panelHaku.add(lblHakuehto);
						}
						{
							JComboBox cbEhdot = new JComboBox();
							cbEhdot.setModel(new DefaultComboBoxModel(new String[] {"Stage name", "Nimi", "Puoliso", "Kotikaupunki", "JLU yhteysnumero"}));
							panelHaku.add(cbEhdot);
						}
					{
						textField = new JTextField();
						panelHaku.add(textField);
						textField.setColumns(10);
					}
					{
						JLabel labelSupikset = new JLabel("Supersankarit");
						panelHaku.add(labelSupikset);
					}
					}

										JScrollPane scrollPane = new JScrollPane();
										panelLista.add(scrollPane, BorderLayout.CENTER);
										JList list = new JList();
										list.setModel(new AbstractListModel() {
											String[] values = new String[] {"Batman", "Captain America", "Iron Man"};
											public int getSize() {
												return values.length;
											}
											public Object getElementAt(int index) {
												return values[index];
											}
										});
										scrollPane.add(list);
										scrollPane.setViewportView(list);

				}
			{
				JPanel panelSupiksenTiedot = new JPanel();
				splitPane.setRightComponent(panelSupiksenTiedot);
				panelSupiksenTiedot.setLayout(new BorderLayout(0, 0));
				{
					JLabel labelSupis = new JLabel("Klikkaa supersankaria n\u00E4hd\u00E4ksesi tiedot");
					panelSupiksenTiedot.add(labelSupis, BorderLayout.NORTH);
				}
				{
					JScrollPane scrollSupis = new JScrollPane();
					scrollSupis.setToolTipText("");
					scrollSupis.setPreferredSize(new Dimension(200, 2));
					panelSupiksenTiedot.add(scrollSupis, BorderLayout.CENTER);
					JPanel panelSupis = new JPanel();
					//scrollSupis.add(panelSupis, BorderLayout.EAST);
					scrollSupis.setViewportView(panelSupis);
					panelSupis.setLayout(new BorderLayout(0, 0));

					panelSupis.setLayout(new BorderLayout(0, 0));

					scrollSupis.setViewportView(panelSupis);
					panelSupis.setLayout(new BorderLayout(0, 0));
					{
						Box verticalBox = Box.createVerticalBox();
						panelSupis.add(verticalBox, BorderLayout.NORTH);
						{
							JPanel panel = new JPanel();
							verticalBox.add(panel);
							panel.setLayout(new BorderLayout(0, 0));
							{
								JPanel panel_1 = new JPanel();
								panel.add(panel_1, BorderLayout.NORTH);
								panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setCaption(" Stage name");
									editPanel.setLabelWidth(200);
									panel_1.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setLabelWidth(200);
									editPanel.setCaption(" Sukunimi Etunimi");
									panel_1.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setLabelWidth(200);
									editPanel.setCaption(" Puoliso");
									panel_1.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setLabelWidth(200);
									editPanel.setCaption(" Kotikaupunki");
									panel_1.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setLabelWidth(200);
									editPanel.setCaption(" JLU yhteysnumero");
									panel_1.add(editPanel);
								}
								{
									EditPanel editPanel = new EditPanel();
									editPanel.setLabelWidth(200);
									editPanel.setCaption(" Voimanumero");
									panel_1.add(editPanel);
								}
							}
						}
					}


				}

				{
					JPanel panelNappulat = new JPanel();
					panelSupiksenTiedot.add(panelNappulat, BorderLayout.SOUTH);
					{
						JButton btnTallenna = new JButton("Tallenna muutokset");
						panelNappulat.add(btnTallenna);
					}
				}
			}
			}
		{
			ValikkoEdit valikkoEdit = new ValikkoEdit();
			setJMenuBar(valikkoEdit);
		}
		}
		{
		}
	}



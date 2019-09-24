package guiKyvyt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import guilibmenu.ValikkoEdit;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.AbstractListModel;

public class KyvytVaihe2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KyvytVaihe2 dialog = new KyvytVaihe2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public KyvytVaihe2() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				ValikkoEdit valikkoEdit = new ValikkoEdit();
				panel.add(valikkoEdit, BorderLayout.NORTH);
				valikkoEdit.setLayout(new BoxLayout(valikkoEdit, BoxLayout.X_AXIS));
			}
		}
		{
			JSplitPane splitPaneListaKykyTiedot = new JSplitPane();
			contentPanel.add(splitPaneListaKykyTiedot, BorderLayout.CENTER);
			{
				JPanel panelLista = new JPanel();
				splitPaneListaKykyTiedot.setLeftComponent(panelLista);
				panelLista.setLayout(new BorderLayout(0, 0));
				{
					JPanel panelHaku = new JPanel();
					panelLista.add(panelHaku, BorderLayout.NORTH);
					panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
					{
						JLabel lblValitse = new JLabel("Valitse kyky klikkaamalla");
						lblValitse.setAlignmentX(Component.RIGHT_ALIGNMENT);
						lblValitse.setHorizontalTextPosition(SwingConstants.LEADING);
						panelHaku.add(lblValitse);
					}
					{
						JScrollPane scrollPane = new JScrollPane();
						panelHaku.add(scrollPane);
						JList listKyk = new JList();
						listKyk.setModel(new AbstractListModel() {
							String[] values = new String[] {"Tuli", "J\u00E4\u00E4", "Projektiilit            ", "Taikuus                 ", "Melee                   ", "Long range              ", "Supervoima", "Gadgets"};
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						scrollPane.setViewportView(listKyk);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				splitPaneListaKykyTiedot.setRightComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JLabel labelKyvynOmistajat = new JLabel("Kyvyn omistajat");
					panel.add(labelKyvynOmistajat, BorderLayout.NORTH);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						table = new JTable();
						table.setModel(new DefaultTableModel(
							new Object[][] {
								{"Captain America", "750"},
								{"Batman", "0"},
								{"Iron Man", "0"},
								{null, null},
								{null, null},
							},
							new String[] {
								"Supersankari", "Voimakkuus"
							}
						));
						table.getColumnModel().getColumn(0).setPreferredWidth(115);
						scrollPane.setViewportView(table);
					}
				}
				{
					JPanel panelMoreInfo = new JPanel();
					panel.add(panelMoreInfo, BorderLayout.SOUTH);
					{
						JButton buttonMoreInfo = new JButton("Lis\u00E4tietoja");
						panelMoreInfo.add(buttonMoreInfo);
					}
				}
			}
		}
	}


}

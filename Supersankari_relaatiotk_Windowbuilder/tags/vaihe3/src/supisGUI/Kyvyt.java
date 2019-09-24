package supisGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
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
import fi.jyu.mit.gui.EditPanel;
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
public class Kyvyt extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Kyvyt dialog = new Kyvyt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Kyvyt() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
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
						listKyk.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								JOptionPane.showMessageDialog(null, "Lopullisessa ohjelmassa yht‰ klikkaamalla p‰‰set n‰kem‰‰n listan kyvyn omistajista. Et viel‰.",
										"Tba", JOptionPane.INFORMATION_MESSAGE);
							}
						});

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
					JLabel labelKyvynOmistajat = new JLabel("Kyvyn omistajat - Voimakkuus");
					panel.add(labelKyvynOmistajat, BorderLayout.NORTH);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						JPanel panelKykyOmistajat = new JPanel();
						scrollPane.setViewportView(panelKykyOmistajat);
						panelKykyOmistajat.setLayout(new BorderLayout(0, 0));
						{
							JPanel panelKykyOm = new JPanel();
							panelKykyOm.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									JOptionPane.showMessageDialog(null, "Lopullisessa ohjelmassa p‰‰set klikkaamalla katsomaan supiksen tietoja. Et viel‰.",
											"Tba", JOptionPane.INFORMATION_MESSAGE);
								}
							});
							panelKykyOmistajat.add(panelKykyOm, BorderLayout.NORTH);
							panelKykyOm.setLayout(new BoxLayout(panelKykyOm, BoxLayout.Y_AXIS));
							{
								EditPanel editPanel = new EditPanel();
								editPanel.getEdit().setEditable(false);
								editPanel.getEdit().setText("1200");
								editPanel.setCaption("Superman");
								panelKykyOm.add(editPanel);
							}
							{
								EditPanel editPanel = new EditPanel();
								editPanel.getEdit().setText("750");
								editPanel.getEdit().setEditable(false);
								editPanel.setCaption("Captain America");
								panelKykyOm.add(editPanel);
							}
							{
								EditPanel editPanel = new EditPanel();
								editPanel.getEdit().setText("400");
								editPanel.getEdit().setEditable(false);

								editPanel.setCaption("Spiderman");
								panelKykyOm.add(editPanel);
							}
							{
								EditPanel editPanel = new EditPanel();
								editPanel.getEdit().setText("350");
								editPanel.getEdit().setEditable(false);

								editPanel.setCaption("Deadpool");
								panelKykyOm.add(editPanel);
							}
						}
					}
				}
				{
					JPanel panelMoreInfo = new JPanel();
					panel.add(panelMoreInfo, BorderLayout.SOUTH);
					{
						JButton buttonMoreInfo = new JButton("Lis\u00E4tietoja");
						buttonMoreInfo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null, "T‰m‰ on t‰ss‰ sit‰ varten ett‰ jos editPanelien klikkauslinkkaus\n ei onnistukaan, voin hoitaa homman t‰ll‰ napilla. Ehk‰.",
										"Tba", JOptionPane.INFORMATION_MESSAGE);
							}
						});
						panelMoreInfo.add(buttonMoreInfo);
					}
				}
			}
		}
	}


}

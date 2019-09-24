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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import fi.jyu.mit.gui.EditPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UusiKyky extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
			JPanel panelKyky = new JPanel();
			contentPanel.add(panelKyky, BorderLayout.CENTER);
			panelKyky.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelKyky.add(scrollPane, BorderLayout.CENTER);
				{
					JPanel panelKyvytUudet = new JPanel();
					scrollPane.setViewportView(panelKyvytUudet);
					panelKyvytUudet.setLayout(new BorderLayout(0, 0));
					{
						JPanel panelUusKyvytEdit = new JPanel();
						panelKyvytUudet.add(panelUusKyvytEdit, BorderLayout.NORTH);
						panelUusKyvytEdit.setLayout(new BoxLayout(panelUusKyvytEdit, BoxLayout.Y_AXIS));
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getLabel().setText("Batman");
							panelUusKyvytEdit.add(editPanel);
						}
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getLabel().setText("Captain America");
							panelUusKyvytEdit.add(editPanel);
						}
						{
							EditPanel editPanel = new EditPanel();
							editPanel.getLabel().setText("Iron Man");
							panelUusKyvytEdit.add(editPanel);
						}
					}
				}
			}
			{
				JLabel lblUusiKyky = new JLabel("Uuden kyvyn luonti: kerro supersankarien voimakkuudet kyvyss\u00E4");
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane
						.showMessageDialog(
								null,
								"Kun ohjelma toimii voit tallentaa uuden kyvyn tietokantaan. Et vielä.",
								"Tba", JOptionPane.INFORMATION_MESSAGE);
					}});
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

}

package supisGUI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Luokka otsikon ja edit-kentän yhdistämiseksi
 * @author vesal
 * @version 4.1.2010
 */
public class EditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField edit;
	private JLabel labelStageName;
	/**
	 * Create the panel.
	 */
	public EditPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		labelStageName = new JLabel("Stage name");
		labelStageName.setHorizontalAlignment(SwingConstants.TRAILING);
		labelStageName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		labelStageName.setPreferredSize(new Dimension(100, 14));
		add(labelStageName);

		JLabel fill1 = new JLabel(" ");
		add(fill1);

		edit = new JTextField();
		add(edit);
		edit.setColumns(10);

		JLabel fill2 = new JLabel(" ");
		add(fill2);

	}
	/**
     * Asetetaan labelin leveys
     * @param w asetettava leveys
     */
    public void setLabelWidth(int w) {
        int h = getPreferredSize().height;
        labelStageName.setPreferredSize(new Dimension(w, h));
    }

	public String getCaption() {
		return labelStageName.getText();
	}
	public void setCaption(String text) {
		labelStageName.setText(text);
	}
	public String getText() {
		return edit.getText();
	}
	public void setText(String text_1) {
		edit.setText(text_1);
	}
	public int getColumns() {
		return edit.getColumns();
	}
	public void setColumns(int columns) {
		edit.setColumns(columns);
	}
}

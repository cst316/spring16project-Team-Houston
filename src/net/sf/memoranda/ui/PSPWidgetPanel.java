package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classname: PSPWidgetPanel
 *
 * Description: The generic panel instance to use with the PSPControlPanel
 * class. Sets up a standard panel with a button used to link to a Wizard.
 * Panel size determined by PSPControlPanel layout or, failing that, the
 * size of the components used within the specific instance.
 * 
 * Version 1.0
 * 
 * Date: 2/21/2016
 */
@SuppressWarnings("serial")
public class PSPWidgetPanel extends JPanel  {

	private JButton _nextButton;
	private JPanel _buttonPanel;
	private JPanel _contentPane;
	private String _title;
	private Object _pspWizard;
	
	/**
	 * Constructor
	 * @param title the title for panel
	 */
	public PSPWidgetPanel(String title) {
		this(title, null, null);
	}
	
	/**
	 * Constructor
	 * @param title the title for panel
	 * @param contentPane the main content of panel
	 */
	public PSPWidgetPanel(String title, JPanel contentPane) {
		this(title, contentPane, null);
	}
	
	/**
	 * Constructor
	 * @param title the title for panel
	 * @param pspWizard the wizard called from this panel
	 */
	public PSPWidgetPanel(String title, Object pspWizard) {
		this(title, null, pspWizard);
	}
	
	/**
	 * Constructor
	 * @param title the title for panel
	 * @param contentPane the main content of panel
	 * @param pspWizard the wizard called from this panel
	 */
	public PSPWidgetPanel(String title, JPanel contentPane, Object pspWizard) {
		super(new BorderLayout());
		_title = title;
		if (contentPane != null) {
			_contentPane = contentPane;
		}
		else {
			_contentPane = new JPanel(new BorderLayout());
			_contentPane.add(new JLabel(String.format("%s - replace me", _title)), BorderLayout.CENTER);
		}
		if (pspWizard != null) {
			_pspWizard = pspWizard;
		}
		else {
			_pspWizard = new JDialog();
		}
		init();
	}
	
	/**
	 * @return the JButton used in this panel
	 */
	public JButton getButton() {
		return _nextButton;
	}
	
	/**
	 * @return the main content JPanel
	 */
	public JPanel getContentPane() {
		return _contentPane;
	}
	
	/**
	 * @return the wizard called by this panel
	 */
	public Object getWizard() {
		return _pspWizard;
	}
	
	/**
	 * Sets the new button to use for this panel
	 * @param nb the new JButton to use
	 */
	public void setButton(JButton nb) {
		_buttonPanel.remove(_nextButton);
		_nextButton = nb;
		_buttonPanel.add(_nextButton, BorderLayout.EAST);
		revalidate();
		repaint();
	}
	
	/**
	 * Sets the new content pane for this panel
	 * @param cp the new JPanel to use
	 */
	public void setContentPane(JPanel cp) {
		remove(_contentPane);
		_contentPane = cp;
		add(_contentPane, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	/**
	 * Sets the new wizard for this panel
	 * @param wizard the new wizard to call from this panel
	 */
	public void setWizard(Object wizard) {
		_pspWizard = wizard;
	}
	
	/**
	 * Initializes this panel, including layout and other settings.
	 */
	private void init() {
		add(_contentPane, BorderLayout.CENTER);
		_buttonPanel = new JPanel(new BorderLayout());
		_nextButton = new JButton(String.format("Go to %s wizard", _title));
		_nextButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				((JDialog) _pspWizard).setVisible(true);;
			}
		});
		_buttonPanel.add(_nextButton, BorderLayout.EAST);
		add(_buttonPanel, BorderLayout.SOUTH);
		setBorder(BorderFactory.createTitledBorder(_title));
	}
}

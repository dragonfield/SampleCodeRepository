package jp.dragon.field;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JComboBox;


import java.awt.Color;
import java.awt.event.ItemEvent;

import jp.dragon.field.model.Setting;

public class LoginConsole extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	private JComboBox jComboBox1 = null;
	
	private Setting setting_ = null;
	private Selected selected_ = null;

	/**
	 * This is the default constructor
	 */
	public LoginConsole(Setting setting) {
		super();
		setting_ = setting;
		selected_ = new Selected();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(200, 200, 378, 203));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("vSphere Client");
		this.setVisible(false);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(31, 79, 103, 26));
			jLabel1.setText("VM Name :");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(29, 44, 104, 27));
			jLabel.setText("Host Name :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJComboBox1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(100, 130, 69, 26));
			jButton.setText("Login");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Utils.start(selected_.getHost(), selected_.getVm(), setting_);
					System.exit(0);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(191, 130, 66, 26));
			jButton1.setText("Close");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			String[] hostNames = setting_.getHostNames();
			selected_.setHost(hostNames[0]);
			
			jComboBox = new JComboBox(hostNames);
			jComboBox.setBounds(new Rectangle(152, 44, 190, 23));
			jComboBox.setBackground(Color.white);			
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
					
						String selectedHost = (String) e.getItem();
						
						jComboBox1.removeAllItems();
						String[] vmNames = setting_.getVmNames(selectedHost);
						
						for (int i = 0; i < vmNames.length; i++) {
							jComboBox1.addItem(vmNames[i]);
						}
						
						selected_.setHost(selectedHost);
					}
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			String[] vmNames = setting_.getVmNames(selected_.getHost());
			selected_.setVm(vmNames[0]);
			
			jComboBox1 = new JComboBox(vmNames);
			jComboBox1.setBounds(new Rectangle(152, 81, 191, 26));
			jComboBox1.setBackground(Color.white);
			jComboBox1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String selectedVm = (String) e.getItem();
						selected_.setVm(selectedVm);
					}
				}
			});
		}
		return jComboBox1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

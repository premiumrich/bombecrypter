package net.premiumrich.main;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import net.premiumrich.exceptions.*;

public class Bombecrypter {

	JFrame frame;
	private JTextField VCKey;
	private JTextField statusBar;

	public Bombecrypter() {
		initialize();
	}

	private void initialize() {
		// Initialize frame
		frame = new JFrame();
		ImageIcon appIcon = new ImageIcon("net/premiumrich/res/icon.png");
		frame.setIconImage(appIcon.getImage());
		frame.setTitle("Bombecrypter");
		frame.setBounds(100, 100, 500, 275);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create combobox at the top and its frame
		String ciphers[] = {"Please select a cipher:", "Caesar cipher", "Vigenère cipher", "A1Z26 cipher"};
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(ciphers));
		comboBox.setSelectedIndex(0);

		JPanel cipherSelector = new JPanel();
		cipherSelector.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cipherSelector.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(cipherSelector, BorderLayout.NORTH);
		cipherSelector.add(comboBox);

		// Create content panels
		JPanel mainContentPanel = new JPanel();
		frame.getContentPane().add(mainContentPanel, BorderLayout.CENTER);
		mainContentPanel.setLayout(new BorderLayout(0, 0));
		
				JPanel contentPanel = new JPanel();
				mainContentPanel.add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(null);
				
						JPanel inputPanel = new JPanel();
						inputPanel.setBounds(0, 0, 175, 154);
						contentPanel.add(inputPanel);
						inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
					
								JPanel inputTitlePanel = new JPanel();
								inputTitlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
								inputPanel.add(inputTitlePanel);
						
										JLabel lblInputTitle = new JLabel("Input");
										inputTitlePanel.add(lblInputTitle);
						
								JPanel inputTextPanel = new JPanel();
								inputTextPanel.setBackground(Color.WHITE);
								inputPanel.add(inputTextPanel);
								inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.X_AXIS));
						
										Component inputTextLeftSpacer = Box.createHorizontalStrut(5);
										inputTextPanel.add(inputTextLeftSpacer);
										
										JTextArea inputText = new JTextArea();
										inputText.setLineWrap(true);
										inputText.setWrapStyleWord(true);
										inputTextPanel.add(inputText);
										
										Component inputTextRightSpacer = Box.createHorizontalStrut(5);
										inputTextPanel.add(inputTextRightSpacer);
				
						JPanel settingsPanel = new JPanel();
						settingsPanel.setBounds(175, 0, 150, 154);
						contentPanel.add(settingsPanel);
						settingsPanel.setLayout(null);
				
								JPanel settingsTitlePanel = new JPanel();
								settingsTitlePanel.setBounds(0, 0, 150, 30);
								settingsTitlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
								settingsPanel.add(settingsTitlePanel);
					
										JLabel lblSettingsTitle = new JLabel("Settings");
										settingsTitlePanel.add(lblSettingsTitle);
					
								JPanel settingsCards = new JPanel();
								settingsCards.setBounds(0, 30, 150, 124);
								settingsPanel.add(settingsCards);
								settingsCards.setLayout(new CardLayout(0, 0));
								
										JPanel settingsDefaultPanel = new JPanel();
										settingsCards.add(settingsDefaultPanel, "DefaultPanel");
										settingsDefaultPanel.setLayout(null);
										
												JLabel lblDefaultTitle = new JLabel("N/A");
												lblDefaultTitle.setBounds(10, 52, 130, 16);
												lblDefaultTitle.setHorizontalAlignment(SwingConstants.CENTER);
												settingsDefaultPanel.add(lblDefaultTitle);
						
										JPanel settingsCCPanel = new JPanel();
										settingsCards.add(settingsCCPanel, "CCPanel");
										settingsCCPanel.setLayout(null);
						
												JLabel lblCCShiftTitle = new JLabel("Encryption Shift");
												lblCCShiftTitle.setBounds(10, 30, 130, 16);
												settingsCCPanel.add(lblCCShiftTitle);
												lblCCShiftTitle.setHorizontalAlignment(SwingConstants.CENTER);
												
												JLabel lblCCShiftValue = new JLabel("0");
												lblCCShiftValue.setBounds(10, 52, 130, 16);
												settingsCCPanel.add(lblCCShiftValue);
												lblCCShiftValue.setHorizontalAlignment(SwingConstants.CENTER);
							
												JSlider ccShiftValueSlider = new JSlider();
												ccShiftValueSlider.setBounds(10, 70, 130, 30);
												settingsCCPanel.add(ccShiftValueSlider);
							
												ccShiftValueSlider.setValue(0);
												ccShiftValueSlider.setSnapToTicks(true);
												ccShiftValueSlider.setMinorTickSpacing(1);
												ccShiftValueSlider.setMajorTickSpacing(1);
												ccShiftValueSlider.setMaximum(26);
												ccShiftValueSlider.setMinimum(-26);
						
										JPanel settingsVCPanel = new JPanel();
										settingsCards.add(settingsVCPanel, "VCPanel");
										settingsVCPanel.setLayout(null);
						
												JLabel lblVCKeyTitle = new JLabel("Encryption Key");
												lblVCKeyTitle.setHorizontalAlignment(SwingConstants.CENTER);
												lblVCKeyTitle.setBounds(10, 30, 130, 16);
												settingsVCPanel.add(lblVCKeyTitle);
							
												VCKey = new JTextField();
												VCKey.setBounds(10, 60, 130, 26);
												settingsVCPanel.add(VCKey);
												VCKey.setColumns(10);
						
										JPanel settingsA1Z26Panel = new JPanel();
										settingsCards.add(settingsA1Z26Panel, "A1Z26Panel");
										settingsA1Z26Panel.setLayout(null);
						
												JLabel lblA1Z26Title = new JLabel("N/A");
												lblA1Z26Title.setHorizontalAlignment(SwingConstants.CENTER);
												lblA1Z26Title.setBounds(10, 52, 130, 16);
												settingsA1Z26Panel.add(lblA1Z26Title);
		
				
						JPanel outputPanel = new JPanel();
						outputPanel.setBounds(325, 0, 175, 154);
						contentPanel.add(outputPanel);
						outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
						
								JPanel outputTitlePanel = new JPanel();
								outputTitlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
								outputPanel.add(outputTitlePanel);
							
										JLabel lblOutputTitle = new JLabel("Output");
										outputTitlePanel.add(lblOutputTitle);
							
								JPanel outputTextPanel = new JPanel();
								outputTextPanel.setBackground(Color.WHITE);
								outputPanel.add(outputTextPanel);
								outputTextPanel.setLayout(new BoxLayout(outputTextPanel, BoxLayout.X_AXIS));
							
										Component outputTextLeftSpacer = Box.createHorizontalStrut(5);
										outputTextPanel.add(outputTextLeftSpacer);
										
										JTextArea outputText = new JTextArea();
										outputText.setEditable(false);
										outputText.setWrapStyleWord(true);
										outputText.setLineWrap(true);
										outputTextPanel.add(outputText);
								
										Component outputTextRightSpacer = Box.createHorizontalStrut(5);
										outputTextPanel.add(outputTextRightSpacer);
				
				JPanel lowerPanel = new JPanel();
				lowerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				mainContentPanel.add(lowerPanel, BorderLayout.SOUTH);
				lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
				
						JPanel encryptDecryptButtonsPanel = new JPanel();
						lowerPanel.add(encryptDecryptButtonsPanel);
						
								JButton btnEncrypt = new JButton("Encrypt");
								encryptDecryptButtonsPanel.add(btnEncrypt);
							
								Component encryptDecryptButtonsSpacer = Box.createHorizontalStrut(45);
								encryptDecryptButtonsPanel.add(encryptDecryptButtonsSpacer);
							
								JButton btnDecrypt = new JButton("Decrypt");
								encryptDecryptButtonsPanel.add(btnDecrypt);
					
						JPanel statusBarPanel = new JPanel();
						lowerPanel.add(statusBarPanel);
						statusBarPanel.setLayout(new BorderLayout(0, 0));
					
						statusBar = new JTextField();
						statusBar.setEditable(false);
						statusBarPanel.add(statusBar);
						statusBar.setColumns(10);
						statusBar.setText("");
					
		// Listeners - heart of the program
		// Interactively change the settings card specific for each cipher
		CardLayout cards = (CardLayout)settingsCards.getLayout();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusBar.setForeground(Color.BLACK);
				switch (comboBox.getSelectedIndex()) {
				case 0:
					cards.show(settingsCards, "DefaultPanel");
					statusBar.setText("");
					break;
				case 1:
					cards.show(settingsCards, "CCPanel");
					statusBar.setText("Caesar cipher selected. Please use the slider to set the encryption shift.");
					break;
				case 2:
					cards.show(settingsCards, "VCPanel");
					statusBar.setText("Vigenère cipher selected. Please type in the original encryption key.");
					break;
				case 3:
					cards.show(settingsCards, "A1Z26Panel");
					statusBar.setText("A1Z26 cipher selected. Please enter a plaintext or encrypted message.");
					break;
				default:
					statusBar.setForeground(Color.RED);
					System.out.println("Error: comboBox");
				}
				outputText.setText("");
			}
		});
		
		// Caesar cipher, update number on screen with slider
		ccShiftValueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblCCShiftValue.setText(String.valueOf(ccShiftValueSlider.getValue()));
			}
		});
		
		// Handling when "encrypt" is pressed
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EncryptionHandler eh = new EncryptionHandler();
				try {
					String output = eh.handle("encrypt", comboBox.getSelectedIndex(), ccShiftValueSlider.getValue(), VCKey.getText(), inputText.getText());
					outputText.setText(output);
					statusBar.setForeground(Color.BLACK);
					statusBar.setText("Encryption successful.");
				} catch (InvalidCipherException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				} catch (NoInputException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				} catch (ProgrammingException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				}
			}
		});
		
		// Handling when "decrypt" is pressed
		btnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EncryptionHandler eh = new EncryptionHandler();
				try {
					String output = eh.handle("decrypt", comboBox.getSelectedIndex(), ccShiftValueSlider.getValue(), VCKey.getText(), inputText.getText());
					outputText.setText(output);
					statusBar.setForeground(Color.BLACK);
					statusBar.setText("Decryption successful.");
				} catch (InvalidCipherException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				} catch (NoInputException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				} catch (ProgrammingException ex) {
					System.out.println("Exception occured: " + ex);
					statusBar.setForeground(Color.RED);
					statusBar.setText(ex.getMessage());
				}
	
			}
		});
	}
	
}

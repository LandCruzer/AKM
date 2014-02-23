package me.pragmaticobject.akm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import me.pragmaticobject.akm.script.AutoClicker;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ACGUI extends JFrame {
	
	/**
	 * ACGUI object's serialization code.
	 */
	private static final long serialVersionUID = -5407353259039134100L;
	
	public static boolean isOpen;
	
	public static JPanel[] panels = new JPanel[3];
	
	public static JButton[] buttons = new JButton[1];
	
	public static JTextField[] txtFields = new JTextField[5];
	
	public static JComboBox[] comboBoxes = new JComboBox[4];
	
	public static JCheckBox[] chkBoxes = new JCheckBox[4];
	
	public static JMenuBar mb = new JMenuBar();
	
	public static JMenu[] menus = new JMenu[1];
	
	public static JMenuItem[] mItems = new JMenuItem[4];
	
	public static JFileChooser[] fc = new JFileChooser[2];
	
	public static JLabel[] labels = new JLabel[13];
	
	public static File selectedFile;
	
	public static boolean alwaysOnTop = true;
	
	int i = 0, j = 0, h=0;//175-136 panels[0]
	
	
	/**
	 * Main constructor.
	 */
	public ACGUI() {
		setTitle("Auto-Clicker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(244, 228);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(false);
		setResizable(false);
		load();
		add(mb);
		add(panels[0]);
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (AutoClicker.isRunning) {
					AutoClicker.stopClicker();
					buttons[0].setText("Start (F6)");
				}
				if (AutoClicker.listenerRunning)
					AutoClicker.stopListener();
				isOpen = false;
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
		});
	}
	
	public void load() {
		loadMenu();
		loadButtons();
		loadLabels();
		loadTxtFields();
		loadComboBoxes();
		loadChkBoxes();
		loadPanels();
	}
	

	public void init() {
		setVisible(true);
		AutoClicker.startListener();
		isOpen = true;
	}
	
	
		/*
		 * 
		 * Components
		 * 
		 */
	private void loadPanels() {
		panels[0] = new JPanel();
		panels[1] = new JPanel();
		panels[2] = new JPanel();
		panels[0].setBounds(0, 0, 244, 220);
		panels[1].setBounds(109, 20, 129, 64);
		panels[2].setBounds(0, 80, 238, 118);
		panels[1].setBorder(BorderFactory.createTitledBorder("Statistics"));
		panels[2].setBorder(BorderFactory.createTitledBorder("Settings"));
		panels[0].setLayout(null);
		panels[1].setLayout(null);
		panels[2].setLayout(null);
		panels[1].add(labels[0]);
		panels[1].add(labels[1]);
		panels[1].add(labels[2]);
		panels[1].add(labels[3]);
		panels[1].add(labels[4]);
		panels[1].add(labels[5]);
		panels[2].add(labels[6]);
		panels[2].add(labels[7]);
		panels[2].add(labels[8]);
		panels[2].add(labels[9]);
		panels[2].add(labels[10]);
		panels[2].add(labels[11]);
		panels[2].add(txtFields[0]);
		panels[2].add(txtFields[1]);
		panels[2].add(txtFields[2]);
		panels[2].add(txtFields[3]);
		panels[2].add(txtFields[4]);
		panels[2].add(comboBoxes[0]);
		panels[2].add(comboBoxes[1]);
		panels[2].add(comboBoxes[2]);
		panels[2].add(comboBoxes[3]);
		panels[2].add(chkBoxes[0]);
		panels[2].add(chkBoxes[1]);
		panels[2].add(chkBoxes[2]);
		panels[2].add(chkBoxes[3]);
		panels[0].add(mb);
		panels[0].add(buttons[0]);
		panels[0].add(panels[1]);
		panels[0].add(panels[2]);
	}
	
	private void loadMenu() {
		menus[0] = new JMenu("File");
		mItems[0] = new JMenuItem("<html><u>O</u>pen</html>");
		mItems[1] = new JMenuItem("<html><u>S</u>ave</html>");
		mItems[2] = new JMenuItem("Save As...");
		mItems[3] = new JMenuItem("<html><u>R</u>eset</html>");
		mItems[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mItems[0].isEnabled()) {
					fc[0] = new JFileChooser();
					fc[0].setAcceptAllFileFilterUsed(false);
					fc[0].setMultiSelectionEnabled(false);
					fc[0].setDragEnabled(true);
					fc[0].setFileFilter(new FileFilter() {

						@Override
						public boolean accept(File f) {
							return true;
						}

						@Override
						public String getDescription() {
							return "All Files (*.*)";
						}
						
					});
					int i = fc[0].showOpenDialog(getParent());
					if (i == JFileChooser.APPROVE_OPTION)
						selectedFile = fc[0].getSelectedFile();
				}
			}
			
		});
		mItems[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mItems[1].isEnabled()) {
					if (selectedFile != null) {
						fc[1] = new JFileChooser();
						fc[1].setAcceptAllFileFilterUsed(false);
						fc[1].setMultiSelectionEnabled(false);
						fc[1].setDragEnabled(true);
						fc[1].setFileFilter(new FileFilter() {
	
							@Override
							public boolean accept(File f) {
								return true;
							}
	
							@Override
							public String getDescription() {
								return "All Files (*.*)";
							}
							
						});
						int i = fc[1].showSaveDialog(getParent());
						if (i == JFileChooser.APPROVE_OPTION) {
							selectedFile = new File(fc[1].getSelectedFile().getAbsolutePath());
							try {
								BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
								writer.write("i1=" + Integer.parseInt(txtFields[0].getText()) + ",i2=" + Integer.parseInt(txtFields[1].getText()) + ";");
								writer.newLine();
								writer.write("t1=" + AutoClicker.intType1 + ",t2=" + AutoClicker.intType2 + ";");
								writer.newLine();
								writer.write("r=" + AutoClicker.getRepetitions() + ",c=" + AutoClicker.getMouseClicks() + ";");
								writer.newLine();
								writer.write("x=" + AutoClicker.getX() + ",y=" + AutoClicker.getY() + ";");
								writer.newLine();
								writer.write("inf=" + AutoClicker.infiniteRepeat + ",am=" + AutoClicker.allowMove + ",sm=" + AutoClicker.stopOnMove + ";");
	//							writer.newLine();
	//							writer.write("STATISTICS:");
								writer.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					} else {
						try {
							BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
							writer.write("i1=" + Integer.parseInt(txtFields[0].getText()) + ",i2=" + Integer.parseInt(txtFields[1].getText()) + ";");
							writer.newLine();
							writer.write("t1=" + AutoClicker.intType1 + ",t2=" + AutoClicker.intType2 + ";");
							writer.newLine();
							writer.write("r=" + AutoClicker.getRepetitions() + ",c=" + AutoClicker.getMouseClicks() + ";");
							writer.newLine();
							writer.write("x=" + AutoClicker.getX() + ",y=" + AutoClicker.getY() + ";");
							writer.newLine();
							writer.write("inf=" + AutoClicker.infiniteRepeat + ",am=" + AutoClicker.allowMove + ",sm=" + AutoClicker.stopOnMove + ";");
//							writer.newLine();
//							writer.write("STATISTICS:");
							writer.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			
		});
		menus[0].add(mItems[0]);
		menus[0].add(mItems[1]);
		menus[0].add(mItems[2]);
		menus[0].add(mItems[3]);
		mb.setBounds(0, 0, 244, 20);
		mb.add(menus[0]);
	}
	
	private void loadButtons() {
		buttons[0] = new JButton("Start (F6)");
		buttons[0].setBounds(2, 27, 107, 54);
		buttons[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[0].isEnabled()) {
/*					i ++;
					labels[10].setBounds(8, 99, 30, 11);
					labels[11].setBounds(28, 99, 30, 11);
					System.out.println(i);
					labels[6].setBounds(55, 18, 56, 11);
					labels[7].setBounds(78, 31, 7, 11);
					txtFields[0].setBounds(112, 15, 31, 17);
					txtFields[1].setBounds(112, 34, 31, 17);
					timeUnits[0].setBounds(146, 15, 80, 17);
					timeUnits[1].setBounds(146, 34, 80, 17);
					System.out.println(i);*/
					if (AutoClicker.isRunning) {
						AutoClicker.stopClicker();
						buttons[0].setText("Start (F6)");
					} else if (!AutoClicker.isRunning) {
						AutoClicker.startClicker();
						buttons[0].setText("Stop (F6)");
					}
				}
			}
			
		});
//		buttons[1].setBounds(8, 14, 40, 40);
//		buttons[1].setMargin(bInsets);
//		buttons[1].addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (buttons[1].isEnabled()) {
//					isAdvanced = true;
//					panels[1].setVisible(false);
//					buttons[1].setVisible(false);
//					panels[2].setVisible(true);
//					buttons[2].setVisible(true);
//					setSize(244, 220);
//				}
//			}
//			
//		});
//		buttons[2].setBounds(8, 14, 40, 40);
//		buttons[2].setMargin(bInsets);
//		buttons[2].setVisible(false);
//		buttons[2].addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (buttons[2].isEnabled()) {
//					isAdvanced = false;
////					chkBoxes[0].setVisible(false);
////					labels[8].setVisible(false);
////					labels[9].setVisible(false);
////					txtFields[2].setVisible(false);
////					txtFields[3].setVisible(false);
////					timeUnits[2].setVisible(false);
////					timeUnits[3].setVisible(false);
//					panels[1].setVisible(true);
//					buttons[1].setVisible(true);
//				}
//			}
//			
//		});
//		try {
//			buttons[1].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/img/s1.png"))));
//			buttons[2].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/img/s2.png"))));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	private void loadComboBoxes() {
		comboBoxes[0] = new JComboBox(new String[] {"Left Click", "Right Click"});
		comboBoxes[1] = new JComboBox(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
		comboBoxes[2] = new JComboBox(new String[] {"Milliseconds", "Seconds", "Minutes", "Hours"});
		comboBoxes[3] = new JComboBox(new String[] {"Milliseconds", "Seconds", "Minutes", "Hours"});
		comboBoxes[0].setBounds(8, 15, 74, 17);//15
		if (AutoClicker.isLeftClick)
			comboBoxes[0].setSelectedIndex(0);
		comboBoxes[0].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					if (e.getItem().toString().equalsIgnoreCase("Left Click")) {
						AutoClicker.isLeftClick = true;
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("Right Click")) {
						AutoClicker.isLeftClick = false;
						return;
					}
				}
			}
			
		});
		comboBoxes[1].setBounds(193, 15, 37, 17);//95
		comboBoxes[1].setSelectedIndex(0);
		comboBoxes[1].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					if (e.getItem().toString().equalsIgnoreCase("1")) {
						AutoClicker.setMouseClicks(1);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("2")) {
						AutoClicker.setMouseClicks(2);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("3")) {
						AutoClicker.setMouseClicks(3);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("4")) {
						AutoClicker.setMouseClicks(4);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("5")) {
						AutoClicker.setMouseClicks(5);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("6")) {
						AutoClicker.setMouseClicks(6);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("7")) {
						AutoClicker.setMouseClicks(7);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("8")) {
						AutoClicker.setMouseClicks(8);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("9")) {
						AutoClicker.setMouseClicks(9);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("10")) {
						AutoClicker.setMouseClicks(10);
						return;
					}
				}
			}
			
		});
		comboBoxes[2].setBounds(151, 34, 80, 17);//15
		comboBoxes[2].setSelectedIndex(1);
		comboBoxes[2].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					if (e.getItem().toString().equalsIgnoreCase("MILLISECONDS")) {
						AutoClicker.setIntervalType1(AutoClicker.MILLISECONDS);
						AutoClicker.intType1 = "ms";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("SECONDS")) {
						AutoClicker.setIntervalType1(AutoClicker.SECONDS);
						AutoClicker.intType1 = "s";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("MINUTES")) {
						AutoClicker.setIntervalType1(AutoClicker.MINUTES);
						AutoClicker.intType1 = "min";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("HOURS")) {
						AutoClicker.setIntervalType1(AutoClicker.HOURS);
						AutoClicker.intType1 = "h";
						return;
					}
				}
			}
			
		});
		comboBoxes[3].setBounds(151, 53, 80, 17);//34
		comboBoxes[3].setSelectedIndex(0);
		comboBoxes[3].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					if (e.getItem().toString().equalsIgnoreCase("MILLISECONDS")) {
						AutoClicker.setIntervalType2(AutoClicker.MILLISECONDS);
						AutoClicker.intType2 = "ms";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("SECONDS")) {
						AutoClicker.setIntervalType2(AutoClicker.SECONDS);
						AutoClicker.intType2 = "s";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("MINUTES")) {
						AutoClicker.setIntervalType2(AutoClicker.MINUTES);
						AutoClicker.intType2 = "min";
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("HOURS")) {
						AutoClicker.setIntervalType2(AutoClicker.HOURS);
						AutoClicker.intType2 = "h";
						return;
					}
				}
			}
			
		});
	}
	
	private void loadTxtFields() {
		txtFields[0] = new JTextField("1");
		txtFields[0].setBounds(118, 34, 31, 17);//15
		txtFields[0].addKeyListener(new KeyListener() {
			
			JTextField tf;
			
			@Override
			public void keyPressed(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
			
		});
		txtFields[0].addFocusListener(new FocusListener() {
			
			JTextField tf;
			
			@Override
			public void focusGained(FocusEvent e) {
				tf = (JTextField) e.getSource();
				tf.selectAll();
			}
	
			@Override
			public void focusLost(FocusEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText() == null || tf.getText().equals("")) {
					tf.setText("1");
				}
			}
			
		});
		txtFields[1] = new JTextField("0");
		txtFields[1].setBounds(118, 53, 31, 17);//34
		txtFields[1].addKeyListener(new KeyListener() {
			
			JTextField tf;
			
			@Override
			public void keyPressed(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
			
		});
		txtFields[1].addFocusListener(new FocusListener() {
			
			JTextField tf;
			
			@Override
			public void focusGained(FocusEvent e) {
				tf = (JTextField) e.getSource();
				tf.selectAll();
			}
	
			@Override
			public void focusLost(FocusEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText() == null || tf.getText().equals("")) {
					tf.setText("0");
				}
			}
			
		});
		txtFields[2] = new JTextField("0");
		txtFields[2].setBounds(19, 34, 31, 17);//15 -30
		txtFields[2].addKeyListener(new KeyListener() {
			
			JTextField tf;
			
			@Override
			public void keyPressed(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
			
		});
		txtFields[2].addFocusListener(new FocusListener() {
			
			JTextField tf;
			
			@Override
			public void focusGained(FocusEvent e) {
				tf = (JTextField) e.getSource();
				tf.selectAll();
			}
	
			@Override
			public void focusLost(FocusEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText() == null || tf.getText().equals("")) {
					tf.setText("0");
				}
			}
			
		});
		txtFields[3] = new JTextField("0");
		txtFields[3].setBounds(19, 53, 31, 17);//34
		txtFields[3].addKeyListener(new KeyListener() {
			
			JTextField tf;
			
			@Override
			public void keyPressed(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 3)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
			
		});
		txtFields[3].addFocusListener(new FocusListener() {
			
			JTextField tf;
			
			@Override
			public void focusGained(FocusEvent e) {
				tf = (JTextField) e.getSource();
				tf.selectAll();
			}
	
			@Override
			public void focusLost(FocusEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText() == null || tf.getText().equals("")) {
					tf.setText("0");
				}
			}
			
		});
		txtFields[4] = new JTextField("1");
		txtFields[4].setVisible(false);
		txtFields[4].setBounds(0, 0, 0, 0);//34
		txtFields[4].addKeyListener(new KeyListener() {
			
			JTextField tf;
			
			@Override
			public void keyPressed(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 27)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 27)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText().length() > 27)
					e.setKeyChar((char) 0);
				if (!Character.isDigit(e.getKeyChar())) {
					e.setKeyChar((char) 0);
				}
			}
			
		});
		txtFields[4].addFocusListener(new FocusListener() {
			
			JTextField tf;
			
			@Override
			public void focusGained(FocusEvent e) {
				tf = (JTextField) e.getSource();
				tf.selectAll();
			}
	
			@Override
			public void focusLost(FocusEvent e) {
				tf = (JTextField) e.getSource();
				if (tf.getText() == null || tf.getText().equals("")) {
					tf.setText("1");
				}
			}
			
		});
	}
	
	private void loadLabels() {
		labels[0] = new JLabel("Loops:");
		labels[1] = new JLabel("Clicks:");
		labels[2] = new JLabel("Time Ran:");
		labels[3] = new JLabel("0");//CAN HOLD: 88888888888 PLACEHOLDERS
		labels[4] = new JLabel("0");//CAN HOLD: 88888888888 PLACEHOLDERS
		labels[5] = new JLabel("00:00:00");//CAN HOLD: 88888888888 PLACEHOLDERS
		labels[6] = new JLabel("X:");//8
		labels[7] = new JLabel("Y:");//9
		labels[8] = new JLabel("Click every:");
		labels[9] = new JLabel("+");
		labels[10] = new JLabel("Clicks Per Loop:");
		labels[11] = new JLabel("Repeats:");
		labels[0].setBounds(7, 17, 32, 11);
		labels[0].setForeground(Color.BLUE);
		labels[1].setBounds(7, 32, 30, 11);
		labels[1].setForeground(Color.RED);
		labels[2].setBounds(7, 46, 48, 11);
		labels[3].setBounds(41, 17, 66, 11);
		labels[3].setForeground(Color.BLUE);
		labels[4].setBounds(39, 32, 66, 11);
		labels[4].setForeground(Color.RED);
		labels[5].setBounds(57, 46, 66, 11);
		labels[6].setBounds(8, 37, 10, 11);//18 -30
		labels[7].setBounds(8, 56, 10, 11);//37
		labels[8].setBounds(61, 37, 56, 11);//18
		labels[9].setBounds(106, 56, 10, 11);//37
		labels[10].setBounds(117, 17, 75, 11);//98
		labels[11].setVisible(false);
		labels[11].setBounds(0, 0, 0, 0);
	}
	
	private void loadChkBoxes() {
		chkBoxes[0] = new JCheckBox("Infinite Repeats");
		chkBoxes[1] = new JCheckBox("Stop On Move");
		chkBoxes[2] = new JCheckBox("Allow Move");
		chkBoxes[3] = new JCheckBox("Stay On Top");
		chkBoxes[0].setBounds(4, 76, 102, 15);//58
		if (AutoClicker.infiniteRepeat)
			chkBoxes[0].setSelected(true);
		chkBoxes[0].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					AutoClicker.infiniteRepeat = true;
					setSize(244, 228);
					panels[2].setBounds(0, 80, 238, 118);
					chkBoxes[0].setBounds(4, 76, 102, 15);
					chkBoxes[1].setBounds(4, 94, 93, 15);
					chkBoxes[2].setBounds(141, 76, 79, 15);
					chkBoxes[3].setBounds(141, 94, 85, 15);
					labels[11].setVisible(false);
					labels[11].setBounds(0, 0, 0, 0);
					txtFields[4].setVisible(false);
					txtFields[4].setBounds(0, 0, 0, 0);
					repaint();
				} else if (e.getStateChange() == 2) {//we add what to do when chkbox is selected
					AutoClicker.infiniteRepeat = false;
					setSize(244, 249);
					panels[2].setBounds(0, 80, 238, 139);
					chkBoxes[0].setBounds(4, 97, 102, 15);
					chkBoxes[1].setBounds(4, 115, 93, 15);
					chkBoxes[2].setBounds(141, 97, 79, 15);
					chkBoxes[3].setBounds(141, 115, 85, 15);
					labels[11].setVisible(true);
					labels[11].setBounds(8, 76, 48, 11);
					txtFields[4].setVisible(true);
					txtFields[4].setBounds(53, 73, 178, 17);
					repaint();
				}
			}
			
		});
		chkBoxes[1].setBounds(4, 94, 93, 15);//76
		if (AutoClicker.stopOnMove)
			chkBoxes[1].setSelected(true);
		chkBoxes[1].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					AutoClicker.stopOnMove = true;
				else if (e.getStateChange() == 2)
					AutoClicker.stopOnMove = false;
			}
			
		});
		chkBoxes[2].setBounds(141, 76, 79, 15);//58 -30
		if (AutoClicker.allowMove)
			chkBoxes[2].setSelected(true);
		chkBoxes[2].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					AutoClicker.allowMove = true;
				else if (e.getStateChange() == 2)
					AutoClicker.allowMove = false;
			}
			
		});
		chkBoxes[3].setBounds(141, 94, 85, 15);//58 -30
		if (alwaysOnTop) {
			chkBoxes[3].setSelected(true);
			setAlwaysOnTop(true);
		}
		chkBoxes[3].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					alwaysOnTop = true;
					setAlwaysOnTop(true);
				} else if (e.getStateChange() == 2) {
					alwaysOnTop = false;
					setAlwaysOnTop(false);
				}
			}
			
		});
	}
	
}

package me.pragmaticobject.akm;

import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.pragmaticobject.akm.script.AutoClicker;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

/**
 * 
 * @author Thomas Wall
 * 
 * @Download https://mega.co.nz/#!nBgVmKbb!hlMywooKHILYdrDbqiqS1yrEkKuguYggzHxnspX94TU
 * @Download http://www.mediafire.com/download/xfgujl0kqbnzfjc/AKM.jar
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class AutoKM implements NativeKeyListener, NativeMouseMotionListener {
	
	private static ACGUI acGUI;
	private static ATGUI atGUI;
	
	public static Image icon;
	
	/**
	 * Auto-Typer window check.
	 */
	public static boolean win1Opened;
	
	/**
	 * Auto-Clicker window check.
	 */
	public static boolean win2Opened;
	
	public static boolean amRunning, atRunning;
	
	public static boolean autoAMCoords = true;
	
	public static KeyListener keys;
	
	public static JFrame[] frames = new JFrame[3];
	
	public static JPanel[] panels = new JPanel[5];
	
	public static JLabel[] labels = new JLabel[3];
	
	public static JButton[] buttons = new JButton[6];
	
	public static JTextField[] txtBoxes = new JTextField[4];
	
	public static JComboBox[] comBoxes = new JComboBox[3];
	
	public static JCheckBox[] checkBoxes = new JCheckBox[4];
	

	static int i = 40;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		//Construct the example object and initialze native hook.
		GlobalScreen.getInstance().addNativeKeyListener(new AutoKM());//DOESN"T WORK SOMETIMES WHEN ECLIPSE RUNS A LOT.
        GlobalScreen.getInstance().addNativeMouseMotionListener(new AutoKM());
		loadMainFrame();
		acGUI = new ACGUI();
		atGUI = new ATGUI();
	}
	
		/*
		 * 
		 * Main frame
		 * 
		 */
	public static void loadMainFrame() {
		frames[0] = new JFrame("Auto Key & Mouse");
		frames[0].setIconImage(icon);
		frames[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames[0].setVisible(true);
		frames[0].setResizable(false);
		frames[0].setLayout(null);
		frames[0].setBounds(40, 40, 260, 80);
		frames[0].add(button1());
		frames[0].add(button2());
		frames[0].add(button3());
		frames[0].add(button4());
		frames[0].repaint();
	}
	
	/**
	 * The auto typer's window frame. Only for the button1 method.
	 */
	public static JFrame autoTyperF() {
		frames[1] = new JFrame("AKM - Auto Typer");
		frames[1].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frames[1].setSize(500, 500);
		frames[1].setVisible(true);
		frames[1].setResizable(false);
		frames[1].setLayout(null);
		frames[1].setLocationRelativeTo(null);
		frames[1].addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosed(WindowEvent e) {
				win1Opened = false;
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		frames[1].repaint();
		return frames[1];
	}
	
	/**
	 * The auto clicker's window frame. Only for the button2 method.
	 */
	public static JFrame autoClickerF() {
		frames[2] = new JFrame("AKM - Auto Clicker");
		frames[2].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frames[2].setSize(381, 365);
		frames[2].setAlwaysOnTop(true);
		frames[2].setVisible(true);
		frames[2].setResizable(false);
		frames[2].setLayout(null);
		frames[2].setLocationRelativeTo(null);
		frames[2].add(acMenu());
		frames[2].add(buttons[4]);
		frames[2].add(panels[0]);
		frames[2].add(panels[1]);
		frames[2].add(panels[2]);
		frames[2].addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosed(WindowEvent e) {
				win2Opened = false;
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		return frames[2];
	}
	
	
		/*
		 * 
		 * Main menu buttons
		 * 
		 */
	/**
	 * Auto-Typer button. Activates the auto-typer's frame.
	 * @return The Auto-Typer button.
	 */
	public static JButton button1()  {
		buttons[0] = new JButton("Auto-Typer");
		buttons[0].setSize(120, 20);
		buttons[0].setLocation(4, 4);
		buttons[0].addActionListener(new ActionListener() {

			JButton b;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b = (JButton) e.getSource();
				if (!ATGUI.isOpen) {
					if (b.isEnabled()) {
						System.out.println("Button 1 has been clicked (Opened Auto-Typer)");
						atGUI.init();
					}
				}
			}
			
		});
		return buttons[0];
	}
	
	/**
	 * Auto-Clicker button. Activates the auto-clicker's frame.
	 * @return The Auto-Clicker button.
	 */
	public static JButton button2()  {
		buttons[1] = new JButton("Auto-Clicker");
		buttons[1].setSize(120, 20);
		buttons[1].setLocation(130, 4);
		buttons[1].addActionListener(new ActionListener() {

			JButton b;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b = (JButton) e.getSource();
				if (b.isEnabled())
					if (!ACGUI.isOpen)
						acGUI.init();
			}
			
		});
		return buttons[1];
	}
	
	/**
	 * Calculator button. Activates the calculator's frame.
	 * @return The Calculator button.
	 */
	public static JButton button3()  {
		buttons[2] = new JButton("Calculator");
		buttons[2].setSize(120, 20);
		buttons[2].setLocation(4, 28);
		buttons[2].addActionListener(new ActionListener() {

			JButton b;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b = (JButton) e.getSource();
				if (b.isEnabled())
					try {
						Runtime.getRuntime().exec("calc");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
			
		});
		return buttons[2];
	}
	
	/**
	 * Update / About button. Activates the Update / About frame; if an update is available it will show up in the about.
	 * @return The Calculator button.
	 */
	public static JButton button4()  {
		buttons[3] = new JButton("Update / About");
		buttons[3].setSize(120, 20);
		buttons[3].setLocation(130, 28);
		buttons[3].addActionListener(new ActionListener() {

			JButton b;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b = (JButton) e.getSource();
				if (!win1Opened) {
					if (b.isEnabled()) {
						System.out.println("Button 4 has been clicked (Opened Update / About)");
						win1Opened = true;
						autoTyperF();
					}
				}
			}
			
		});
		return buttons[3];
	}
	
	
		/*
		 * 
		 * Auto-Clicker
		 * 
		 */
	public static void acLoadPanels() {
		for (int i = 0; i < 3; i ++)
			panels[i] = new JPanel();
		JLabel l1 = new JLabel("Clicks Per Execution:", JLabel.CENTER);
		JLabel l2 = new JLabel("Time:");
		JLabel l3 = new JLabel("Repeats:");
		JLabel l4 = new JLabel("X:");
		JLabel l5 = new JLabel("Y:");
		JLabel l6 = new JLabel("Total Clicks:");
		JLabel l7 = new JLabel("Executions:");
		JLabel l8 = new JLabel("Time Ran:");
		labels[0] = new JLabel("0");
		labels[1] = new JLabel("0");
		labels[2] = new JLabel("0");
		l1.setBounds(16, 36, 120, 15);
		l2.setBounds(223, 22, 50, 15);
		l3.setBounds(223, 57, 50, 15);
		l4.setBounds(223, 90, 20, 15);
		l5.setBounds(283, 90, 20, 15);
		l6.setBounds(8, 22, 60, 15);
		l7.setBounds(8, 38, 60, 15);
		l8.setBounds(8, 54, 50, 15);
		labels[0].setBounds(70, 22, 200, 15);
		labels[1].setBounds(68, 38, 200, 15);
		labels[2].setBounds(60, 54, 200, 15);
		panels[0].setBounds(150, 20, 225, 85);
		panels[0].setLayout(null);
		panels[0].setBorder(BorderFactory.createTitledBorder("Stats"));
		panels[0].add(l6);
		panels[0].add(l7);
		panels[0].add(l8);
		panels[0].add(labels[0]);
		panels[0].add(labels[1]);
		panels[0].add(labels[2]);
		panels[1].setBounds(0, 100, 375, 120);
		panels[1].setLayout(null);
		panels[1].setBorder(BorderFactory.createTitledBorder("Settings"));
		panels[1].add(l1);
		panels[1].add(l2);
		panels[1].add(l3);
		panels[1].add(l4);
		panels[1].add(l5);
		panels[1].add(txtBoxes[0]);
		panels[1].add(txtBoxes[1]);
		panels[1].add(txtBoxes[2]);
		panels[1].add(txtBoxes[3]);
		panels[1].add(acTimeUnits());
		panels[1].add(acClicksSelector());
		panels[2].setBounds(0, 217, 375, 120);
		panels[2].setLayout(null);
		panels[2].setBorder(BorderFactory.createTitledBorder("Options"));
		panels[2].add(acCheck1());
		panels[2].add(acCheck2());
		panels[2].add(acCheck3());
		panels[2].add(acCheck4());
		panels[0].add(panels[1]);
		panels[0].add(panels[2]);
//		l1.setBounds(12, 15, 120, 15);
/*		for (int i = 0; i < 3; i ++) {
			panels[i] = new JPanel();
			panels[i].setSize(375, 120);
			panels[i].setLayout(null);
		}*/
		//MAIN PANEL
//		panels[0].add(acButton2());
		
		//SETTINGS
//		panels[1].add(l1);
//		panels[1].add(acClicksSelector());
				
		//OPTIONS
		//panels[2]
	}
	
/*	public static JTabbedPane pane() {
		JTabbedPane tp = new JTabbedPane();
		tp.setSize(375, 120);
		tp.setLocation(0, 30);
		tp.setVisible(true);
		tp.addTab("Main", panels[0]);
		tp.addTab("Settings", panels[1]);
		tp.addTab("Options", panels[2]);
		return tp;
	}*/
	
	public static JSlider acClicksSelector() {
		final JSlider s = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		s.setBounds(6, 50, 140, 45);
		s.setMinorTickSpacing(1);
		s.setMajorTickSpacing(9);
		s.setPaintTicks(true);
		s.setPaintLabels(true);
		s.setSnapToTicks(true);
		s.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				AutoClicker.setMouseClicks(s.getValue());
			}
			
		});
		return s;
	}
	
	public static JMenuBar acMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Help");
		JMenuItem mi1 = new JMenuItem("Save settings");
		JMenuItem mi2 = new JMenuItem("Open settings");
		JMenuItem mi3 = new JMenuItem("Reset settings");
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		mb.setSize(375, 20);
		mb.add(m1);
		mb.add(m2);
		return mb;
	}
	
	public static void acButton1()  {
		buttons[4] = new JButton("Start (Alt + F6)");
		buttons[4].setBounds(2, 27, 146, 50);
		buttons[4].setToolTipText("Toggles the auto clicker");
		buttons[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[4].isEnabled()) {
					if (AutoClicker.isRunning) {
						AutoClicker.stopClicker();
						buttons[4].setText("Start (Alt + F6)");
					} else if (!AutoClicker.isRunning) {
						AutoClicker.startClicker();
						buttons[4].setText("Stop (Alt + F6)");
					}
				}
			}
			
		});
	}
	
	public static void acButton2()  {
		buttons[5] = new JButton("Advanced Mode");
		buttons[5].setBounds(55, 27, 146, 50);
		buttons[5].setToolTipText("Toggles settings");
		buttons[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[5].isEnabled()) {
					if (AutoClicker.isRunning) {
						AutoClicker.stopClicker();
						buttons[5].setText("Advanced Mode");
					} else if (!AutoClicker.isRunning) {
						AutoClicker.startClicker();
						buttons[5].setText("Simple Mode");
					}
				}
			}
			
		});
	}
	
	public static JComboBox acTimeUnits() {
		JComboBox cb = new JComboBox(new String[] {"Milliseconds", "Seconds", "Minutes", "Hours"});
		cb.setBounds(287, 19, 81, 19);
		cb.setBackground(Color.DARK_GRAY);
		cb.setSelectedIndex(1);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					if (e.getItem().toString().equalsIgnoreCase("MILLISECONDS")) {
						AutoClicker.setIntervalType1(AutoClicker.MILLISECONDS);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("SECONDS")) {
						AutoClicker.setIntervalType1(AutoClicker.SECONDS);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("MINUTES")) {
						AutoClicker.setIntervalType1(AutoClicker.MINUTES);
						return;
					} else if (e.getItem().toString().equalsIgnoreCase("HOURS")) {
						AutoClicker.setIntervalType1(AutoClicker.HOURS);
						return;
					}
				}
			}
			
		});
		return cb;
	}
	
	public static JCheckBox acCheck1() {
		final JCheckBox cb = new JCheckBox("Allow Move");
		cb.setBounds(8, 22, 79, 15);
		if (AutoClicker.allowMove)
			cb.setSelected(true);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					AutoClicker.allowMove = true;
				else if (e.getStateChange() == 2)
					AutoClicker.allowMove = false;
			}
			
		});
		return cb;
	}
	
	public static JCheckBox acCheck2() {
		final JCheckBox cb = new JCheckBox("Stop When Moved");
		cb.setBounds(8, 41, 113, 15);
		if (AutoClicker.stopOnMove)
			cb.setSelected(true);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					AutoClicker.stopOnMove = true;
				else if (e.getStateChange() == 2)
					AutoClicker.stopOnMove = false;
			}
			
		});
		return cb;
	}
	
	public static JCheckBox acCheck3() {
		final JCheckBox cb = new JCheckBox("Auto Mouse Coords");
		cb.setBounds(8, 60, 120, 15);
		if (autoAMCoords)
			cb.setSelected(true);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					autoAMCoords = true;
				else if (e.getStateChange() == 2)
					autoAMCoords = false;
			}
			
		});
		return cb;
	}
	
	public static JCheckBox acCheck4() {
		final JCheckBox cb = new JCheckBox("Infinite Repeats");
		cb.setBounds(8, 80, 120, 15);
		if (AutoClicker.getRepetitions() == -1)
			cb.setSelected(true);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					AutoClicker.setRepetitions(-1);
				else if (e.getStateChange() == 2)
					AutoClicker.setRepetitions(Integer.parseInt(txtBoxes[1].getText()));
			}
			
		});
		return cb;
	}
	
	public static void acTxtBox1() {
		txtBoxes[0] = new JTextField("1");
		txtBoxes[0].setBounds(253, 20, 31, 17);
//		txtBoxes[0].setLocation(153, 5);
		txtBoxes[0].addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtBoxes[0].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[0].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[0].setText(str);
				} else if (txtBoxes[0].getText().matches("[0-9]+") && txtBoxes[0].getText().length() > 4) {
					char[] chars = txtBoxes[0].getText().toCharArray();
					txtBoxes[0].setText("" + chars[0] + chars[1] + chars[2] + chars[3]);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBoxes[0].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[0].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[0].setText(str);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!txtBoxes[0].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[0].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[0].setText(str);
				}
			}
			
		});
		txtBoxes[0].addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				txtBoxes[0].setSelectionStart(0);
				txtBoxes[0].setSelectionEnd(4);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtBoxes[0].getText() == null || txtBoxes[0].getText().equals("")) {
					txtBoxes[0].setText("1");
				}
			}
			
		});
	}
	
	public static void acTxtBox2() {
		txtBoxes[1] = new JTextField("1");
		txtBoxes[1].setBounds(270, 55, 31, 17);
//		txtBoxes[1].setLocation(294, 5);
		txtBoxes[1].addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtBoxes[1].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[1].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[1].setText(str);
				} else if (txtBoxes[1].getText().matches("[0-9]+") && txtBoxes[1].getText().length() > 4) {
					char[] chars = txtBoxes[1].getText().toCharArray();
					txtBoxes[1].setText("" + chars[0] + chars[1] + chars[2] + chars[3]);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBoxes[1].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[1].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[1].setText(str);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!txtBoxes[1].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[1].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[1].setText(str);
				}
			}
			
		});
		txtBoxes[1].addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				txtBoxes[1].setSelectionStart(0);
				txtBoxes[1].setSelectionEnd(4);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtBoxes[1].getText() == null || txtBoxes[1].getText().equals("")) {
					txtBoxes[1].setText("1");
				}
			}
			
		});
	}
	
	public static void acTxtBox3() {
		txtBoxes[2] = new JTextField("0");
		txtBoxes[2].setBounds(236, 88, 31, 17);
//		txtBoxes[2].setLocation(156, 39);*/
		txtBoxes[2].addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtBoxes[2].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[2].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[2].setText(str);
				} else if (txtBoxes[2].getText().matches("[0-9]+") && txtBoxes[2].getText().length() > 4) {
					char[] chars = txtBoxes[2].getText().toCharArray();
					txtBoxes[2].setText("" + chars[0] + chars[1] + chars[2] + chars[3]);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBoxes[2].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[2].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[2].setText(str);
				} else if (Integer.valueOf(txtBoxes[2].getText()) > Toolkit.getDefaultToolkit().getScreenSize().width) {
					txtBoxes[2].setText(Toolkit.getDefaultToolkit().getScreenSize().width + "");
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!txtBoxes[2].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[2].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[2].setText(str);
				} else if (Integer.valueOf(txtBoxes[2].getText()) > Toolkit.getDefaultToolkit().getScreenSize().width) {
					txtBoxes[2].setText(Toolkit.getDefaultToolkit().getScreenSize().width + "");
				}
			}
			
		});
		txtBoxes[2].addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				txtBoxes[2].setSelectionStart(0);
				txtBoxes[2].setSelectionEnd(4);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtBoxes[2].getText() == null || txtBoxes[2].getText().equals("")) {
					txtBoxes[2].setText("0");
				}
			}
			
		});
	}
	
	public static void acTxtBox4() {
		txtBoxes[3] = new JTextField("0");
		txtBoxes[3].setBounds(296, 88, 31, 17);
//		txtBoxes[3].setLocation(205, 39);
		txtBoxes[3].addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtBoxes[3].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[3].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[3].setText(str);
				} else if (txtBoxes[3].getText().matches("[0-9]+") && txtBoxes[3].getText().length() > 4) {
					char[] chars = txtBoxes[3].getText().toCharArray();
					txtBoxes[3].setText("" + chars[0] + chars[1] + chars[2] + chars[3]);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBoxes[3].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[3].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[3].setText(str);
				} else if (Integer.valueOf(txtBoxes[3].getText()) > Toolkit.getDefaultToolkit().getScreenSize().height) {
					txtBoxes[3].setText(Toolkit.getDefaultToolkit().getScreenSize().height + "");
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!txtBoxes[3].getText().matches("[0-9]+")) {
					String str = "";
					char[] chars = txtBoxes[3].getText().toCharArray();
					for (int i = 0; i < chars.length; i ++) {
						if (Character.isDigit(chars[i])) {
							str = str + chars[i];
						}
					}
					txtBoxes[3].setText(str);
				} else if (Integer.valueOf(txtBoxes[3].getText()) > Toolkit.getDefaultToolkit().getScreenSize().height) {
					txtBoxes[3].setText(Toolkit.getDefaultToolkit().getScreenSize().height + "");
				}
			}
			
		});
		txtBoxes[3].addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				txtBoxes[3].setSelectionStart(0);
				txtBoxes[3].setSelectionEnd(4);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtBoxes[3].getText() == null || txtBoxes[3].getText().equals("")) {
					txtBoxes[3].setText("0");
				}
			}
			
		});
	}
	
	
		/*
		 * 
		 * Useful methods for components
		 * 
		 */
	public static String calcTR(String[] tr, int[] digits) {
		digits[0] ++;
		if (digits[0] == 60) {
			digits[0] = 0;
			digits[1] ++;
		}
		if (digits[1] == 60) {
			digits[1] = 0;
			digits[2] ++;
		}
		if (digits[2] < 10)
			tr[2] = "0" + digits[2];
		else
			tr[2] = digits[2] + "";
		if (digits[1] < 10)
			tr[1] = "0" + digits[1];
		else
			tr[1] = digits[1] + "";
		if (digits[0] < 10)
			tr[0] = "0" + digits[0];
		else
			tr[0] = digits[0] + "";
		return tr[2] + ":" + tr[1] + ":" + tr[0];
	}
	
		/*
		 * 
		 * Native Key Listeners (So freaking greatful for this!!!)
		 * 
		 */
	/*
	 * (non-Javadoc)
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyPressed(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("1KEY1: " + e.getKeyChar());
		System.out.println("1KEY2: " + e.getRawCode());
		System.out.println("1KEY3: " + e.getModifiers());
		System.out.println("1KEY4: " + e.isActionKey());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyReleased(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("2KEY1: " + e.getKeyChar());
		System.out.println("2KEY2: " + e.getRawCode());
		System.out.println("2KEY3: " + e.getModifiers());
		System.out.println("2KEY4: " + e.isActionKey());
		if (ACGUI.isOpen)
			if (e.getRawCode() == 117)
				if (AutoClicker.isRunning) {
					AutoClicker.stopClicker();
					ACGUI.buttons[0].setText("Start (F6)");
				} else {
					AutoClicker.startClicker();
					ACGUI.buttons[0].setText("Stop (F6)");
				}
			if (e.getRawCode() == 162) {
				ACGUI.txtFields[2].setText(MouseInfo.getPointerInfo().getLocation().x + "");
				ACGUI.txtFields[3].setText(MouseInfo.getPointerInfo().getLocation().y + "");
			}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyTyped(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("3KEY1: " + e.getKeyChar());
		System.out.println("3KEY2: " + e.getRawCode());
		System.out.println("3KEY3: " + e.getModifiers());
		System.out.println("3KEY4: " + e.isActionKey());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jnativehook.mouse.NativeMouseMotionListener#nativeMouseDragged(org.jnativehook.mouse.NativeMouseEvent)
	 */
	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		System.out.println("DRAGGED");
	}

	/*
	 * (non-Javadoc)
	 * @see org.jnativehook.mouse.NativeMouseMotionListener#nativeMouseMoved(org.jnativehook.mouse.NativeMouseEvent)
	 */
	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		
	}

}

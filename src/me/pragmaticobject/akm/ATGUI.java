package me.pragmaticobject.akm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit;

public class ATGUI extends JFrame {

	/**
	 * ATGUI object's serialization code.
	 */
	private static final long serialVersionUID = -2637652105633467535L;
	
	public static boolean isOpen;
	
	public static JLabel[] labels = new JLabel[2];
	
	public static JButton[] buttons = new JButton[4];
	
	public static JTextField[] txtFields = new JTextField[2];
	
	public static JTextArea txtArea = new JTextArea();
	
	public static JPanel[] panels = new JPanel[3];
	
	public static Action selectedLine;
	
	
	public ATGUI() {
		load();
		setTitle("Auto Typer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setSize(500, 500);
		setSize(200, 100);
		setVisible(false);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		labels[0] = new JLabel("Auto-Typer Coming Soon! - Prag");
		labels[0].setBounds(0, 0, 200, 100);
		add(labels[0]);
		//add(panels[0]);
	}
	
	
	/**
	 * Loads the frame on start-up so everything is prepared before being used. This will not initialize the frame to be
	 * usable on load.
	 */
	public void load() {
		loadButtons();
		loadTxtBoxes();
		loadPanels();
	}
	
	/**
	 * Loads the frame into a visible / usable state.
	 */
	public void init() {
		setVisible(true);
		isOpen = true;
	}
	
	private void loadPanels() {
		panels[0] = new JPanel();
		panels[1] = new JPanel();
		panels[2] = new JPanel();
		panels[0].setBounds(0, 0, 492, 492);
		panels[1].setBounds(0, 308, 129, 64);
		panels[2].setBounds(0, 80, 238, 118);
		panels[1].setBorder(BorderFactory.createTitledBorder("Statistics"));
		panels[2].setBorder(BorderFactory.createTitledBorder("Settings"));
		panels[0].setLayout(null);
		panels[1].setLayout(null);
		panels[2].setLayout(null);
		panels[0].add(txtArea);//Typing lines go here
		panels[2].add(txtFields[0]);
		panels[2].add(txtFields[1]);
		panels[0].add(buttons[0]);
		panels[0].add(buttons[1]);
		panels[0].add(panels[1]);
		panels[0].add(panels[2]);
	}
	
	private void loadButtons() {
		buttons[0] = new JButton("Start (F12)");
		buttons[1] = new JButton("Add");
		buttons[2] = new JButton("Delete");
		buttons[3] = new JButton("Edit");
		buttons[0].setBounds(0, 0, 120, 25);
		buttons[0].addActionListener(new ActionListener() {

			int i = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[0].isEnabled()) {
					i ++;
					System.out.println(i);
					buttons[1].setBounds(130, i, 120, 45);//i=313 try it..
				}
			}
			
		});
		buttons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[1].isEnabled()) {
					txtArea.setText("sdfsdfsdfsdf");
				}
			}
			
		});
		buttons[1].setBounds(130, 314, 120, 25);
		buttons[2].setBounds(130, 314, 120, 25);
	}
	
	private void loadTxtBoxes() {
		txtArea = new JTextArea();
		txtFields[0] = new JTextField("1");
		txtFields[1] = new JTextField("0");
		txtArea.setBounds(4, 28, 486, 280);//TODO: work on typing lines area border then position setting panels and stats panels to fit properly in auto-typer.
		txtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtArea.setBackground(Color.BLACK);
		txtArea.setForeground(Color.WHITE);
		txtArea.setAutoscrolls(true);
		txtArea.setEditable(false);
		txtArea.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
					selectedLine.actionPerformed(null);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		selectedLine = getAction(DefaultEditorKit.selectLineAction);
	}
	
	private Action getAction(String name) {
		Action action = null;
		Action[] actions = txtArea.getActions();
		for (int i = 0; i < actions.length; i++) {
			if (name.equals(actions[i].getValue(Action.NAME).toString())) {
				action = actions[i];
				break;
			}
		}
		return action;
	}
}

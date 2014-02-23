package me.pragmaticobject.akm.script;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

import me.pragmaticobject.akm.ACGUI;
import me.pragmaticobject.akm.AutoKM;


public class AutoClicker {//TODO: add a switch() {} case: method to clickerAdvanced to optimize performance (for each boolean and setting)
	
	public static String intType1 = "s";//MILLISECONDS = ms, SECONDS = s, MINUTES = min, HOURS = h;
	
	public static String intType2 = "ms";
	
	/**
	 * Time ran strings.
	 */
	public static String[] trs = new String[3];
	
	/**
	 * Time ran integers.
	 */
	public static int[] tri = new int[3];
	
	public static int MILLISECONDS = 1, SECONDS = 1000, MINUTES = 60000, HOURS = 3600000;
	
	/**
	 * Settings.
	 */
	private static int clicks = 1, repetitions = 1, intervals1 = 1, intervals2 = 0, intervalType1 = SECONDS, intervalType2 = MILLISECONDS;
	
	public static int totalClicks, totalRepeats, loops;
	
	public static int mouseX, mouseY;
	
	public static boolean isRunning, listenerRunning, isLeftClick = true, autoCoords = true, allowMove = true, infiniteRepeat = true, stopOnMove;
	
	private static Robot r;
	
    private static Timer timer, listener, time;
    
    private static TimerTask task, taskListener, timeRan;
	
    
    /**
     * Init auto-clicker.
     */
	public AutoClicker() {
		
	}
	
	
		/*
		 * 
		 * Main methods
		 * 
		 */
	public static void startClicker() {
		tri[0] = 0;
		tri[1] = 0;
		tri[2] = 0;
		totalClicks = 0;
		totalRepeats = 0;
		repetitions = Integer.parseInt(ACGUI.txtFields[4].getText());
		intervals1 = Integer.parseInt(ACGUI.txtFields[0].getText());
		intervals2 = Integer.parseInt(ACGUI.txtFields[1].getText());
		mouseX = Integer.parseInt(ACGUI.txtFields[2].getText());
		mouseY = Integer.parseInt(ACGUI.txtFields[3].getText());
		timer = new Timer();
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		if (isLeftClick) {
			if (clicks == 1) {
				if (infiniteRepeat) {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								r.mousePress(InputEvent.BUTTON1_MASK);
								r.mouseRelease(InputEvent.BUTTON1_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								r.mousePress(InputEvent.BUTTON1_MASK);
								r.mouseRelease(InputEvent.BUTTON1_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				} else {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								r.mousePress(InputEvent.BUTTON1_MASK);
								r.mouseRelease(InputEvent.BUTTON1_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								r.mousePress(InputEvent.BUTTON1_MASK);
								r.mouseRelease(InputEvent.BUTTON1_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				}
			} else {
				if (infiniteRepeat) {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON1_MASK);
									r.mouseRelease(InputEvent.BUTTON1_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON1_MASK);
									r.mouseRelease(InputEvent.BUTTON1_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				} else {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON1_MASK);
									r.mouseRelease(InputEvent.BUTTON1_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON1_MASK);
									r.mouseRelease(InputEvent.BUTTON1_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				}
			}
		} else {//SWITCH
			if (clicks == 1) {
				if (infiniteRepeat) {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								r.mousePress(InputEvent.BUTTON3_MASK);
								r.mouseRelease(InputEvent.BUTTON3_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								r.mousePress(InputEvent.BUTTON3_MASK);
								r.mouseRelease(InputEvent.BUTTON3_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				} else {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								r.mousePress(InputEvent.BUTTON3_MASK);
								r.mouseRelease(InputEvent.BUTTON3_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								r.mousePress(InputEvent.BUTTON3_MASK);
								r.mouseRelease(InputEvent.BUTTON3_MASK);
								totalClicks ++;
								ACGUI.labels[4].setText(totalClicks + "");
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				}
			} else {
				if (infiniteRepeat) {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON3_MASK);
									r.mouseRelease(InputEvent.BUTTON3_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON3_MASK);
									r.mouseRelease(InputEvent.BUTTON3_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				} else {
					if (allowMove) {
						task = new TimerTask() {
			
							@Override
							public void run() {
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON3_MASK);
									r.mouseRelease(InputEvent.BUTTON3_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					} else {
						task = new TimerTask() {
							
							@Override
							public void run() {
								r.mouseMove(mouseX, mouseY);
								for (int i = 0; i < clicks; i ++) {
									r.mousePress(InputEvent.BUTTON3_MASK);
									r.mouseRelease(InputEvent.BUTTON3_MASK);
									totalClicks ++;
									ACGUI.labels[4].setText(totalClicks + "");
								}
								loops ++;
								ACGUI.labels[3].setText(loops + "");
								totalRepeats ++;
								if (totalRepeats >= repetitions) {
									stopClicker();
									ACGUI.buttons[0].setText("Start (F6)");
								}
							}
							
						};
					    timer.schedule(task, 0, (intervals1 * intervalType1) + (intervals2 * intervalType2));
						isRunning = true;
						startTimeRan();
						return;
					}
				}
			}
		}
	}
	
	public static void startListener() {
		listener = new Timer();
		taskListener = new TimerTask() {

			@Override
			public void run() {
				if (isRunning) {
					if (stopOnMove && mouseMoved()) {
						stopClicker();
						ACGUI.buttons[0].setText("Start (F6)");
						listenerRunning = false;
					}
				}
			}
			
		};
		listenerRunning = true;
		listener.schedule(taskListener, 0, 50);
	}
	
	public static void startTimeRan() {
		time = new Timer();
		timeRan = new TimerTask() {

			@Override
			public void run() {
				ACGUI.labels[5].setText(AutoKM.calcTR(trs, tri));
			}
			
		};
		time.schedule(timeRan, SECONDS, SECONDS);//Delays the timer for 1 second, then updates it every 1 second.
	}
	
	public static void stopClicker() {
		stopTimeRan();
		timer.cancel();
		task.cancel();
		r = null;
		timer = null;
		task = null;
		isRunning = false;
	}
	
	public static void stopListener() {
		listener.cancel();
		taskListener.cancel();
		listener = null;
		taskListener = null;
		listenerRunning = false;
	}
	
	public static void stopTimeRan() {
		loops = 0;
		time.cancel();
		timeRan.cancel();
		time = null;
		timeRan = null;
	}
	
	public static boolean mouseMoved() {
		return MouseInfo.getPointerInfo().getLocation().x != mouseX || MouseInfo.getPointerInfo().getLocation().y != mouseY;
	}
	
	
		/*
		 * 
		 * Getters
		 * 
		 */
	/**
	 * Gets the amount of mouse clicks per interval.
	 * @return The mouse clicks.
	 */
	public static int getMouseClicks() {
		return clicks;
	}
	
	/**
	 * How many times the mouse clicker will repeat (-1 for infinite).
	 */
	public static int getRepetitions() {
		return repetitions;
	}
	
	/**
	 * The amount of time that is between each execution.
	 */
	public static int getIntervals1() {
		return intervals1;
	}
	
	public static int getIntervals2() {
		return intervals2;
	}
	
	/**
	 * The type of intervals between each execution (Milliseconds, Seconds, Minutes, and Hours).
	 */
	public static int getIntervalType1() {
		return intervalType1;
	}
	
	public static int getIntervalType2() {
		return intervalType2;
	}
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	
		/*
		 * 
		 * Setters
		 * 
		 */
	/**
	 * Sets the amount of mouse clicks per interval.
	 * @param The mouse clicks.
	 */
	public static void setMouseClicks(int i) {
		if (i < 1)
			clicks = 1;
		else
			clicks = i;
	}
	
	/**
	 * How many times the mouse clicker will repeat (-1 for infinite).
	 * @param i - The amount of times it will repeat.
	 */
	public static void setRepetitions(int i) {
		if (i < 1)
			repetitions = -1;
		else
			repetitions = i;
	}
	
	/**
	 * The amount of time that is between each execution.
	 * @param i - The new execution time.
	 */
	public static void setIntervals1(int i) {
		if (i < 1)
			intervals1 = 1;
		else
			intervals1 = i;
	}
	
	/**
	 * The amount of time that is between each execution.
	 * @param i - The new execution time.
	 */
	public static void setIntervals2(int i) {
		intervals2 = i;
	}
	
	/**
	 * The type of intervals between each execution (Milliseconds, Seconds, Minutes, and Hours).
	 * @param i - The new interval type.
	 */
	public static void setIntervalType1(int i) {
		intervalType1 = i;
	}
	
	/**
	 * The type of intervals between each execution (Milliseconds, Seconds, Minutes, and Hours).
	 * @param i - The new interval type.
	 */
	public static void setIntervalType2(int i) {
		intervalType2 = i;
	}
	
	public static void setX(int i) {
		mouseX = i;
	}
	
	public static void setY(int i) {
		mouseY = i;
	}
	
}

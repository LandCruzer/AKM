package me.pragmaticobject.akm.script;

import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

import me.pragmaticobject.akm.ACGUI;
import me.pragmaticobject.akm.AutoKM;

@SuppressWarnings("unused")
public class AutoTyper {

	public static String intType1 = "s";//MILLISECONDS = ms, SECONDS = s, MINUTES = min, HOURS = h;
	
	public static String intType2 = "ms";
	
	public static String[] lines;
	
	/**
	 * Time ran string.
	 */
	private static String[] trs = new String[3];
	
	/**
	 * Time ran integers.
	 */
	private static int[] tri = new int[3];
	
	public static int MILLISECONDS = 1, SECONDS = 1000, MINUTES = 60000, HOURS = 3600000;
	
	private static int repetitions = 1, intervals1 = 1, intervals2 = 0, intervalType1 = SECONDS, intervalType2 = MILLISECONDS;
		
	public static boolean isRunning, listenerRunning, infiniteRepeat = true;
	
	/**
	 * Statistics for the Auto-Typer.
	 */
	public static int totalLines, totalRepeats, loops;
	
	private static Robot r;
	
	private static Timer timer, listener, time;
	
	private static TimerTask task, taskListener, timeRan;
	
	
	public AutoTyper() {
		
	}
	
	
	public static void startTyper() {
		tri[0] = 0;
		tri[1] = 0;
		tri[2] = 0;
		totalLines = 0;
		totalRepeats = 0;
		timer = new Timer();
		if (infiniteRepeat) {
			task = new TimerTask() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
				
			};
		} else {
			task = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
				
			};
		}
	}
	
	public static void startTimeRan() {
		time = new Timer();
		timeRan = new TimerTask() {

			@Override
			public void run() {
				ACGUI.labels[5].setText(AutoKM.calcTR(trs, tri));
				loops ++;
			}
			
		};
		time.schedule(timeRan, 0, SECONDS);
	}
	
	public static void stopTyper() {
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
	
	
		/*
		 * 
		 * Line Manipulation.
		 * 
		 */
	public void updateList() {
		String[] newLines = new String[lines.length];
		int j = 0;
		for (int i = 0; i < lines.length; i ++)
			if (lines[i] != null) {
				newLines[j] = lines[i];
				j ++;
			}
		lines = newLines;
	}
	
	public void addLine(String line) {
		String[] newLines = new String[lines.length + 1];
		int j = 0;
		for (int i = 0; i < newLines.length; i ++)
			if (lines[i] != null) {
				newLines[j] = lines[i];
				j ++;
			}
		newLines[newLines.length - 1] = line;
		lines = newLines;
	}
	
	public void removeLine(String line) {
		String[] newLines = new String[lines.length - 1];
		int j = 0;
		for (int i = 0; i < lines.length; i ++)
			if (!lines[i].equals(line)) {
				newLines[j] = lines[i];
				j ++;
			}
		lines = newLines;
	}
	
	
		/*
		 * 
		 * Getters / Setters
		 * 
		 */
	public String getLine(String name) {
		for (int i = 0; i < lines.length; i ++)
			if (lines[i].equals(name))
				return lines[i];
		return null;
	}
	
}

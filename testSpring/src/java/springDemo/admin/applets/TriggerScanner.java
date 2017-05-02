package springDemo.admin.applets;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class TriggerScanner extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7607842424927923118L;
	StringBuffer strBuffer;

	public void init() {
		strBuffer = new StringBuffer();
		addItem("initializing the applet ");
	}

	public void start() {
		addItem("starting the applet ");
	}

	public void stop() {
		addItem("stopping the applet ");
	}

	public void destroy() {
		addItem("unloading the applet");
	}

	void addItem(String word) {
		System.out.println(word);
		strBuffer.append(word);
		repaint();
	}

	public void paint(Graphics g) {
		// Draw a Rectangle around the applet's display area.
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

		int lineHeight = 10;
		int lineSeperator = 20;
		int y = 0;

		// display the string inside the rectangle.
		y = y + lineSeperator;
		g.drawString(strBuffer.toString(), lineHeight, y);

		String cmd = "";

		// cmd = "notepad.exe";
		cmd = "WFS.exe";

		y = y + lineSeperator;
		g.drawString(cmd, lineHeight, y);

		try {
			Runtime rt;
			rt = Runtime.getRuntime();
			Process p = rt.exec(cmd);

			y = y + lineSeperator;
			g.drawString("Scanner App Opened", lineHeight, y);

			if (p.waitFor() == 0) {
				y = y + lineSeperator;
				g.drawString("App Closed", lineHeight, y);
				
				y = y + lineSeperator;
				g.drawString("Reading Files", lineHeight, y);

				final File folder = new File("D:/ScannedDocuments");
				listFilesForFolder(folder);
			}

			System.out.println("Exit Value = " + p.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
			y = y + lineSeperator;
			g.drawString(e.getMessage(), lineHeight, y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void listFilesForFolder(File folder) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}
}
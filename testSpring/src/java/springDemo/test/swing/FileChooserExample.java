package springDemo.test.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class FileChooserExample extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar mb;
	JMenu file;
	JMenuItem open;
	JTextArea ta;
	JScrollBar sb;

	FileChooserExample() {
		open = new JMenuItem("Open File");
		open.addActionListener(this);
		file = new JMenu("File");
		file.add(open);
		mb = new JMenuBar();
		mb.setBounds(0, 0, 800, 20);
		mb.add(file);
		ta = new JTextArea(470, 500);
		ta.setBounds(0, 20, 470, 500);
		sb = new JScrollBar();
		sb.setBounds(470, 0, 30, 500);
		
		add(mb);
		add(ta);
		add(sb);
		setSize(500, 500);// 400 width and 500 height
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == open) {
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				String filepath = f.getPath();
				try {
					BufferedReader br = new BufferedReader(new FileReader(filepath));
					String s1 = "", s2 = "";
					while ((s1 = br.readLine()) != null) {
						s2 += s1 + "\n";
					}
					ta.setText(s2);
					br.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}

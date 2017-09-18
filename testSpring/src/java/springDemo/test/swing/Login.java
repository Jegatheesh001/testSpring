package springDemo.test.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField tf;
	JPasswordField pf;
	JButton b;
	JTextArea ta;

	Login() {
		tf = new JTextField();
		tf.setBounds(50, 50, 150, 30); // x axis, y axis, width, height

		pf = new JPasswordField();
		pf.setBounds(50, 90, 150, 30); // x axis, y axis, width, height

		b = new JButton("Login");// creating instance of JButton
		b.setBounds(70, 130, 100, 30);// x axis, y axis, width, height
		b.addActionListener(this);// Handling action

		ta = new JTextArea();
		ta.setBounds(50, 180, 150, 30);// x axis, y axis, width, height
		ta.setFont(new Font("Verdana", Font.BOLD, 12));

		add(tf);// adding TextField in JFrame
		add(pf);// adding PasswordField in JFrame
		add(b);// adding Button in JFrame
		add(ta);// adding TextArea in JFrame

		setSize(300, 400);// 400 width and 500 height
		setLayout(null);// using no layout managers
		setVisible(true);// making the frame visible

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b) {
			if (tf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Login Name", "Alert", 1);
			} else if (String.valueOf(pf.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Password", "Alert", 1);
			} else if (tf.getText().equals("admin") && String.valueOf(pf.getPassword()).equals("admin")) {
				ta.setText("Welcome...");
				ta.setForeground(Color.MAGENTA);
			} else {
				ta.setText("Login Failed...");
				ta.setForeground(Color.RED);
			}
		}
	}

}

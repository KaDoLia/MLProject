import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login {

	private JFrame frame;
	private JTextField userName;
	private JTextField passWord;
	private int logRes = 0;
	private String firstName = null;
	private String lastName = null;
	private DataCall call = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 385, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		userName = new JTextField();
		userName.setBounds(258, 54, 86, 20);
		frame.getContentPane().add(userName);
		userName.setColumns(10);

		JLabel firstNL = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1");
		firstNL.setForeground(Color.WHITE);
		firstNL.setBounds(186, 57, 62, 14);
		frame.getContentPane().add(firstNL);

		JLabel lastNL = new JLabel("\u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2");
		lastNL.setForeground(Color.WHITE);
		lastNL.setBounds(186, 110, 62, 14);
		frame.getContentPane().add(lastNL);

		passWord = new JTextField();
		passWord.setBounds(258, 107, 85, 20);
		frame.getContentPane().add(passWord);

		JButton button = new JButton("\u03A3\u03CD\u03BD\u03B4\u03B5\u03C3\u03B7");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String _userName = userName.getText();
				String _passWord = passWord.getText();
				UserConnection connect = new UserConnection(_userName, _passWord);
				logRes = connect.userConnect();
				if (logRes != 0) {
					firstName = connect.getName();
					lastName = connect.getLast();
					call = connect.getCall();
					frame.dispose();
					UserGui user = new UserGui(firstName, lastName, call);
					user.setVisible(true);
				}

			}
		});
		button.setBounds(146, 174, 89, 23);
		frame.getContentPane().add(button);
	}
}

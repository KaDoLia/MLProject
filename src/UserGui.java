import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UserGui extends JFrame {

	private JPanel contentPane;
	public static String firstName = null;
	public static String lastName = null;
	public String searchS = null;
	private JTextField searchText;
	private JTextField textField;
	public boolean searchT = false;
	public static DataCall call = null;
	public ResultSet rs = null;
	private JTable searchRs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGui frame = new UserGui(firstName, lastName, call);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserGui(String _firstName, String _lastName, DataCall _call) {
		firstName = _firstName;
		lastName = _lastName;
		call = _call;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel welcomeL = new JLabel("");
		welcomeL.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomeL.setBounds(344, 0, 240, 14);
		contentPane.add(welcomeL);
		welcomeL.setText("Καλώς ήρθες " + firstName + " " + lastName);

		searchText = new JTextField();
		searchText.setText("search...");
		searchText.setBounds(10, 60, 179, 20);
		contentPane.add(searchText);
		searchText.setColumns(10);
		searchText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (searchT == false) {
					searchText.setText(null);
					searchT = true;
				} // exafanizetai to mhnyma mesa sto text field gia na eisagoume
					// to diko mas
			}
		});

		JLabel lblSearch = new JLabel("Search Product");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(106, 33, 95, 14);
		contentPane.add(lblSearch);

		JButton btnSearch = new JButton("\u0391\u03BD\u03B1\u03B6\u03AE\u03C4\u03B7\u03C3\u03B7");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (searchT == true) {
					searchS = searchText.getText();
					rs = call.search(searchS);
				} else {
					searchS = null;
					rs = call.search(searchS);
				}
				
			}
		});
		btnSearch.setBackground(new Color(176, 224, 230));
		btnSearch.setBounds(199, 59, 89, 23);
		contentPane.add(btnSearch);

		JButton button = new JButton("\u03A0\u03C1\u03BF\u03B2\u03BF\u03BB\u03AE");
		button.setBackground(new Color(176, 224, 230));
		button.setBounds(307, 93, 89, 23);
		contentPane.add(button);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(34, 309, 95, 16);
		contentPane.add(textArea_1);

		textField = new JTextField();
		textField.setBounds(153, 306, 95, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(176, 224, 230));
		btnNewButton.setBounds(307, 305, 89, 23);
		contentPane.add(btnNewButton);
		
		String[] columnNames = {"Id",
                "Name",
                "Type",
                "Qty."};
		searchRs = new JTable();
		searchRs.setBounds(10, 93, 278, 145);
		contentPane.add(searchRs);
	}
}

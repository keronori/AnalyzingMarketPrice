import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class Analysis {

	private JFrame frame;
	private JTextField textField;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analysis window = new Analysis();
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
	public Analysis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void resultPage(String inputData, int target) {
		// analysis
		ChartGraph cg = new ChartGraph(inputData, target);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/* Title label */
		JLabel title = new JLabel("価格相場 自動計算機");
		title.setForeground(Color.RED);
		title.setFont(new Font("LiHei Pro", Font.BOLD | Font.ITALIC, 22));
		title.setBounds(116, 6, 220, 46);
		frame.getContentPane().add(title);

		
		/* max index data for analysis */
		JLabel lblNewLabel = new JLabel("最大検索件数(対象データ)");
		lblNewLabel.setBounds(150, 72, 151, 16);
		frame.getContentPane().add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(188, 89, 96, 27);
		comboBox.addItem("~50");
		comboBox.addItem("~100");
		comboBox.addItem("~500");
		comboBox.addItem("~1000");
		comboBox.addItem("~5000");
		comboBox.addItem("~10000");
		frame.getContentPane().add(comboBox);
		
		
		/* label text field */
		JLabel searchLabel = new JLabel("検索ワード");
		searchLabel.setBounds(6, 179, 75, 16);
		frame.getContentPane().add(searchLabel);
		/* text field process */
		textField = new JTextField();
		textField.setBounds(82, 143, 288, 89);
		frame.getContentPane().add(textField);
		textField.setColumns(10);


		/* button which send data for analysis */
		JButton btnNewButton = new JButton("分析");
		btnNewButton.setBounds(168, 237, 110, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultPage(textField.getText(), Integer.parseInt(((String)comboBox.getSelectedItem()).replace("~", "")) );
			}
		});
		frame.getContentPane().add(btnNewButton);		

		
		}
}

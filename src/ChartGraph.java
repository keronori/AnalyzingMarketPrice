import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChartGraph {

	private JFrame frame;
	private String inputData;
	private int target;

	

	/**
	 * Create the application.
	 */
	public ChartGraph(String s, int i) {
		inputData = s;
		target = i;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		Scraping scra = new Scraping();
		scra.run(inputData.split(" "), target);
		scra.analysis();
		
		String[] labels = scra.getClassRange();
		int[] mode = scra.getMode();
		
		
		JLabel[] jLabels = new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            jLabels[i] = new JLabel(String.format("%s: %4d: ", labels[i], mode[i]));
            jLabels[i].setHorizontalAlignment(JLabel.RIGHT);
        }
		JComponent[] jComponents = new JComponent[labels.length];
		for (int i = 0; i < labels.length; i++) {
            final double count = mode[i];
            jComponents[i] = new JComponent() {
                @Override
                public void paint(Graphics g) {
                    super.paintComponents(g);
                    g.setColor(Color.ORANGE);
                    g.fillRect(0, 0, (int)(count / scra.getModeElement() * getWidth()), getHeight());
                }
            };
        }
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("点数分布");
        frame.setSize(450, 300);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(labels.length, 3, 10, 10));
        for (int i = 0; i < labels.length; i++) {
            frame.add(jLabels[i]);
            frame.add(jComponents[i]);
            frame.add(new JLabel(""));
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}

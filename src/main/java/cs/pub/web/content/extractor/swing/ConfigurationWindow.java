package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.springframework.stereotype.Service;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;
import cs.pub.web.content.extractor.swing.listeners.CrawlListener;
import cs.pub.web.content.extractor.swing.listeners.DomainsListener;
import cs.pub.web.content.extractor.swing.listeners.QuitListener;
import cs.pub.web.content.extractor.swing.listeners.StorageListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Service
public class ConfigurationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MyCrawlerController runner = new MyCrawlerController();
	
	private JTextField maxDepthField;
	private JTextField storageField;
	private JTextField domainsField;
	
	private JScrollPane scrollPane;

	public ConfigurationWindow() {
		initUI();
	}

	private void initUI() {
		createLayout(null);
		setTitle("WebContentExtractor");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int) screenSize.getHeight() - 40 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setRunner(runner);
	}

	private void createLayout(JComponent arg) {

		Container pane = getContentPane();
		
		JButton btnNewButton = new JButton("Crawl");
		btnNewButton.addActionListener(new CrawlListener(runner));
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new QuitListener(runner));
		
		JLabel sitesLabel = new JLabel("Sites(s):");
		sitesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		domainsField = new JTextField();
		domainsField.getDocument().addDocumentListener(new DomainsListener(runner, domainsField));
		domainsField.setColumns(10);
		
		JLabel maxDepthLabel = new JLabel("Maximum depth");
		maxDepthLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		maxDepthField = new JTextField();
		maxDepthField.setColumns(10);
		
		JLabel storageLabel = new JLabel("Storage");
		storageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		storageField = new JTextField();
		scrollPane = new JScrollPane();
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if("r".equals(arg0.getKeyChar())){
					System.out.println("\nR\n");
					scrollPane.revalidate();
					scrollPane.repaint();
				}
			}
		});
		storageField.getDocument().addDocumentListener(new StorageListener(runner, scrollPane, storageField));
		scrollPane.setVisible(true);
		pack();
		storageField.setColumns(10);
		
		GroupLayout gl = new GroupLayout(pane);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1489, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl.createSequentialGroup()
					.addGap(80)
					.addComponent(btnNewButton)
					.addGap(104)
					.addComponent(btnQuit))
				.addGroup(gl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl.createSequentialGroup()
								.addComponent(maxDepthLabel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(sitesLabel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addComponent(storageLabel))
					.addGap(146)
					.addGroup(gl.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl.createSequentialGroup()
							.addComponent(storageLabel)
							.addGap(374)
							.addComponent(storageField, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl.createParallelGroup(Alignment.TRAILING)
							.addComponent(domainsField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
							.addComponent(maxDepthField, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)))
					.addGap(1107))
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl.createSequentialGroup()
					.addGap(37)
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(sitesLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(domainsField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(maxDepthLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(maxDepthField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(storageLabel)
						.addComponent(storageField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnQuit))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		pane.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true);
	}

	public MyCrawlerController getRunner() {
		return runner;
	}

	public void setRunner(MyCrawlerController runner) {
		this.runner = runner;
	}
}
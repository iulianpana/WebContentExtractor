package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.springframework.stereotype.Service;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;
import cs.pub.web.content.extractor.utils.JsonUtils;

@Service
public class ConfigurationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MyCrawlerController runner;
	private File fileRoot = new File("outputFolder");
	private JTextField maxDepthField;
	private JTextField storageField;
	private JTextField domainsField;

	public ConfigurationWindow() {
		initUI();
	}

	private void initUI() {
		createLayout(null);
		setTitle("WebContentExtractor");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int) screenSize.getHeight() - 40 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setRunner(new MyCrawlerController());
	}

	private void createLayout(JComponent arg) {

		Container pane = getContentPane();
		
		JButton btnNewButton = new JButton("Crawl");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(JsonUtils.toJson(runner.getCrawlDomains()));
				runner.run();
				
			}
		});
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(null != runner){
					System.out.println("Stop!");
					runner.stop();
				}
			}
		});

	    DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot);
	    DefaultTreeModel model = new DefaultTreeModel(root);
	    JTree tree = new JTree();
		tree.setModel(model);

		if (null != fileRoot && null != fileRoot.listFiles()) {
			File[] subItems = fileRoot.listFiles();
			for (File file : subItems) {
				root.add(new DefaultMutableTreeNode(file));
			}
			for (int i = 0; i < root.getChildCount(); i++) {
				tree.expandRow(i);
			}
		}
	    pack();
		
		JScrollPane scrollPane = new JScrollPane(tree);
		
		JLabel sitesLabel = new JLabel("Sites(s):");
		sitesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		domainsField = new JTextField();
		domainsField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String domainsText = domainsField.getText();
				if (null != domainsText && !domainsText.isEmpty()) {
					runner.setCrawlDomains(domainsText.split(","));
					System.out.println(domainsText + " " + JsonUtils.toJson(domainsText.split(",")));
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		domainsField.setColumns(10);
		
		JLabel maxDepthLabel = new JLabel("Maximum depth");
		maxDepthLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		maxDepthField = new JTextField();
		maxDepthField.setColumns(10);
		
		JLabel storageLabel = new JLabel("Storage");
		storageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		storageField = new JTextField();
		storageField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileRoot = new File(storageField.getText());
			}
		});
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
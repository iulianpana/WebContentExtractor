package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.springframework.stereotype.Service;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

@Service
public class ConfigurationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MyCrawlerController runner;

	public ConfigurationWindow() {
		initUI();
	}

	private void initUI() {
		createLayout(null);
		setTitle("WebContentExtractor");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int) screenSize.getHeight() - 40 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent arg) {

		Container pane = getContentPane();
		
		JButton btnNewButton = new JButton("Crawl");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRunner(new MyCrawlerController());
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
	    File fileRoot = new File("dosarImagini");

	    DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot);
	    DefaultTreeModel model = new DefaultTreeModel(root);
	    JTree tree = new JTree();
		tree.setModel(model);

	    File[] subItems = fileRoot.listFiles();
	    for (File file : subItems) {
	      root.add(new DefaultMutableTreeNode(file));
	    }
	    for (int i = 0; i < root.getChildCount(); i++) {
	        tree.expandRow(i);
	    }
	    pack();
		
		JScrollPane scrollPane = new JScrollPane(tree);
		
		GroupLayout gl = new GroupLayout(pane);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl.createSequentialGroup()
							.addGap(51)
							.addComponent(btnNewButton)
							.addGap(41)
							.addComponent(btnQuit))
						.addGroup(gl.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1489, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGap(65)
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnQuit))
					.addPreferredGap(ComponentPlacement.RELATED, 420, Short.MAX_VALUE)
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
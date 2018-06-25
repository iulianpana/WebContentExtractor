package cs.pub.web.content.extractor.swing.listeners;

import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;

public class StorageListener implements DocumentListener {

	private File fileRoot;
	private JTextField storageField;
	private JScrollPane scrollPane;
	private MyCrawlerController runner;
	
	public StorageListener(MyCrawlerController runner, JScrollPane scrollPane, JTextField storageField){
		this.runner = runner;
		this.scrollPane = scrollPane;
		this.storageField = storageField;
	}
	
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (null != storageField) {
			if (null != storageField.getText() && false == storageField.getText().isEmpty()) {
				fileRoot = new File(storageField.getText());
			} 
		}
		runner.setCrawlStorageFolder(fileRoot.getAbsolutePath());
		if (null != fileRoot) {
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
			
			//scrollPane.add(tree);
			scrollPane.setViewportView(tree);
			scrollPane.setVisible(true);
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}

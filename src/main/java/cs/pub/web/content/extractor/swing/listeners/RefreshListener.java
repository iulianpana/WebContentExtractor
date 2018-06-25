package cs.pub.web.content.extractor.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class RefreshListener implements ActionListener {

	private File fileRoot;
	private JScrollPane scrollPane;
	
	public RefreshListener(File fileRoot, JScrollPane scrollPane) {
		this.fileRoot = fileRoot;
		this.scrollPane = scrollPane;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
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

}

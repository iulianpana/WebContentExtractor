package cs.pub.web.content.extractor.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class RefreshListener implements ActionListener {

	private File fileRoot;
	private JScrollPane scrollPane;
	private StorageListener storageListener;
	
	public RefreshListener(File fileRoot, JScrollPane scrollPane, StorageListener storageListener) {
		this.fileRoot = fileRoot;
		this.scrollPane = scrollPane;
		this.storageListener = storageListener;
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
		storageListener.insertUpdate(new DocumentEvent() {
			
			@Override
			public EventType getType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getOffset() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Document getDocument() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ElementChange getChange(Element elem) {
				// TODO Auto-generated method stub
				return null;
			}
		});//new DocumentEvent(this, ActionEvent.ACTION_PERFORMED, null);

	}

}

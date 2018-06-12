package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.stereotype.Service;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;

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
		GroupLayout gl = new GroupLayout(pane);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGap(51)
					.addComponent(btnNewButton)
					.addGap(41)
					.addComponent(btnQuit)
					.addContainerGap(1664, Short.MAX_VALUE))
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGap(65)
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnQuit))
					.addContainerGap(914, Short.MAX_VALUE))
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
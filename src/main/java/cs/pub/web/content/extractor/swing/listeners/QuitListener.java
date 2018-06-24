package cs.pub.web.content.extractor.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;

public class QuitListener implements ActionListener {

	private MyCrawlerController runner;

	public QuitListener(MyCrawlerController runner) {
		super();
		this.runner = runner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (null != runner) {
			System.out.println("Stop!");
			runner.stop();
		}
	}

}

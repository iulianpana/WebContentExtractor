package cs.pub.web.content.extractor.swing.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;
import cs.pub.web.content.extractor.utils.JsonUtils;

public class CrawlListener implements ActionListener {

	private MyCrawlerController runner;

	public CrawlListener(MyCrawlerController runner) {
		super();
		this.runner = runner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (null != runner) {
			System.out.println("Domains to crawl " + JsonUtils.toJson(runner.getCrawlDomains()));
			runner.run();
		}
	}

}

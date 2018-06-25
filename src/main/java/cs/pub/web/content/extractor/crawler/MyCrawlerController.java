package cs.pub.web.content.extractor.crawler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 *
 */
@Component
public class MyCrawlerController implements CommandLineRunner {

	private CrawlController controller;

	private String[] crawlDomains = {""};
	private String crawlStorageFolder = "";
	private int numberOfCrawlers = 10;

	public MyCrawlerController() {

	}

	public void run() {
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		
		config.setIncludeBinaryContentInCrawling(true);
		config.setUserAgentString("Googlebot/2.1 (+http://www.google.com/bot.html)");
		// config.setProxyHost("kirk.crm.orange.intra");
		// config.setProxyPort(3128);
		config.setPolitenessDelay(1000);
		PageFetcher fetcher = new PageFetcher(config);
		RobotstxtConfig robotsConfig = new RobotstxtConfig();
		robotsConfig.setEnabled(true);
		RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);
		config.setIncludeHttpsPages(true);
		
		try {
			controller = new CrawlController(config, fetcher, robotsSvr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String domain : getCrawlDomains()) {
			System.out.println("Added domain : " + domain);
			controller.addSeed(domain);
		}

		MyCrawler.configure(getCrawlDomains(), getCrawlStorageFolder());

		controller.startNonBlocking(MyCrawler.class, 10);
	}

	public void stop() {
		controller.shutdown();
	}

	@Override
	public void run(String... args) throws Exception {

	}

	public String[] getCrawlDomains() {
		return crawlDomains;
	}

	public void setCrawlDomains(String[] crawlDomains) {
		this.crawlDomains = crawlDomains;
	}

	public String getCrawlStorageFolder() {
		return crawlStorageFolder;
	}

	public void setCrawlStorageFolder(String crawlStorageFolder) {
		this.crawlStorageFolder = crawlStorageFolder;
	}

	public int getNumberOfCrawlers() {
		return numberOfCrawlers;
	}

	public void setNumberOfCrawlers(int numberOfCrawlers) {
		this.numberOfCrawlers = numberOfCrawlers;
	}

	public static class MyCrawlBuilder {

		private static MyCrawlerController crawler;

		public static void createCrawler() {
			crawler = new MyCrawlerController();
		}

		public MyCrawlBuilder withDomains(String[] crawlDomains) {
			crawler.setCrawlDomains(crawlDomains);
			return this;
		}
		
		public MyCrawlBuilder withNumberOfCrawlers(int numberOfCrawlers){
			crawler.setNumberOfCrawlers(numberOfCrawlers);
			return this;
		}
		
		public MyCrawlBuilder withStorageFolder(String crawlStorageFolder){
			crawler.setCrawlStorageFolder(crawlStorageFolder);
			return this;
		}
		
		public MyCrawlerController build(){
			return crawler;
		}
	}

}

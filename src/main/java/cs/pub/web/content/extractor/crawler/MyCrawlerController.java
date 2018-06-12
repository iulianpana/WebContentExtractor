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
	
	public MyCrawlerController() {
		 String[] crawlDomains = {"https://pixabay.com/en/"};
	        String crawlStorageFolder = "./dosarImagini";
	        //int numberOfCrawlers = 10;

	        CrawlConfig config = new CrawlConfig();
	        config.setCrawlStorageFolder(crawlStorageFolder);
	        config.setIncludeBinaryContentInCrawling(true);
	        config.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
	        config.setProxyHost("kirk.crm.orange.intra");
	        config.setProxyPort(3128);

	        PageFetcher fetcher = new PageFetcher(config);
	        RobotstxtConfig robotsConfig = new RobotstxtConfig();
	        robotsConfig.setEnabled(false);
	        RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);

	        try {
				controller = new CrawlController(config, fetcher, robotsSvr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for (String domain : crawlDomains) {
	            controller.addSeed(domain);
	        }

	        MyCrawler.configure(crawlDomains, crawlStorageFolder);
	}
	
    public void run() {
    	controller.startNonBlocking(MyCrawler.class, 10);
    }
    
	public void stop() {
		controller.shutdown();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
    
}

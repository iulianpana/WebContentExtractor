package cs.pub.web.content.extractor.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import cs.pub.web.content.extractor.beans.TagTxtInfo;
import cs.pub.web.content.extractor.beans.WebInfo;
import cs.pub.web.content.extractor.utils.JsonUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


/**
 * 
 */
@Service
public class MyCrawler extends WebCrawler {

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|mid|mp2|mp3|mp4|json|wav|avi|flv|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private static File storageFolder;
    private static String[] crawlDomains;
    private static String[] keyWords;

    public static void configure(String[] domain, String storageFolderName) {
        crawlDomains = domain;

        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }

    public static void configure(String[] domain, String storageFolderName, String[] words) {
        crawlDomains = domain;

        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
        
        setKeyWords(words);
    }
    
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches()) {
            return false;
        }
        for (String domain : crawlDomains) {
            if (href.startsWith(domain)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(Page page) {

        String url = page.getWebURL().getURL();
        HashMap<String, String> properties = new HashMap<>();
        // If it's a regular html page.
        // Extracts new links form attribute: srcset(not supported by crawler4j), then sends them back to links pool.
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
           /* System.out.println("text: " + text
            		+ "\nlinks:" + JsonUtils.toJson(links));
            properties.put("content", text);
            properties.put("links", JsonUtils.toJson(links));*/
            Document doc = Jsoup.parseBodyFragment(htmlParseData.getHtml());
           // doc.getElementsContainingText(searchText)
            TreeSet<String> domTree = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});
           for(Element element : doc.getAllElements()){
        	   String link = url;
        	   String text = element.text();
        	   String tagName = element.tagName();
        	   domTree.add(text);
				WebInfo webInfo = new WebInfo(link, Arrays.asList(new TagTxtInfo(tagName, text)));
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(
							storageFolder + "/" + url.replaceAll("[\\/:\\*?\"<>|]", "_") + ".json", true));

					writer.append("{");
					writer.append(JsonUtils.toJson(webInfo));
					writer.append("}");
					writer.close();
				} catch (Exception e) {

				}
        	   
           }
            	/*try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(storageFolder + "/" + url.replaceAll("[\\/:\\*?\"<>|]", "_") + ".json", true));
					writer.append("{");
					writer.append(JsonUtils.toJson(properties));
					writer.append("}");
					writer.close();
					BufferedWriter textWriter = new BufferedWriter(new FileWriter(storageFolder + "/" + url.replaceAll("[\\/:\\*?\"<>|]", "_") + ".txt", true));
					textWriter.append(JsonUtils.toJson(domTree));
					textWriter.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
    
			for (WebURL webURL : links)
				myController.addSeed(webURL.getURL());
            
        // If it's the targeted images, store them.
        } 
    }

	public static String[] getKeyWords() {
		return keyWords;
	}

	public static void setKeyWords(String[] keyWords) {
		MyCrawler.keyWords = keyWords;
	}
}

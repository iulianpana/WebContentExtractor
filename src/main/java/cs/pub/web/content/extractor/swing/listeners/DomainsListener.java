package cs.pub.web.content.extractor.swing.listeners;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cs.pub.web.content.extractor.crawler.MyCrawlerController;

public class DomainsListener implements DocumentListener {

	
	private MyCrawlerController runner;
	
	private JTextField domainsField;
	
	
	public DomainsListener(MyCrawlerController runner, JTextField domainsField) {
		super();
		this.runner = runner;
		this.domainsField = domainsField;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		if(null == domainsField){
			return;
		}
		String domainsText = domainsField.getText();
		//System.out.println(domainsText);
		if (null != domainsText && !domainsText.isEmpty()) {
			runner.setCrawlDomains(domainsText.split(", "));
			//System.out.println(domainsText + " " + JsonUtils.toJson(runner.getCrawlDomains()));
		}
		
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}

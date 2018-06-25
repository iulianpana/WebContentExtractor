package cs.pub.web.content.extractor.beans;

import java.util.List;

public class WebInfo {
	private String link;
	private List<TagTxtInfo> tagTxtList;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<TagTxtInfo> getTagTxtList() {
		return tagTxtList;
	}

	public void setTagTxtList(List<TagTxtInfo> tagTxtList) {
		this.tagTxtList = tagTxtList;
	}

	public WebInfo(String link, List<TagTxtInfo> tagTxtList) {
		super();
		this.link = link;
		this.tagTxtList = tagTxtList;
	}
	
	
}

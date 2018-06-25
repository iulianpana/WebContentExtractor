package cs.pub.web.content.extractor.beans;

public class TagTxtInfo {
	private String tagName;
	private String text;
	public TagTxtInfo(String tagName, String text) {
		super();
		this.tagName = tagName;
		this.text = text;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	 
	
}

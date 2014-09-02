package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType (XmlAccessType.FIELD)

/**
 * Item data model of rss element.
 * 
 * @author Thunyathon Jaruchotrattanasakul 5510546972
 *
 */
public class Item {
	
	private String title;
	private String link;
	private String description;
	private String pubDate;
	
	public Item() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

}

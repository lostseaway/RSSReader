package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)

/**
 * Channel data model of rss element.
 * 
 * @author Thunyathon Jaruchotrattanasakul 5510546972
 *
 */
public class Channel {
	
	private String title;
	private String link;
	private String description;
	
	@XmlElement(name = "item")
	private List<Item> items;
	
	public Channel() {
		
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


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public String toString(){
		return this.title;
	}
	
}

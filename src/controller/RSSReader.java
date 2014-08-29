package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Item;
import model.RSS;

public class RSSReader {

	public RSS getRSS() throws MalformedURLException {

		try {
			JAXBContext ctx = JAXBContext.newInstance(RSS.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			URL url = new URL("http://feeds.bbci.co.uk/news/rss.xml");
			Object obj = unmarshaller.unmarshal( url );
			RSS rss = (RSS) obj;
			return rss;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Test JAXB
	public static void main(String[] args) throws MalformedURLException {
		RSS rss = (new RSSReader()).getRSS();
		System.out.println(rss.getChannel());
		List<Item> items = rss.getChannel().getItems();
		for(int i = 0;i< items.size();i++){
			System.out.println(items.get(i));
			System.out.println(">>><<<<");
		}
	}


}
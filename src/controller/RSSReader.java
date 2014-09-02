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
/**
 * For reading URL convert to rss class
 * @author Thunyathon Jaruchotrattanasakul 5510546972
 *
 */
public class RSSReader {

	/**
	 * for convert url to 
	 * @param surl
	 * @return
	 * @throws MalformedURLException
	 */
	public RSS getRSS(String surl) throws MalformedURLException {

		try {
			JAXBContext ctx = JAXBContext.newInstance(RSS.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			URL url = new URL(surl);
			Object obj = unmarshaller.unmarshal( url );
			RSS rss = (RSS) obj;
			return rss;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Test Read
	public static void main(String[] args) throws MalformedURLException {
		RSS rss = (new RSSReader()).getRSS("http://www.blognone.com/atom.xml");
		System.out.println(rss.getChannel());
		List<Item> items = rss.getChannel().getItems();
		for(int i = 0;i< items.size();i++){
			System.out.println(items.get(i));
			System.out.println(">>><<<<");
		}
	}


}
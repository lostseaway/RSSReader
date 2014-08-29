package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Item;
import model.RSS;

import controller.RSSReader;

public class MainActivity extends JFrame implements Runnable {
	
	private RSSReader reader ;
	private JTextField urlField;
	private JLabel urlLabel;
	private JButton urlButton;
	private Item[] items;
	private RSS rss;
	private RSSActivity rssAct;
	public MainActivity(){
		super("RSS Reader");
		this.initComponets();
	}

	private void initComponets() {
		FlowLayout layout = new FlowLayout();
		Container contain = new Container();
		contain.setLayout(layout);
		urlLabel = new JLabel("Enter RSS URL : ");
		contain.add(urlLabel);
		urlField = new JTextField(20);
		urlField.setText("http://feeds.bbci.co.uk/news/rss.xml");
		contain.add(urlField);
		urlButton = new JButton("GET RSS!");
		urlButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getRSS();
			}
			
		});
		contain.add(urlButton);
		
		
		
		super.add(contain, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	private void getRSS(){
		RSSReader reader = new RSSReader();
		try {
			rss = reader.getRSS(urlField.getText());
			urlField.setText("");
			rssAct = new RSSActivity(rss);
			this.setVisible(false);
			rssAct.run();
			
		} catch (MalformedURLException e1) {
			System.out.println("BAD URL!");
			e1.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		pack();
		this.setVisible(true);
		
	}

}

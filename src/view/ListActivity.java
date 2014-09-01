package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Channel;
import model.Item;
import model.RSS;

import controller.RSSReader;

public class ListActivity extends JFrame implements Runnable {
	
	private RSSReader reader ;
	private JTextField urlField;
	private JLabel urlLabel;
	private JButton urlButton;
	private List<Item> items;
	private RSS rss;
	private RSSActivity rssAct;
	private JList list;
	private DefaultListModel listModel;
	private JTextArea detailLabel;
	private JEditorPane pane;
	public ListActivity(){
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
		
		
		
		super.add(contain, BorderLayout.NORTH);
		
//		Container cenCon = new Container();
//		cenCon.setLayout(new FlowLayout());
//		String[] select = {"1","2","3"};
//		list = new JList(select);
//		cenCon.add(list);
//		
//		super.add(cenCon,BorderLayout.WEST);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	private void getRSS(){
		RSSReader reader = new RSSReader();
		try {
			rss = reader.getRSS(urlField.getText());
			genList(rss);
			
		} catch (MalformedURLException e1) {
			System.out.println("BAD URL!");
			e1.printStackTrace();
		}
	}
	
	private void genList(RSS rss){
		Channel channel = rss.getChannel();
		Container cenCon = new Container();
		cenCon.setLayout(new FlowLayout());
		
		super.setTitle("RSS : "+channel.toString());
		pane = new JEditorPane();
		
		pane.setEditable(false);
		pane.setOpaque(false);
		pane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		pane.addHyperlinkListener(new HyperlinkListener(){

			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				System.out.println(event);
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		            String url = event.getURL().toString();
		            
		            try {
						Desktop.getDesktop().browse(URI.create(url));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
				
			}
			
		});
//		pane.addHyperlinkListener( this );
//		detailLabel = new JTextArea(10,20);
//		detailLabel.setLineWrap(true);
//		detailLabel.setEditable(false);
		items = channel.getItems();
		if(list == null){
			listModel = new DefaultListModel(); 
			list = new JList(listModel);
		}
		listModel.removeAllElements();
		for(int i = 0;i<items.size();i++){
			listModel.addElement(items.get(i).getTitle());
		}
		JScrollPane scrollPane = new JScrollPane(list);
		
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					Item item = items.get(list.getSelectedIndex());
					String des = "<html>"+item.getDescription();
					des+="<br><br>";
					des+="<a href=\""+item.getLink()+"\">ReadMore...</a>";
					des+="</html>";
					pane.setText(des);
					pane.setPreferredSize(new Dimension(300,200));
					pack();
				}
			}
			
		});
		cenCon.add(scrollPane);
		super.add(cenCon,BorderLayout.WEST);
		super.add(pane,BorderLayout.EAST);
		pack();

	}

	
	@Override
	public void run() {
		pack();
		this.setVisible(true);
		
	}

}

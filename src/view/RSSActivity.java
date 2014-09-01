package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.List;
//import java.nio.channels.Channel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.*;

public class RSSActivity extends JFrame implements Runnable {

	private Channel channel;
	private JTable table;
	private JScrollPane sTable;
	public RSSActivity(RSS rss){
		super("RSS : "+rss.getChannel().toString());
		this.channel = rss.getChannel();
		this.initComponets();
	}
	private void initComponets() {
		FlowLayout layout = new FlowLayout();
		Container contain = new Container();
		contain.setLayout(layout);
		
		this.initTable();
		sTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		contain.add(sTable);
		super.add(contain, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initTable(){
		List<Item> items = channel.getItems();
		String[] colName = {"#","Title","Description"};
		Object[][] data = new Object[items.size()][3];
		for(int i = 0;i<items.size();i++){
			data[i][0] = i+1;
			data[i][1] = items.get(i).getTitle();
			data[i][2] = items.get(i).getDescription();
		}
		table = new JTable(data,colName);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.setRowHeight(25);
	}
	
	@Override
	public void run() {
		pack();
		this.setVisible(true);
		
	}

}

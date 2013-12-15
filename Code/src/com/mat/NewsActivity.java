/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 *         Project: Final course project
 *           Title: Android S&P 500 Stock Quote Finder Application
 * Completion Date: December 15, 2012
 *        Compiler: JDK 1.7.0
 *  Development OS: Windows 7 Home Premium (SP1)
 *             IDE: NetBeans 7.1.2 (Build 201204101705)
 * 
 * Description: Activity class displays current news about a stock.
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NewsActivity extends Activity {
    ListView newsItemListView;
    ArrayAdapter<NewsItem> adapter;
    String Connection=null;
    ArrayList<NewsItem> NewsItemList = new ArrayList<NewsItem>();
    NewsItem selectedItem;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
		
    Bundle extras = getIntent().getExtras();
    String Ticker = extras.getString("Ticker");
		
    TextView txt = (TextView)findViewById(R.id.txtNewsTitle);
    txt.setText("News for " + Ticker);
		
    newsItemListView = (ListView)findViewById(R.id.lsvNewsItem);
    newsItemListView.setOnItemClickListener(
            new OnItemClickListener() {
                public void onItemClick(AdapterView av, View arg1, int index, long arg3) {
                    try {
                        selectedItem = NewsItemList.get(index);
                        Intent intentNews ;
                        intentNews = new Intent().setClass(getApplicationContext(), WebViewActivity.class);
		        intentNews.putExtra("Title", selectedItem.Title);
		        intentNews.putExtra("Link", selectedItem.Link);	      
                        startActivity(intentNews); 
                    } catch (Exception ex) {
                    }
                }
            });
    int layoutID = android.R.layout.simple_list_item_1;
    adapter = new ArrayAdapter<NewsItem>(this, layoutID,NewsItemList);
    newsItemListView.setAdapter(adapter);
	    
    loadNewsItem(Ticker);
    //Toast.makeText(this,Ticker + " " + String.valueOf(NewsItemList.size()),Toast.LENGTH_LONG).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_news, menu);
        return true;
    }
    
    private void loadNewsItem(String ticker) {
        String newsPath;
        String newsURL = "http://feeds.finance.yahoo.com/rss/2.0/headline?s=";
        String languageEnglish = "&region=US&lang=en-US";
        if(Connection==null) {
            newsPath = newsURL + ticker + languageEnglish; }
        else { 
            newsPath = Connection; 
        }
        
        URL url;
        try {
            url = new URL(newsPath);
            URLConnection connection;
            connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection)connection;
            int respronseCode = httpConnection.getResponseCode();
            
            if(respronseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
				
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
                
                Document dom = db.parse(in);	
		Element docEle = dom.getDocumentElement();
		NewsItemList.clear();
                NodeList nodeList = docEle.getElementsByTagName("item");
                if ((nodeList != null) && (nodeList.getLength()) > 0) {
                    for(int i = 0; i < nodeList.getLength(); i++) {
                        try {
                            Element entry = (Element)nodeList.item(i);
                            Element title = (Element)entry.getElementsByTagName("title").item(0);
        		    //Element description = (Element)entry.getElementsByTagName("description").item(0);
                            Element link = (Element)entry.getElementsByTagName("link").item(0);
                            Element pubDate = (Element)entry.getElementsByTagName("pubDate").item(0);
						
                            String stTitle = title.getFirstChild().getNodeValue();
                            //String stDescription = description.getFirstChild().getNodeValue();
                            String stLink = link.getFirstChild().getNodeValue();
                            String stpubDate = pubDate.getFirstChild().getNodeValue();
						
                            NewsItem newsItem = new NewsItem(stTitle, stLink, "", stpubDate);
						
                            NewsItemList.add(newsItem);		
                        } catch (Exception ex) {
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
        } catch (SAXException e) {
            // TODO Auto-generated catch block
        }
    }
}
/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 *         Project: Final course project
 *           Title: Android S&P 500 Stock Quote Finder Application
 * Completion Date: December 16, 2012
 *        Compiler: JDK 1.7.0
 *  Development OS: Windows 7 Home Premium (SP1)
 *             IDE: NetBeans 7.1.2 (Build 201204101705)
 * 
 * Description: Displays articles from various website links retrieved from
 * Yahoo! Finance RSS Feeds.
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class WebViewActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_web_view);
		
	Bundle extras = getIntent().getExtras();
	String Title = extras.getString("Title");
	String Link = extras.getString("Link");
		
	TextView txt = (TextView)findViewById(R.id.txtNewsItemTitle);
	txt.setText(Title);
		
	WebView myWebView = (WebView) findViewById(R.id.webvNews);
	WebSettings webSettings = myWebView.getSettings();
	webSettings.setJavaScriptEnabled(true);
	myWebView.loadUrl(Link);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_web_view, menu);
        return true;
    }
}

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
 * Description: Activity class displays a daily stock chart from Yahoo! finance
 * plotted with a 50-day and 200-day moving average.
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import java.net.URL;

public class ChartActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_chart);
		
	Bundle extras = getIntent().getExtras();
	String Ticker = extras.getString("Ticker");
		
	TextView txt = (TextView)findViewById(R.id.txtChartTitle);
	txt.setText("Chart of " + Ticker);
        String chartURL = "http://chart.finance.yahoo.com/z?s=";
        String chartType = "&t=6m&q=l&l=on&z=l&p=m50,m200";
	String urlString =  chartURL + Ticker + chartType;
	URL url;
        try {
            url = new URL(urlString);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            ImageView img = (ImageView)findViewById(R.id.imvChart);
            img.setImageBitmap(bmp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_chart, menu);
        return true;
    }
}
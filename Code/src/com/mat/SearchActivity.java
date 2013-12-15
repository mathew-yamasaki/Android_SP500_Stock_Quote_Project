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
 * Description: Activity class displays text field and button.
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class SearchActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }
}

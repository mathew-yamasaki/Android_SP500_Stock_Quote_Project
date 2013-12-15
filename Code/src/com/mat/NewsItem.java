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
 * Description: This class defines the attributes for a news item displayed in 
 * the NewsActivity.java class.
 ******************************************************************************/
package com.mat;

public class NewsItem {
    public String Title;
    public String Link;
    public String Description;
    public String PubDate;
   
    public NewsItem(String title, String link, String desciption, String pubDate) {
        this.Title = title;
        this.Link = link;
	this.Description = desciption;
	this.PubDate = pubDate;
    }
    
    public String getSummaryTitle(int length) {
        if (Title.length() >= length)
            return Title;
        else {
            String str = Title.substring(0, length - 4);
            str += "...";
            return str; 
        }
    }
    
    @Override
    public String toString() {
        if(Title.length() > 42) {
            return "Title :" + Title.substring(0,42) + "...";
        }
        return "Title :" + Title;
    }
}
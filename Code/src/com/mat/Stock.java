/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 *         Project: Final course project
 *           Title: Android S&P 500 Stock Quote Finder Application
 * Completion Date: November 28, 2012
 *        Compiler: JDK 1.7.0
 *  Development OS: Windows 7 Home Premium (SP1)
 *             IDE: NetBeans 7.1.2 (Build 201204101705)
 * 
 * Description: Defines the attributes for a Stock object.
 ******************************************************************************/
package com.mat;

public class Stock {

    private int _id;
    private String _ticker;
    private double _closing_price;
    private double _daily_dollar_change;
    private String _daily_percent_change;
    private double _daily_high;
    private double _daily_low;
    private double _52_week_high;
    private double _52_week_low;
    private double _pe_ratio;
    private int _volume;
    private double _50_day_moving_average;
    private String _name;
    
    // No-args constructor
    public Stock() {
        
    }
    
    // Constructor
    public Stock(String ticker, String name) {
        this._ticker = ticker;
        this._name = name;
    }
    
    // Constructor
    public Stock(int id, String ticker, String name) {
        this._id = id;
        this._ticker = ticker;
        this._name = name;
    }
    
    // Constructor
    public Stock(int id, String ticker, double closingPrice, double dailyDollarChange, 
    		String dailyPercentChange, double dailyHigh, double dailyLow, double week52High, 
    		double week52Low, double peRatio, int volume, double movingAverage50Day, String name) {
        
        this._id = id;
        this._ticker = ticker;
        this._name = name;
        this._closing_price = closingPrice;
        this._daily_dollar_change = dailyDollarChange;
        this._daily_percent_change = dailyPercentChange;
        this._daily_high = dailyHigh;
        this._daily_low = dailyLow;
        this._52_week_high = week52High;
        this._52_week_low = week52Low;
        this._pe_ratio = peRatio;
        this._volume = volume;
        this._50_day_moving_average = movingAverage50Day;
    }
    
    // Constructor
    public Stock(String ticker, double closingPrice, double dailyDollarChange, 
    		String dailyPercentChange, double dailyHigh, double dailyLow, double week52High, 
    		double week52Low, double peRatio, int volume, double movingAverage50Day, String name) {
        
        //this._id = id;
        this._ticker = ticker;       
        this._closing_price = closingPrice;
        this._daily_dollar_change = dailyDollarChange;
        this._daily_percent_change = dailyPercentChange;
        this._daily_high = dailyHigh;
        this._daily_low = dailyLow;
        this._52_week_high = week52High;
        this._52_week_low = week52Low;
        this._pe_ratio = peRatio;
        this._volume = volume;
        this._50_day_moving_average = movingAverage50Day;
        this._name = name;
    }

    public int getID() {
        return this._id;
    }
    
    public void setID(int id) {
        this._id = id;
    }
    
    public String getTicker() {
        return this._ticker;
    }
    
    public void setTicker(String ticker) {
        this._ticker = ticker;
    }
    
    public double getClosingPrice() {
        return this._closing_price;
    }
    
    public void setClosingPrice(double closingPrice) {
        this._closing_price = closingPrice;
    }
    
    public double getDailyDollarChange() {
    	return this._daily_dollar_change;
    }
    
    public void setDailyDollarChange(double dailyDollarChange) {
    	this._daily_dollar_change = dailyDollarChange;
    }
    
    public String getDailyPercentChange() {
        return this._daily_percent_change;
    }
    
    public void setDailyPercentChange(String dailyPercentChange) {
        this._daily_percent_change = dailyPercentChange;
    }
    
    public double getDailyHigh() {
    	return this._daily_high;
    }
    
    public void setDailyHigh(double dailyHigh) {
    	this._daily_high = dailyHigh;
    }
    
     public double getDailyLow() {
        return this._daily_low;
    }
    
    public void setDailyLow(double dailyLow) {
        this._daily_low = dailyLow;
    }
     
    public double get52WeekHigh() {
        return this._52_week_high;
    }
    
    public void set52WeekHigh(double week52High) {
        this._52_week_high = week52High;
    }
    
    public double get52WeekLow() {
        return this._52_week_low;
    }
    
    public void set52WeekLow(double week52Low) {
        this._52_week_low = week52Low;
    }
    
    public double getPEratio() {
        return this._pe_ratio;
    }
    
    public void setPEratio(double peRatio) {
        this._pe_ratio = peRatio;
    }
    
    public int getVolume() {
        return this._volume;
    }
    
    public void setVolume(int volume) {
        this._volume = volume;
    }
    
    public double get50DayMovingAverage() {
        return this._50_day_moving_average;
    }
    
    public void set50DayMovingAverage(double day50MovingAverage) {
        this._50_day_moving_average = day50MovingAverage;
    }
    
    public String getName() {
        return this._name;
    }
    
    public void setName(String name) {
        this._name = name;
    }
    
    @Override
    public String toString() {
    	String str = "ID: " + _id + " - Name: " + _name + "\n";
    	return str;
    }
}
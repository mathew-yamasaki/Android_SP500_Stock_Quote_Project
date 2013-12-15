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
 * Description: Class creates a new database and open() and close() methods
 * and also contains the method for creating and inserting a Stock object
 * into the database.
 *
 ******************************************************************************/
package com.mat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StocksDataSource {
    // Database fields
    private SQLiteDatabase database;
    private StockDatabaseHelper dbHelper;
    private String[] allColumns = { StockDatabaseHelper.KEY_ID, StockDatabaseHelper.KEY_TICKER, StockDatabaseHelper.KEY_NAME, 
        StockDatabaseHelper.KEY_CLOSING_PRICE, StockDatabaseHelper.KEY_DAILY_DOLLAR_CHANGE, StockDatabaseHelper.KEY_DAILY_PERCENT_CHANGE,
	StockDatabaseHelper.KEY_DAILY_HIGH, StockDatabaseHelper.KEY_DAILY_LOW, StockDatabaseHelper.KEY_52_WEEK_HIGH,
        StockDatabaseHelper.KEY_52_WEEK_LOW, StockDatabaseHelper.KEY_PE_RATIO, StockDatabaseHelper.KEY_VOLUME, 
	StockDatabaseHelper.KEY_50_DAY_MOVING_AVERAGE };
    
    public StocksDataSource(Context context) {
        dbHelper = new StockDatabaseHelper(context);
    }
    
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    
    public void close() {
        dbHelper.close();
    }
    
    public Stock createStock(Stock stock) {
        ContentValues values = new ContentValues();
        values.put(StockDatabaseHelper.KEY_TICKER, stock.getTicker());
        values.put(StockDatabaseHelper.KEY_NAME, stock.getName());
        values.put(StockDatabaseHelper.KEY_CLOSING_PRICE, stock.getClosingPrice());
        values.put(StockDatabaseHelper.KEY_DAILY_DOLLAR_CHANGE, stock.getDailyDollarChange());
        values.put(StockDatabaseHelper.KEY_DAILY_PERCENT_CHANGE, stock.getDailyPercentChange());
        values.put(StockDatabaseHelper.KEY_DAILY_HIGH, stock.getDailyHigh());
        values.put(StockDatabaseHelper.KEY_DAILY_LOW, stock.getDailyLow());
	values.put(StockDatabaseHelper.KEY_52_WEEK_HIGH, stock.get52WeekHigh());
	values.put(StockDatabaseHelper.KEY_52_WEEK_LOW, stock.get52WeekLow());
        values.put(StockDatabaseHelper.KEY_PE_RATIO, stock.getPEratio());
	values.put(StockDatabaseHelper.KEY_VOLUME, stock.getVolume());
	values.put(StockDatabaseHelper.KEY_50_DAY_MOVING_AVERAGE, stock.get50DayMovingAverage());
	    
	long insertId = database.insert(StockDatabaseHelper.TABLE_STOCKS, null, values);

        Cursor cursor = database.query(StockDatabaseHelper.TABLE_STOCKS,
                allColumns, StockDatabaseHelper.KEY_ID + " = " + insertId, null, 
                null, null, null);
        cursor.moveToFirst();
	Stock newStock = cursorToStock(cursor);
        cursor.close();
        return newStock;
    }
    
    private Stock cursorToStock(Cursor cursor) {
        return new Stock();
    }
}
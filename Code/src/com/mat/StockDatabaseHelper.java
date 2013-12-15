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
 * Description: This class defines the variables and method to create a table
 * within SQLiteDatabase and all methods for Stock object CRUD operations.
 ******************************************************************************/
package com.mat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class StockDatabaseHelper extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 6;
    
    // Database name
    private static final String DATABASE_NAME = "S&P_500_Database";
    
    // Stock table name
    public static final String TABLE_STOCKS = "Stocks";
    
    // Table column names
    public static final String KEY_ID = "stockID";
    public static final String KEY_TICKER = "Ticker";
    public static final String KEY_NAME = "companyName";
    public static final String KEY_CLOSING_PRICE = "closingPrice";
    public static final String KEY_DAILY_DOLLAR_CHANGE = "dailyDollarChange";
    public static final String KEY_DAILY_PERCENT_CHANGE = "dailyPercentChange";
    public static final String KEY_DAILY_HIGH = "dailyHigh";
    public static final String KEY_DAILY_LOW = "dailyLow";
    public static final String KEY_52_WEEK_HIGH = "Week52High";
    public static final String KEY_52_WEEK_LOW = "Week52Low";
    public static final String KEY_PE_RATIO = "peRatio";
    public static final String KEY_VOLUME = "volume";
    public static final String KEY_50_DAY_MOVING_AVERAGE = "movingAverage50Day";
    
    public StockDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STOCKS_TABLE = "CREATE TABLE " + TABLE_STOCKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
                + KEY_TICKER + " TEXT,"
                + KEY_CLOSING_PRICE + " TEXT,"
                + KEY_DAILY_DOLLAR_CHANGE + " TEXT,"
                + KEY_DAILY_PERCENT_CHANGE + " TEXT,"
                + KEY_DAILY_HIGH + " TEXT,"
                + KEY_DAILY_LOW + " TEXT,"
                + KEY_52_WEEK_HIGH + " TEXT,"
                + KEY_52_WEEK_LOW + " TEXT,"
                + KEY_PE_RATIO + " TEXT,"
                + KEY_VOLUME + " TEXT,"
                + KEY_50_DAY_MOVING_AVERAGE + " TEXT,"
        		+ KEY_NAME + " TEXT" + ")";
        
        db.execSQL(CREATE_STOCKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCKS);
        
        // Create tables again
        onCreate(db);
    }
    
    /*
     * All CRUD operations
     */
    
    // Add new stock
    void addStock(Stock stock) {
    	// Open database
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        //values.put(KEY_ID, stock.getID());
        values.put(KEY_TICKER, stock.getTicker());
        values.put(KEY_CLOSING_PRICE, stock.getClosingPrice());
        values.put(KEY_DAILY_DOLLAR_CHANGE, stock.getDailyDollarChange());
        values.put(KEY_DAILY_PERCENT_CHANGE, stock.getDailyPercentChange());
        values.put(KEY_DAILY_HIGH, stock.getDailyHigh());
        values.put(KEY_DAILY_LOW, stock.getDailyLow());
        values.put(KEY_52_WEEK_HIGH, stock.get52WeekHigh());
        values.put(KEY_52_WEEK_LOW, stock.get52WeekLow());
        values.put(KEY_PE_RATIO, stock.getPEratio());
        values.put(KEY_VOLUME, stock.getVolume());
        values.put(KEY_50_DAY_MOVING_AVERAGE, stock.get50DayMovingAverage());
        values.put(KEY_NAME, stock.getName());
        
        // Inserting rows
        db.insert(TABLE_STOCKS, null, values);
                
        // Close database connection
        db.close();
    }         
    
    // Get single stock
    public Stock getStock(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_STOCKS, new String[] { KEY_ID,
            KEY_TICKER, KEY_CLOSING_PRICE, KEY_DAILY_DOLLAR_CHANGE,
            KEY_DAILY_PERCENT_CHANGE, KEY_52_WEEK_HIGH, KEY_52_WEEK_LOW, KEY_DAILY_HIGH, 
            KEY_DAILY_LOW, KEY_PE_RATIO, KEY_VOLUME, KEY_50_DAY_MOVING_AVERAGE, KEY_NAME }, KEY_ID + "?",
            new String[] {String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        Stock stock = new Stock(
                Integer.parseInt(cursor.getString(0)), 	// database ID
                cursor.getString(1), 			// ticker
                cursor.getDouble(2), 			// closing price
                cursor.getDouble(3), 			// daily dollar change
                cursor.getString(4), 			// daily percent change
                cursor.getDouble(5),            	// daily high
                cursor.getDouble(6),    		// daily low
                cursor.getDouble(7), 			// 52-week high
                cursor.getDouble(8), 			// 52-week low
                cursor.getDouble(9), 			// PE ratio
        	cursor.getInt(10), 			// volume
        	cursor.getDouble(11), 			// 50-day moving average
       		cursor.getString(12)); 			// name
        return stock;
    }
        
    // Get all contacts
    public List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<Stock>();
        
        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_STOCKS;
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        // Looping through all rows and adding to list
        
        if (cursor.moveToFirst()) {
            do{
                Stock stock = new Stock();
                stock.setID(Integer.parseInt(cursor.getString(0)));
                stock.setTicker(cursor.getString(1));
                stock.setClosingPrice(cursor.getDouble(2));
                stock.setDailyDollarChange(cursor.getDouble(3));
                stock.setDailyPercentChange(cursor.getString(4));
                stock.setDailyHigh(cursor.getDouble(5));
                stock.setDailyLow(cursor.getDouble(6));
                stock.set52WeekHigh(cursor.getDouble(7));
                stock.set52WeekLow(cursor.getDouble(8));
                stock.setPEratio(cursor.getDouble(9));
                stock.setVolume(cursor.getInt(10));
                stock.set50DayMovingAverage(cursor.getDouble(11));
                stock.setName(cursor.getString(12));
            
                stockList.add(stock);
            } while (cursor.moveToNext());
        }
        // return list
        return stockList;
    }
    
    // Updating single contact
    public int updateStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        values.put(KEY_ID, stock.getID());
        values.put(KEY_TICKER, stock.getTicker());
        values.put(KEY_NAME, stock.getName());
        values.put(KEY_CLOSING_PRICE, stock.getClosingPrice());
        values.put(KEY_DAILY_DOLLAR_CHANGE, stock.getDailyDollarChange());
        values.put(KEY_DAILY_PERCENT_CHANGE, stock.getDailyPercentChange());
        values.put(KEY_DAILY_HIGH, stock.getDailyHigh());
        values.put(KEY_DAILY_LOW, stock.getDailyLow());
        values.put(KEY_52_WEEK_HIGH, stock.get52WeekHigh());
        values.put(KEY_52_WEEK_LOW, stock.get52WeekLow());
        values.put(KEY_PE_RATIO, stock.getPEratio());
        values.put(KEY_VOLUME, stock.getVolume());
        values.put(KEY_50_DAY_MOVING_AVERAGE, stock.get50DayMovingAverage());
        
        // Updating row
        return db.update(TABLE_STOCKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(stock.getID()) });
    }
    
    // Deleting single stock
    public void deleteStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STOCKS, KEY_ID + " = ?",
                new String[] { String.valueOf(stock.getID()) });
        db.close();
    }
    
    // Delete all stocks
    public void deleteAllStocks() {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_STOCKS, null, null);
    }
    
    // Getting all contacts count
    public int getStocksCount() {
        String countQuery = "SELECT * FROM " + TABLE_STOCKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        
        // Return count
        return cursor.getCount();
    }
}
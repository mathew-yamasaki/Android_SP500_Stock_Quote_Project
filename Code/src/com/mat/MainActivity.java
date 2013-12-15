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
 * Description: Main application activity that displays a text field and button
 * for accepting user input to query the database. The top five daily winners 
 * and bottom five daily losers by percent gain or loss, respectively,
 * are displayed for informational purposes. 
 *
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {
	public static final String NAME_KEY = "Name";
	public static final String TICKER_KEY = "Ticker";
	public static final String CLOSING_PRICE_KEY = "ClosingPrice";
	public static final String CHANGE_DOLLAR_KEY = "ChangeDollar";
	public static final String DAILY_HIGH_KEY = "DailyHigh";
	public static final String DAILY_LOW_KEY = "DailyLow";
	public static final String DAY_MA_KEY = "DayMA";
	public static final String RATIO_KEY = "Ratio";
	public static final String VOLUME_KEY = "Volume";
	public static final String WEEK_HIGH_KEY = "WeekHigh";
	public static final String WEEK_LOW_KEY = "WeekLow";
	public static final String CHANGE_PERCENT_KEY = "ChangePercent";
	
	private List<Stock> stockList;
	private Button btnSubmit;
	private EditText edtInput;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // create database
        StockDatabaseHelper db = new StockDatabaseHelper(this);              
        
        Log.d("Insert", "Inserting...");
        
        //Log.d("Reading: ", "Reading all stocks");
        stockList = new ArrayList<Stock>();
        stockList = YahooFinanceDataHelper.parseRespone500();
        db.deleteAllStocks();      
        
        for (Stock s : stockList) {
        	db.addStock(s);
        }   
        
        stockList.clear();
        stockList = db.getAllStocks();  
        stockList = sortByPercent(stockList);
        
        bindingDailyLosers();
        bindingDailyWinners();
        
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edtInput = (EditText)findViewById(R.id.edtInput);
        btnSubmit.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSubmit:
                if (checkInput(edtInput.getText().toString())) {
                    Stock stock = searchStock(edtInput.getText().toString().trim());
                    if (stock.getTicker() == "") {
                        Toast.makeText(this,"Not found!",Toast.LENGTH_SHORT).show();
                    } 
                    else {
                        Intent intent = new Intent(this, InfomationActivity.class);
                        intent.putExtra(NAME_KEY, stock.getName());
    			intent.putExtra(TICKER_KEY, stock.getTicker());
    			intent.putExtra(CLOSING_PRICE_KEY, String.valueOf(stock.getClosingPrice()));
    			intent.putExtra(CHANGE_DOLLAR_KEY, String.valueOf(stock.getDailyDollarChange()));
    			intent.putExtra(DAILY_HIGH_KEY, String.valueOf(stock.getDailyHigh()));
    			intent.putExtra(DAILY_LOW_KEY, String.valueOf(stock.getDailyLow()));
    			intent.putExtra(DAY_MA_KEY, String.valueOf(stock.get50DayMovingAverage()));
    			intent.putExtra(RATIO_KEY, String.valueOf(stock.getPEratio()));
    			intent.putExtra(VOLUME_KEY, String.valueOf(stock.getVolume()));
    			intent.putExtra(WEEK_HIGH_KEY, String.valueOf(stock.get52WeekHigh()));
    			intent.putExtra(WEEK_LOW_KEY, String.valueOf(stock.get52WeekLow()));
    			intent.putExtra(CHANGE_PERCENT_KEY, stock.getDailyPercentChange());
    			startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }
    
    private List<Stock> sortByPercent(List<Stock> lstStock) {
        List<Stock> stocks = lstStock;
        for (int i = 0; i < stocks.size() - 1; i++) {
            for (int j = i + 1; j < stocks.size(); j++) {
                if (valueOfPercentString(stocks.get(i).getDailyPercentChange()) < 
                        valueOfPercentString(stocks.get(j).getDailyPercentChange())) {
                    Stock temp = stocks.get(i);
                    stocks.set(i, stocks.get(j));
                    stocks.set(j, temp);
                }
            }
        }
        return stocks;
    }
    
    private double valueOfPercentString(String percentString) {
        double value;
        percentString = percentString.replace(" ", "");
    	percentString = percentString.replace("%", "");
        try {
            value = Double.parseDouble(percentString);
            return value; 
        } catch(Exception ex) {
            return Double.NaN;
    	}
    }

    private void bindingDailyWinners() {
        TextView txtTicker1 = (TextView)findViewById(R.id.txtTickerWin1);
    	TextView txtTicker2 = (TextView)findViewById(R.id.txtTickerWin2);
    	TextView txtTicker3 = (TextView)findViewById(R.id.txtTickerWin3);
    	TextView txtTicker4 = (TextView)findViewById(R.id.txtTickerWin4);
    	TextView txtTicker5 = (TextView)findViewById(R.id.txtTickerWin5);
    	
    	TextView txtValue1 = (TextView)findViewById(R.id.txtValueWin1);
    	TextView txtValue2 = (TextView)findViewById(R.id.txtValueWin2);
    	TextView txtValue3 = (TextView)findViewById(R.id.txtValueWin3);
    	TextView txtValue4 = (TextView)findViewById(R.id.txtValueWin4);
    	TextView txtValue5 = (TextView)findViewById(R.id.txtValueWin5);
    	
    	try {
            txtTicker1.setText(stockList.get(0).getTicker());
            txtTicker2.setText(stockList.get(1).getTicker());
            txtTicker3.setText(stockList.get(2).getTicker());
            txtTicker4.setText(stockList.get(3).getTicker());
            txtTicker5.setText(stockList.get(4).getTicker());
    		
            txtValue1.setText(stockList.get(0).getDailyPercentChange());
            txtValue2.setText(stockList.get(1).getDailyPercentChange());
            txtValue3.setText(stockList.get(2).getDailyPercentChange());
            txtValue4.setText(stockList.get(3).getDailyPercentChange());
            txtValue5.setText(stockList.get(4).getDailyPercentChange());
        } catch (Exception ex) {
            Toast.makeText(this,"The number of stock list is smaller than 5!",Toast.LENGTH_SHORT).show();
        }
    }
    
    private void bindingDailyLosers() {
        TextView txtTicker1 = (TextView)findViewById(R.id.txtTickerLose1);
    	TextView txtTicker2 = (TextView)findViewById(R.id.txtTickerLose2);
    	TextView txtTicker3 = (TextView)findViewById(R.id.txtTickerLose3);
    	TextView txtTicker4 = (TextView)findViewById(R.id.txtTickerLose4);
    	TextView txtTicker5 = (TextView)findViewById(R.id.txtTickerLose5);
    	
    	TextView txtValue1 = (TextView)findViewById(R.id.txtValueLose1);
    	TextView txtValue2 = (TextView)findViewById(R.id.txtValueLose2);
    	TextView txtValue3 = (TextView)findViewById(R.id.txtValueLose3);
    	TextView txtValue4 = (TextView)findViewById(R.id.txtValueLose4);
    	TextView txtValue5 = (TextView)findViewById(R.id.txtValueLose5);
    	
    	try {
            txtTicker1.setText(stockList.get(stockList.size() - 5).getTicker());
            txtTicker2.setText(stockList.get(stockList.size() - 4).getTicker());
            txtTicker3.setText(stockList.get(stockList.size() - 3).getTicker());
            txtTicker4.setText(stockList.get(stockList.size() - 2).getTicker());
            txtTicker5.setText(stockList.get(stockList.size() - 1).getTicker());
    		
            txtValue1.setText(stockList.get(stockList.size() - 5).getDailyPercentChange());
            txtValue2.setText(stockList.get(stockList.size() - 4).getDailyPercentChange());
            txtValue3.setText(stockList.get(stockList.size() - 3).getDailyPercentChange());
            txtValue4.setText(stockList.get(stockList.size() - 2).getDailyPercentChange());
            txtValue5.setText(stockList.get(stockList.size() - 1).getDailyPercentChange());
        } catch (Exception ex) {
            Toast.makeText(this,"The number of stock list is smaller than 5!",Toast.LENGTH_SHORT).show();
    	}
    }

    private Stock searchStock(String input) {
        Stock stock = searchStockByTicker(input);
        
        if (stock.getTicker() == "") {
            stock = searchStockByName(input);
        }
        return stock;
    }
    
    private Stock searchStockByTicker(String input) {
        input = input.toLowerCase();
    	String str = "";
    	for (int i = 0; i < stockList.size(); i++) {
            str = stockList.get(i).getTicker().toLowerCase();
            if (str.equals(input)) {
                return stockList.get(i);
            }
        }
        return new Stock(0,"","");
    }
    
    private Stock searchStockByName(String input) {
        input = input.toLowerCase();
    	String str = "";
    	for (int i = 0; i < stockList.size(); i++) {
            str = stockList.get(i).getName().toLowerCase();
            //if (str.contains(input))
            if (str.equals(input)) {
                return stockList.get(i);
            }
        }
        return new Stock(0, "", "");
    }
    
    private boolean checkInput(String input) {
        if (input == null || input == "") {
            Toast.makeText(this,"Input is empty!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            input = input.replace(" ", ""); 
            if (input.length() <= 0) {
                Toast.makeText(this,"Input is empty!",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}
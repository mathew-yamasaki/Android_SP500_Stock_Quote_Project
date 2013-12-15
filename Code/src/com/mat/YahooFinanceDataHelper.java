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
 * Description: This class makes three separate URL calls to Yahoo! Finance to
 * receive stock data.  The String input stream is parsed to the required data 
 * type.
 ******************************************************************************/
package com.mat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class YahooFinanceDataHelper {
    
    // master S&P 500 stock String
    /*private static final String SP500_LIST = "MMM+ACE+AES+AFL+GAS+T+ABT+ANF+ACN+ADBE+AMD+"
            + "AET+A+APD+ARG+AKAM+AA+ALXN+ATI+AGN+ALL+ALTR+MO+AMZN+AEE+AEP+AXP+AIG+AMT+"
            + "AMP+ABC+AMGN+APH+APC+ADI+AON+APA+AIV+APOL+AAPL+AMAT+ADM+AIZ+AN+AZO+ADSK+"
            + "ADP+AVB+AVY+AVP+BBT+BMC+BHI+BLL+BAC+BCR+BAX+BEAM+BDX+BBBY+BMS+BRK/B+BBY+"
            + "BIG+BIIB+BLK+HRB+BA+BWA+BXP+BSX+BMY+BRCM+BF/B+CA+CBG+CBS+CF+CHRW+CMS+CNX+"
            + "CSX+CVS+CVC+COG+CAM+CPB+COF+CAH+CFN+KMX+CCL+CAT+CELG+CNP+CTL+CERN+CHK+CVX+"
            + "CME+CMG+CB+CI+CINF+CTAS+CSCO+C+CTXS+CLF+CLX+COH+KO+CCE+CTSH+CL+CMCSA+CMA+CSC+"
            + "CAG+COP+ED+STZ+CBE+GLW+COST+CVH+COV+CCI+CMI+DTV+DTE+DVA+DHR+DRI+DF+DE+DELL+"
            + "DNR+XRAY+DVN+DO+DFS+DISCA+DLTR+D+RRD+DOV+DOW+DPS+DUK+DNB+ETFC+DD+EMC+EOG+EQT+"
            + "EMN+ETN+ECL+EIX+EW+EA+EMR+ESV+ETR+EFX+EQR+EL+EXC+EXPE+EXPD+ESRX+XOM+FFIV+"
            + "FLIR+FMC+FTI+FDO+FAST+FDX+FII+FIS+FITB+FHN+FSLR+FE+FISV+FLS+FLR+F+FRX+FOSL+"
            + "BEN+FCX+FTR+GME+GCI+GPS+GD+GE+GIS+GPC+GNW+GILD+GS+GT+GOOG+GWW+HCP+HAL+HOG+"
            + "HAR+HRS+HIG+HAS+HCN+HNZ+HP+HSY+HES+HPQ+HD+HON+HRL+DHI+HSP+HST+HCBK+HUM+HBAN+"
            + "ITW+IR+TEG+INTC+ICE+IPG+IBM+IFF+IGT+IP+INTU+ISRG+IVZ+IRM+JDSU+JPM+JBL+JEC+"
            + "JNJ+JCI+JOY+JNPR+KLAC+K+KEY+KMB+KIM+KMI+KSS+KRFT+KR+LLL+LSI+LH+LRCX+LM+LEG+"
            + "LEN+LUK+LIFE+LLY+LTD+LNC+LLTC+LMT+L+LO+LOW+LYB+MTB+M+MRO+MPC+MAR+MMC+MAS+MA+"
            + "MAT+MKC+MCD+MHP+MCK+MJN+MWV+MDT+MRK+MET+PCS+MCHP+MU+MSFT+MOLX+TAP+MDLZ+MON+"
            + "MNST+MCO+MS+MOS+MSI+MUR+MYL+NKE+NRG+NYX+NBR+NDAQ+NOV+NTAP+NFLX+NWL+NFX+NEM+"
            + "NWSA+NEE+NI+NE+NBL+JWN+NSC+NU+NTRS+NOC+NUE+NVDA+ORLY+OKE+OXY+OMC+ORCL+OI+"
            + "PCAR+PETM+PCG+PNC+PPG+PPL+PLL+PH+PDCO+PAYX+BTU+JCP+PNR+PBCT+POM+PEP+PKI+PRGO+"
            + "PFE+PM+PSX+PNW+PXD+PBI+PCL+PX+PCP+PCLN+PFG+PLD+PG+PGR+PRU+PEG+PSA+PHM+QEP+"
            + "QCOM+PWR+DGX+RL+RRC+RTN+RHT+RF+RSG+RAI+RHI+ROK+COL+ROP+ROST+RDC+R+SAI+SCG+"
            + "SLM+SWY+CRM+SNDK+SLB+SCHW+SNI+STX+SEE+SRE+SHW+SIAL+SPG+SJM+SNA+SO+LUV+SWN+SE+"
            + "S+STJ+SWK+SPLS+SBUX+HOT+STT+SRCL+SYK+STI+SYMC+SYY+TROW+TEL+TE+TJX+TGT+THC+TDC+"
            + "TER+TSO+TXN+TXT+ADT+BK+WMB+TMO+TIF+TWC+TWX+TIE+TMK+TSS+TRV+TRIP+TYC+TSN+USB+"
            + "UNP+UPS+X+UTX+UNH+UNM+URBN+VFC+VLO+VAR+VTR+VRSN+VZ+VIAB+V+VNO+VMC+WPX+WMT+WAG+"
            + "DIS+WPO+WM+WAT+WPI+WLP+WFC+WDC+WU+WY+WHR+WFM+WIN+WEC+WYN+WYNN+XL+XEL+XRX+XLNX+"
            + "XYL+YHOO+YUM+ZMH+ZION+EBAY";*/
    
    private static final String FIRST_SP500_LIST = "MMM+ACE+AES+AFL+GAS+T+ABT+ANF+ACN+ADBE+"
            + "AET+A+APD+ARG+AKAM+AA+ALXN+ATI+AGN+ALL+ALTR+MO+AMZN+AEE+AEP+AXP+AIG+AMT+AMP+ABC+"
            + "AMD+AMGN+APH+APC+ADI+AON+APA+AIV+APOL+AAPL+AMAT+ADM+AIZ+AN+AZO+ADSK+ADP+AVB+AVY+AVP+"
            + "BBT+BMC+BHI+BLL+BAC+BCR+BAX+BEAM+BDX+BBBY+ED+STZ+CBE+GLW+COST+CVH+COV+CCI+CMI+DTV+"
            + "BSX+BMY+BRCM+BF/B+CA+CBG+CBS+CF+CHRW+CMS+CNX+CSX+CVS+CVC+COG+CAM+CPB+COF+CAH+CFN+"
            + "KMX+CCL+CAT+CELG+CNP+CTL+CERN+CHK+CVX+CME+CMG+CB+CI+CINF+CTAS+CSCO+C+CTXS+CLF+CLX+"
            + "COH+KO+CCE+CTSH+CL+CMCSA+CMA+CSC+CAG+COP+BMS+BRK/B+BBY+BIG+BIIB+BLK+HRB+BA+BWA+BXP+"
            + "DTE+DVA+DHR+DRI+DF+DE+DELLDNR+XRAY+DVN+DO+DFS+DISCA+DLTR+D+RRD+DOV+DOW+DPS+DUK+"
            + "DNB+ETFC+DD+EMC+EOG+EQT+EMN+ETN+ECL+EIX+EW+EA+EMR+ESV+ETR+EFX+EQR+EL+EXC+EXPE+"
            + "EXPD+ESRX+XOM+FFIV+FLIR+FMC+FTI+FDO+FAST+FDX+FII+FIS+FITB+FHN+FSLR+FE+FISV+FLS+FLR+F+"
            + "FRX+FOSL+BEN+FCX+FTR+GME+GCI+GPS+GD+GE";
    
    private static final String SECOND_SP500_LIST = "GIS+GPC+GNW+GILD+GS+GT+GOOG+GWW+HCP+HAL+"
            + "HOG+HAR+HRS+HIG+HAS+HCN+HNZ+HP+HSY+HES+HPQ+HD+HON+HRL+DHI+HSP+HST+HCBK+HUM+HBAN+"
            + "ITW+IR+TEG+INTC+ICE+IPG+IBM+IFF+IGT+IP+INTU+ISRG+IVZ+IRM+JDSU+JPM+JBL+JEC+JNJ+JCI+"
            + "JOY+JNPR+KLAC+K+KEY+KMB+KIM+KMI+KSS+KRFT+KR+LLL+LSI+LH+LRCX+LM+LEG+LEN+LUK+LIFE+"
            + "LLY+LTD+LNC+LLTC+LMT+L+LO+LOW+LYB+MTB+M+MRO+MPC+MAR+MMC+MAS+MA+MAT+MKC+MCD+"
            + "MHP+MCK+MJN+MWV+MDT+MRK+MET+PCS+MCHP+MU+MSFT+MOLX+TAP+MDLZ+MON+MNST+MCO+MS+MOS+MSI+"
            + "MUR+MYL+NKE+NRG+NYX+NBR+NDAQ+NOV+NTAP+NFLX+NWL+NFX+NEM+NWSA+NEE+NI+NE+NBL+JWN+NSC+"
            + "NU+NTRS+NOC+NUE+NVDA+ORLY+OKE+OXY+OMC+ORCL+OI+PCAR+PETM+PCG+PNC+PPG+PPL+PLL+PH+PDCO+"
            + "PAYX+BTU+JCP+PNR+PBCT+POM+PEP+PKI+PRGO+PFE+PM+PSX+PNW+PXD+PBI+PCL+PX+PCP+PCLN+PFG+"
            + "PLD+PG+PGR+PRU+PEG+PSA+PHM+QEP+QCOM+PWR+DGX+RL+RRC+RTN+RHT+RF+RSG+RAI+RHI+ROK+"
            + "COL+ROP+ROST+RDC+R+SAI+SCG+SLM+SWY+CRM";
    
    private static final String THIRD_SP500_LIST = "SNDK+SLB+SCHW+SNI+STX+SEE+SRE+SHW+SIAL+SPG+"
            + "SJM+SNA+SO+LUV+SWN+SE+S+STJ+SWK+SPLS+SBUX+HOT+STT+SRCL+SYK+STI+SYMC+SYY+TROW+TEL+"
            + "TE+TJX+TGT+THC+TDC+TER+TSO+TXN+TXT+ADT+BK+WMB+TMO+TIF+TWC+TWX+TIE+TMK+TSS+TRV+"
            + "TRIP+TYC+TSN+USB+UNP+UPS+X+UTX+UNH+UNM+URBN+VFC+VLO+VAR+VTR+VRSN+VZ+VIAB+V+VNO+"
            + "VMC+WPX+WMT+WAG+DIS+WPO+WM+WAT+WPI+WLP+WFC+WDC+WU+WY+WHR+WFM+WIN+WEC+WYN+WYNN+"
            + "XL+XEL+XRX+XLNX+XYL+YHOO+YUM+ZMH+ZION+EBAY";

    
    private static final String URL_TAGS = "sl1c1p2kjhgrvm3n";   
    
    private static final String FIRST_QUOTE_URL = "http://finance.yahoo.com/d/quotes.csv?s=" + FIRST_SP500_LIST + "&f=" + URL_TAGS;
    private static final String SECOND_QUOTE_URL = "http://finance.yahoo.com/d/quotes.csv?s=" + SECOND_SP500_LIST + "&f=" + URL_TAGS;
    private static final String THIRD_QUOTE_URL = "http://finance.yahoo.com/d/quotes.csv?s=" + THIRD_SP500_LIST + "&f=" + URL_TAGS;
    
    static String ticker = "";
    static double closingPrice = 0.0;
    static double dollarChange = 0.0;
    static String percentChange = "";
    static double dailyHigh = 0.0;
    static double dailyLow = 0.0;
    static double week52High = 0.0;
    static double week52Low = 0.0;
    static double peRatio = 0.0;
    static int volume = 0;
    static double movingAverage50Day = 0.0;
    static String name = "";
    
    public static ArrayList<Stock> stockList;
    /*   
    public static List<Stock> parseResponse() {
     
    	BufferedReader br = null;
    	URL yahooFinance;
    	URLConnection yc;
        Stock s = new Stock();
        stockList = new ArrayList<Stock>();
        
            try 
            {
                yahooFinance = new URL(FIRST_QUOTE_URL);
                yc = yahooFinance.openConnection();
                 
                String currentLine;
                br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((currentLine = br.readLine()) != null) {
                   
                    	s = parseLineToStock(currentLine);
                        stockList.add(s);                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
            
        return stockList;
    } // end parseResponse
    * 
    */
    
    public static List<Stock> parseRespone500() {
        
        BufferedReader br = null;
    	URL yahooFinance;
    	URLConnection yc;
        Stock s = new Stock();
        stockList = new ArrayList<Stock>();
        
            try {
                yahooFinance = new URL(FIRST_QUOTE_URL);
                yc = yahooFinance.openConnection();
                 
                String currentLine;
                br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((currentLine = br.readLine()) != null) {
                   
                    	s = parseLineToStock(currentLine);
                        stockList.add(s);                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
            
            try {
                yahooFinance = new URL(SECOND_QUOTE_URL);
                yc = yahooFinance.openConnection();
                 
                String currentLine;
                br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((currentLine = br.readLine()) != null) {
                   
                    	s = parseLineToStock(currentLine);
                        stockList.add(s);                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            try {
                yahooFinance = new URL(THIRD_QUOTE_URL);
                yc = yahooFinance.openConnection();
                 
                String currentLine;
                br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((currentLine = br.readLine()) != null) {
                   
                    	s = parseLineToStock(currentLine);
                        stockList.add(s);                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return stockList;
    }
    
    private static Stock parseLineToStock(String line) {
    	
        String[] stock = line.split(",", 12);
        ticker = stock[0].replaceAll("\"", "");
        
        if (Pattern.matches("N/A", stock[1]))
            closingPrice = 0;
        else
            closingPrice = Double.parseDouble(stock[1]);
            
        if (Pattern.matches("N/A", stock[2]))               
            dollarChange = 0;
        else
            dollarChange = Double.parseDouble(stock[2]);    
            
        percentChange = stock[3].replaceAll("\"", "");
            
        if (Pattern.matches("N/A", stock[4]))               
            dailyHigh = 0;
        else
            dailyHigh = Double.parseDouble(stock[4]);
            
        if (Pattern.matches("N/A", stock[5]))               
            dailyLow = 0;
        else
            dailyLow = Double.parseDouble(stock[5]);
            
        if (Pattern.matches("N/A", stock[6]))               
            week52High = 0;
        else
            week52High = Double.parseDouble(stock[6]);
            
        if (Pattern.matches("N/A", stock[7]))               
            week52Low = 0;
        else
            week52Low = Double.parseDouble(stock[7]);
            
        if (Pattern.matches("N/A", stock[8]))
            peRatio = 0;
        else
            peRatio = Double.parseDouble(stock[8]);

        if (Pattern.matches("N/A", stock[9]))
            volume = 0;
        else
            volume = Integer.parseInt(stock[9]);
            
        if (Pattern.matches("N/A", stock[10]))
            movingAverage50Day = 0;
        else
            movingAverage50Day = Double.parseDouble(stock[10]);
            
        name = stock[11].replaceAll("\"", "").trim();
                            
        Stock s = new Stock(ticker, closingPrice, dollarChange, percentChange, dailyHigh, dailyLow,
                week52High, week52Low, peRatio, volume, movingAverage50Day, name);
            
        return s;
    }
}

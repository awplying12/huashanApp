package com.example.utils.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import android.provider.Settings;

public class ReportData {
	
	private static ReportData mReportData;
    private static Context mContext;
    
    
    private double[] mCurLatLng = new double[4];
    private double mCurSpeed;
	
	private int mBatLevel;
	private String mADDR;
	private String mTime;

	private boolean mGpsState;

    public static synchronized ReportData getInstance(Context context){
        if(mReportData == null){
            synchronized (ReportData.class) {
                if(mReportData == null){
                	mReportData = new ReportData();
                	ReportData.mContext = context;
                }
            }
        }
        return mReportData;
    }   
    
   public void setBatLev(int batlev){
    	this.mBatLevel = batlev;
    }
    
 public int getBatLev(){
    	return this.mBatLevel;
    }
    
    public void setCurLatLng(double[] latlng){
    	this.mCurLatLng = latlng;
    }
    
    public double[] getCurLatLng(){
    	return this.mCurLatLng;
    }
    
    public void setCurSpeed(double speed){
    	this.mCurSpeed = speed;
    }
    
    public double getCurSpeed(){
    	return this.mCurSpeed;
    }

    public void setCurADDR(String addr){
    	this.mADDR = addr;
    }
    
    public String getCurADDR(){
    	return this.mADDR;
    }

    public void setCurTime(String time){
    	this.mTime = time;
    }
    
    public String getCurTime(){
    	return this.mTime;
    }

	public void setGpsState(boolean state){
		this.mGpsState = state;
	}

	public boolean getGpsLocationState(){
		return this.mGpsState;
	}

	public boolean getGpsState(){
		LocationManager locationManager=(LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		ContentResolver resolver = mContext.getContentResolver();
        	boolean open = Settings.Secure.isLocationProviderEnabled(resolver, LocationManager.GPS_PROVIDER);
        	return open;
	}

	
}

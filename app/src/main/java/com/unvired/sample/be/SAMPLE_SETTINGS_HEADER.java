package com.unvired.sample.be;

import com.unvired.database.DBException;
import com.unvired.model.DataStructure;

/*
This class represents Business Entity for Settings table
*/	
public class SAMPLE_SETTINGS_HEADER extends DataStructure {
	
	public static final String TABLE_NAME = "SAMPLE_SETTINGS_HEADER";
	
	// Key name
	public static final String KEY_NAME = "KEY_NAME";

	// Key value
	public static final String KEY_VALUE = "KEY_VALUE";


	public SAMPLE_SETTINGS_HEADER() throws DBException {
		super(TABLE_NAME, true);
	}

	
	public void setKEY_NAME(String value) {
		setField(KEY_NAME, value);
	} 
		
	public String getKEY_NAME() { 
		return (String)getField(KEY_NAME); 
	}

	public void setKEY_VALUE(String value) {
		setField(KEY_VALUE, value);
	} 
		
	public String getKEY_VALUE() { 
		return (String)getField(KEY_VALUE); 
	}

}

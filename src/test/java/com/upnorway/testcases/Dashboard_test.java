package com.upnorway.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.upnorway.utilities.TestUtil;
import com.upnorway.base.TestBase;
import com.upnorway.pages.Dashboard;

public class Dashboard_test extends TestBase {
	
	
	Dashboard db= new Dashboard();
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void changeName_happyPath(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		db.goToMyProfile();
		db.enterFirstName(data.get("fname"));
		db.enterLastName(data.get("lname"));
		db.saveProfile();
	}
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	public void profileChangeValidations(Hashtable<String, String> data) {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		db.goToMyProfile();
		db.enterFirstName(data.get("fname"));
		db.enterLastName(data.get("lname"));
		db.saveProfile();
	}
	
	
	
	

}

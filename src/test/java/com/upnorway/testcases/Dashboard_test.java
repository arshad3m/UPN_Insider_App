package com.upnorway.testcases;

import org.testng.annotations.Test;

import com.upnorway.pages.Dashboard;

public class Dashboard_test {
	
	
	Dashboard db= new Dashboard();
	
	
	@Test
	public void changeName_happyPath() {
	
		db.goToProfileMenu();
		db.enterFirstName("Arshad");
		db.enterLastName("Mohamed");
		db.saveProfile();
	}
	
	
	@Test
	public void profileChangeValidations() {
		
		db.goToProfileMenu();
		db.enterFirstName("");
		db.enterLastName("");
		db.saveProfile();
	}
	
	
	
	

}

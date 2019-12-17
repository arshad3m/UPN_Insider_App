package com.upnorway.pages;

import com.upnorway.base.TestBase;

public class Dashboard extends TestBase {

	public void goToProfileMenu() {
		click("Menu_XPATH");

	}

	public void goToMyProfile() {
		goToProfileMenu();
		click("profile_XPATH");
	}

	public void goToSettings() {
		goToProfileMenu();
		click("settings_XPATH");
	}
	
	public void enterFirstName(String name) {
		type("firstName_XPATH",name);
	}
	
	public void enterLastName(String name) {
		type("lastName_XPATH",name);
	}
	
	public void saveProfile() {
		click("save_XPATH");

	}
	

	public void logOut() {

	}

}

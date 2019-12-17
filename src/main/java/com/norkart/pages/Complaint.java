package com.norkart.pages;

import com.norkart.base.TestBase;

public class Complaint extends TestBase {

	
	
	public void goToComplaint() {
		
		click("main_manu_XPATH");
		click("Eiendomsskatt_XPATH");
		click("Fritak_XPATH");
		click("Klagehåndtering_XPATH");
		
	}
	
	
	public void createNewComplaint() {
		
		goToComplaint();
		click("Opprett_XPATH");
		
	}
	
	
	
	
}

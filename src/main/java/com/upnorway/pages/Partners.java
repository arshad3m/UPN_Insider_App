package com.upnorway.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;

public class Partners extends TestBase{
	
	public void uploadAvatar(String locator, String path) throws InterruptedException {
		
		upload(locator,path);
	}
	
	
	public void dragNdropAvatar() {
		
	}
	
	public void enterBrandName(String text) {
		type("brand_XPATH", text);
	}
	
	public void enterCompanyName(String text) {
		type("company_XPATH", text);

	}
	
	public void enterWebsite(String text) {
		type("website_XPATH", text);

	}
	
	
	public void selectContactType(String locator, String value) {
		select(locator, value);
	}
	
	
	public void enterContactPerson_admin(String text) {
		type("contactPerson_A_XPATH", text);

	}
	
	
	public void enterEmail(String text) {
		type("partnerEmail_XPATH", text);

	}
	
	
	public void enterPhone(String text) {
		type("partnerPhone_XPATH", text);

	}
	
	
	public void enterContactPerson_booking(String text) {
		type("contactPerson_B_XPATH", text);

	}
	
	public void enterEmailBooking(String text) {
		type("emailBooking_XPATH", text);

	}
	
	
	public void enterPhoneBooking(String text) {
		type("phoneBooking_XPATH", text);

	}

	public void enterAddress(String text) {
		type("address_XPATH", text);

	}
	
	
	public void enterPostalCode(String text) {
		type("postalCode_XPATH", text);

	}
	
	
	public void enterPostCity(String text) {
		type("postCity_XPATH", text);

	}
	
	
	public void enterCountry(String text) {
		type("Country_XPATH", text);

	}
	
	
	public void enterDescription(String text) {
		type("Description_XPATH", text);

	}
	
	public void SaveNewPartner() {
		click("savePartner_XPATH");
	}
	
	
	public String getToastMessageText() {
		String toastmessage=getTextAttribute("toast_XPATH");
		return toastmessage;
	}
	
	public String getFirstCardText() {
		
		return getTextAttribute("firstcard_XPATH");
	}
	
	
	public int getCardNumbersInPartnerList() {
		return driver.findElements(By.xpath(OR.getProperty("cardList_XPATH"))).size();
	}
	
	public void verifySaveButtonIsDisabled() {
		
		boolean condition= driver.findElement(By.xpath(OR.getProperty("savePartner_XPATH"))).isEnabled();
		assertTrue(!condition);
		test.log(LogStatus.INFO, "Verified Saved button is disabled");

	}
	
	public void verifySaveButtonIsEnabled() {
		boolean condition=driver.findElement(By.xpath(OR.getProperty("savePartner_XPATH"))).isEnabled();
		assertTrue(condition);
		test.log(LogStatus.INFO, "Verified Saved button is enabled");

	}
	
}

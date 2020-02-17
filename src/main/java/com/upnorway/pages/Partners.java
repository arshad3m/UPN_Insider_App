package com.upnorway.pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;

public class Partners extends TestBase{
	
	public static String companyName ="";
	public static String address="";
	public static String phone="";
	public static String name="";
	String postalcode="";
	String country="";
	String email="";
	String brandName="";
	
	public static String account_CompanyName="";
	public static String account_Address="";
	public static String account_phone="";
	public static String account_name="";

	
	
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
	
	public void UpdatePartner() {
		click("update_XPATH");
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
	
	public void verifyFieldValidationMessage(String locator, String message) throws IOException {
		String xpath=OR.getProperty(locator)+"/../div/p";
		String text= driver.findElement(By.xpath(xpath)).getAttribute("innerText");
		verifyEquals(message, text);
	}
	
	
	public void readPartnerCardInfo(int position) {
		
		String xpath="("+OR.getProperty("cardList_XPATH")+")["+position+"]";
		
		 companyName =driver.findElement(By.xpath(xpath)).getAttribute("innerText");;
		 address=driver.findElement(By.xpath(xpath+"/../div[2]")).getAttribute("innerText");
		 phone=driver.findElement(By.xpath(xpath+"/../div[3]")).getAttribute("innerText");
		 name=driver.findElement(By.xpath(xpath+"/../div[4]")).getAttribute("innerText");
		 email="";
		 postalcode="";
		 country="";
		 brandName="";
				
	}
	
	
	public void readPartnerAccountInfo() {
		
		account_CompanyName=driver.findElement(By.xpath("//label[text()='Company Name']/../div")).getAttribute("innerText");
		account_Address=driver.findElement(By.xpath("//label[text()='Address']/../div")).getAttribute("innerText");
		account_phone=driver.findElement(By.xpath("//label[text()='Phone']/../div")).getAttribute("innerText");
		account_name=driver.findElement(By.xpath("//label[text()='Name']/../div")).getAttribute("innerText");

	}
	
	
	public String getValueOfInputField(String xpath) {
		
		test.log(LogStatus.INFO, "Reading value of input field" + xpath);
		return driver.findElement(By.xpath(OR.getProperty(xpath))).getAttribute("value");
	}
	
	
	
}

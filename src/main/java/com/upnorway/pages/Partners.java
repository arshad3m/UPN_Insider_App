package com.upnorway.pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;

public class Partners extends TestBase {

	public static String companyName = "";
	public static String address = "";
	public static String phone = "";
	public static String name = "";
	String postalcode = "";
	String country = "";
	String email = "";
	String brandName = "";

	public static String account_CompanyName = "";
	public static String account_Address = "";
	public static String account_phone = "";
	public static String account_name = "";
	
	Account account = new Account();

	public void uploadAvatar(String locator, String path) throws InterruptedException {

		upload(locator, path);
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
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty("toast_XPATH")))));
		String toastmessage = getTextAttribute("toast_XPATH");
		return toastmessage;
	}

	public String getFirstCardText() {

		return getTextAttribute("firstcard_XPATH");
	}

	public int getCardNumbersInPartnerList() {
		return driver.findElements(By.xpath(OR.getProperty("cardList_XPATH"))).size();
	}

	public void verifySaveButtonIsDisabled() {

		boolean condition = driver.findElement(By.xpath(OR.getProperty("savePartner_XPATH"))).isEnabled();
		assertTrue(!condition);
		test.log(LogStatus.INFO, "Verified Saved button is disabled");

	}

	public void verifySaveButtonIsEnabled() {
		boolean condition = driver.findElement(By.xpath(OR.getProperty("savePartner_XPATH"))).isEnabled();
		assertTrue(condition);
		test.log(LogStatus.INFO, "Verified Saved button is enabled");

	}

	public void verifyFieldValidationMessage(String locator, String message) throws IOException {
		String xpath = OR.getProperty(locator) + "/../div/p";
		String text = driver.findElement(By.xpath(xpath)).getAttribute("innerText");
		verifyEquals(message, text);
	}

	public void readPartnerCardInfo(int position) {

		String xpath = "(" + OR.getProperty("cardList_XPATH") + ")[" + position + "]";

		companyName = driver.findElement(By.xpath(xpath)).getAttribute("innerText");
		;
		address = driver.findElement(By.xpath(xpath + "/../div[2]")).getAttribute("innerText");
		phone = driver.findElement(By.xpath(xpath + "/../div[3]")).getAttribute("innerText");
		name = driver.findElement(By.xpath(xpath + "/../div[4]")).getAttribute("innerText");
		email = "";
		postalcode = "";
		country = "";
		brandName = "";
		
		

	}

	public Account readPartnerAccountInfo() {

		
		account.setCompany_name(driver.findElement(By.xpath("//label[text()='Company Name']/../div"))
				.getAttribute("innerText"));
		account.setAddress(driver.findElement(By.xpath("//label[text()='Address']/../div")).getAttribute("innerText"));
		account.setBooking_name(driver.findElement(By.xpath("//label[text()='Name']/../div")).getAttribute("innerText"));
		account.setBooking_phone(driver.findElement(By.xpath("//label[text()='Phone']/../div")).getAttribute("innerText"));

		return account;
	}

	public String getValueOfInputField(String xpath) {

		test.log(LogStatus.INFO, "Reading value of input field" + xpath);
		return driver.findElement(By.xpath(OR.getProperty(xpath))).getAttribute("value");
	}

	public void selectBookingType(String type) {
		select("partnerBookingType_XPATH", type);

	}

	public void enterAgreementWebsite(String text) {
		type("agreementWebsite_XPATH", text);
	}

	public void enterAgreementUsername(String text) {
		type("agreementUsername_XPATH", text);

	}

	public void enterAgreementPassword(String text) {
		type("agreementPassword_XPATH", text);

	}

	public void enterGeneralNotes(String text) {
		type("agreementGeneralNotes_XPATH", text);

	}

	public String getLastUploadedAgreementName() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("uploaded_agreement_XPATH")));
		String fileNameXpath = "(" + OR.getProperty("uploaded_agreement_XPATH") + ")[" + (list.size()) + "]";
		String filename = driver.findElement(By.xpath(fileNameXpath)).getAttribute("innerText");
		test.log(LogStatus.INFO, "Reading the value of the: " + list.size() + " th file uploaded");
		// System.out.println(filename);
		return filename;
	}

	public void closeActivity() throws InterruptedException {
		click("activityClose_XPATH");
		Thread.sleep(5000);
	}

	/**
	 * This method will read all the fields of an activity card and will hold in an
	 * Activity class instance
	 * 
	 * @param cardNumber
	 *            - the position of the activity card.
	 * @return - returns an Activity object with values for Activity name, Activity
	 *         type, Price, Items and Description
	 * @author ArshadM
	 * @throws InterruptedException 
	 */
	public Activity getActivityCardInfo(int cardNumber) throws InterruptedException {
		Thread.sleep(5000);
		Activity activity = new Activity();

		// String xpath="("+OR.getProperty("activities_XPATH")+")["+cardNumber+"]";
		activity.setActivityName(
				driver.findElement(By.xpath("(//label[text()=' Activity name ']/../div)[" + cardNumber + "]"))
						.getAttribute("innerText"));
		activity.setActivtyType(
				driver.findElement(By.xpath("(//label[text()=' Activity Type ']/../div)[" + cardNumber + "]"))
						.getAttribute("innerText"));
		activity.setPrice(driver.findElement(By.xpath("(//label[text()=' Price ']/../div)[" + cardNumber + "]"))
				.getAttribute("innerText"));
		activity.setItems(
				Integer.parseInt(driver.findElement(By.xpath("(//label[text()=' Items ']/../div)[" + cardNumber + "]"))
						.getAttribute("innerText")));
		activity.setDescription(
				driver.findElement(By.xpath("(//label[text()=' Description ']/../div)[" + cardNumber + "]"))
						.getAttribute("innerText"));
		return activity;

	}

	/**
	 * Gets the count of activities a partner account has
	 * 
	 * @return returns the number in int
	 */
	public int getNumberOfActivites() {
		return (driver.findElements(By.xpath(OR.getProperty("activities_XPATH"))).size()) / 2;
	}

	public void filterActivity() {

	}

	/**
	 * Edit an activity
	 * @param activityNumber - which activity of the partner
	 * @author ArshadM
	 */
	public void editActivity(int activityNumber) {
		String xpath = "(//label[text()=' Activity name ']/../div)[" + activityNumber + "]";
		// click(xpath);
		driver.findElement(By.xpath(xpath)).click();
		;
		test.log(LogStatus.INFO, "clicking on" + xpath);
	}

	/**
	 * Delete an activity
	 * 
	 * @param confirm
	 *            - specify true or false to confirm the delete
	 * @author ArshadM
	 * @throws InterruptedException
	 */
	public void deleteActivity(boolean confirm) throws InterruptedException {
		click("delete_activity_XPATH");
		if (confirm == true) {
			click("confirm_delete_XPATH");
		} else
			click("confirm_not_delete_XPATH");
		Thread.sleep(4000);

	}

	/**
	 * Selects a given option from the drop down
	 * Based on the selected option it asserts that the correct set of fields get populated in the form.
	 * If an expected field has not got populated, an exception is thrown with the field name
	 * @param option - the option to select from the drop down
	 * @author ArshadM
	 */
	public void selectPriceOptions(String option) {
		select("priceOption_XPATH", option);
		
		if (option.equalsIgnoreCase("FIXED")) {
			//verify relevent labels exist
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'UPN price incl. VAT')]")).size()>0,"Expected field 'UPN price incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'VAT rate in %')]")).size()>0,"Expected field 'VAT rate in %' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Market price incl. VAT')]")).size()>0,"Expected field 'Market price incl.Vat' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Commission in %')]")).size()>0,"Expected field 'Commission in %' was not found");

		}

		if (option.equalsIgnoreCase("PERSON")) {
			//verify relevent labels exist
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'UPN price per adult incl. VAT')]")).size()>0,"Expected field 'UPN price per adult incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'VAT rate in %')]")).size()>0,"Expected field 'VAT rate in %' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Market price per adult incl. VAT')]")).size()>0,"Expected field 'Market price per adult incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Commission in %')]")).size()>0,"Expected field 'Commission in %' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'UPN price per child incl. VAT')]")).size()>0,"Expected field 'UPN price per child incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Market price per child incl. VAT')]")).size()>0,"Expected field 'Market price per child incl. VAT' was not found");
			
		}
		
		if (option .equalsIgnoreCase("ROOM")) {
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'UPN price per room incl. VAT')]")).size()>0,"Expected field 'UPN price per room incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'VAT rate in %')]")).size()>0,"Expected field 'VAT rate in %' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Market price per room incl. VAT')]")).size()>0,"Expected field 'Market price per room incl. VAT' was not found");
			assertTrue(driver.findElements(By.xpath("//button[text()=' Add New Item ']/ancestor::div[@class='panel card upInfoAccordionGroupEnd']//label[contains(text(),'Commission in %')]")).size()>0,"Expected field 'Commission in %' was not found");

		}

	}
	
	
	/**
	 * Edits already added items in an activity template.
	 * Verifies that hte added items (rows) equals the counter shown adhjoining the 'Items' label.
	 * @param itemNumber - The position of the item that needs to be edited
	 * @author ArshadM
	 * @throws Exception 
	 */
	public void editAddedItems(int itemNumber) throws Exception {

		int numberOfItemsAdded = driver.findElements(By.xpath(OR.getProperty("added_items_XPATH"))).size();
		test.log(LogStatus.INFO, "Found "+numberOfItemsAdded+" added activities in the Activity template");

		if (numberOfItemsAdded > 0 && itemNumber <=numberOfItemsAdded) {

			String xpath="("+OR.getProperty("added_items_XPATH")+")["+itemNumber+"]";
			driver.findElement(By.xpath(xpath)).click();
			test.log(LogStatus.INFO, "clicking on "+xpath);
			
			int counter = Integer.parseInt(driver.findElement(By.xpath("//span[@class='counter']")).getAttribute("innerHTML"));
			verifyEquals(counter, numberOfItemsAdded);
			
			
			
		} else {
			
			throw new Exception("No items available in the Activity template or the specified position of item is incorrect");
		}

	}
	
	
	/**
	 * Deletes the selected item.
	 * Verifies correct toastmessage.
	 * Verifies deleted item is no longer available in the list
	 * @param confirm - True or false to repsond to the delete confirmation prompt
	 * @author ArshadM
	 * @throws IOException 
	 */
	public void deleteItem(boolean confirm) throws IOException {
		
		int number_of_items_before_delete = driver.findElements(By.xpath(OR.getProperty("added_items_XPATH"))).size();
		driver.findElement(By.xpath("//accordion-group[@class='item-accordion-group panel ng-star-inserted panel-open']//a[text()='Delete item']")).click();
		test.log(LogStatus.INFO, "Clicking on 'Delete item' button");

		if(confirm==true) {
			driver.findElement(By.xpath("//button[@type='button'][text()=' Delete ']")).click();;
			/*WebElement element = driver.findElement(By.xpath("//button[@type='button'][text()=' Delete ']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);*/
			test.log(LogStatus.INFO,"Deleting the item");
			verifyEqualsIgnoreCase("Item deleted successfully!", getToastMessageText());
			int number_of_items_after_delete = driver.findElements(By.xpath(OR.getProperty("added_items_XPATH"))).size();
			assertTrue(number_of_items_before_delete-number_of_items_after_delete==1);
			test.log(LogStatus.INFO, "Asserted the deleted item is no longer available in the list");
		}
		else {
			driver.findElement(By.xpath("//button[@type='button'][text()=' Cancel ']")).click();;
			test.log(LogStatus.INFO,"Deleting not confirmed");		}
		
	}
	
	public void saveUpdatedItem() throws IOException {
		
		driver.findElement(By.xpath("//accordion-group[@class='item-accordion-group panel ng-star-inserted panel-open']//button[@type='submit']")).click();
		test.log(LogStatus.INFO, "Saving the changes to the item");
		
		verifyEqualsIgnoreCase("Item updated successfully!", getToastMessageText());
		
	}
	
	public void addOtherItemsOfSamePartner(int itemNumber) throws Exception {
		int numberOfOtherItemsAvailable = driver.findElements(By.xpath("//label[@class='up-checkbox-wrapper mr-3']/span[@class='checkbox']")).size();
		test.log(LogStatus.INFO, "Found "+numberOfOtherItemsAvailable+" other items from this Partner in the Activity template");

	//	int counter_before_adding_item = Integer.parseInt(driver.findElement(By.xpath("//span[@class='counter']")).getAttribute("innerHTML"));
	//	int counter_after_adding_item=0;

		if (numberOfOtherItemsAvailable > 0 && itemNumber <=numberOfOtherItemsAvailable) {

			String xpath="("+"//label[@class='up-checkbox-wrapper mr-3']/span[@class='checkbox'])"+"["+itemNumber+"]";
			driver.findElement(By.xpath(xpath)).click();
			test.log(LogStatus.INFO, "clicking on "+xpath);
			
		//	int counter = Integer.parseInt(driver.findElement(By.xpath("//span[@class='counter']")).getAttribute("innerHTML"));
//			driver.findElement(By.xpath("//div[@class='panel-collapse collapse in show']//button[@type='submit']")).click();
//			counter_after_adding_item=Integer.parseInt(driver.findElement(By.xpath("//span[@class='counter']")).getAttribute("innerHTML"));;
	//		test.log(LogStatus.INFO, "Adding the other item");
			
			
//			assertTrue(counter_after_adding_item-counter_before_adding_item==1);
			
			
			
		} else {
			
			throw new Exception("No other items are available in the Activity template or the specified position of the other item is incorrect");
		}
	}

}

package com.upnorway.testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;
import com.upnorway.pages.Account;
import com.upnorway.pages.Activity;
import com.upnorway.pages.Partners;
import com.upnorway.utilities.TestUtil;

public class Partner_test extends TestBase {

	Partners partner = new Partners();

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void add_New_Partner(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("addNewPartner_XPATH");

		partner.uploadAvatar("avatar_XPATH", data.get("avatar"));
		partner.enterBrandName(data.get("brand"));
		partner.enterCompanyName(data.get("company"));
		partner.enterWebsite(data.get("website"));
		partner.select("contactType_XPATH", data.get("contacttype"));
		partner.enterContactPerson_admin(data.get("contactpersonadmin"));
		partner.enterEmail(data.get("email"));
		partner.enterPhone(data.get("phone"));
		partner.enterContactPerson_booking(data.get("contactpersonbooking"));
		partner.enterPhoneBooking(data.get("phonebooking"));
		partner.enterEmailBooking(data.get("emailbooking"));
		partner.enterAddress(data.get("address"));
		partner.enterPostalCode(data.get("postalcode"));
		partner.enterPostCity(data.get("postcity"));
		partner.enterCountry(data.get("country"));
		partner.enterDescription(data.get("description"));
		partner.SaveNewPartner();
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());

		// Verify the added partner is shown first in the partner list
		verifyEqualsIgnoreCase(data.get("company"), partner.getFirstCardText());

	}

	@Test(enabled = true,priority=2)
	public void verify_Partner_List() throws IOException {

		click("partnersMenu_XPATH");

		// verify number of cards shown in one page
		verifyEquals(4, partner.getCardNumbersInPartnerList());
		;
	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void Add_Partner_Field_Validation(Hashtable<String, String> data) throws InterruptedException, IOException {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("addNewPartner_XPATH");

		partner.uploadAvatar("avatar_XPATH", data.get("avatar"));
		partner.verifySaveButtonIsDisabled();
		partner.enterBrandName(data.get("brand"));
		partner.verifySaveButtonIsDisabled();

		partner.enterCompanyName(data.get("company"));
		partner.verifySaveButtonIsDisabled();

		partner.enterWebsite(data.get("website"));
		partner.verifySaveButtonIsDisabled();

		partner.select("contactType_XPATH", data.get("contacttype"));
		partner.verifySaveButtonIsDisabled();

		partner.enterContactPerson_admin(data.get("contactpersonadmin"));
		partner.verifySaveButtonIsDisabled();

		partner.enterEmail(data.get("email"));
		partner.verifySaveButtonIsDisabled();

		partner.enterPhone(data.get("phone"));
		partner.verifySaveButtonIsDisabled();

		partner.enterContactPerson_booking(data.get("contactpersonbooking"));
		partner.verifySaveButtonIsDisabled();

		partner.enterPhoneBooking(data.get("phonebooking"));
		partner.verifySaveButtonIsDisabled();

		// clear mandatory data to verify validation message pops up
		partner.enterPhoneBooking(data.get("wrongphone"));
		partner.verifyFieldValidationMessage("phoneBooking_XPATH", data.get("phonevalidation"));

		// re-enter correct phone number
		partner.enterPhoneBooking(data.get("phonebooking"));

		partner.enterEmailBooking(data.get("emailbooking"));
		partner.verifySaveButtonIsDisabled();

		partner.enterAddress(data.get("address"));
		partner.verifySaveButtonIsDisabled();

		partner.enterPostalCode(data.get("postalcode"));
		partner.verifySaveButtonIsDisabled();

		partner.enterPostCity(data.get("postcity"));
		partner.verifySaveButtonIsDisabled();

		partner.enterCountry(data.get("country"));
		partner.enterDescription(data.get("description"));
		partner.verifySaveButtonIsEnabled();

		// clear mandatory data to verify save button gets disabled and validation
		// message pops up
		partner.enterEmailBooking(data.get("wrongemail"));
		partner.verifySaveButtonIsDisabled();
		partner.verifyFieldValidationMessage("emailBooking_XPATH", data.get("emailvalidation"));

		// re-enter the mandatory data
		partner.enterEmailBooking(data.get("emailbooking"));

		partner.SaveNewPartner();
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());

		// Verify the added partner is shown first in the partner list
		verifyEqualsIgnoreCase(data.get("company"), partner.getFirstCardText());
	}

	@Test(enabled = true, priority = 4)
	public void verify_Company_Info() throws IOException {

		click("partnersMenu_XPATH");
		partner.readPartnerCardInfo(1);

		click("firstcard_XPATH");
		Account acc=partner.readPartnerAccountInfo();

		verifyEqualsIgnoreCase(acc.getCompany_name(), Partners.companyName.trim());
		verifyEqualsIgnoreCase(acc.getBooking_name(), Partners.name.trim());
		verifyEqualsIgnoreCase(acc.getAddress(), Partners.address.trim());
		verifyEqualsIgnoreCase(acc.getBooking_phone(), Partners.phone.trim());

	}

	@Test(enabled = true, priority = 5)
	public void verify_PartnerEdit() throws IOException {

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");

		// Read partner account information of the first card
		Account acc=partner.readPartnerAccountInfo();
		click("editPartnerCompanyInfo_XPATH");
		
		

		// Verify the read info before clicking on the edit and after clicking are equal
		verifyEqualsIgnoreCase(acc.getCompany_name(), partner.getValueOfInputField("companyName_XPATH"));
		verifyEqualsIgnoreCase(acc.getAddress(), partner.getValueOfInputField("address_XPATH"));
		// verifyEqualsIgnoreCase(Partners.account_name,
		// partner.getValueOfInputField("contactPerson_A_XPATH"));
		verifyEqualsIgnoreCase(acc.getBooking_phone(), partner.getValueOfInputField("partnerPhone_XPATH"));

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 6)
	public void update_Partner_Company_Info(Hashtable<String, String> data) throws IOException, InterruptedException {
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		click("editPartnerCompanyInfo_XPATH");

		partner.enterBrandName(data.get("brand"));
		partner.enterEmail(data.get("email"));
		partner.enterPhone(data.get("phone"));
		partner.UpdatePartner();
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 7)
	public void update_partner_agreeement_info(Hashtable<String, String> data) {
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		click("editPartnerAgreementInfo_XPATH");

		// partner.selectBookingType(data.get("bookingtype"));
		partner.enterAgreementWebsite(data.get("website"));
		partner.enterAgreementUsername(data.get("username"));
		partner.enterAgreementPassword(data.get("password"));
		partner.enterGeneralNotes(data.get("generalnotes"));
		partner.UpdatePartner();

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 8)
	public void upload_partner_agreement(Hashtable<String, String> data) throws InterruptedException, IOException {
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		click("upload_agreement_XPATH");
		upload("upload_file_XPATH", data.get("file"));
		type("upload_document_name_XPATH", data.get("documentname"));
		click("upload_button_XPATH");
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());
		partner.getLastUploadedAgreementName();
		verifyContains(partner.getLastUploadedAgreementName(), ".pdf");
		verifyContains(partner.getLastUploadedAgreementName(), data.get("documentname"));

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 9)
	public void Add_new_activity(Hashtable<String, String> data) throws InterruptedException, IOException {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		click("AddNewActivity_XPATH");
		select("activityType_XPATH", data.get("activityType"));
		type("activityTitle_XPATH", data.get("title"));
		upload("activityImage_XPATH", data.get("activityimage"));
		type("activityDesc_XPATH", data.get("desc"));
		click("activitySave_XPATH");
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());
		partner.closeActivity();

		Activity activity = partner.getActivityCardInfo(partner.getNumberOfActivites());
		

		verifyEqualsIgnoreCase(data.get("title"), activity.getActivityName());
		verifyEqualsIgnoreCase(data.get("activityType"), activity.getActivtyType());
		verifyEqualsIgnoreCase(data.get("desc"), activity.getDescription());

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 10)
	public void update_activity_template(Hashtable<String, String> data) throws InterruptedException, IOException {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		partner.editActivity(1);
		partner.enterDescription(data.get("desc"));
		partner.UpdatePartner();
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());

		Activity activity = partner.getActivityCardInfo(1);
		verifyEqualsIgnoreCase(data.get("desc"), activity.getDescription());

	}

	@Test(enabled = true, priority = 11)
	public void delete_activity_template() throws InterruptedException {
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		int activity_count_before_delete = partner.getNumberOfActivites();
		partner.editActivity(1);
		partner.deleteActivity(true);
		int activity_count_after_delete = partner.getNumberOfActivites();

		// confirm one activity is removed from the partner account
		assertTrue(activity_count_before_delete - activity_count_after_delete == 1);

	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 12)
	public void add_new_item(Hashtable<String, String> data) throws IOException {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		partner.editActivity(1);
		click("items_XPATH");
		click("add_item_XPATH");
		
		partner.selectPriceOptions(data.get("priceoption"));

		select("vat_rate_XPATH",data.get("op1_vat_rate"));
		
		if (data.get("priceoption").equalsIgnoreCase("FIXED")) {
			type("marketPrice_XPATH", data.get("op1_marketprice"));
			type("UPN_price_XPATH", data.get("op1_upn_price"));
			type("item_desc_XPATH",data.get("desc"));
			type("itemName_XPATH", data.get("itemname"));
		}
		
		if (data.get("priceoption").equalsIgnoreCase("PERSON")) {
			type("marketPrice_XPATH", data.get("op2_marketpriceadult"));
			type("marketPriceChild_XPATH", data.get("op2_marketpricechild"));
			type("item_desc_XPATH",data.get("desc"));
			type("itemName_XPATH", data.get("itemname"));
		}
		
		if (data.get("priceoption").equalsIgnoreCase("ROOM")) {
			type("marketPrice_XPATH", data.get("op3_priceroom"));
			type("UPN_price_XPATH", data.get("op1_upn_price"));
			type("item_desc_XPATH",data.get("desc"));
			type("itemName_XPATH", data.get("itemname"));
		}
		
		click("item_add_XPATH");
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());

	}
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 13)
	public void edit_item_template(Hashtable<String, String> data) throws Exception {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		Thread.sleep(3000);
		partner.editActivity(1);
		click("items_XPATH");
		partner.editAddedItems(1);
		partner.saveUpdatedItem();
		

	}
	
	
	@Test(enabled=false, priority=14)
	public void delete_item_template() throws Exception {
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		partner.editActivity(1);
		click("items_XPATH");
		partner.editAddedItems(1);
		partner.deleteItem(true);
	}
	
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 15)
	public void add_another_item(Hashtable<String, String> data) throws Exception {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		
		//Get the count of items before adding another item
		Activity activity = partner.getActivityCardInfo(1);
		int before_count= activity.getItems();
		
		partner.editActivity(1);
		click("items_XPATH");
		partner.addOtherItemsOfSamePartner(1);
		click("add_other_item_XPATH");
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());
		partner.closeActivity();
		
		activity=partner.getActivityCardInfo(1);
		int after_count=activity.getItems();
		
		//verify Items count increased after adding the other item
		assertTrue(after_count-before_count==1);
	}
	

}

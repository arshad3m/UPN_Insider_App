package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;
import com.upnorway.pages.Partners;
import com.upnorway.utilities.TestUtil;

public class Partner_test extends TestBase{
	
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
		partner.select("contactType_XPATH",data.get("contacttype"));
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
		
		//Verify the added partner is shown first in the partner list
		verifyEqualsIgnoreCase(data.get("company"), partner.getFirstCardText());
		
	}
	
	
	@Test(enabled=true)
	public void verify_Partner_List() throws IOException {
		
		click("partnersMenu_XPATH");
		
		//verify number of cards shown in one page
		verifyEquals(4, partner.getCardNumbersInPartnerList());;
	}
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
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

		partner.select("contactType_XPATH",data.get("contacttype"));
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
		
		//clear mandatory data to verify  validation message pops up
		partner.enterPhoneBooking(data.get("wrongphone"));
		partner.verifyFieldValidationMessage("phoneBooking_XPATH", data.get("phonevalidation"));
		
		//re-enter correct phone number
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
		
		//clear mandatory data to verify save button gets disabled and validation message pops up
		partner.enterEmailBooking(data.get("wrongemail"));
		partner.verifySaveButtonIsDisabled();
		partner.verifyFieldValidationMessage("emailBooking_XPATH", data.get("emailvalidation"));

		
		//re-enter the mandatory data
		partner.enterEmailBooking(data.get("emailbooking"));

		
		partner.SaveNewPartner();
		verifyEqualsIgnoreCase(data.get("toastmessage"), partner.getToastMessageText());
		
		//Verify the added partner is shown first in the partner list
		verifyEqualsIgnoreCase(data.get("company"), partner.getFirstCardText());
	}
	
	@Test(enabled=true)
	public void verify_Company_Info() throws IOException {
		
		click("partnersMenu_XPATH");
		partner.readPartnerCardInfo(1);

		click("firstcard_XPATH");
		partner.readPartnerAccountInfo();
		
		verifyEqualsIgnoreCase(Partners.companyName.trim(), Partners.account_CompanyName);
		verifyEqualsIgnoreCase(Partners.name.trim(), Partners.account_name);
		verifyEqualsIgnoreCase(Partners.address.trim(), Partners.account_Address);
		verifyEqualsIgnoreCase(Partners.phone.trim(), Partners.account_phone);

		
		
	}
	
	@Test(enabled=true)
	public void verify_PartnerEdit() throws IOException {
		
		click("partnersMenu_XPATH");
		click("firstcard_XPATH");
		
		//Read partner account information of the first card
		partner.readPartnerAccountInfo();
		click("editPartnerCompanyInfo_XPATH");
	
		//Verify the read info before clicking on the edit and after clicking are equal
		verifyEqualsIgnoreCase(Partners.account_CompanyName, partner.getValueOfInputField("companyName_XPATH"));
		verifyEqualsIgnoreCase(Partners.account_Address, partner.getValueOfInputField("address_XPATH"));
//		verifyEqualsIgnoreCase(Partners.account_name, partner.getValueOfInputField("contactPerson_A_XPATH"));
		verifyEqualsIgnoreCase(Partners.account_phone, partner.getValueOfInputField("partnerPhone_XPATH"));

		
		
	}
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
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

}

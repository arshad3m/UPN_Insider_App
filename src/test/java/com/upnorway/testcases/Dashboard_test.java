package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.upnorway.utilities.TestUtil;
import com.upnorway.base.TestBase;
import com.upnorway.pages.Dashboard;

public class Dashboard_test extends TestBase {

	Dashboard db = new Dashboard();

	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void changeName_happyPath(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		db.loginEmail(data.get("email"));
		db.loginPassword(data.get("password"));
		db.clickEnter();
		db.goToMyProfile();
		db.enterFirstName(data.get("fname")); // b.enterFirstName("Gihan")
		db.enterLastName(data.get("lname"));
		db.saveProfile();
	}

	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	public void profileChangeValidations(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		db.loginEmail(data.get("email"));
		db.loginPassword(data.get("password"));
		db.clickEnter();
		db.goToMyProfile();
		db.enterFirstName(data.get("fname"));
		db.enterLastName(data.get("lname"));
		db.saveProfile();
	}

	@Test(enabled = false, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void changePasswordHappyPath(Hashtable<String, String> data) throws IOException, Exception {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		db.loginEmail(data.get("email"));
		db.loginPassword(data.get("password"));
		db.clickEnter();
		db.goToMyProfile();
		db.ChangePassword();
		db.enterCurrentPwd(data.get("currentpwd"));
		db.enterNewPwd(data.get("newpwd"));
		db.enterConfirmPwd(data.get("confirmpwd"));
		db.changePwd();

		Thread.sleep(3000);

		String validationMessage = driver.findElement(By.xpath(OR.getProperty("cpSuccessmessage_XPATH"))).getText();
		;

		verifyEquals(validationMessage, data.get("message"));

	}

	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
	public void checkChangePasswordValidation(Hashtable<String, String> data) throws IOException, Exception {

		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		db.loginEmail(data.get("email"));
		db.loginPassword(data.get("password"));
		db.clickEnter();
		db.goToMyProfile();
		db.ChangePassword();
		db.enterCurrentPwd(data.get("currentpwd"));
		db.enterNewPwd(data.get("newpwd"));
		db.enterConfirmPwd(data.get("confirmpwd"));
		db.changePwd();

		Thread.sleep(3000);

		String validationMessage = driver.findElement(By.xpath(OR.getProperty("cpValidationMessage_XPATH"))).getText();;

		verifyEquals(validationMessage, data.get("message"));

	}

}

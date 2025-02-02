package com.tutorialsninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base{
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserandOpenApplication("chrome");
		
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
	        driver.quit();  // Close the session after all tests are done
	    }
	}
	@Test (priority=1)
	public void verifyRegisterUsingMandatoryFields() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Joseph");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Test123");
		driver.findElement(By.id("input-confirm")).sendKeys("Test123");
		driver.findElement(By.xpath("(//input[@name='newsletter'] )[2]")).click();
		driver.findElement(By.xpath("//input[@name='agree'] ")).click();
		driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
		
		String actualSuccessfulRegisterText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedText = "Your Account Has Been Created!";
		AssertJUnit.assertEquals(actualSuccessfulRegisterText, expectedText );
	
			
	}
	@Test (priority=2)
	public void verifyRegisterUsingExiistingEmail() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Joseph");
		driver.findElement(By.id("input-email")).sendKeys("nisha222@yopmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Test123");
		driver.findElement(By.id("input-confirm")).sendKeys("Test123");
		driver.findElement(By.xpath("(//input[@name='newsletter'] )[2]")).click();
		driver.findElement(By.xpath("//input[@name='agree'] ")).click();
		driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: E-Mail Address is already registered!";
		AssertJUnit.assertEquals(actualMessage, expectedMessage, "No error message after registering using existing email id.");
	
			
	}
		
	@Test (priority=3)
	public void verifyValidationofMandatoryFields() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
		
		
		String actualfirstnamevalidation = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String expectedfirstnamevalidaton = "First Name must be between 1 and 32 characters!";
		AssertJUnit.assertEquals(actualfirstnamevalidation, expectedfirstnamevalidaton, "No Validation on First Name Field.");
		
		
		String actuallastnamevalidation = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		String expectedLastnamevalidaton = "Last Name must be between 1 and 32 characters!";
		AssertJUnit.assertEquals(actuallastnamevalidation, expectedLastnamevalidaton, "No Validation on Last Name Field.");
		
		String actualemailvalidation = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		String expectedemailvalidaton = "E-Mail Address does not appear to be valid!";
		AssertJUnit.assertEquals(actualemailvalidation, expectedemailvalidaton, "No Validation on Email Field.");
		
		String actualtelephonevalidation = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		String expectedtelephonevalidaton = "Telephone must be between 3 and 32 characters!";
		AssertJUnit.assertEquals(actualtelephonevalidation, expectedtelephonevalidaton, "No Validation on Phone Number Field.");
		
		
		String actualPasswordValidation = driver.findElement(By.xpath("//input[@id='input-password']//following-sibling::div")).getText();
		String expectedPasswordvalidation = "Password must be between 4 and 20 characters!";
		AssertJUnit.assertEquals(actualPasswordValidation, expectedPasswordvalidation, "No Validation on password field.");
		
		String actualPrivacyWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyWarning = "Warning: You must agree to the Privacy Policy!";
		AssertJUnit.assertEquals(actualPrivacyWarning, expectedPrivacyWarning, "No Warning message for privacy policy");
	
		
	};
}

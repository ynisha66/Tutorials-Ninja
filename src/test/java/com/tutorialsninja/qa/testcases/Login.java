package com.tutorialsninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Login extends Base {
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserandOpenApplication("chrome");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
	        driver.quit();  // Close the session after all tests are done
	    }
	}
	
	
	@Test (priority=1)
	public void verifyLoginwithValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("couwukittazei-5083@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	@Test (priority=2)
		public void verifyLoginwithInvalidCredentials() {
			driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimestamp());
			driver.findElement(By.id("input-password")).sendKeys("12345678");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			String actualElement = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
			String expectedElement = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualElement.contains(expectedElement), "Expected warning message is not displayed");
			
		
	}
	

}

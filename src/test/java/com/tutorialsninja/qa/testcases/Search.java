package com.tutorialsninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Search extends Base {
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		 driver = initializeBrowserandOpenApplication("chrome");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test (priority = 1)
	public void verifySearchbyValidName() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Valid Result not displayed");
	}
	@Test (priority = 2)
	public void VerifySearchNonExistingProduct() {
		
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		String actualMessage = driver.findElement(By.xpath("//div[@id = 'content']/h2/following-sibling::p")).getText();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(actualMessage, expectedMessage, "No message displayed while searching ");
	}
	@Test (priority = 3)
	public void verifySearchWithoutProductName() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		String actualMessage = driver.findElement(By.xpath("//div[@id = 'content']/h2/following-sibling::p")).getText();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(actualMessage, expectedMessage, "No message displayed while searching without product name.");
		
		
	}

}

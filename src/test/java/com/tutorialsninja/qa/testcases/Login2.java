package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Login2 extends Base{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		String browserName="chrome";
		
		if(browserName.equals("chrome")) {
//		ChromeOptions ops = new ChromeOptions();
//		ops.addArguments("--remote-allow-origins=*");
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, ops);
//        ops.merge(capabilities);
		driver=new ChromeDriver();
		}
		
		else if(browserName.equals("firefox")) {
//			FirefoxOptions ops=new FirefoxOptions();
//			ops.addArguments("--remote-allow-origins=*");
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//	        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ops);
//	        ops.merge(capabilities);
			driver=new FirefoxDriver();
		}
		
		else if(browserName.equals("edge")) {
//			EdgeOptions ops=new EdgeOptions();
//			ops.addArguments("--remote-allow-origins=*");
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//	        capabilities.setCapability(EdgeOptions.CAPABILITY, ops);
//	        ops.merge(capabilities);
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("http://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
    
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("amit@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("amit");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("amit" + generateTimeStamp() + "@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("amit12");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".alert-dismissible")).getText(), "Warning: No match for E-Mail Address and/or Password.");
		
		
	}
	
	public String generateTimeStamp() {
		
		Date date=new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
}

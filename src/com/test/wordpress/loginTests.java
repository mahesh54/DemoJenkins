package com.test.wordpress;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class loginTests {
	String seleniumUrl = null;
	WebDriver driver = null;
	String seleniumBrowserType = null;

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		
		try{
			String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
			int seleniumPort = Integer.parseInt(context.getCurrentXmlTest().getParameter("selenium.port"));
			String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
			seleniumBrowserType = context.getCurrentXmlTest().getParameter("selenium.browsertype");
			seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
			String ieEXE = context.getCurrentXmlTest().getParameter("selenium.webdriver.ie.driver");
			String chromeEXE = context.getCurrentXmlTest().getParameter("selenium.webdriver.chrome.driver");
			
			String hubUrl = "http://" + seleniumHost + ":" + seleniumPort + "/wd/hub";
			System.out.println("Hub URL is: "+hubUrl);
			
			DesiredCapabilities capability = null;
			
			System.out.println("Chrome driver: "+chromeEXE+",Chrome : "+seleniumBrowserType.indexOf("chrome"));
			System.out.println("IE driver: "+ieEXE+",IE: "+seleniumBrowserType.indexOf("internetexplorer"));
			
			
			if(seleniumBrowserType.indexOf("firefox") != -1){
				System.out.println("I'm in Firefox");
				capability = DesiredCapabilities.firefox();
				capability.setBrowserName(seleniumBrowser);
		        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		        driver = new RemoteWebDriver(new URL(hubUrl), capability);
			}else if(seleniumBrowserType.indexOf("internetexplorer") != -1){
				System.out.println("I'm in IE");
				System.setProperty("webdriver.ie.driver", ieEXE);
				capability = DesiredCapabilities.internetExplorer();
				capability.setBrowserName(seleniumBrowser);
		        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
	        	capability.setVersion("9.0");
				driver = new RemoteWebDriver(new URL(hubUrl), capability);
			}else if(seleniumBrowserType.indexOf("chrome") != -1){
				System.out.println("I'm in Chrome");
				System.setProperty("webdriver.chrome.driver", chromeEXE);
				capability = DesiredCapabilities.chrome();
				capability.setBrowserName(seleniumBrowser);
		        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
	        	driver = new RemoteWebDriver(new URL(hubUrl), capability);
			}
	        

		}catch (Exception exp){
			System.out.println(exp);
		}
	}
	
	@Test
	public void loginGoogle(){
		
		driver.get("http://google.com");
		System.out.println("Title: "+driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle(), "Page has not loaded properly.");
		driver.navigate().refresh();
	}
	
	@Test
	public void loginYahoo(){
		
		driver.get("http://yahoo.com");
		System.out.println("Title: "+driver.getTitle());
		
		Assert.assertEquals("Yahoo! India", driver.getTitle(), "Page has not loaded properly.");
		driver.navigate().refresh();
	}
	
	@Test
	public void loginRediff(){
		
		driver.get("http://rediff.com");
		System.out.println("Title: "+driver.getTitle());
		Assert.assertEquals("Rediff.com - India, Business, Stock, Sports, Cricket, Entertainment, Bollywood, Music, Video and Breaking news, Rediffmail NG, Shopping", driver.getTitle(), "Page has not loaded properly.");
	}
	
	@AfterSuite
	public void closeBrowser(){
		driver.quit();
	}
}

package com.test.wordpress;

import org.testng.annotations.*;
import org.testng.ITestContext;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wp_login_logoutTest {

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
	  
//	public CopyOfwp_login_logoutTest(){}
	
	@Test(description="Launches the WordPress site")
	public void launchSiteTest(){
		System.out.println("driver...."+driver);
        driver.get(seleniumUrl);
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	Boolean bln = d.getTitle().startsWith("WordPress Demo Install");
                return bln;
            }
        });
	}
	  
//	@Test(description="Enters valid login data")
	public void loginAsAdminTest() {
		WebElement userElement = driver.findElement(By.id("user_login"));
		userElement.sendKeys("admin");
		WebElement passElement = driver.findElement(By.id("user_pass"));
		passElement.sendKeys("demo123");
		WebElement submitElement = driver.findElement(By.id("wp-submit"));
		submitElement.click();
        /*(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	Boolean bln = driver.getPageSource().contains("Howdy, admin");
            	System.out.println("Check admin Name: "+bln);
                return bln;
            }
        });*/
     }
	  
//	@Test(description="Logs out")
	public void logoutTest() {
		Actions builder = new Actions(driver);
		if(seleniumBrowserType.indexOf("firefox") != -1 || seleniumBrowserType.indexOf("chrome") != -1
				){
			WebElement tagElement = driver.findElement(By.xpath("//a[contains(text(),'Howdy,')]"));
			builder.moveToElement(tagElement,0,10).build().perform();
			WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'Howdy,')]/following::a[contains(text(),'Log Out')]"));
			menu.click();
		}else if(seleniumBrowserType.indexOf("internetexplorer") != -1){
			WebElement tagElement = driver.findElement(By.xpath("//a[contains(text(),'Howdy,')]"));
			builder.moveToElement(tagElement).build().perform();
			WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'Howdy,')]/following::a[contains(text(),'Log Out')]"));
			menu.click();
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void setupAfterSuite() {
		driver.quit();
	}

}


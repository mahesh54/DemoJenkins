package com.etouch.net;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SampleScript {

	@Test
	public void loginShaadiTest(){
		System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.shaadi.com");
		
		WebElement email= driver.findElement(By.cssSelector("input[id=email]"));
		email.sendKeys("mynameKaru@gmail.com");
		
		WebElement pwd= driver.findElement(By.cssSelector("input[id=password1]"));
		pwd.sendKeys("etouchindia");
		
		
		WebElement profileFor = driver.findElement(By.cssSelector("select[id=postedby]"));
		Select profile= new Select(profileFor);
//		profile.deselectAll();
		profile.selectByVisibleText("Son");
		
		WebElement fname= driver.findElement(By.cssSelector("input[id=first_name]"));
		fname.sendKeys("myname");
		
		WebElement lname= driver.findElement(By.cssSelector("input[id=last_name]"));
		lname.sendKeys("Karu");
		
		WebElement gender= driver.findElement(By.cssSelector("input[id=gender-Male]"));
		gender.click();
		
		WebElement dd = driver.findElement(By.cssSelector("select[id=day]"));
		Select day= new Select(dd);
//		day.deselectAll();
		day.selectByIndex(5);
		
		WebElement mm = driver.findElement(By.cssSelector("select[id=month]"));
		Select month= new Select(mm);
//		month.deselectAll();
		month.selectByValue("04");
		
		WebElement yy = driver.findElement(By.cssSelector("select[id=year]"));
		Select year= new Select(yy);
//		year.deselectAll();
		year.selectByValue("1980");
		
		WebElement community = driver.findElement(By.cssSelector("select[id=community]"));
		java.util.List <WebElement> cOptions = community.findElements(By.tagName("option"));
		for (WebElement option : cOptions) {
			if(option.getText().equals("Christian")){
				option.click();
			}
		}
		
		WebElement mother_tongue = driver.findElement(By.cssSelector("select[id=mother_tongue]"));
		java.util.List <WebElement> mtOptions = mother_tongue.findElements(By.tagName("option"));
		for (WebElement option : mtOptions) {
			if(option.getText().equals("Gujarati")){
				option.click();
			}
		}
		
		WebElement residence = driver.findElement(By.cssSelector("select[id=countryofresidence]"));
		java.util.List <WebElement> rOptions = residence.findElements(By.tagName("option"));
		for (WebElement option : rOptions) {
			if(option.getText().equals("Norway")){
				option.click();
			}
		}
		
		WebElement pPolicy= driver.findElement(By.cssSelector("input[id=confirm_policy]"));
		pPolicy.click();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		pPolicy.click();
		
		WebElement submitBtn= driver.findElement(By.cssSelector("a[id=btnSubmit]"));
		submitBtn.click();
	}
}

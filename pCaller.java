package AugSekenium;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class pCaller {
	
	public static void main(String args[]) throws InterruptedException {
		pBrowserUtil p=new pBrowserUtil();
		WebDriver driver = p.launchBrowser("Chrome");
		p.launchURL("https://ecom.functionizeapp.com/testsites/anaqa/index.php?route=common/home");
		
		
		// Locator Link && Images 
		By links = By.tagName("a");
		By images = By.tagName("img");
		
		By clickAcc=By.linkText("create an account");
		By fName=By.id("firstname");
		By lName=By.id("lastname");
		By email=By.id("email");
		By phNumber = By.id("telephone");
		
		/*Select DropDown for All Value*/
		By countryDropDown= By.id("country_id");
		
		/*Select DropDown for Specific Value*/
		By countrySpecificVal= By.id("country_id");
		
		// dependent dropdown
		By child = By.xpath("//select[@id='zone_id']");
		
		By pwd=By.xpath("//input[@id='password']");
		By pwdConfim= By.xpath("//input[@id='confirm']");
		
		By checkBox= By.xpath("//input[@id='agree']");
		By submit = By.xpath("//input[@id='submit']");
		
		
		// 
		By parentMenuLocator = By.xpath("//nav[@id='mainmenutop']//a[normalize-space(text()) = 'Electronics']");
		By childMenuLocator = By.linkText("Air Conditioner (5)");
		
		
		pElementUtil e=new pElementUtil(driver);
		//List<WebElement> getElementText = e.getElements(links);
		
		int totalLink = e.getElementCount(links);
		System.out.println("Number of Link " + totalLink);
		int totalImage = e.getElementCount(images);
		System.out.println("Number of Images " + totalImage);
		
		e.doClick(clickAcc);
		
		
		e.doSendKeys(fName, "firstName");
		e.doSendKeys(lName, "lastname");
		e.doSendKeys(email, "email2112121121@gmail.com");
		e.doSendKeys(phNumber, "7845787@!");
		
		
		
		ArrayList<String> getAllValue = e.getAllElemenr(countryDropDown);
		System.out.println(getAllValue);
		Thread.sleep(3000);
		
		e.selectDropDownValue(countrySpecificVal, "Afghanistan");
		Thread.sleep(2000);
		e.selectDropDownValue(child, "Badakhshan");
		
		e.doSendKeys(pwd, "here@123");
		e.doSendKeys(pwdConfim, "here@123");
		e.doClick(checkBox);
		
		Thread.sleep(3000);
		e.doClick(submit);
		
		e.parentClassMenu(parentMenuLocator, childMenuLocator);
		e.doClick(childMenuLocator);
	}
}

package AugSekenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class pElementUtil {

	WebDriver driver;
	
	public pElementUtil (WebDriver driver) {
		this.driver=driver;
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/*Find Element then Do Actions*/
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	/*Account Creation*/
	public void doSendKeys(By locator, String value ) {
		getElement(locator).sendKeys(value);
	}
	
	
	//Capture of all Links/Text 
	public List<String> getElementText(By locator) {
		 List<WebElement> List = getElements(locator);
		 List<String> eList= new ArrayList<String>();
		 
		for(WebElement e: List) {
			String captureText=e.getText();
			
			if(captureText.length()!=0)
				eList.add(captureText);
					System.out.println(eList);
		}	return eList;	
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public int getElementCount(By locator) {
		return getElements(locator).size();
	}
	
	//Using the Select Method to Select OR Capture All of the Vales from the Select Drop Down
	public ArrayList<String> getAllElemenr(By locator) {
		Select sAll= new Select(getElement(locator));
		List<WebElement> allText = sAll.getOptions();	
		ArrayList<String> optionTextList = new ArrayList<String>();	

		
		for(WebElement e : allText) {
			String textVal=e.getText();
			optionTextList.add(textVal);
		}return optionTextList;		
	}
	
	//Using  Select Method to Select specific Vales from the Select Drop Down
	public void selectDropDownValue(By locator, String value) {
		Select sValue = new Select(getElement(locator));
		List<WebElement> ddList = sValue.getOptions();
		
		for(WebElement e : ddList) {
			String text=e.getText();
			
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void parentClassMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).perform();
		Thread.sleep(3000);
		
	}
}

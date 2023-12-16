package AugSekenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class pElementUtil {

	static WebDriver driver;
	
	/** @param drive
	 *  At time of declaration driver value is NULL - To Call this getElement() we'll get Null Pointer Exception.  
	 *  Creating parameterized Cons to Perform initialization
	 *     
	 */
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		}
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void deSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	/**
	 * This method overloaded we define below here we are calling method internally (getElement) 
	 * getelement say if you call me you've to pass locator  
	 * sendkeys whatever the value we passing same value pass here...
	 * 
	 */
	
	public WebElement getElement(By locator) {
		 return driver.findElement(locator);	
		 /**
		  * here we're passing locator as Parameter 
		  * driver we define as globally 
		  * then we are finding the element by passing locator .
		  * whatever we find the element it will return to method because it call somewhere else eg - deSendkeys()..  
		  */
	}
	public List<String> getElementText(By locator) {   
		List<WebElement> eList = getElements(locator);   // Custom Method will give list of element and calling getElements internally
		List<String> eleTextList = new ArrayList<String>(); // 
			for(WebElement e : eList) {
				String text = e.getText();
				
					if(text.length()!=0) {
						eleTextList.add(text);
						System.out.println(eleTextList);
				}
			}return eleTextList;
	}
		
	public int getElementCount(By locator) {
		return getElements(locator).size();
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	
	// ***************Select drop Down Utils***************//
	private Select createSelect(By locator) {
		Select select = new Select(getElement(locator));
		return select;
	}
	public void doSelectDropDownByIndex(By locator, int index) {
		createSelect(locator).selectByIndex(index);
	}
	
	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		createSelect(locator).selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		createSelect(locator).selectByValue(value);
	}

	public int getDropDownOptionsCount(By locator) {
		return createSelect(locator).getOptions().size();
	}

	public List<String> getDropDownOptions(By locator) {
		List<WebElement> optionsList = createSelect(locator).getOptions();

		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}

		return optionsTextList;
	}

	public void selectDropDownOption(By locator, String dropDownValue) {

		List<WebElement> optionsList = createSelect(locator).getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				e.click();
				break;
			}
		}
	}

	public void selectDropDownValue(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// *****************Actions utils ***************//

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	public void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();
		Thread.sleep(1000);
		doClick(childMenuLocator);
	}
	
	public void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocaor, By secondChildMenuLocaor,
		By thirdChildMenuLocaor) throws InterruptedException {

		Actions act = new Actions(driver);

		doClick(parentMenuLocator);
		Thread.sleep(1000);

		act.moveToElement(getElement(firstChildMenuLocaor)).build().perform();

		Thread.sleep(1000);

		act.moveToElement(getElement(secondChildMenuLocaor)).build().perform();

		Thread.sleep(1000);

		doClick(thirdChildMenuLocaor);
	}	
	
	// *****************WaitsUtil*****************
	/**
	   * An expectation for checking that an element is present on the DOM of a page. This does not
	   * necessarily mean that the element is visible.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located
	   */
	public WebElement waitPresentOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	  /**
	   * An expectation for checking that an element is present on the DOM of a page and visible.
	   * Visibility means that the element is not only displayed but also has a height and width that is
	   * greater than 0.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and visible
	   */
	
	public WebElement waitVisibilityOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	// Calling Implicitly to wait some specifically element 
	public  void waitDoClicks(By locator, int  timeOut) {
		waitVisibilityOfElement(locator, timeOut);
	}
	// Calling Implicitly
	public void waitDoSendKeys(By locator, String Value, int  timeOut) {
		waitVisibilityOfElement(locator, timeOut).sendKeys(Value);
	}
	// Polling time
	public WebElement waitForWebElement(By locator, int timeout, int intervalTimeOut) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTimeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitPresentOfElement(By locator, int timeout, int intervalTimeOut) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTimeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	// ******************* waitsForNonWebElements *******************
	public String waitForTitleContains(String titleContains, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		 
		try {
		if(wait.until(ExpectedConditions.titleContains(titleContains))) {
			 return driver.getTitle();
		 }}
		catch (TimeoutException e) {
			System.out.println(titleContains + "title Not Present");
			e.printStackTrace();
		}
		return null;
	}
	
	public String waitForURLContains(String URLContains, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		 
		try {
		if(wait.until(ExpectedConditions.urlContains(URLContains))) {
			 return driver.getCurrentUrl();
		 }}
		catch (TimeoutException e) {
			System.out.println(URLContains + "title Not Present");
			e.printStackTrace();
		}
		return null;
	}
	
	//***************WaitsAlerts***************
	public Alert waitForAlert(int timeout) {
		WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
		return waitAlert.until(ExpectedConditions.alertIsPresent());
		
		// Internally alertIsPresent this will check and switch to return driver.switchTo().alert(); 
	}
	public void accecptJSAlert(int timeout) {
		waitForAlert(timeout).accept();
	}
	public void dismissJSAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	public String getTextJSAlert(int timeout) {
		return waitForAlert(timeout).getText();
	}
	public void enterValJsAlert(int timeout, String val) {
		 waitForAlert(timeout).sendKeys(val);
	}
	
	//*************WaitSelectDenpendentDropDown *************
	public WebElement waitVisibilityOfElement1(By locator, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void doSelectByText(By locator, int timeout , String value) {
		Select s1 = new Select(waitVisibilityOfElement(locator, timeout));
		s1.selectByVisibleText(value);
	}
}

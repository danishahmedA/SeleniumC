package AugSekenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class pBrowserUtil {
	private WebDriver driver;
	
	public WebDriver launchBrowser(String browserName) {
		System.out.println(browserName + " Launch");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver =new ChromeDriver();
			break;
		case "edge":
			driver =new EdgeDriver();
			break;
		case "firefox":
			driver =new FireFox();
			break;
		case "safari":
			driver =new Safari();
			break;
			
				
		default:
			System.out.println("Pls Pass correct browser...");
			throw new AutoException("invalid Browser " + browserName);
		}
		return driver; 	
		
	}
	
	public void launchURL(String URL) {
		if(URL==null) {
			System.out.println("URL Is NUll");
			throw new AutoException("null url");
		}if(URL.indexOf("https")==0) {
			driver.get(URL);
		}else {
			throw new AutoException("HTTP Missing");
		}
	}
	
	public String gettitle() {
		String title =driver.getTitle();
		//System.out.println("Page title "+ title);
		return title;
	}
	
	public String getPageURL() {
		String url = driver.getCurrentUrl();
		//System.out.println("Page URL is : " + url);
		return url;
		}
	
	public void browserClose() {
		driver.close();
	}
	
	public void browserQuite() {
		driver.quit();
	}
}

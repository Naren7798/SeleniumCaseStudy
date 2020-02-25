package testmeapp.utility;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

public class Drivers {
	
  public static WebDriver getDriver(String browser) {
	

if (browser.equals("Chrome"))
{
	
	  System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
	  return new ChromeDriver();
}
else
{
	  System.setProperty("webdriver.ie.driver","src\\test\\resources\\IEDriverServer.exe");
	  return new InternetExplorerDriver();
}
	
	
}

}

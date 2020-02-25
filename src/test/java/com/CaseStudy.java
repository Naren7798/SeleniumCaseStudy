package com;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testmeapp.utility.Drivers;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class CaseStudy {
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	 @BeforeTest
	  public void startReportBeforeTest() {
		  driver=Drivers.getDriver("Chrome");
		  driver.get("https://10.232.237.143:443/TestMeApp/fetchcat.htm");
		  driver.manage().timeouts().pageLoadTimeout(1000,TimeUnit.SECONDS);

		  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/"
					+ new SimpleDateFormat("hh-mm-ss-ms-dd-MM-yyyy").format(new Date()) + ".htm");

			// initialize ExtentReports and attach the HtmlReporter
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			// To add system or environment info by using the setSystemInfo method.
			extent.setSystemInfo("host Name", "GFT Selenium");
			extent.setSystemInfo("user", "v.vishnu");

			extent.setSystemInfo("environment", "Selenium");

			// configuration items to change the look and feel
			// add content, manage tests etc
			htmlReporter.config().setDocumentTitle("Report");
			htmlReporter.config().setReportName("Test result");
			htmlReporter.config().setTheme(Theme.DARK);

	}

	
	
  @Test(priority=1)
	  public void testregistration() {
	 
	  driver.findElement(By.id("details-button")).click();
	  driver.findElement(By.id("proceed-link")).click();
	 
	 // driver.findElement(By.xpath("//*[@href='login.htm']")).click();
	 // driver.findElement(By.linkText("SignUp")).click();
	  driver.findElement(By.xpath("//*[@href='RegisterUser.htm']")).click();
	  
	 
	  
	  
	  driver.findElement(By.name("userName")).sendKeys("ffredqfg");
	 
	  driver.findElement(By.name("firstName")).sendKeys("Naren");
		driver.findElement(By.id("lastName")).sendKeys("dran");
		driver.findElement(By.id("password")).sendKeys("Naren1234");
		driver.findElement(By.name("confirmPassword")).sendKeys("Naren1234");
		driver.findElement(By.xpath("//input[@value= 'Male']")).click();
		driver.findElement(By.id("emailAddress")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("1234567894");
		driver.findElement(By.name("dob")).sendKeys("07/07/1998");
		driver.findElement(By.id("address")).sendKeys("coimbatore");
		Select sel=new Select(driver.findElement(By.name("securityQuestion")));
		sel.selectByIndex(1);
		driver.findElement(By.id("answer")).sendKeys("Blue");
		driver.findElement(By.name("Submit")).click();
		
		logger = extent.createTest("registration");
		Assert.assertEquals("Login", driver.getTitle());
	    
		  
	  }
  @Test(priority=2)
  public void login() {
	  logger = extent.createTest("login");
	  
	  driver.findElement(By.name("userName")).sendKeys("ffredqfg");
	  driver.findElement(By.name("password")).sendKeys("Naren1234");
	  driver.findElement(By.xpath("//input[@name='Login']")).click();
	  
	//  WebDriverWait wait=new WebDriverWait(driver,1000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.name("val")));
	
		Assert.assertEquals("Home", driver.getTitle());
	  	  
  }
  
  @Test(priority=3)
  public void cart() {
	 
	  
	  
	  
	  
	  Actions cart=new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  cart.moveToElement( driver.findElement(By.linkText("All Categories"))).perform();
	  cart.moveToElement( driver.findElement(By.linkText("Home Appliances"))).click().perform();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  cart.moveToElement( driver.findElement(By.linkText("Floor"))).click().build().perform();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  cart.moveToElement( driver.findElement(By.linkText("Add to cart"))).click().build().perform();
	 
	  Assert.assertEquals("Search", driver.getTitle());
	  
	 /* driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  driver.findElement(By.linkText("All Categories")).click();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	 driver.findElement(By.linkText("Electronics")).click();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  driver.findElement(By.linkText("Head Phone")).click();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  driver.findElement(By.linkText("Add to cart")).click();
	  */
	  
	  driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	  
	  Assert.assertEquals("View Cart", driver.getTitle());
	  
	  driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
	  
		logger = extent.createTest("Cart");
		Assert.assertEquals("Cart Checkout", driver.getTitle());
  }
  
  @Test(priority=4)
  public void payment() {

	  
		  driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
		  
		  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		 // driver.get("https://10.232.237.143/PaymentGateway/getOrderDetails.htm");
		 // driver.get("https://10.232.237.143/PaymentGateway/loginhere.htm?radio=Andhra%20Bank");
		  
		  driver.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();
		
		  driver.findElement(By.xpath("//a[@href='#' and @id='btn']")).click();
		  
		  
		  driver.findElement(By.name("username")).sendKeys("123456"); 
		  driver.findElement(By.name("password")).sendKeys("Pass@456");
		  driver.findElement(By.xpath("//input[@type='submit'and @value='LOGIN']")).click();  
		  driver.findElement(By.name("transpwd")).sendKeys("Trans@456"); 
		  driver.findElement(By.xpath("//input[@type='submit'and @value='PayNow']")).click();
		   
		  
		  logger = extent.createTest("payment");
			Assert.assertEquals("Order Details", driver.getTitle());

	  
	  
  }
  
  
  @AfterMethod
  public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
			
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + "Test Case Failed", ExtentColor.RED));

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.ORANGE));

		}

		else {

			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));

		}

		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report

	}


  @AfterTest
  public void endReportAfterTest() {
	  extent.flush();
	  
}

}








//WebDriverWait wait = new WebDriverWait(driver,30);
//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
//element.sendKeys("john");

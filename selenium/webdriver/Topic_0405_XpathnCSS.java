package webdriver;


import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_0405_XpathnCSS {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}
	
	
	@Test
	public void TC_01_Empty_Email_Password()
	{
		//Step 1 - Click "My Account" to navigate login page
		driver.findElement(By.className("skip-account")).click();
		
		driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
		//Step 2 - Leave Email and Password blank
		
		//Step 3 - Click Login button
		driver.findElement(By.id("send2")).click();
		//Step 4 - Verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	

    @Test   
    public void TC_02_Invalid_Email()
    {
    	//Step 1 - Click "My Account" to navigate login page
    	driver.findElement(By.className("skip-account")).click();
    	driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
    	
    	//Step 2 - Enter invalid Email and Password
    	driver.findElement(By.cssSelector(".input-box #email")).sendKeys("12121@121212.12121");
    	driver.findElement(By.cssSelector(".input-box #pass")).sendKeys("123456");
    	
    	
    	//Step 3 - Click Login button
    	driver.findElement(By.id("send2")).click();
    	
    	//Step 4 - Verify error message
    	Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    	
    }
    
    
	@Test
	public void TC_03_Password_less_6characters()
	{
		//Step 1 - Click "My Account" to navigate login page
    	driver.findElement(By.className("skip-account")).click();
    	driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
    	
    	//Step 2 - Enter invalid Password
    	driver.findElement(By.cssSelector(".input-box #email")).sendKeys("automation@gmail.com");
    	driver.findElement(By.cssSelector(".input-box #pass")).sendKeys("123");
    	
    	
    	//Step 3 - Click Login button
    	driver.findElement(By.id("send2")).click();
    	
    	//Step 4 - Verify error message
    	Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Incorrect_Email_Password()
	{
		//Step 1 - Click "My Account" to navigate login page
    	driver.findElement(By.className("skip-account")).click();
    	driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
    	
    	//Step 2 - Enter invalid Password
    	driver.findElement(By.cssSelector(".input-box #email")).sendKeys("automation@gmail.com");
    	driver.findElement(By.cssSelector(".input-box #pass")).sendKeys("123123123");
    	
    	
    	//Step 3 - Click Login button
    	driver.findElement(By.id("send2")).click();
    	
    	//Step 4 - Verify error message
    	Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(), "Invalid login or password.");
	}
	public String name;
	public String email;
	String password = "Password";
	public String name()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(20);
		Random random = new Random();
				   
		for (int i = 0; i < 5; i++) {
		char c = chars[random.nextInt(chars.length)];
		sb.append(c);
	    }
	    name = sb.toString();
	    return name;
			   
			   
	}
	public String email()
	{
		Random randomGenerator = new Random();
		   int randomInt = randomGenerator.nextInt(100);
		   email = name + randomInt + "@gmail.com";
		   return email;
	}
	
	@Test
	public void TC_05_Create_New_Account()
	{
		//Step 1 - Click "My Account" to navigate login page
    	driver.findElement(By.className("skip-account")).click();
    	driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
    	name = name();
    	email = email();
    	//Step 2 - Click "Create an account"
    	driver.findElement(By.xpath("//div[@class='buttons-set']//a[contains(@href, 'create')]")).click();
    	
    	//Step 3 - Enter required fields: First Name, Last Name, Email, Password, Confirm Password
    	
    	//FirstName
    	driver.findElement(By.id("firstname")).sendKeys(name);;
    	//LastName
    	driver.findElement(By.id("lastname")).sendKeys(name);;
    	//Email
    	
    	driver.findElement(By.id("email_address")).sendKeys(email);;
    	//Password
    	driver.findElement(By.id("password")).sendKeys(password);
    	//ConfirmPassword
    	driver.findElement(By.id("confirmation")).sendKeys(password);
    	
    	//Step 4 - Click Register button
    	driver.findElement(By.xpath("//button[@type='submit' and @title = 'Register']")).click();
	
    	//Step 5 - Verify the successful message
    	Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "Thank you for registering with Main Website Store.");
    	
    	//Step 6 - Verify the recently created information: First Name/Last Name/ Email
    	driver.findElement(By.xpath("//h3[contains(text(), 'Contact Information')]//following-sibling::a")).click();
    	Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), name);
    	Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), name);
    	Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);
    	
    	//Step 7 - Logout
    	driver.findElement(By.className("skip-account")).click();
    	driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'Log Out')]")).click();
    	
    	//Step 8 - Verify user backs to Home page
    	WebDriverWait wait = new WebDriverWait(driver,5);
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site for')]"))));
    	Assert.assertEquals(driver.getTitle(), "Home page");
	}
	  
	@Test
	public void TC_06_Valid_Email_Password()
	{
		//Step 1 - Click "My Account" to navigate login page
	    driver.findElement(By.className("skip-account")).click();
	    driver.findElement(By.xpath("//div[@id = 'header-account']//a[contains(text(), 'My Account')]")).click();
			
	    //Step 2 - Login with created accounts
	    driver.findElement(By.cssSelector(".input-box #email")).sendKeys(email);
	    driver.findElement(By.cssSelector(".input-box #pass")).sendKeys(password);
	   	
	    //Step 3 - Click Login button
	    driver.findElement(By.id("send2")).click();
	    
	    //Step 4 - Verify the information displayed
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(), 'My Dashboard')]")).getText(), "MY DASHBOARD");
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//p")).getText(), "Hello, " + name + " " + name + "!");
	    driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(name + " " + name);
	    driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(email);
	    
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	

}

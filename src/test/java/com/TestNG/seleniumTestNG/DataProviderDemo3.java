package com.TestNG.seleniumTestNG;
	import java.time.Duration;

	import org.openqa.selenium.*;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.*;
	import org.testng.annotations.*;

	public class DataProviderDemo3 {

	    WebDriver driver;
	    WebDriverWait wait;

	    @DataProvider(name = "loginData")
	    public Object[][] dataProviderFunc() {
	        return new Object[][] {
	                {"samihaM", "2005", "valid"},
	                {"wrongUser", "2005", "invalidUser"},
	                {"samihaM", "wrongPass", "invalidPassword"}
	        };
	    }

	    @BeforeMethod
	    public void setUp() {
	        System.out.println("Start the test");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.get("https://demoblaze.com/");
	    }

	   
	    @Test(dataProvider = "loginData")
	    public void loginTest(String username, String password, String testType) {

	       
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);

	        driver.findElement(By.id("loginpassword")).sendKeys(password);
	        driver.findElement(By.xpath("//button[text()='Log in']")).click();

	        if (testType.equalsIgnoreCase("valid")) {

	            String user = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText();

	            System.out.println("Login Success Text: " + user);

	        } else {

	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	            String msg = alert.getText();
	            System.out.println("Alert Message: " + msg);
	            alert.accept();
	        }

	        System.out.println("Test executed for: " + testType);
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	        System.out.println("End the test");
	    }
	}



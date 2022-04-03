//SCRIPT TO TEST CHECKOUT
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
 

// WebDriverWait
// Multiple Drivers can be called
// Exception handling --> try catch, types of selenium exception
// Driver close
// /Seperate Modules --> Login, product, order, confirmation
// Re-usable methods for oder verification


public class mainpage {


    static String getProduct;
	static String getpricelist;

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bstackdemo.com/");
		// Implicit wait


        Thread.sleep(1000);  //Explicit Wait
		
		// Sign in should be visible
		// Title should be displayed
		// Login steps
		// Product verification
		
		List<WebElement> productlist = driver.findElements(By.xpath("//*[@class='shelf-container']/div/p"));
		List<WebElement> pricelist = driver.findElements(By.xpath("//*[@class='shelf-container']/div/div[3]/div[1]/b"));
		int countproductlist = productlist.size();
		System.out.println("Products size : "+ countproductlist);
		
		int countpricelist = pricelist.size();
		System.out.println("Price size : "+ countpricelist);
		
		for(int i=0;i<=countproductlist;i++) {
			
			getpricelist = pricelist.get(i).getText();
			System.out.println("getpricelist : "+ getpricelist);
			Thread.sleep(1000);
			
			if(productlist.get(i).getText().contains("iPhone 12")) {
				getProduct = productlist.get(i).getText();
				System.out.println("getProduct : "+ getProduct);
				Thread.sleep(1000);
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@class='shelf-container']/div/p[contains(text(),'"+getProduct+"')]//parent::*/div[4]")).click();
				Thread.sleep(1000);
				break;
			}
		}
		
		// Check if Float cart is displayed
		// 

		driver.findElement(By.xpath("//*[contains(text(),'Checkout')]")).click();


		
		Thread.sleep(5000);
		
		//Get the Web Element corresponding to the field UserName (Textfield)
        WebElement user = driver.findElement(By.id("react-select-2-input"));

        //Get the Web Element corresponding to the field Password (Textfield)
        WebElement pass = driver.findElement(By.id("react-select-3-input"));
        
        //Enter UserName and Password
        user.sendKeys("demouser");
        user.sendKeys(Keys.ENTER);
        pass.sendKeys("testingisfun99");
        pass.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//button[@id='login-btn']")).click();
		Thread.sleep(5000);	
		
		// Checkout Page
		String ProductAddedInCart = driver.findElement(By.xpath("//*[@class='product-title optimizedCheckout-contentPrimary']")).getText();
		System.out.println(ProductAddedInCart);
		Thread.sleep(2000);	
		
		String PriceAddedInCart = driver.findElement(By.xpath("//*[@class='product-price optimizedCheckout-contentPrimary']")).getText();
		System.out.println(PriceAddedInCart);
		Thread.sleep(2000);	
		
		
		String CartPrice = PriceAddedInCart.replaceAll("[$,]", "");
		System.out.println(CartPrice);
		
		Assert.assertEquals(getProduct, ProductAddedInCart);
		System.out.println("Product matched");
		
		Assert.assertEquals(getpricelist, CartPrice);
		System.out.println("Product Price match");
			
		Thread.sleep(2000);


		// Code for entering the text
		enterUserDetails(driver);

		
	
		
		Thread.sleep(3000);
		// Check for Confirmation page is visible
		// Verify text "Your Order has been successfully placed.""
		//Verify Donl;oad receipt
		// Verify Order Summary
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping »')]")).click();

		Thread.sleep(2000);
		// Verify user is in product page
		// Verify Orders link is visible

		driver.findElement(By.xpath("//strong[contains(text(),'Orders')]")).click();

		// Verify page title
		// Verify Order details
		Thread.sleep(2000);


        driverClose(driver);

    }
	

	private static void enterUserDetails(WebDriver driver){


		driver.findElement(By.xpath("//input[@id='firstNameInput']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='lastNameInput']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='addressLine1Input']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='provinceInput']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='postCodeInput']")).sendKeys("2166");
		driver.findElement(By.xpath("//button[@id='checkout-shipping-continue']")).click();
	}

    private  static void driverClose(WebDriver driver){
        driver.close();
    }

}
//SCRIPT TO TEST CHECKOUT FOR BSTACKDEMO - KAVYA
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
 

public class mainpage {


    static String getProduct;
	static String getpricelist;

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bstackdemo.com/");
		// Implicit wait - add later


        Thread.sleep(1000);  //Explicit Wait - add later
		
		//I can find elements using these : xpath,class,id ....	
		//GET the complete count of products and price.
        List<WebElement> productlist = driver.findElements(By.className("shelf-item__title"));
		List<WebElement> pricelist = driver.findElements(By.xpath("//*[@class='shelf-container']/div/div[3]/div[1]/b"));
                
		int countproductlist = productlist.size();
		System.out.println("Products size : "+ countproductlist);
		
		int countpricelist = pricelist.size();
		System.out.println("Price size : "+ countpricelist);
		
        
		for(int i=0;i<=countproductlist;i++) {
			
			getpricelist = pricelist.get(i).getText();
			System.out.println("getpricelist : "+ getpricelist);
			Thread.sleep(1000);
			
			if(productlist.get(i).getText().contains("iPhone 11")) {
				getProduct = productlist.get(i).getText();
				System.out.println("getProduct : "+ getProduct);
				Thread.sleep(1000);
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@class='shelf-container']/div/p[contains(text(),'"+getProduct+"')]//parent::*/div[4]")).click();
				Thread.sleep(1000);
				break;
			}
		}
		
		
		
        driver.findElement(By.className("buy-btn")).click();
	
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
		
        driver.findElement(By.id("login-btn")).click();
		
		Thread.sleep(5000);	
		
		// Checkout Page
		String ProductAddedInCart = driver.findElement(By.xpath("//*[@class='product-title optimizedCheckout-contentPrimary']")).getText();
        
        //product-title optimizedCheckout-contentPrimary
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


		// Call Method for entering the text in user details field (Address, Name)
		enterUserDetails(driver);
        Thread.sleep(3000);
		driver.findElement(By.id("checkout-shipping-continue")).click();        
		
	
		
		Thread.sleep(3000);
		//Click continue shopping button at the end
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping »')]")).click();
        
        Thread.sleep(2000);
		
		//Click orders to verify the history
        driver.findElement(By.id("orders")).click();
        

		// I can also Verify page title and order details
		
		Thread.sleep(2000);


        driverClose(driver);

    }
	
    //Method to fill user detials
    	private static void enterUserDetails(WebDriver driver){
        driver.findElement(By.id("firstNameInput")).sendKeys("Firstname");
		driver.findElement(By.id("lastNameInput")).sendKeys("Lastname");
		driver.findElement(By.id("addressLine1Input")).sendKeys("Address");
		driver.findElement(By.id("provinceInput")).sendKeys("NSW");
		driver.findElement(By.id("postCodeInput")).sendKeys("2000");
        
	}

    private  static void driverClose(WebDriver driver){
        driver.close();
    }

}

//GOOD TO HAVE: WORK IN PROGRESS
// WebDriverWait
// Multiple Drivers can be called
// Exception handling --> try catch, types of selenium exception
// /Seperate Modules --> Login, product, order, confirmation
// Re-usable methods for oder verification
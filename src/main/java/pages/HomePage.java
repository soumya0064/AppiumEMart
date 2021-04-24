package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	public AndroidDriver<AndroidElement> driver;
public HomePage(AndroidDriver<AndroidElement> driver)
{
    this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")

WebElement CountryDropDown;

@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")

WebElement UserName;

@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")

WebElement LetsShopButton;

public void verifyNameValidation()
{
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CountryDropDown.click();
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Algeria\"));").click();
	LetsShopButton.click();
	String toastmessage= driver.findElementByXPath("//android.widget.Toast").getAttribute("name");
	Assert.assertEquals(toastmessage,"Please enter your name");
	
}

public void loginHome(String country,String name)
{
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CountryDropDown.click();
	//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Algeria\"));").click();
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"));").click();
	UserName.sendKeys(name);
	driver.hideKeyboard();
	LetsShopButton.click();
	
}

}
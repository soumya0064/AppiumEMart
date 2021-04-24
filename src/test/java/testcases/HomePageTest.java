package testcases;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pages.HomePage;
import test.Base;
import testdata.ParametrizeData;

public class HomePageTest extends Base {

HomePage home;

@Test

public void verifyNameValidationMessage()
{
	AndroidDriver<AndroidElement>driver= baseUtility("appName");	
	home = new HomePage(driver);
	home.verifyNameValidation();
}

@Test(dataProvider="value",dataProviderClass = ParametrizeData.class)
public void homeLogin(String country,String name)
{
AndroidDriver<AndroidElement>driver= baseUtility("appName");	
home = new HomePage(driver);
home.loginHome(country,name);

}
}

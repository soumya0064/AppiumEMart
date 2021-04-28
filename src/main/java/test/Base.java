package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	
	public static AndroidDriver<AndroidElement> driver;
	public static Properties prop;
	public static FileInputStream fs;
	public static AppiumDriverLocalService server;
	AppiumServiceBuilder serviceBuilder;
	
	@BeforeSuite
	
	public void cleanServer()
	{
		try {
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod
	
	public void startAppiumServer()
	{
		serviceBuilder = new AppiumServiceBuilder();
		server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
	}
	
	@AfterMethod
	public void stopAppiumServer()
	{
		server.stop();
	}
	
	public AndroidDriver<AndroidElement> baseUtility(String appName)
	
	{
		
			try {
				File f = new File("src\\main\\java\\App\\");
				File filepath= new File(f,getProprtyFile(appName));
				
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(MobileCapabilityType.DEVICE_NAME,getProprtyFile("device"));
				//getProprtyFile("device").contains("my")
				if(System.getProperty("deviceName").contains("my"))
				{
					startEmulator();
				}
				cap.setCapability(MobileCapabilityType.APP, filepath.getAbsolutePath());
				cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
				cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,240);
				
				try {
					 driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.getMessage();
			}
			
			return driver;
	}
	
	public static String getProprtyFile(String key) throws IOException
	{	
	prop = new Properties();
	fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\global.properties");
	prop.load(fs);
	return (String) prop.get(key);
	
	}
	
	public static void startEmulator()
	{
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\emulator.bat");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void takeScreenshot(String screenName) throws IOException
	{
		File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"\\src\\main\\java\\Screenshots\\"+screenName+".png"));
	}

}

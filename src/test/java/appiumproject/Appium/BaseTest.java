package appiumproject.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeTest
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {
		
		
		 Map<String, String> env = new HashMap<String, String>(System.getenv());
	        env.put("ANDROID_HOME", "/Users/admin/Library/Android/sdk");
	        env.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home");
		
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).
				withIPAddress("127.0.0.1").usingPort(4723).withEnvironment(env).build();
		
		service.start();
		
	       if (service == null || !service.isRunning()) {
	            throw new IllegalStateException("Appium service did not start correctly!");
	        }
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("emulator-5554");
		options.setApp("/Users/admin/eclipse-workspace/Appium/src/test/java/resources/ApiDemos-debug.apk");
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); 
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		//test
		
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}

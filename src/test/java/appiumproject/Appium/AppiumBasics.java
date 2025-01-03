package appiumproject.Appium;

import org.testng.annotations.Test;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics {
	
	@Test
	public void AppiumTest() throws MalformedURLException, URISyntaxException
	{
		//AndroidDriver, IOSDriver
		//Appium code -> Appium Server -> Mobile
		
		 Map<String, String> env = new HashMap<String, String>(System.getenv());
	        env.put("ANDROID_HOME", "/Users/admin/Library/Android/sdk");
	        env.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home");
		
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).
				withIPAddress("127.0.0.1").usingPort(4723).withEnvironment(env).build();
		
		service.start();
		
	       if (service == null || !service.isRunning()) {
	            throw new IllegalStateException("Appium service did not start correctly!");
	        }
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("emulator-5554");
		options.setApp("/Users/admin/eclipse-workspace/Appium/src/test/java/resources/ApiDemos-debug.apk");
		
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); 
		
		driver.quit();
		service.stop();
		
	}

}

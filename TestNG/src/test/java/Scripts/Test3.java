package Scripts;



	import org.testng.annotations.Test;
	import java.net.URL;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;



	public class Test3 {
		
//		Lambdatest Credentails can be found here at https://www.lambdatest.com/capabilities-generator
	    String username = System.getenv("LT_USERNAME") == null ? "tarunramireddy" : System.getenv("LT_USERNAME"); 
	   String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "i7Bv2edQlgb8vMXlmkSMAk0CdsjpY76Ym85uWLKLLf39l8ASI7" : System.getenv("LT_ACCESS_KEY"); 
	   String buildName = System.getenv("LT_BUILD_NAME") == null ? "TestNG Parallel" : System.getenv("LT_BUILD_NAME");
		
		public static WebDriver driver;
		public static String status = "failed";

		@SuppressWarnings("deprecation")
		@BeforeTest(alwaysRun=true)
		@Parameters(value = { "browser", "version", "platform" })
		public void setUp(String browser, String version, String platform) throws Exception {

			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(CapabilityType.BROWSER_NAME, browser);
			capability.setCapability(CapabilityType.VERSION, version);
			capability.setCapability(CapabilityType.PLATFORM, platform);
			capability.setCapability("build", "TestScenario-3");
			capability.setCapability("name", "TestNG Parallel Test");
			capability.setCapability("network", true);
			capability.setCapability("video", true);
			capability.setCapability("console", true);
			capability.setCapability("visual", true);

			String gridURL = "http://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
			try {
				driver = new RemoteWebDriver(new URL(gridURL), capability);
			} catch (Exception e) {
				System.out.println("driver error");
				System.out.println(e.getMessage());
			}
		}

		@Test
		public static void test() {
			try {
				
				driver.get("https://www.lambdatest.com/selenium-playground/");
				
				driver.findElement(By.linkText("Javascript Alerts")).click();
				
				
				driver.findElement(By.cssSelector("#__next > section.mt-50 > div > div > div.w-8\\/12.smtablet\\:w-full > div:nth-child(3) > button")).click();
				
				String expectedAlert = "I am an alert box!";
				
				Alert alert = driver.switchTo().alert(); // switch to alert

				String alertMessage= driver.switchTo().alert().getText();
				
				Assert.assertEquals(alertMessage, expectedAlert);
				alert.accept();
				
				
				status = "passed";
				((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} catch (Error e) {
				System.out.println("Assert failed");
			}

		}





		
		
		@AfterTest
		public void afterTest() {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
			driver.quit();
		}

	}



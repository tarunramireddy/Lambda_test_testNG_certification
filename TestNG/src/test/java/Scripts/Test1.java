package Scripts;



	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;
	import java.net.URL;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;



	public class Test1 {
		
//		Lambdatest Credentails can be found here at https://www.lambdatest.com/capabilities-generator
	    String username = System.getenv("LT_USERNAME") == null ? "tarunramireddy" : System.getenv("LT_USERNAME"); 
	   String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "i7Bv2edQlgb8vMXlmkSMAk0CdsjpY76Ym85uWLKLLf39l8ASI7" : System.getenv("LT_ACCESS_KEY"); 
	   String buildName = System.getenv("LT_BUILD_NAME") == null ? "TestNG Parallel" : System.getenv("LT_BUILD_NAME");
		
	   public static WebDriver driver;
	   public static String status = "failed";

		@SuppressWarnings("deprecation")
		@BeforeTest(alwaysRun = true)
		@Parameters(value = { "browser", "version", "platform" })
		protected void setUp(String browser, String version, String platform) throws Exception {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// set desired capabilities to launch appropriate browser on Lambdatest
			capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
			capabilities.setCapability(CapabilityType.VERSION, version);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
			capabilities.setCapability("build", "TestScenario-1");
			capabilities.setCapability("name", "TestNG Parallel");
			capabilities.setCapability("network", true);
			capabilities.setCapability("video", true);
			capabilities.setCapability("console", true);
			capabilities.setCapability("visual", true);


			System.out.println("capabilities" + capabilities);

			// Launch remote browser and set it as the current thread
			String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
			try {
				driver = new RemoteWebDriver(new URL(gridURL), capabilities);
			} catch (Exception e) {
				System.out.println("driver error");
				System.out.println(e.getMessage());
			}

		}
		@Test
		public static void test1() throws Exception {
			try {

				
				driver.get("https://www.lambdatest.com/selenium-playground/");

				@SuppressWarnings("deprecation")
				WebDriverWait wait = new WebDriverWait(driver,30);
				 
		         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[1]/div/div[1]/a[1]")));
		              // click on the compose button as soon as the "compose" button is visible
		         SoftAssert softAssert = new SoftAssert();
		         String actualTitle = driver.getTitle();
		     	System.out.println(actualTitle);
		     	String getActualTitle = driver.getTitle();
		     	softAssert.assertEquals(getActualTitle, "LamdaTest");
		     	System.out.println("Statement After Soft Assert failed");
		     	
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 

		}
		@AfterTest(alwaysRun = true)
		public void tearDown() throws Exception {
			driver.quit();
		}
	}





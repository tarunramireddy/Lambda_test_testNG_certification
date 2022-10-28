package Scripts;



	import org.testng.annotations.Test;
	import java.net.URL;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;



	public class Test2 {
		
//		Lambdatest Credentails can be found here at https://www.lambdatest.com/capabilities-generator
	    String username = System.getenv("LT_USERNAME") == null ? "tarunramireddy" : System.getenv("LT_USERNAME"); 
	   String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "i7Bv2edQlgb8vMXlmkSMAk0CdsjpY76Ym85uWLKLLf39l8ASI7" : System.getenv("LT_ACCESS_KEY"); 
	   String buildName = System.getenv("LT_BUILD_NAME") == null ? "TestNG Parallel" : System.getenv("LT_BUILD_NAME");
		
	   public static WebDriver driver;

		@SuppressWarnings("deprecation")
		@BeforeTest(alwaysRun = true)
		@Parameters(value = { "browser", "version", "platform" })
		protected void setUp(String browser, String version, String platform) throws Exception {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// set desired capabilities to launch appropriate browser on Lambdatest
			capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
			capabilities.setCapability(CapabilityType.VERSION, version);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
			capabilities.setCapability("build", "TestScenario-2");
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
		public static void test() throws Exception {
			try {

				
				// Launch the app
				driver.get("https://www.lambdatest.com/selenium-playground/");

				
				driver.findElement(By.linkText("Checkbox Demo")).click();

				
				driver.findElement(By.id("isAgeSelected")).click();

				
				boolean value= driver.findElement(By.id("isAgeSelected")).isSelected();
				if(value == true) {
					System.out.println("Checkbox is Selected");
				}
				driver.findElement(By.id("isAgeSelected")).click();
				boolean value1= driver.findElement(By.id("isAgeSelected")).isSelected();
				if(value1 == false) {
					System.out.println("Checkbox is Un-Selected");
				}
				
				
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 

		}



		@AfterTest(alwaysRun = true)
		public void tearDown() throws Exception {
			driver.quit();
		}

	}



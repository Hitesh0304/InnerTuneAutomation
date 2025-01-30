import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    AndroidDriver driver;
    AppiumDriverLocalService service;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //start the appium server automatically
        service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//hiteshmoudgil//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //set the capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("TestingEmulator");
        //options.setPlatformName("Android");
        options.setApp("//Users//hiteshmoudgil//Desktop//AutomationProjects//InnerTuneAutomation//src//test//resources//InnerTune_v0.5.10_foss.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
        //this is to delay the moving between screens
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass
    public void tearDown() {
        if(driver!=null){
            driver.quit();
        }
    }
}

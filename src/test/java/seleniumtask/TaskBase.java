package seleniumtask;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.qameta.allure.Allure;
import seleniumtask.Core.OpenBrowser;
import seleniumtask.Core.TakeScreenShot;

public class TaskBase {
  static String CSVFilesPath, InputFile, OutputFile, ImagesPath;
  static WebDriver driver;
  static String URL = "https://www.booking.com/";
  static TakeScreenShot takeScreenShot;

  @Parameters({ "CSVFilesPath", "InputFile", "OutputFile", "ImagesPath" })
  @BeforeSuite
  public void beforeSuite(String CSVFilesPath, String InputFile, String OutputFile, String ImagesPath)
      throws IOException {
    Allure.step("Get Files Path");
    TaskBase.CSVFilesPath = CSVFilesPath;
    TaskBase.InputFile = InputFile;
    TaskBase.OutputFile = OutputFile;
    TaskBase.ImagesPath = ImagesPath;
    Allure.step("Open Headless Chrome Browser");
    driver = OpenBrowser.openChromeWithOptions();
    takeScreenShot = new TakeScreenShot(driver);
    driver.manage().window().setSize(new Dimension(1200, 800));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
  }

  @AfterSuite
  public void afterSuite() {
    Allure.step("Close Chrome Browser");
    driver.quit();
  }

}

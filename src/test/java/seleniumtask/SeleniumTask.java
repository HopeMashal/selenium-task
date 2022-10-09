package seleniumtask;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import seleniumtask.Core.CSVFile;
import seleniumtask.Pages.BookingPage;
import seleniumtask.Pages.SearchPage;

public class SeleniumTask extends TaskBase {

  static List<String[]> OutputList = new ArrayList<String[]>();

  @DataProvider
  public static Object[][] getInputCSVData() throws Exception {
    String inputFile = CSVFilesPath + InputFile;
    List<String[]> lines = CSVFile.readAllLines(inputFile);
    lines.remove(0);
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for (String[] line : lines) {
      data[index] = line;
      index++;
    }
    return data;
  }

  @Test(dataProvider = "getInputCSVData")
  public void testInputCSVData(String City, String CheckIn, String CheckOut)
      throws Exception {
    Allure.step("Get Data From input.csv File Using Data Provider");
    Allure.step("Open Booking.com");
    driver.get(URL);

    Allure.step("Fill Data in Input Boxes");
    BookingPage bookingPage = new BookingPage(driver);
    bookingPage.FillData(City, CheckIn, CheckOut);

    Allure.step("After Fill Data - Take Screen Shot");
    File afterFillData = takeScreenShot.takeScreenShot(ImagesPath + "DataFilled/FillDataForHotelsIn" + City + ".jpg");
    Allure.addAttachment(
        afterFillData.getName(),
        FileUtils.openInputStream(afterFillData));

    Allure.step("Click Search Button");
    bookingPage.ClickSearchBtn();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-testid='property-card']")));
    Thread.sleep(6000);

    Allure.step("After Click Search Button - Take Screen Shot");
    File afterClickSearchBtn = takeScreenShot
        .takeScreenShot(ImagesPath + "SearchBtnClicked/SearchBtnClickedForHotelsIn" + City + ".jpg");
    Allure.addAttachment(
        afterClickSearchBtn.getName(),
        FileUtils.openInputStream(afterClickSearchBtn));

    Allure.step("Open First Result & Get Details (URL & Name & Review)");
    SearchPage searchPage = new SearchPage(driver);
    String HotelURL = searchPage.GetFirstResultLink();
    String HotelName = searchPage.GetFirstResultName();
    String HotelReview = searchPage.GetFirstResultReview();
    System.out.println("-------------------------------------");
    System.out.println(HotelName);
    System.out.println(HotelReview);
    System.out.println(HotelURL);
    searchPage.ClickFirstResult();
    Thread.sleep(10000);
    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));

    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pp-header__title")));
    Thread.sleep(6000);

    JavascriptExecutor JavaScript = (JavascriptExecutor) driver;
    JavaScript.executeScript("window.scrollTo(0,0)");

    Allure.step("After Click First Result - Take Screen Shot");
    File afterClickFirstResult = takeScreenShot
        .takeScreenShot(ImagesPath + "FirstResultClicked/FirstResultClickedForHotelsIn" + City + ".jpg");
    Allure.addAttachment(
        afterClickFirstResult.getName(),
        FileUtils.openInputStream(afterClickFirstResult));

    Allure.step("Save Hotel Information for Writing in the Output File");
    String[] resultList = new String[6];
    resultList[0] = City;
    resultList[1] = CheckIn;
    resultList[2] = CheckOut;
    resultList[3] = HotelName;
    resultList[4] = HotelReview;
    resultList[5] = HotelURL;
    OutputList.add(resultList);

    Allure.step("Close Hotel Tab");
    driver.close();
    driver.switchTo().window(tabs2.get(0));
  }

  @AfterSuite
  @Override
  public void afterSuite() {
    // TODO Auto-generated method stub
    super.afterSuite();

    Allure.step("Write the Output File");
    String outputFile = CSVFilesPath + OutputFile;
    String[] Headers = new String[] { "City", "Check-in", "Check-out", "Hotel Name", "Hotel Review", "Hotel URL" };
    CSVFile.writeDataLineByLine(outputFile, OutputList, Headers);
  }

}

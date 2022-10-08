package seleniumtask;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import seleniumtask.Core.CSVFile;
import seleniumtask.Pages.BookingPage;

public class SeleniumTask extends TaskBase {

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

    Allure.step("After Click Search Button - Take Screen Shot");
    File afterClickSearchBtn = takeScreenShot
        .takeScreenShot(ImagesPath + "SearchBtnClicked/SearchBtnClickedForHotelsIn" + City + ".jpg");
    Allure.addAttachment(
        afterClickSearchBtn.getName(),
        FileUtils.openInputStream(afterClickSearchBtn));
  }
}

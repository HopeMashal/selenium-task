package seleniumtask;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import seleniumtask.Core.CSVFile;

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
      throws InterruptedException, IOException {
    Allure.step("Get Data From input.csv File Using Data Provider");
    Allure.step("Open Booking.com");
    driver.get(URL);
    // Allure.step("Take Screen Shot");
    // File HomePage = takeScreenShot.takeScreenShot(ImagesPath + "HotelIn" + City + ".jpg");
    // Allure.addAttachment(
    //     HomePage.getName(),
    //     FileUtils.openInputStream(HomePage));
  }
}

package seleniumtask.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingPage {
  WebDriver Driver;
  JavascriptExecutor JavaScript;
  WebElement CityBox;
  List<WebElement> CheckInOutBoxes;
  WebElement CheckInBox;
  WebElement CheckOutBox;
  WebElement SearchBtn;

  public BookingPage(WebDriver driver) {
    Driver = driver;
    JavaScript = (JavascriptExecutor) Driver;
    CityBox = Driver.findElement(By.id("ss"));
    CheckInOutBoxes = Driver.findElements(By.className("sb-date-field__display"));
    CheckInBox = CheckInOutBoxes.get(0);
    CheckOutBox = CheckInOutBoxes.get(1);
    SearchBtn = Driver.findElement(By.className("sb-searchbox__button"));
  }

  public void FillData(String City, String CheckIn, String CheckOut) {
    JavaScript.executeScript("arguments[0].value=`${arguments[1]}`;", CityBox, City);
    JavaScript.executeScript("arguments[0].innerHTML=`${arguments[1]}`;", CheckInBox, CheckIn);
    JavaScript.executeScript("arguments[0].innerHTML=`${arguments[1]}`;", CheckOutBox, CheckOut);
  }

  public void ClickSearchBtn() {
    SearchBtn.click();
  }

}

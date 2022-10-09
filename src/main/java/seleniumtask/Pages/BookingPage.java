package seleniumtask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingPage {
  WebDriver Driver;
  JavascriptExecutor JavaScript;
  WebElement CityBox;
  WebElement CheckInOutBox;
  WebElement SearchBtn;

  public BookingPage(WebDriver driver) {
    Driver = driver;
    JavaScript = (JavascriptExecutor) Driver;
    CityBox = Driver.findElement(By.id("ss"));
    CheckInOutBox = Driver.findElement(By.className("sb-date-field__field"));
    SearchBtn = Driver.findElement(By.className("sb-searchbox__button"));
  }

  public void FillData(String City, String CheckIn, String CheckOut) throws InterruptedException {
    JavaScript.executeScript("arguments[0].click();", CityBox);
    JavaScript.executeScript("arguments[0].value=`${arguments[1]}`;", CityBox, City);
    CityBox.sendKeys(Keys.SPACE);
    Thread.sleep(3000);
    JavaScript.executeScript("document.getElementsByClassName('c-autocomplete__item')[0].click();");
    Thread.sleep(3000);
    JavaScript.executeScript("arguments[0].click();", CheckInOutBox);
    JavaScript.executeScript("document.querySelector('[data-date=\"" + CheckIn + "\"]').click();");
    JavaScript.executeScript("document.querySelector('[data-date=\"" + CheckOut + "\"]').click();");
  }

  public void ClickSearchBtn() throws InterruptedException {
    JavaScript.executeScript("arguments[0].click();", SearchBtn);
  }

}

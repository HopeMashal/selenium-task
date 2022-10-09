package seleniumtask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
  WebDriver Driver;
  WebElement FirstResult;
  WebElement FirstResultLink;
  WebElement FirstResultName;
  WebElement FirstResultReview;

  public SearchPage(WebDriver driver) {
    Driver = driver;
    FirstResult = Driver.findElement(By.cssSelector("div[data-testid='property-card']"));
    FirstResultLink = Driver.findElement(By.cssSelector("div[data-testid='property-card']>div>div>div>a"));
    FirstResultName = Driver.findElement(By.cssSelector("div[data-testid='title']"));
    FirstResultReview = Driver.findElement(By.cssSelector("div[data-testid='review-score']>div>div"));
  }

  public void ClickFirstResult() {
    FirstResult.click();
  }

  public String GetFirstResultLink() {
    return FirstResultLink.getAttribute("href");
  }

  public String GetFirstResultName() {
    return FirstResultName.getText();
  }

  public String GetFirstResultReview() {
    return FirstResultReview.getText();
  }

}

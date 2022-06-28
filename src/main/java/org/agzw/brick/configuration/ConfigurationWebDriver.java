package org.agzw.brick.configuration;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.agzw.brick.constant.CommonConstant.*;

public class ConfigurationWebDriver {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public ConfigurationWebDriver() {
        System.setProperty("webdriver.chrome.driver", getPathChromeDriverValue());
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments(USER_AGENT);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public String getPathChromeDriverValue(){
        try {
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.load("application.properties");
            return config.getString(PATH_CHROME_DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public List<String> prepareTwoTabs() {
        driver.get(GOOGLE_URL);
        jsExecutor.executeScript(JS_WINDOW_OPEN);
        return new ArrayList<> (driver.getWindowHandles());
    }

    public List<WebElement> getElementListByScrollingDown(String url, String xpath, String tab) {
        switchTab(tab);
        driver.get(url);
        jsExecutor.executeScript(JS_SCROLL_MEDIUM);
        return driver.findElements(By.xpath(xpath));
    }

    public void getWebpage(String path, String tab) {
        switchTab(tab);
        driver.get(path);
    }

    public void waitOnElement(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(xpath)));
    }

    public void scrollDownSmall() {
        jsExecutor.executeScript(JS_SCROLL_SMALL);
    }

    public String getText(String xpath) {
        return driver.findElements(By.xpath(xpath)).isEmpty()
                ? EMPTY : driver.findElement(By.xpath(xpath)).getText();
    }

    public String getText(String xpath, String attribute) {
        return driver.findElements(By.xpath(xpath)).isEmpty()
                ? EMPTY : driver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public void switchTab(String tab) {
        driver.switchTo().window(tab);
    }

    public void quit() {
        driver.quit();
    }
}

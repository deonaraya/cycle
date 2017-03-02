package com.deo.cycle.tests ;

import java.util.jar.Attributes.Name;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by chandrad on 2/13/17.
 */
public class DemoFeb13 {

    WebDriver driver ;
    WebDriverWait wait ;


    @BeforeClass
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

//        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

    }

    @Test
    public void demoDropDown(){

        WebElement signinLink = driver.findElement(By.className("login")) ;
        signinLink.click();
       // CommonMethods.waitForSomeTime(2000);  // wait strategy ...


        waitForWebElement(driver.findElement(By.id("email_create")));

        WebElement createEmailTextField = driver.findElement(By.id("email_create"));
        createEmailTextField.sendKeys(getUniqueEmail("Robert"));
       // System.out.println(CommonMethods.getUniqueEmail("Robert"));
        WebElement submitButton = driver.findElement(By.id("SubmitCreate"));
        submitButton.click();

        //waitForSometime(4000);

        waitForWebElement(driver.findElement(By.id("noSlide")));

        driver.findElement(By.id("uniform-id_gender1")).click();

        WebElement stateDropDown = driver.findElement(By.id("id_state"));
        new Select(stateDropDown).selectByValue("5");


        //CommonMethods.waitForSomeTime(2000);

    }


  //  @Test
    public void demoHover(){

        // class name and ids were tested on browser using $(".   ") is classname .. .and $("#   ") is id
        // xpath is tested using a syntax of $x(" // ......") ..


        WebElement signinLink = driver.findElement(By.className("login")) ;
        WebElement catalogTabs = driver.findElement(By.id("home-page-tabs")) ;
        WebElement mouseHoverWomen = driver.findElement(By.xpath("(//a[@class='sf-with-ul'])[1]")) ;

        WebElement tShirtLink = driver.findElement(By.xpath("(//a[@class='sf-with-ul'])[1]//parent::li//a[@title='T-shirts']"));
        // we can perform aciton supported by selenium only on a webEleent .....

        System.out.println("this tells me if the catalog has loaded or not " + catalogTabs.isDisplayed());

      //  signinLink.click();

        Actions action = new Actions(driver);
        action.moveToElement(mouseHoverWomen).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tShirtLink.click();
    }

    public void waitForSometime(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
    public String getUniqueEmail(String name){
    	return (name + System.currentTimeMillis() +"@mailinator.com") ;
    }

    public  void waitForWebElement(WebElement element){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element)) ;
    }

//    public void waitFluently(){
//
//        Wait wait = new FluentWait(driver)
//
//                .withTimeout(10, TimeUnit.SECONDS)
//
//                .pollingEvery(5, TimeUnit.MILLISECONDS)
//
//                .ignoring(NoSuchElementException.class);
//
//        WebElement foo = wait.until(new ExpectedConditions. {
//
//            public WebElement apply(WebDriver driver) {
//
//                return driver.findElement(By.id("foo"));
//
//            }
//
//        });
//    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();

    }
}

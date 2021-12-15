package com.knoldus;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ImageTesting extends TesseractExtender {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/software_photos/001/205/265/datas/original.png");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(testName = "DUMMY_TEST")
    public void dummyTest() throws IOException, TesseractException {
        WebElement image = driver.findElement(By.tagName("img"));

        // call the method to write the image to resource folder
        TesseractExtender.capturePicture(image);

        // get the Tesseract direct interace
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/test/resources/tessdata");

        // the doOCR method of Tesseract will retrive the text
        // from image captured by Selenium
        String result = tesseract.doOCR(new File("src/test/resources/testImage.png"));
        System.out.println(result);
        Assert.assertTrue(result.contains("TEST"));
    }
}

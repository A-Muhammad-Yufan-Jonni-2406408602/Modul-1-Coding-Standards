package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void testSetUp() {
        baseUrl = String.format("%s:%d/%s", testBaseUrl, serverPort,"product/list");
    }

    @Test
    void testCreateProduct(ChromeDriver driver) {
        driver.get(baseUrl);
        WebElement createButton = driver.findElement(By.id("createProductBtn"));
        createButton.click();

        // 3. Fill the form
        driver.findElement(By.id("nameInput"))
                .sendKeys("Sampo Oplos");

        driver.findElement(By.id("quantityInput"))
                .sendKeys("10");

        // 4. Submit form
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // 5. Verify product is shown in table
        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains("Laptop"));
        assertTrue(pageSource.contains("10"));
    }
}

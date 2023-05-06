package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SonarQubeUtility {

    private static final String SONARQUBE_URL = "http://localhost:9000/sessions/new?return_to=%2F";
    private static final String SONARQUBE_DIR_PATH = "D:\\sonarqubedemo\\sonarqube-10.0.0.68432\\sonarqube-10.0.0.68432\\bin\\windows-x86-64";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    public static void startSonarQubeServer() {
        List<String> command = Arrays.asList("cmd", "/c", "StartSonar.bat");
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(SONARQUBE_DIR_PATH));
        try {
            Process p = pb.start();
            p.waitFor();
            int exitCode = p.exitValue();
            if (exitCode != 0) {
                System.err.println("SonarQube server startup failed with exit code " + exitCode);
            } else {
                System.out.println("SonarQube server started successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void loginToSonarQube() {
        
    	// Initialize a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Navigate to the SonarQube login page
        driver.get(SONARQUBE_URL);

        // Wait for login page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".login-title")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-title")));

        // Find the username and password fields and enter the login credentials
        WebElement username = driver.findElement(By.id("login"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.className("button"));
        username.sendKeys(ADMIN_USERNAME);
        password.sendKeys(ADMIN_PASSWORD);
        loginButton.click();

        // Verify that the login was successful by checking the page title
        String expectedTitle = "SonarQube";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Login failed");

        // Close the browser
        //driver.quit();
    }

    public static void main(String[] args) {
        startSonarQubeServer();
        loginToSonarQube();
    }

}

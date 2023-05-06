package sonartestpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SonarServerCheck {
  
  public static void main(String[] args) {
    // Set the path to the chromedriver executable
    //System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
    
    // Create a new ChromeDriver instance
    WebDriver driver = new ChromeDriver();
    
    // Navigate to the SonarQube server homepage
    driver.get("http://localhost:9000/");
    
    // Check if the SonarQube server is running
    if (driver.getTitle().contains("SonarQube")) {
      System.out.println("SonarQube server is running");
    } else {
      System.out.println("SonarQube server is not running");
    }
    
    // Close the browser window
    //driver.quit();
  }

}


package sonartestpackage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class StartSonarQubeServer {
  
  public static void main(String[] args) {
    List<String> command = Arrays.asList(
        "cmd", "/c", "StartSonar.bat"
    );
    
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File("E:\\\\sonarqube-9.8.0.63668\\\\sonarqube-9.8.0.63668\\\\bin\\\\windows-x86-64"));
    
    try {
      Process p = pb.start();
      p.waitFor();
      int exitCode = p.exitValue();
      if (exitCode != 0) {
        System.err.println("SonarQube server startup failed with exit code " + exitCode);
      } else {
        System.out.println("SonarQube server started successfully");
        boolean serverRunning = checkIfServerIsRunning();
        if (serverRunning) {
          System.out.println("SonarQube server is running");
          openSonarQubeInBrowser();
        } else {
          System.err.println("SonarQube server is not running");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static boolean checkIfServerIsRunning() throws IOException, InterruptedException {
    // Create a HTTP client and send a GET request to the SonarQube web interface
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:9000/api/system/status"))
        .build();
    HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    
    // Check if the response indicates that the server is up and running
    if (httpResponse.statusCode() == 200 && httpResponse.body().contains("\"status\":\"UP\"")) {
      return true;
    } else {
      return false;
    }
  }
  
  private static void openSonarQubeInBrowser() {
    // Open the SonarQube web interface in the default system browser
    try {
      Desktop.getDesktop().browse(new URI("http://localhost:9000"));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
  
	/*
	 * This updated program includes two new methods:
	 * 
	 * checkIfServerIsRunning(): This method sends a GET request to the SonarQube
	 * web interface to check if the server is up and running. It returns true if
	 * the server is running and false otherwise. openSonarQubeInBrowser(): This
	 * method opens the SonarQube web interface in the default system browser. After
	 * starting the SonarQube server using the SonarServerStart program, the program
	 * will check if the server is running and open the SonarQube web interface in a
	 * web browser if it is. If the server is not running, an error message will be
	 * printed to the console.
	 */
}


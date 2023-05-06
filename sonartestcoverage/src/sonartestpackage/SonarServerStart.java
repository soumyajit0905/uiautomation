package sonartestpackage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SonarServerStart {
  
  public static void main(String[] args) {
    List<String> command = Arrays.asList("cmd", "/c", "StartSonar.bat");
    
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File("E:\\sonarqube-9.8.0.63668\\sonarqube-9.8.0.63668\\bin\\windows-x86-64"));
    
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

}

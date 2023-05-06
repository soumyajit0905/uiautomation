package sonartestpackage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SonarScanner {
  
  public static void main(String[] args) {
    String projectKey = "your_project_key_here";
    String projectName = "your_project_name_here";
    String projectVersion = "your_project_version_here";
    String sourcePath = "path_to_your_java_file_here";
    String sonarHostUrl = "http://localhost:9000"; // replace with your SonarQube host URL
    
    List<String> command = Arrays.asList(
        "sonar-scanner",
        "-Dsonar.projectKey=" + projectKey,
        "-Dsonar.projectName=" + projectName,
        "-Dsonar.projectVersion=" + projectVersion,
        "-Dsonar.sources=" + sourcePath,
        "-Dsonar.host.url=" + sonarHostUrl
    );
    
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File(System.getProperty("user.dir")));
    
    try {
      Process p = pb.start();
      p.waitFor();
      int exitCode = p.exitValue();
      if (exitCode != 0) {
        System.err.println("SonarScanner failed with exit code " + exitCode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}


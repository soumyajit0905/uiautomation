package sonartestpackage;

import org.testng.annotations.Test;

import sourcefilestoanalyze.SonarSourceFile;

public class Sonartestscript {
	
	@Test
	public void test() {
		SonarSourceFile obj = new SonarSourceFile();
		obj.division();
	}

}

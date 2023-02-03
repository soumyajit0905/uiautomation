package sonartestpackage;

import org.testng.annotations.Test;

import sonartestcoverage.Sonarsourcefile;

public class Sonartestscript {
	
	@Test
	public void test() {
		Sonarsourcefile obj = new Sonarsourcefile();
		obj.division();
	}

}

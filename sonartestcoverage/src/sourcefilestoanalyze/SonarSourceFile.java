package sourcefilestoanalyze;

public class SonarSourceFile {

	public void division() {
		int numerator = 40;
		int denominator = 20;
		try {
			int result = numerator / denominator;
			if (result == 0) {
				System.out.println("numerator is divisible by denominator");
			} else {
				System.out.println("numerator is NOT divisible by denominator");
			}
		} catch (ArithmeticException e) {
			System.out.println("Division by zero error!");
		}
	}
}

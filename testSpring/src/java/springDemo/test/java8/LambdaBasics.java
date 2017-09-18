package springDemo.test.java8;

public class LambdaBasics {

	MyLamda greetingFunction = () -> {
		System.out.println("hello World");
	};

	MyLamdaReturn greetingFunctionWithReturn = (String welcomeString) -> {
		System.out.println(welcomeString);
		return welcomeString;
	};

	// From Default functions
	@SuppressWarnings("rawtypes")
	java.util.function.DoubleFunction factorial = (factor) -> {
		double value = 1;
		if (factor <= 0) {
			value = 0;
		} else {
			for (int i = 1; i <= factor; i++) {
				value *= i;
			}
		}
		System.out.println("Factorial of " + factor + " is " + value);
		return value;
	};

	StringLength stringLength = s -> s.length();

	public void getStringLength(StringLength l) {
		System.out.println(l.getStringLength("Hello world"));
	}

	public static void main(String[] args) {

		LambdaBasics lambda = new LambdaBasics();

		lambda.greetingFunction.foo();

		// ---Listen Method Declarations Above with comments--- //

		// With arguments and type
		lambda.greetingFunctionWithReturn.fooReturn("Again hello World");

		// With only arguments
		lambda.factorial.apply(10.0);

		// If one line code with return, no need define return
		// If one argument no need to define parenthesis
		System.out.println(lambda.stringLength.getStringLength("Hello world"));

		// Passing function
		lambda.getStringLength(s -> s.length());

	}

}

// Functional Interface for lambda Expressions

interface MyLamda {
	void foo();
}

interface MyLamdaReturn {
	String fooReturn(String s);
}

interface Factorial {
	double getFactorial(int factor);
}

// optional for lambda expression
// It avoids messing up
@FunctionalInterface
interface StringLength {
	int getStringLength(String s);
}

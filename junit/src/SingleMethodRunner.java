import java.lang.reflect.Method;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

public class SingleMethodRunner {
	public static void main(String... args) throws ClassNotFoundException {
		for (String a : args) {
			String[] classAndMethod = a.split("#");
			if (classAndMethod.length == 1) {
				for (Method s : Class.forName(classAndMethod[0])
						.getDeclaredMethods())
					_run(classAndMethod[0], s.getName());
			} else
				_run(classAndMethod[0], classAndMethod[1]);
		}
		System.out.println("PASSED: " + _isOK);
		System.out.println("FAILED: " + _isNotOK);
	}

	private static JUnitCore _runner = new JUnitCore();
	private static int _isOK, _isNotOK = 0;

	private static void _run(String cls, String method)
			throws ClassNotFoundException {
		if (_runner.run(Request.method(Class.forName(cls), method))
				.wasSuccessful())
			_isOK++;
		else
			_isNotOK++;
	}
}
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.console.ConsoleLauncher;

/**
 * Class for testing everyone else's implementation
 * 
 * @author girik
 *
 */
public class StudentTests {

	public static void main(String[] args) {
		String className = MethodHandles.lookup().lookupClass().getName();
		String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
		String[] arguments = new String[] { "-cp", classPath, "--include-classname=.*", "--select-class=" + className };
		ConsoleLauncher.main(arguments);
	}

	// Data Wrangler Code Tests

	/**
	 * Test for the StudentData class Confirms the accessors work as intended Tests
	 * equals method and compareTo show correct function
	 */
	@Test
	void testStudentDataClass() {
		// Create students for testing
		StudentData student1 = new StudentData("Chris", 300, 2, "Computer Engineering");
		StudentData student2 = new StudentData("Dan", 504, 3, "Computer Science");

		// Test accessors
		if (!student1.getName().equals("Chris")) {
			fail("Name accessor fails for student class");
		}
		if (!student1.getID().equals(300)) {
			fail("ID accessor fails for student class");
		}
		if (!(student1.getYear() == 2)) {
			fail("Year accessor fails for student class");
		}
		if (!student1.getMajor().equals("Computer Engineering")) {
			fail("Major accessor fails for student class");
		}

		// Test equals method
		StudentData duplicate = new StudentData("Chris", 300, 2, "Computer Engineering");
		if (student1.equals(student2)) {
			fail("Equals method fails for non equal input");
		}
		if (!student1.equals(duplicate)) {
			fail("Equals method fails for equal input");
		}
		if (!student2.equals("Test Object")) {
			fail("Equals fails for object of different type");
		}

		// Test compareTo method
		if (student1.compareTo(duplicate) != 0) {
			fail("compareTo fails for equivilent input");
		}
		if (student1.compareTo(student2) > 0) {
			fail("compareTo fails when less than input");
		}
		if (student2.compareTo(student1) < 0) {
			fail("compareTo fails when greater than input");
		}
	}

	/**
	 * Tests loadFile Confirms failure for invalid path If path is valid, confirms
	 * size of student linked list is correct Confirms first and last student along
	 * with specific fields of first few students Checks all students loaded
	 * non-null
	 */
	@Test
	void testLoadFile() {
		StudentLoader testLoader = new StudentLoader();
		List<StudentDataInterface> testData;
		try {
			testData = testLoader.loadFile("invalid path");
			fail("StudentLoader did not throw error for invaild file path");
		} catch (FileNotFoundException e) {
			// Intended exception
		}
		try {
			// Load data of the datafile
			testData = testLoader.loadFile("./data/Student_Information.csv");

			// Check all students are loaded
			if (testData.size() != 4000) {
				System.out.println(testData.size());
				fail("Size of list does not match size of dataset");
			}

			// Check students load correctly
			StudentData student1 = new StudentData("Olivia", 20131143, 2, "IDEPT7783");
			if (!student1.equals(testData.get(0))) {
				fail("First student invalid");
			}

			// Checks students 1 - 11
			for (int i = 1; i < 11; i++) {
				if (testData.get(i).getID() != (20131143 + i)) {
					fail("ID does not match for student " + i);
				}
			}

			// Checks last student
			StudentData student2 = new StudentData("Santino", 20189989, 2, "IDEPT3062");
			if (!student2.equals(testData.get(3999))) {
				fail("Last student does not match");
			}

			// Checks all students non null
			if (testData.contains(null)) {
				fail("Null data found within linked list");
			}

		} catch (FileNotFoundException excpt) {
			fail("File cannot be found");
		}

	}

	/**
	 * Tests for the loadAllFilesInDirectory method Tests proper function for
	 * invalid file path
	 *
	 * If file path is valid, first and last students confirmed correct Checks
	 * correct number of students loaded and all students non-null
	 */
	@Test
	void testLoadAllFilesInDirectory() {
		StudentLoader newLoader = new StudentLoader();
		List<StudentDataInterface> dataTest;

		try {
			dataTest = newLoader.loadAllFilesInDirectory("./data/Student_Information.csv");
			fail("StudentLoader did not throw error for invalid file path");
		} catch (FileNotFoundException e) {
			// Desired exception
		}

		try {
			// Load the data
			dataTest = newLoader.loadAllFilesInDirectory("./data");

			// Check all students are loaded
			if (dataTest.size() != 4000) {
				fail("Size of list does not match size of dataset");
			}

			// Check first student loaded correctly
			StudentDataInterface studentFirst = new StudentData("Olivia", 20131143, 2,
					"Centre for Distance Engineering Education Programme (CDEEP)");
			if (!dataTest.get(0).equals(studentFirst)) {
				fail("First student does not match expected");
			}

			// Check last student loaded correctly
			StudentDataInterface studentLast = new StudentData("Santino", 20189989, 2,
					"Centre for Environmental Science and Engineering (CESE)");
			if (!dataTest.get(3999).equals(studentLast)) {
				fail("Last student does not match expected");
			}

			// Check all students non null
			if (dataTest.contains(null)) {
				fail("Null students found");
			}
		} catch (FileNotFoundException excpt) {
			fail("File cannot be found");
		}
	}

	// Back End Developer Tests

	// Front End Developer Tests
	// PJ Rubin
	@Test
	void testAddStudent() {

		boolean d = true;
		StudentFrontEndInterface frontEnd = new StudentFrontEnd();
		StudentBackEndInterface engine = new StudentBackEndPlaceholder();

		TextUITester tester = new TextUITester("1\nPJ Rubin\n6969\n3\nCS\n5\n");
		frontEnd.run(engine);
		String output = tester.checkOutput();
		if (!(output.startsWith("Welcome ") && output.contains("Success!"))) {
			d = false;
			// this will fail if there is a problem with adding the student
			fail();
		}

		// will return true if the student is successfully added
		assertEquals(d, true);

	}

	@Test
	void testStudentsByMajor() {

		boolean d = true;
		StudentFrontEndInterface frontEnd = new StudentFrontEnd();
		StudentBackEndInterface engine = new StudentBackEndPlaceholder();

		engine.addStudent(new StudentDataPlaceholderA());

		TextUITester tester = new TextUITester("2\nComputer Engineering\n5\n");
		frontEnd.run(engine);

		String output = tester.checkOutput();
		if (!(output.startsWith("Welcome ") && output.contains("Here are the students with major ")
				&& output.contains("Chris"))) {
			d = false;
			// this will fail if there is a problem with returning the list
			fail();
		}

		// will return true if the list successfully displays the name Chris
		assertEquals(d, true);

	}

	@Test
	void testStudentsByYear() {

		boolean d = true;
		StudentFrontEndInterface frontEnd = new StudentFrontEnd();
		StudentBackEndInterface engine = new StudentBackEndPlaceholder();

		engine.addStudent(new StudentDataPlaceholderA());

		TextUITester tester = new TextUITester("3\n2\n5\n");
		frontEnd.run(engine);

		String output = tester.checkOutput();
		if (!(output.startsWith("Welcome ") && output.contains("Here are the students in year 2")
				&& output.contains("Chris"))) {
			d = false;
			// this will fail if there is a problem with returning the list
			fail();
		}

		// will return true if the list successfully displays the name Chris
		assertEquals(d, true);

	}

	// Integration Manager Tests

}

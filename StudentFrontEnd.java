import java.util.*;





interface StudentFrontEndInterface{
	public void run(StudentBackEndInterface engine);
	
}

public class StudentFrontEnd implements StudentFrontEndInterface{
	public void run(StudentBackEndInterface engine) {
		System.out.println("Welcome to the student search Database!");
		
		Scanner s = new Scanner(System.in);
		int in = 0;
		do {
			System.out.println("\n");
			//Show the menu
			System.out.println("Here are your Options. Enter an integer 1 - 5");
			System.out.println("1: Add a new Student");
			System.out.println("2: Find all Students by Major");
			System.out.println("3: Find all Students by Year");
			System.out.println("4: Find the number of students in a particular major in a particular year");
			System.out.println("5: Quit\n\n");
			
			//get the user's input
			try {
				in = s.nextInt();s.nextLine();		
			}catch(Exception e) {
				System.out.println("OOPS, looks like that input didn't work!! \n");
				in = 0;
				s = new Scanner(System.in);
			}
			if(in < 1 || in > 5)
			{
				System.out.println("OOPS, looks like that input didn't work!! \n");
				
			}
			//if the input was 1
			else if(in == 1) {
				//get the name
				System.out.println("Enter the Student's Name: ");
				String inName = s.nextLine();
				//get the ID
				System.out.println("Enter the Student's ID Number: ");
				int inID = 0;
				try {
					inID = s.nextInt(); s.nextLine();
				}catch(Exception e) {
					System.out.println("OOPS, looks like that input didn't work!! \n");
					in = 0;
					s = new Scanner(System.in);
					continue;
				}
				//get the Year
				System.out.println("Enter the Student's year as an integer: ");
				int inYear = 0;
				try {
					inYear = s.nextInt(); s.nextLine();
				}catch(Exception e) {
					System.out.println("OOPS, looks like that input didn't work!! \n");
					in = 0;
					s = new Scanner(System.in);
					continue;
				}
				//get the Major
				System.out.println("Enter the Student's Major: ");
				String inMajor = "";
				inMajor = s.nextLine();
				//add the new student to the back end
				StudentData newStudent = new StudentData(inName, inID, inYear, inMajor);
				try {
					engine.addStudent(newStudent);
				}catch(IllegalArgumentException e) {
					System.out.println("Already have a student with that ID!");
					continue;
				}
				
				System.out.println("Success!");
				
			}
			//if the input was 2
			else if(in == 2) {
				System.out.println("Enter a Major:");
				String inMajor = s.nextLine();
				List<String> studList = null;
				try {
					studList = engine.studentsListMajor(inMajor);
				}catch(Exception e) {
					System.out.println("Error 403\nSomething went wrong!\n");
					in = 0;
					continue;
				}
				
				System.out.println("\nHere are the students with major " + inMajor + ":");
				if(studList == null || studList.isEmpty()) {
					System.out.println("None! Did you enter the major correctly?\n");
					continue;
				}
				for(String name: studList) {
					System.out.println(name);
				}
			}
			//if input is 3
			else if(in == 3) {
				System.out.println("Enter the year you want to see all the students from as an integer: ");
				int inYear = 0;
				try {
					inYear = s.nextInt(); s.nextLine();
				}catch(Exception e) {
					System.out.println("OOPS, looks like that input didn't work!! \n");
					in = 0;
					s = new Scanner(System.in);
					continue;
				}
				
				List<String> studList = null;
				try {
					studList = engine.studentsListYear(inYear);
				}catch(Exception e) {
					System.out.println("Error 403\nSomething went wrong!\n");
					in = 0;
					continue;
				}
				
				System.out.println("\nHere are the students in year " + inYear);
				if(studList == null || studList.isEmpty()) {
					System.out.println("None! Did you enter the year correctly?\n");
					continue;
				}
				for(String name: studList) {
					System.out.println(name);
				}
			}
			//if input is 4
			else if(in == 4){
				System.out.println("Enter a Major:");
				String inMajor = s.nextLine();
				System.out.println("Enter the year as an integer: ");
				int inYear = 0;
				try {
					inYear = s.nextInt(); s.nextLine();
				}catch(Exception e) {
					System.out.println("OOPS, looks like that input didn't work!! \n");
					in = 0;
					s = new Scanner(System.in);
					continue;
				}
				int total = 0;
				try {
					total = engine.findNumberOfMajorInYear(inMajor, inYear);
				}catch(Exception e) {
					System.out.println("Error 403\nSomething went wrong!\n");
					in = 0;
					continue;
				}
				System.out.println("The number of students in that year with that major is: " + total);
			}
			
			
		//loop again if the user didn't choose to quit	
		}while(in != 5);
		System.out.println("See Ya Later!");
	}
	
}

class StudentFrontEndPlaceholder implements StudentFrontEndInterface{
	public void run(StudentBackEndInterface engine) {
		System.out.println("Welcome to the placeholder interface!");
	}

	




}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.List;
import java.util.NoSuchElementException;


		
		/*
		 * Interface for the StudentBackEnd class
		 * This includes all the methods that will be used in the front end of the project
		 */
		
	interface StudentBackEndInterface {

        //These methods are a simple add and lookup based on the objects
        public void addStudent(StudentDataInterface student);
        public boolean containsStudent(StudentDataInterface student);

        //This method will return a string of students by their common major
        public ArrayList<String> studentsListMajor(String major);

        //This method will return a list of students by looking what year they will graduate in
        public ArrayList<String> studentsListYear(int year);

        //This method will show how many students have a specified major by their year
        public int findNumberOfMajorInYear(String major, int year);


 }

	
			/*
			 * StudentBackEnd class that is the final version of all the methods and code.
			 */
public class StudentBackEnd implements StudentBackEndInterface {
	
		private RedBlackTree<StudentDataInterface> studentList = new RedBlackTree<StudentDataInterface>();
		
		
	
		/*
		 * Method that will add a student to the directory of students
		 * @Param: takes a StudentDataInterface object
		 */
		
       
        public void addStudent(StudentDataInterface student) {
        	
        	if (student == null) {
        		return;
        	}
        	 boolean check = false;
        		if (studentList.contains(student)) {
        			check = true;
        		}
        	
        	if (check) {
        		throw new IllegalArgumentException("Student with this ID is already in the directory");
        		
        	} else {
        	studentList.insert(student);
        	
        	}
        	
        	
        }

        /*
         * This method will check to see if the Red Black Tree Contains the Student 
         * 
         * @Param : StudentDataInterface type Object
         */
        public boolean containsStudent(StudentDataInterface student) {
        	if (student == null ) {
        		return false;
        	}
        	try {
        		if (studentList.contains(student)) {
        		return true;
        		}
            
        	}catch(NoSuchElementException e ) {
        		return false;
        	}
        	
        	return false;
       }

       
        /*
         * Method that returns a list of students with the major that was passed in
         * @Param: Takes in a major as a String 
         */
        public ArrayList<String> studentsListMajor(String major) {
        	
        	if (major == null) {
        		return null;
        	}
        	
        	ArrayList<String> majorList = new ArrayList<String>();
        	Iterator<StudentDataInterface> studentIterator = studentList.iterator();
        	
        	try {
        		while (studentIterator.hasNext()) {
        			StudentDataInterface student = studentIterator.next();
            		String studentMajor = student.getMajor();
            		String studentName = student.getName();
            			if (studentMajor.equals(major)) {
            				majorList.add(studentName);
            			}
            		
            	}
        		
        	} catch (NoSuchElementException e ) {
        		return majorList;
        	}
        	
              return majorList;
        }

        
        /*
         * Method that will return a list of Student names that are in that year
         * @Param: takes in an integer
         */
        public ArrayList<String> studentsListYear(int year) {
        	
        	ArrayList<String> yearList = new ArrayList<String>();
        	Iterator<StudentDataInterface> studentIterator = studentList.iterator();

        	try {
        	while(studentIterator.hasNext()) {
            	
        		StudentDataInterface student = studentIterator.next();
        		int studentYear = student.getYear();
        		String studentName = student.getName();
        			if (studentYear == year) {
        				yearList.add(studentName);
        			}
        	}
       } catch (NoSuchElementException e) {
    	   return yearList;
       }
        	
                return null;
        }

       /*
        * Method that will show the number of people in a certain year with the major that was passed in 
        * @Param : Major and year, in String and int respectively
        */
        public int findNumberOfMajorInYear(String major, int year) {
        	if (major == null) {
        		return 0;
        	}
        	int count = 0;
        	Iterator<StudentDataInterface> studentIterator = studentList.iterator();

        	while(studentIterator.hasNext()) {
        		StudentDataInterface student = studentIterator.next();
        		
        		if (student.getMajor().equals(major) && student.getYear() == year) {
        			count++;
        		}
        	}
        	
                return count;
        }

      
       


}

//Placeholder class
class StudentBackEndPlaceholder implements StudentBackEndInterface {
      private StudentDataInterface onlyStudent;

      public void addStudent(StudentDataInterface student){

              this.onlyStudent = student;
              }

      public boolean containsStudent(StudentDataInterface student) {

              return onlyStudent.equals(student);

              }
      public ArrayList<String> studentsListMajor(String major) {

    	  ArrayList<String> majorList = new ArrayList<String>();
              if (onlyStudent.getMajor().equals(major))
                      majorList.add(onlyStudent.getName());
              return majorList;

              }
      public ArrayList<String> studentsListYear(int year) {

    	  	ArrayList<String> yearList = new ArrayList<String>();
              if (onlyStudent.getYear() == year)
                      yearList.add(onlyStudent.getName());
              return yearList;


              }
      public int findNumberOfMajorInYear(String major, int year) {

              if(onlyStudent.getMajor().equals(major) && onlyStudent.getYear() == year){
                      return 1;
              }
              return 0;

              }
      
      

}

